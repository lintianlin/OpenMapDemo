package com.sinfeeloo.openmap;

import android.widget.Toast;

/**
 * toast封装
 * Created by Administrator icon_btn_on 2016/9/5.
 */
public class ToastUtils {

    private ToastUtils() {
            /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;


    private static Toast toast;

    /**
     * 显示单例的吐司，能连续快速弹的吐司
     *
     * @param text
     */
    public static void showToast(String text) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(App.instance(), text, Toast.LENGTH_SHORT);
            }
            toast.setText(text);
            toast.show();
        }
    }

}
