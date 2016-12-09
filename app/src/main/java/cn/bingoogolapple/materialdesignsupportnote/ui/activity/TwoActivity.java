package cn.bingoogolapple.materialdesignsupportnote.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
        lp.setMargins(10, 10, 10, 10);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 自定义返回按钮图标
//        toolbar.setNavigationIcon(R.drawable.selector_back);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.selector_back);


        Toolbar customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        customToolbar.inflateMenu(R.menu.toolbar_demo);

        // 设置溢出菜单的图标
        customToolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.number_3));
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
