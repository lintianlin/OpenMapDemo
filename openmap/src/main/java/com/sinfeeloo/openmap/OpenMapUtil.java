package com.sinfeeloo.openmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import java.net.URISyntaxException;

/**
 * @author: mhj
 * @date: 2018/4/4 11:12
 * @desc:
 */

public class OpenMapUtil {


    //打开外部地图
    public static void openMapPopupWindow(final Activity activity, View root, final String title, final double latitude, final double longitude) {
        View view = View.inflate(activity, R.layout.popu_open_map, null);
        final Button btn_openBaidu = view.findViewById(R.id.btn_open_baidu);
        Button btn_openGaode = view.findViewById(R.id.btn_open_gaode);
        Button btn_openTencent = view.findViewById(R.id.btn_open_tencent);
        Button btn_cancel = view.findViewById(R.id.btn_open_cancel);


        final PopupWindow mPopup = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setFocusable(true);
        mPopup.setOutsideTouchable(true);
        mPopup.setBackgroundDrawable(new ColorDrawable());
        mPopup.setAnimationStyle(R.style.popupwindow_style);

        btn_openBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBaidu(activity, title, latitude, longitude);
                mPopup.dismiss();
            }
        });

        btn_openGaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGaode(activity, title, latitude, longitude);
                mPopup.dismiss();
            }
        });

        btn_openTencent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTencent(activity, title, latitude, longitude);
                mPopup.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopup.dismiss();
            }
        });

        mPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeActivityBg(activity, 1f);
            }
        });

        mPopup.showAtLocation(root, Gravity.BOTTOM, 0, 0);

        changeActivityBg(activity, 0.7f);
    }


    /**
     * 打开腾讯地图
     * com.tencent.map
     *
     * @param latitude
     * @param longitude
     */
    private static void openTencent(Activity activity, String title, double latitude, double longitude) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        //将功能Scheme以URI的方式传入data
        Uri uri = Uri.parse("qqmap://map/routeplan?type=drive&to=" + title + "&tocoord=" + latitude + "," + longitude);
        intent.setData(uri);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            //启动该页面即可
            activity.startActivity(intent);
        } else {
            ToastUtils.showToast("您尚未安装腾讯地图");
            Uri uri2 = Uri.parse("market://details?id=com.tencent.map");
            Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
            if (intent2.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent2);
            }
        }
    }

    /**
     * 打开高德
     *
     * @param latitude
     * @param longitude
     */
    private static void openGaode(Activity activity, String title, double latitude, double longitude) {
        if (AppUtils.isAvilible("com.autonavi.minimap")) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            //将功能Scheme以URI的方式传入data
            Uri uri = Uri.parse("androidamap://route/plan/?dlat=" + latitude + "&dlon=" + longitude + "&dname=" + title + "&dev=0&t=0");
            intent.setData(uri);

            //启动该页面即可
            activity.startActivity(intent);
        } else {
            ToastUtils.showToast("您尚未安装高德地图");
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
            }
        }
    }

    /**
     * 打开百度地图
     *
     * @param latitude
     * @param longitude
     */
    private static void openBaidu(Activity activity, String title, double latitude, double longitude) {
        LngLat baiduLngLat = CoodinateCovertor.bd_encrypt(new LngLat(longitude, latitude));//将高德地图转换为百度坐标
        if (AppUtils.isAvilible("com.baidu.BaiduMap")) {//传入指定应用包名
            try {

                Intent intent = Intent.getIntent("intent://map/direction?" +
                        "destination=latlng:" + baiduLngLat.getLantitude() + "," + baiduLngLat.getLongitude() + "|name:" + title +    //终点
                        "&mode=driving&" +     //导航路线方式
                        "&src=distribution#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                activity.startActivity(intent); //启动调用
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
        } else {//未安装
            //market为路径，id为包名
            //显示手机上所有的market商店
            ToastUtils.showToast("您尚未安装百度地图");
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
            }
        }
    }


    //改变activity背景透明度
    private static void changeActivityBg(Activity activity, float f) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = f;// 值越小透明度越暗
        activity.getWindow().setAttributes(params);
    }
}
