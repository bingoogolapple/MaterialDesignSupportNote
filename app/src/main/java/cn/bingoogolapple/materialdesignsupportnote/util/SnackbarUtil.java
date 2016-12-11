package cn.bingoogolapple.materialdesignsupportnote.util;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bingoogolapple.materialdesignsupportnote.R;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/6/25 17:55
 * 描述:
 */
public class SnackbarUtil {

    private SnackbarUtil() {
    }

    public static void show(View view, CharSequence text) {
        Snackbar snackbar = Snackbar.make(view, text, text.length() < 10 ? Snackbar.LENGTH_SHORT : Snackbar.LENGTH_LONG)
                .setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "点击了 Action", Toast.LENGTH_SHORT).show();
                    }
                }).setActionTextColor(Color.parseColor("#00ffff"))
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        Toast.makeText(snackbar.getView().getContext(), "消失", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        Toast.makeText(snackbar.getView().getContext(), "显示", Toast.LENGTH_SHORT).show();
                    }
                });

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;

        ImageView number1Iv = new ImageView(view.getContext());
        number1Iv.setImageResource(R.mipmap.number_1);

        snackbarLayout.addView(number1Iv, 0, layoutParams);
        ((TextView) snackbarLayout.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.parseColor("#ffaaaa"));
        snackbarLayout.setBackgroundColor(Color.parseColor("#aaaaff"));

        snackbar.show();
    }
}