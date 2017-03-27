package at.wels.htl.a5ahit.uebungsaufgabe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements Finishable {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new LoadAppTask(this).execute();
    }

    public void finished() {
        mTextView.setVisibility(View.VISIBLE);
//        mProgressBar.setVisibility(View.GONE);
        ((ViewGroup) mProgressBar.getParent()).removeView(mProgressBar);
    }
}
