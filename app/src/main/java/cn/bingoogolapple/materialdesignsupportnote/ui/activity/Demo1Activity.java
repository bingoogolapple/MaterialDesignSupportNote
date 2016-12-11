package cn.bingoogolapple.materialdesignsupportnote.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import cn.bingoogolapple.materialdesignsupportnote.R;
import cn.bingoogolapple.materialdesignsupportnote.ui.fragment.ContentFragment;
import cn.bingoogolapple.materialdesignsupportnote.util.SnackbarUtil;

public class Demo1Activity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private CoordinatorLayout mCoordinatorLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        initView();
        setUpToolbar();
        setUpNavDrawer();
        setUpNavigationView();
        setUpTabLayoutAndViewPager();

        changeTopBgColor(0);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.toolbar_demo_item_collect:
                    SnackbarUtil.show(mCoordinatorLayout, item.getTitle());
                    break;
                case R.id.toolbar_demo_item_feedback:
                    SnackbarUtil.show(mCoordinatorLayout, item.getTitle());
                    break;
                case R.id.toolbar_demo_item_help:
                    SnackbarUtil.show(mCoordinatorLayout, item.getTitle());
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void setUpNavDrawer() {
        mToolbar.setNavigationIcon(R.drawable.selector_drawer);
        mToolbar.setNavigationOnClickListener(v -> showDrawer());
    }

    private void setUpNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
//            menuItem.setChecked(true);
            hideDrawer();
            switch (menuItem.getItemId()) {
                case R.id.navigation_demo_item_1:
                    SnackbarUtil.show(mCoordinatorLayout, menuItem.getTitle());
//                        TypedValue typedValue = new TypedValue();
//                        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
//                        int color = typedValue.data;
                    int color = getResources().getColor(R.color.testStatusBarColor);
                    // 注意setStatusBarBackgroundColor方法需要将fitsSystemWindows设置为true才会生效
                    mDrawerLayout.setStatusBarBackgroundColor(color);
                    break;
                case R.id.navigation_demo_item_2:
                    SnackbarUtil.show(mCoordinatorLayout, menuItem.getTitle());
                    break;
                case R.id.navigation_demo_item_3:
                    SnackbarUtil.show(mCoordinatorLayout, menuItem.getTitle());
                    break;
                case R.id.navigation_demo_item_4:
                    SnackbarUtil.show(mCoordinatorLayout, menuItem.getTitle());
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void setUpTabLayoutAndViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                changeTopBgColor(position);
            }
        });
        mViewPager.setAdapter(new ContentPagerAdapter(getSupportFragmentManager()));
//        mTabLayout.setupWithViewPager(mViewPager);

        /**
         如果在布局文件中指定 TabItem，那么就不能使用 setupWithViewPager
         <android.support.design.widget.TabLayout
         android:id="@+id/tabLayout"
         style="@style/MatchWrap"
         android:textAppearance="@style/TextAppearance.AppCompat.Title"
         app:tabBackground="@drawable/selector_drawer"
         app:tabIndicatorColor="#ff0000"
         app:tabIndicatorHeight="3dp"
         app:tabMode="fixed"
         app:tabSelectedTextColor="#0000ff"
         app:tabTextColor="#00ff00">

         <android.support.design.widget.TabItem
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:text="第1项"/>

         <android.support.design.widget.TabItem
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:icon="@mipmap/number_1"/>

         <android.support.design.widget.TabItem
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:text="第3项"/>
         </android.support.design.widget.TabLayout>
         */
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            hideDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void onClickFab(View view) {
        startActivity(new Intent(Demo1Activity.this, TwoActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void showDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
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
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "第" + (position + 1) + "页";
        }
    }


    /**
     * 根据Palette提取的颜色，修改tab和toolbar以及状态栏的颜色
     */
    private void changeTopBgColor(int position) {
        int imageResource = R.mipmap.one;
        if (position % 3 == 1) {
            imageResource = R.mipmap.two;
        } else if (position % 3 == 2) {
            imageResource = R.mipmap.three;
        }
        // 用来提取颜色的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageResource);
        // Palette的部分
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取到充满活力的这种色调
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                //根据调色板Palette获取到图片中的颜色设置到toolbar和tab中背景，标题等，使整个UI界面颜色统一
                mTabLayout.setBackgroundColor(vibrant.getRgb());
                mTabLayout.setSelectedTabIndicatorColor(colorBurn(vibrant.getRgb()));
                mToolbar.setBackgroundColor(vibrant.getRgb());

                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
    }

    /**
     * 颜色加深处理
     */
    private int colorBurn(int RGBValues) {
//        int alpha = RGBValues >> 24 & 0xFF;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
