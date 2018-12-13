package com.ivyzh.ubing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ivyzh.baselibrary.ioc.CheckNet;
import com.ivyzh.baselibrary.ioc.OnClick;
import com.ivyzh.baselibrary.ioc.ViewById;
import com.ivyzh.baselibrary.ioc.ViewUtils;
import com.ivyzh.baselibrary.log.L;

public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tv_msg)
    private TextView mMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        L.d("debug");
        L.v("debug");

        mMsg.setText("mMsg");

    }

    @OnClick(R.id.tv_msg)
    @CheckNet(msg = R.string.net_invisible)
    private void hello() {
        Toast.makeText(this, "Hello.", Toast.LENGTH_LONG).show();
    }
}
