package at.wels.htl.a5ahit.androidthreadsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import at.wels.htl.a5ahit.androidthreadsdemo.threading.CorrectPrimeThread;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeThread;
import at.wels.htl.a5ahit.androidthreadsdemo.threading.PrimeUtils;

public class MainActivity extends AppCompatActivity {

    public static final long MIN_PRIME = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//        new PrimeThread(MIN_PRIME).start();
    }

    public void uiThread(View view) {
        PrimeUtils.FindNextPrime(999);
    }

    public void javaThread(View view) {
        new PrimeThread(MIN_PRIME, getApplicationContext()).start();
    }

    public void javaThreadCorrect(View view) {
        new CorrectPrimeThread(MIN_PRIME, this).start();
    }

    public void futureTask(View view) {

    }

    public void asyncTask(View view) {

    }
}
