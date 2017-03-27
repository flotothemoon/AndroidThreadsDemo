package at.wels.htl.a5ahit.uebungsaufgabe;

import android.os.AsyncTask;
import android.os.SystemClock;

public class LoadAppTask extends AsyncTask<Void, Void, Void> {

    private Finishable mFinishable;

    public LoadAppTask(Finishable finishable) {
        if (finishable == null) throw new IllegalArgumentException("finishable");

        mFinishable = finishable;
    }

    @Override
    protected Void doInBackground(Void... params) {
        SystemClock.sleep(7000);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mFinishable.finished();
    }
}
