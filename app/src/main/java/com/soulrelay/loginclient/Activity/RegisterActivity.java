package com.soulrelay.loginclient.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
public class RegisterActivity extends BaseActivity {

    @Bind(R.id.edt_reg_username)
    EditText edtRegUsername;
    @Bind(R.id.edt_reg_password)
    EditText edtRegPassword;
    @Bind(R.id.edt_reg_email)
    EditText edtRegEmail;
    @Bind(R.id.edt_reg_contact)
    EditText edtRegContact;
    @Bind(R.id.btn_reg_submit)
    Button btnRegSubmit;

    Observer<ResultReturn> observer = new Observer<ResultReturn>() {
        @Override
        public void onCompleted() {
            //Toast.makeText(LoginActivity.this,"onCompleted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(RegisterActivity.this, "onError:" + e.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(ResultReturn result) {
            Toast.makeText(RegisterActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
            if (result.isSuccess()) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_reg_submit)
    public void onClick() {
        String name = edtRegUsername.getText().toString().trim();
        String email = edtRegEmail.getText().toString().trim();
        String password = edtRegPassword.getText().toString().trim();
        String contact = edtRegContact.getText().toString().trim();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            registerUser(name, email, password, contact);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please enter your details!", Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * Function to store user in MySQL database will post params(name, email,
     * contact, password) to register url
     * @param name
     * @param email
     * @param password
     * @param contact
     */
    private void registerUser(final String name, final String email,
                              final String password, final String contact) {
        subscription = Network.getRegisterApi()
                .register(name, email, contact, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
