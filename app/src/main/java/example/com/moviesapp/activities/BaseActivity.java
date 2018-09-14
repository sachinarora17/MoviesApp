package example.com.moviesapp.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import example.com.moviesapp.R;
import example.com.moviesapp.databinding.ActivityBaseBinding;

public abstract class BaseActivity extends AppCompatActivity {

    public ActivityBaseBinding activityBaseBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();

    }

    private void initVariables() {

        activityBaseBinding = DataBindingUtil.setContentView(this,R.layout.activity_base);

    }

    public abstract void setToolbarHeading();
}