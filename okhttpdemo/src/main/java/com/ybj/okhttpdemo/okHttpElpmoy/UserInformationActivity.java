package com.ybj.okhttpdemo.okHttpElpmoy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ybj.okhttpdemo.R;
import com.ybj.okhttpdemo.model.BaseResult;
import com.ybj.okhttpdemo.okHttp.BaseCallback;
import com.ybj.okhttpdemo.okHttp.SimpleHttpClient;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class UserInformationActivity extends AppCompatActivity {

    @BindView(R.id.btn_get)
    Button mBtnGet;
    @BindView(R.id.imgview)
    ImageView mImgview;
    @BindView(R.id.txt_username)
    TextView mTxtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get)
    public void onViewClicked() {
        getUserInfo();
    }

    private void updateInfo(BaseResult baseResult) {
        BaseResult.ResultBean result = baseResult.getResult();
        //原生解析
        mTxtUsername.setText(result.getCity());
    }

    private void getUserInfo() {
        SimpleHttpClient.newBuilder()
                .get()
                .url("http://apis.juhe.cn/mobile/get")
                .addParam("phone",1539026)
                .addParam("dtype","json")
                .addParam("key","858f48bb6fcebd319dccdfcd5af70813")
                .build()
                .enqueue(new BaseCallback<BaseResult>(){
                    @Override
                    public void onSuccess(BaseResult baseResult) {
                        super.onSuccess(baseResult);
                        Log.e("TAG", "成功");
                        updateInfo(baseResult);
                    }

                    @Override
                    public void onError(int code) {
                        super.onError(code);
                        Log.e("TAG", "出错");
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        super.onFailure(call, e);
                        Log.e("TAG", "失败");
                    }
                });
    }
}
