package cn.bingoogolapple.materialdesignsupportnote.util;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.util.Log;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

import cn.bingoogolapple.materialdesignsupportnote.R;

public class MyActionProvider extends ActionProvider {

	public MyActionProvider(Context context) {
		super(context);
	}

	@Override
	public View onCreateActionView() {
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		subMenu.clear();
		subMenu.add("子菜单1").setIcon(R.mipmap.number_1).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Log.i("bingo", "点击子菜单1");
				return true;
			}
		});
		subMenu.add("子菜单2").setIcon(R.mipmap.number_2).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Log.i("bingo", "点击子菜单2");
				return false;
			}
		});
	}

	@Override
	public boolean hasSubMenu() {
		// 为了表示这个Action Provider是有子菜单的，需要重写hasSubMenu()方法并返回true
		return true;
	}

}
