package at.wels.htl.a5ahit.androidthreadsdemo;

import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import at.wels.htl.a5ahit.androidthreadsdemo.intentservices.BackgroundService;
import at.wels.htl.a5ahit.androidthreadsdemo.services.JobSchedulerService;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.CorrectPrimeThread;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeAsyncTask;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeFutureTask;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeThread;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeUtils;

public class MainActivity extends AppCompatActivity {
    public static final long MIN_PRIME = 8;

    private ExecutorService fredPool = Executors.newCachedThreadPool();

    private TextView backgroundTicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundTicks = (TextView) findViewById(R.id.background);

        scheduleJob(this, JobSchedulerService.class, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // start background operator on start
        if (!isServiceRunning(BackgroundService.class)) {
            Intent service = new Intent(this, BackgroundService.class);
            this.startService(service);
        }
        BackgroundService.Activity = this;
    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private static void scheduleJob(Context context, Class jobClass, int jobID) {
        JobScheduler js = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo job = new JobInfo.Builder(jobID,
                new ComponentName(context, jobClass))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build();

        js.schedule(job);
    }

    public void uiThread(View view) {
        PrimeUtils.FindNextPrime(998);
    }

    public void javaThread(View view) {
        new PrimeThread(MIN_PRIME, getApplicationContext()).start();
    }

    public void javaThreadCorrect(View view) {
        new CorrectPrimeThread(MIN_PRIME, this).start();
    }

    public void futureTask(View view) {
        fredPool.execute(new PrimeFutureTask(MIN_PRIME, this));
    }

    public void asyncTask(View view) {
        new PrimeAsyncTask(getApplicationContext()).execute(MIN_PRIME);
    }

    public void setTicks(int ticks) {
        backgroundTicks.setText("" + ticks);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BackgroundService.Activity = null;
    }
}
