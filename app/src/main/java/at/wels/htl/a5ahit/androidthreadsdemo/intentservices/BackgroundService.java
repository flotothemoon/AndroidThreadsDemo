package at.wels.htl.a5ahit.androidthreadsdemo.intentservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import at.wels.htl.a5ahit.androidthreadsdemo.MainActivity;

public class BackgroundService extends Service {
    private Timer mTimer = new Timer();
    private int mTicks;
    public static MainActivity Activity;
    public static boolean Running = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Running = true;
        Toast.makeText(this, "Background service started...", Toast.LENGTH_SHORT).show();

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mTicks++;
                if (Activity != null)
                {
                    Activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Activity.setTicks(mTicks);
                        }
                    });
                }
            }
        }, 0, 1000);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Running = false;
        Toast.makeText(this, "Background service has been forced to stop...", Toast.LENGTH_SHORT).show();
    }
}