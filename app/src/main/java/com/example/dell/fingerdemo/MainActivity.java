package com.example.dell.fingerdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FingerprintManager fmanager;
    private FingerprintManager.AuthenticationCallback mCallback;
    private String TAG = "tag";
    private ImageView iv;
    private LineView lv;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(this);
        lv = (LineView) findViewById(R.id.lv);
        lv.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        fmanager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
        lv.setVisibility(View.VISIBLE);
        lv.startAnim(300);

        mCallback = new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
                Log.e(TAG, "onAuthenticationError:  //多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证");
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                super.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.e(TAG, "onAuthenticationSucceeded:    成功");
                iv.setImageResource(R.mipmap.se);
                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                lv.setVisibility(View.GONE);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.e(TAG, "onAuthenticationFailed:   失败");
                iv.setImageResource(R.mipmap.error);
                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
                lv.setVisibility(View.GONE);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
        }
        fmanager.authenticate(null, null, 0, mCallback, null);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                fmanager.authenticate(null, null, 0, mCallback, null);
                lv.setVisibility(View.VISIBLE);
                lv.startAnim(300);
                iv.setImageResource(R.mipmap.se);
                break;
        }
    }
}
