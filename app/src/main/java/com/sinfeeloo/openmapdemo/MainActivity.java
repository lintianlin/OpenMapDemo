package com.sinfeeloo.openmapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Inputtips.InputtipsListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText searchText = findViewById(R.id.et_search);
        TextView address = findViewById(R.id.tv_address);
        TextView lan = findViewById(R.id.tv_lan);
        TextView lon = findViewById(R.id.tv_lon);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                InputtipsQuery inputquery = new InputtipsQuery(searchText.getText().toString(), "");
                inputquery.setCityLimit(true);//限制在当前城市

                Inputtips inputTips = new Inputtips(MainActivity.this, inputquery);
                inputTips.setInputtipsListener(MainActivity.this);
                inputTips.requestInputtipsAsyn();
            }
        });

        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {

    }
}
