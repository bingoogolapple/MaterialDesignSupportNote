package cn.bingoogolapple.materialdesignsupportnote.ui.activity;

import android.content.Intent;
import android.os.Bundle;
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
public class TwoActivity extends AppCompatActivity {
    private static final String TAG = TwoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 自定义返回图标
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.selector_drawer);
    }

    public void changeToThree(View view) {
        startActivity(new Intent(this, ThreeActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
