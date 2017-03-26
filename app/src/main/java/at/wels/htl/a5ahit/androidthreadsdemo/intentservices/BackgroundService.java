package at.wels.htl.a5ahit.androidthreadsdemo.intentservices;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class BackgroundService extends Service {
    private Timer mTimer = new Timer();
    private int mTicks;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Toast.makeText(this, "Background service started...", Toast.LENGTH_SHORT).show();

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mTicks++;
                Log.w("BackgroundService", "" + mTicks);
            }
        }, 0, 1000);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Background service has been forced to stop...", Toast.LENGTH_SHORT).show();
    }

}
