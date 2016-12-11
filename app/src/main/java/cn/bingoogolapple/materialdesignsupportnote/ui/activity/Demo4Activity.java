package cn.bingoogolapple.materialdesignsupportnote.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.bingoogolapple.materialdesignsupportnote.R;
import cn.bingoogolapple.materialdesignsupportnote.ui.fragment.ContentFragment;
import cn.bingoogolapple.materialdesignsupportnote.ui.fragment.MyBottomSheetDialogFragment;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:16/12/11 下午5:49
 * 描述:
 */
public class Demo4Activity extends AppCompatActivity {
    private static final String TAG = Demo4Activity.class.getSimpleName();
    private BottomNavigationView mBottomNavigationView;

    private BottomSheetBehavior mBottomSheetBehavior;

    private ViewPager mViewPager;
    private MenuItem mPrevMenuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initViewPager();
        initBottomNavigationView();

        initBottomSheet();
    }

    private void initView() {
        setContentView(R.layout.activity_demo4);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv_bottom_menu);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                resetPreMenuItem();

                mPrevMenuItem = mBottomNavigationView.getMenu().getItem(position);
                mPrevMenuItem.setChecked(true);
            }
        });
        mViewPager.setAdapter(new ContentPagerAdapter(getSupportFragmentManager()));
    }

    private void resetPreMenuItem() {
        if (mPrevMenuItem != null) {
            mPrevMenuItem.setChecked(false);
        } else {
            mBottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
    }

    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            // 结合 ViewPager 使用时
            switch (item.getItemId()) {
                case R.id.action_one:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.action_two:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.action_three:
                    mViewPager.setCurrentItem(2);
                    break;
                case R.id.action_four:
                    mViewPager.setCurrentItem(3);
                    break;
            }
            return true;
        });

        disableShiftMode(mBottomNavigationView);
    }

    private void initBottomSheet() {
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.design_bottom_sheet));
        // 默认设置为隐藏
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.i(TAG, "onStateChanged bottomSheet " + bottomSheet.getClass().getSimpleName() + " newState " + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i(TAG, "onSlide bottomSheet " + bottomSheet.getClass().getSimpleName() + " newState " + slideOffset);
            }
        });

        // 设置为 true 之后，点击或拖动时会被直接关闭
//        mBottomSheetBehavior.setSkipCollapsed(true);
    }

    public void changeToTwo(View v) {
//        activateNewButton(mBottomNavigationView, 1);

        resetPreMenuItem();
        mPrevMenuItem = mBottomNavigationView.getMenu().getItem(1);
        mPrevMenuItem.setChecked(true);
        mViewPager.setCurrentItem(1);
    }

    public void changeToFour(View v) {
//        activateNewButton(mBottomNavigationView, 3);

        resetPreMenuItem();
        mPrevMenuItem = mBottomNavigationView.getMenu().getItem(3);
        mPrevMenuItem.setChecked(true);
        mViewPager.setCurrentItem(3);
    }

    public void bottomOne(View v) {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    public void bottomTwo(View v) {
        new MyBottomSheetDialogFragment().show(getSupportFragmentManager(), MyBottomSheetDialogFragment.class.getSimpleName());
    }

    private class ContentPagerAdapter extends FragmentStatePagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ContentFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "第" + (position + 1) + "页";
        }
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

    /**
     * 取消大于三项时占用其他 item 的空间
     *
     * @param bottomNavigationView
     */
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
