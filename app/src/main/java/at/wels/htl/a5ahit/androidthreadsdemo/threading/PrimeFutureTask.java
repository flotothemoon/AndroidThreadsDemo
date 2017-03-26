package at.wels.htl.a5ahit.androidthreadsdemo.threading;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import at.wels.htl.a5ahit.androidthreadsdemo.R;

public class PrimeFutureTask extends FutureTask<Long> {

    protected Activity mActivity;

    public PrimeFutureTask(final long prime, final Activity activity) {
        super(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return PrimeUtils.FindNextPrime(prime, new PrimeTester() {
                    private View view = activity.findViewById(R.id.activity_main);
                    @Override
                    public void test(final long prime) {
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity.getApplicationContext(), "Testing " + prime, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
}
