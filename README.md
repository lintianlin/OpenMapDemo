# OpenMapDemo

从应用内打开百度、高德、腾讯地图app

## 原理 ##
- 通过高德、百度、腾讯提供的Uri打开应用外部地图app.

## 高德、百度、腾讯官方uri调起app文档传送门##
- [高德地图：http://lbs.amap.com/api/amap-mobile/guide/android/route](http://lbs.amap.com/api/amap-mobile/guide/android/route)
- [百度地图：http://lbsyun.baidu.com/index.php?title=uri/api/android](http://lbsyun.baidu.com/index.php?title=uri/api/android)
- [腾讯地图：http://lbs.qq.com/uri_v1/index.html](http://lbs.qq.com/uri_v1/index.html)

## 关键代码 ##
-以高德地图为例：
```
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
```

## 需要注意的地方 ##
-1.由于自己应用中集成的是高德地图，用的是GCJ-02坐标系（火星坐标系），所以当打开百度地图时需要进行坐标转换，将火星坐标系转换成百度坐标系BD09。
 具体看代码： [CoodinateCovertor.java](https://github.com/lintianlin/OpenMapDemo/blob/master/openmap/src/main/java/com/sinfeeloo/openmap/CoodinateCovertor.java)

## 使用方法 ##
-1.Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
-2.Add the dependency
```
dependencies {
	        compile 'com.github.lintianlin:OpenMapDemo:v1.0.0'
	}
```
## 示例效果 ##
<img src="https://github.com/lintianlin/OpenMapDemo/blob/master/screenshot/pic4.jpg" width=300 height=450 />
<img src="https://github.com/lintianlin/OpenMapDemo/blob/master/screenshot/pic3.jpg" width=300 height=450 />
<img src="https://github.com/lintianlin/OpenMapDemo/blob/master/screenshot/pic2.jpg" width=300 height=450 />
<img src="https://github.com/lintianlin/OpenMapDemo/blob/master/screenshot/pic1.jpg" width=300 height=450 />


# License
    Copyright 2018 SinFeeLoo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.