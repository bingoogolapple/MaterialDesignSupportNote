package cn.bingoogolapple.materialdesignsupportnote.ui.activity;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.bingoogolapple.materialdesignsupportnote.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        mToolbar.setNavigationContentDescription("描述");
        mToolbar.setSubtitle("小标题");
        mToolbar.setLogo(R.mipmap.ic_launcher);
        mToolbar.setNavigationIcon(R.mipmap.number_1);
        // 如果没有在 setSupportActionBar(mToolbar) 之前调用过 mToolbar.setTitle 方法的话，后续再调用 mToolbar.setTitle 都不会生效，不过直接调用 ctivity 的 setTitle 方法设置标题就可以了
//        mToolbar.setTitle("大标题");

//        mToolbar.setContentInsetStartWithNavigation(0);

        setTitle("大标题");


        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.change_mode);
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                recreate();
//            }

            UiModeManager uiManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
            if (isChecked) {
                uiManager.enableCarMode(0);
                uiManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
            } else {
                uiManager.disableCarMode(0);
                uiManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
            }
        });
    }

    public void demo1(View view) {
        startActivity(new Intent(this, Demo1Activity.class));
    }

    public void demo2(View view) {
        startActivity(new Intent(this, Demo2Activity.class));
    }

    public void demo3(View view) {
        startActivity(new Intent(this, Demo3Activity.class));
    }

    public void demo4(View view) {
        startActivity(new Intent(this, Demo4Activity.class));
    }

}