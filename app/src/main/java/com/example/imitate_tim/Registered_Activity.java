package com.example.imitate_tim;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imitate_tim.Utils.SQL;
import com.example.imitate_tim.Utils.TableUtils_user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registered_Activity extends AppCompatActivity {

    private TextView tvReturn;
    private EditText edUsername;
    private EditText edPassword;
    private EditText edPassword2;
    private EditText edPhone;
    private ImageView ivArgee;
    private TextView tvTerms;
    private Button btRegistered;
    private TableUtils_user tableUtils_user;
    private boolean argee=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
    }

    private void initView() {
        tableUtils_user=new TableUtils_user(new SQL(this,"USER_DB"));
        tvReturn = (TextView) findViewById(R.id.tv_return);
        tvReturn.setText("< 返回");
        edUsername = (EditText) findViewById(R.id.ed_username);
        edPassword = (EditText) findViewById(R.id.ed_password);
        edPassword2 = (EditText) findViewById(R.id.ed_password2);
        edPhone = (EditText) findViewById(R.id.ed_phone);
        ivArgee = (ImageView) findViewById(R.id.iv_argee);
        tvTerms = (TextView) findViewById(R.id.tv_Terms);
        btRegistered = (Button) findViewById(R.id.bt_registered);

        tvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registered_Activity.this,MainActivity.class));
                finish();
            }
        });
        ivArgee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                argee=!argee;
                if(argee){
                    ivArgee.setBackgroundResource(R.drawable.paf);
                    btRegistered.setEnabled(true);
                    btRegistered.setTextColor(Color.parseColor("#ffffff"));
                }
                else {
                    ivArgee.setBackgroundResource(R.drawable.pae);
                    btRegistered.setEnabled(false);
                    btRegistered.setTextColor(Color.parseColor("#A6A6A6"));
                }
            }
        });
        btRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edUsername.getText().toString();
                String pass1=edPassword.getText().toString();
                String pass2=edPassword2.getText().toString();
                String phone=edPhone.getText().toString();
                if(Judge(username,pass1,pass2,phone)){
                    tableUtils_user.insert(username,pass1,phone,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                }
            }
            private boolean Judge(String u,String p1,String p2,String pe){
                if(u.length()<0){
                    Toast.makeText(Registered_Activity.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(p1.length()<0){
                    Toast.makeText(Registered_Activity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(p2.length()<0){
                    Toast.makeText(Registered_Activity.this, "请输入确认密码！", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(pe.length()<0){
                    Toast.makeText(Registered_Activity.this, "请输入手机号！", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(!p1.equals(p2)){
                    Toast.makeText(Registered_Activity.this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Toast.makeText(Registered_Activity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
