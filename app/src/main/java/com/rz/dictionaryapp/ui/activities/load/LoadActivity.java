package com.rz.dictionaryapp.ui.activities.load;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rz.dictionaryapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadActivity extends AppCompatActivity implements LoadContract.View{

    @BindView(R.id.load_progressbar) ProgressBar mLoading;
    @BindView(R.id.load_description) TextView mDescription;
    LoadContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        ButterKnife.bind(this);

        mPresenter = new LoadPresenter(this, this);
        mPresenter.loadData();
    }

    @Override
    public void setProgress(Integer i) {
        mLoading.setProgress(i);
    }

    @Override
    public void onFinishLoading() {
        finish();
    }
}
