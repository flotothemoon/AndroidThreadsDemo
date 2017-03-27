package at.wels.htl.a5ahit.androidthreadsdemo.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

public class JobSchedulerService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this,
                "Internet is on",
                Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
