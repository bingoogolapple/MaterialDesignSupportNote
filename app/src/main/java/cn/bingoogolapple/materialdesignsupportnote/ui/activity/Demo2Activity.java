package cn.bingoogolapple.materialdesignsupportnote.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Method;

import cn.bingoogolapple.materialdesignsupportnote.R;
import cn.bingoogolapple.materialdesignsupportnote.util.SnackbarUtil;

public class Demo2Activity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private CoordinatorLayout mCoordinatorLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private TextInputLayout mUsernameTil;
    private EditText mUsernameEt;
    private TextInputLayout mPwdTil;
    private EditText mPwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        initView();
        setUpToolbar();
        setUpNavDrawer();
        setUpNavigationView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);

        mUsernameTil = (TextInputLayout) findViewById(R.id.til_demo2_username);
        mPwdTil = (TextInputLayout) findViewById(R.id.til_demo2_pwd);
        mUsernameEt = (EditText) findViewById(R.id.et_demo2_username);
        mPwdEt = (EditText) findViewById(R.id.et_demo2_pwd);
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

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.i("bingo", "onMenuOpened " + menu.getClass().getName());
//        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
        if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
            try {
                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                method.setAccessible(true);
                method.invoke(menu, true);
            } catch (Exception e) {
            }
        }
//        }
        return super.onMenuOpened(featureId, menu);
    }

    private void setUpNavDrawer() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
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

    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            SnackbarUtil.show(mCoordinatorLayout, "点击了fab");
        } else if (view.getId() == R.id.btn_demo2_login) {
            if (TextUtils.isEmpty(mUsernameEt.getText().toString().trim())) {
                mUsernameTil.setError("用户名不能为空");
            } else {
                mUsernameTil.setErrorEnabled(false);
            }

            if (TextUtils.isEmpty(mPwdEt.getText().toString().trim())) {
                mPwdTil.setError("密码不能为空");
            } else {
                mPwdTil.setErrorEnabled(false);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            hideDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
}
