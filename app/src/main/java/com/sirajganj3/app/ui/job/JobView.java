package com.sirajganj3.app.ui.job;

import com.sirajganj3.app.ui.bazar.models.BazarInfo;
import com.sirajganj3.app.ui.job.models.JobInfo;

import java.util.List;

public interface JobView {
    void loadJobInfo(List<JobInfo> jobInfo);
    void showProgressBar();
    void hideProgressBar();
}
