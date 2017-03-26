package at.wels.htl.a5ahit.androidthreadsdemo.threading;

import android.app.Activity;
import android.widget.Toast;

public class CorrectPrimeThread extends PrimeThread{
    protected Activity mActivity;

    public CorrectPrimeThread(long prime, Activity activity) {
        super(prime, activity.getApplicationContext());

        if (activity == null) throw new IllegalArgumentException("activity");
        mActivity = activity;
    }

    @Override
    public void run() {
        PrimeUtils.FindNextPrime(mPrime, new PrimeTester() {
            @Override
            public void test(long prime) {
                final long finalPrime = prime;
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "Testing " + finalPrime, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
