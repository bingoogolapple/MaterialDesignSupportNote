package cn.bingoogolapple.materialdesignsupportnote.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import cn.bingoogolapple.materialdesignsupportnote.R;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/6/26 10:18
 * 描述:
 */
public class ThreeActivity extends AppCompatActivity {
    private static final String TAG = ThreeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void changeToDemo1(View view) {
        Intent parentIntent = getSupportParentActivityIntent();
        if (supportShouldUpRecreateTask(parentIntent)) {
            TaskStackBuilder.create(this).addNextIntentWithParentStack(parentIntent).startActivities();
        } else {
            supportNavigateUpTo(parentIntent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}