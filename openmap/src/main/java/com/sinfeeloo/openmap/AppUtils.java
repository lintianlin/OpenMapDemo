package com.sinfeeloo.openmap;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * AppUtils
 * @author: mhj
 * @date: 2018/4/4 11:47
 *
 *
 */
public class AppUtils {


    /**
     * 判断应用是否安装
     *
     * @param packageName
     * @return
     */
    public static boolean isAvilible(String packageName) {
        final PackageManager packageManager = App.instance().getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

}
