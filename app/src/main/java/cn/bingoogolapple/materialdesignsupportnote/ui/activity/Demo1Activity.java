package cn.bingoogolapple.materialdesignsupportnote.ui.activity;

import android.content.Intent;
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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
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
            }
        });
    }

    private void setUpNavDrawer() {
        mToolbar.setNavigationIcon(R.drawable.selector_drawer);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDrawer();
            }
        });
    }

    private void setUpNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                hideDrawer();
                switch (menuItem.getItemId()) {
                    case R.id.navigation_demo_item_1:
                        SnackbarUtil.show(mCoordinatorLayout, menuItem.getTitle());
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
            }
        });
    }

    private void setUpTabLayoutAndViewPager() {
        ContentPagerAdapter contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(contentPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(contentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
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
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "第" + (position + 1) + "页";
        }
    }


}
