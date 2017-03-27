package at.wels.htl.a5ahit.androidthreadsdemo.threading;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import static at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeUtils.FindNextPrime;

// 1. Params, the type of the parameters sent to the task upon execution.
// 2. Progress, the type of the progress units published during the background computation.
// 3. Result, the type of the result of the background computation.
public class PrimeAsyncTask extends AsyncTask<Long, Long, Void> {

    protected Context mContext;

    public PrimeAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Long... params) {
        for (int i = 0; i < params.length; i++) {
            if (isCancelled()) break;
            FindNextPrime(params[i], new PrimeTester() {
                @Override
                public void test(long prime) {
                    publishProgress(prime);
                }
            });
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        for (int i = 0; i < values.length; i++) {
            Toast.makeText(mContext, "Testing " + values[i], Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}