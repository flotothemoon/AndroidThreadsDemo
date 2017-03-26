package at.wels.htl.a5ahit.androidthreadsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import at.wels.htl.a5ahit.androidthreadsdemo.threading.CorrectPrimeThread;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeAsyncTask;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeFutureTask;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeThread;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeUtils;

public class MainActivity extends AppCompatActivity {

    public static final long MIN_PRIME = 8;

    private ExecutorService fredPool = Executors.newCachedThreadPool();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//        new PrimeThread(MIN_PRIME).start();
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
}
