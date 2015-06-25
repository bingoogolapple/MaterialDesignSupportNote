package cn.bingoogolapple.materialdesignsupportnote.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/6/25 17:55
 * 描述:
 */
public class SnackbarUtil {

    private SnackbarUtil() {
    }

    public static void show(View view, CharSequence text) {
        if (text.length() < 10) {
            Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
        }
    }
}