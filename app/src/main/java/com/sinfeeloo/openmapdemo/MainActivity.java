package com.sinfeeloo.openmapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.help.Tip;
import com.sinfeeloo.openmap.OpenMapUtil;

public class MainActivity extends AppCompatActivity {


    private TextView address;
    private TextView lan;
    private TextView lon;
    private TextView searchText;
    private TextView name;
    private double mLatitude;
    private double mLongitude;
    private String mName;
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.tv_search);
        name = findViewById(R.id.tv_name);
        address = findViewById(R.id.tv_address);
        lan = findViewById(R.id.tv_lan);
        lon = findViewById(R.id.tv_lon);
        root = findViewById(R.id.ll_root);


        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputTipsActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        findViewById(R.id.btn_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMapUtil.openMapPopupWindow(MainActivity.this, root, mName, mLatitude, mLongitude);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101 && data != null) {
            Tip tip = data.getParcelableExtra("EXTRA_TIP");
            if (tip.getPoiID() != null && !tip.getPoiID().equals("")) {
                name.setText("名称：" + tip.getName());
                address.setText("地址：" + tip.getAddress());
                lan.setText("纬度：" + tip.getPoint().getLatitude());
                lon.setText("经度：" + tip.getPoint().getLongitude());
                mLatitude = tip.getPoint().getLatitude();
                mLongitude = tip.getPoint().getLongitude();
                mName = tip.getName();
            }

        } else if (resultCode == 102 && data != null) {
            String keywords = data.getStringExtra("KEY_WORDS_NAME");
            if (keywords != null && !keywords.equals("")) {
                Toast.makeText(this, keywords, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
