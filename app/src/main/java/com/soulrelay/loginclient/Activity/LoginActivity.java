package com.soulrelay.loginclient.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.soulrelay.loginclient.R;
import com.soulrelay.loginclient.model.ResultReturn;
import com.soulrelay.loginclient.network.Network;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sushuai on 2016/3/25.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.edt_email)
    EditText edtEmail;
    @Bind(R.id.edt_password)
    EditText edtPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Bind(R.id.tv_response)
    TextView tvResponse;

    Observer<ResultReturn> observer = new Observer<ResultReturn>() {
        @Override
        public void onCompleted() {
            //Toast.makeText(LoginActivity.this,"onCompleted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(LoginActivity.this, "onError:"+e.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(ResultReturn result) {
            Toast.makeText(LoginActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
            if(result.isSuccess()){
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }
    };

    private void login(String email, String password) {
        subscription = Network.getloginApi()
                .login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                excuteLogin();
                break;
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void excuteLogin() {
        String email = edtEmail.getText().toString().trim();
        String pwd = edtPassword.getText().toString().trim();
        login(email, pwd);
    }
}
