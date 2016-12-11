package cn.bingoogolapple.materialdesignsupportnote.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.bingoogolapple.materialdesignsupportnote.R;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:16/12/11 下午5:49
 * 描述:
 */
public class Demo4Activity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private TextView mContentTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo4);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv_bottom_menu);
        mContentTv = (TextView) findViewById(R.id.tv_demo4_content);
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            mContentTv.setText(item.getTitle());
            return true;
        });

        disableShiftMode(mBottomNavigationView);
    }

    public void changeToTwo(View v) {
        activateNewButton(mBottomNavigationView, 1);
    }

    public void changeToFour(View v) {
        activateNewButton(mBottomNavigationView, 3);
    }

    public static void activateNewButton(BottomNavigationView bottomNavigationView, int position) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        try {
            Method activateNewButtonMethod = bottomNavigationMenuView.getClass().getDeclaredMethod("activateNewButton", int.class);
            activateNewButtonMethod.setAccessible(true);
            activateNewButtonMethod.invoke(bottomNavigationMenuView, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disableShiftMode(BottomNavigationView bottomNavigationView) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        try {
            Field shiftingModeField = bottomNavigationMenuView.getClass().getDeclaredField("mShiftingMode");
            shiftingModeField.setAccessible(true);
            shiftingModeField.setBoolean(bottomNavigationMenuView, false);
            shiftingModeField.setAccessible(false);

            for (int i = 0; i < bottomNavigationMenuView.getChildCount(); i++) {
                BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) bottomNavigationMenuView.getChildAt(i);
                bottomNavigationItemView.setShiftingMode(false);
                bottomNavigationItemView.setChecked(bottomNavigationItemView.getItemData().isChecked());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
