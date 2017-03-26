package at.wels.htl.a5ahit.androidthreadsdemo.threading;

import android.content.Context;
import android.widget.Toast;

import static at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeUtils.FindNextPrime;

public class PrimeThread extends Thread {
    protected long mPrime;
    protected Context mContext;

    public PrimeThread(long prime, Context context) {
        if (prime <= 0) throw new IllegalArgumentException("prime");
        if (context == null) throw new IllegalArgumentException("context");

        mPrime = prime;
        mContext = context;
    }

    @Override
    public void run() {
        FindNextPrime(mPrime, new PrimeTester() {
            @Override
            public void test(long prime) {
                Toast.makeText(mContext, "Testing " + prime, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
