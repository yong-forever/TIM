package com.example.imitate_tim;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import com.example.imitate_tim.Utils.SQL;
import com.example.imitate_tim.Utils.TableUtils_user;
import com.example.imitate_tim.Utils.UserInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivHead;
    private EditText edUsername;
    private Spinner spUsers;
    private EditText edPassword;
    private Button btLogin;
    private ImageView ivArgee;
    private boolean argee=true;
    private TextView tvTerms;
    private TextView tvCreateNew;
    private TextView tvFail;
    private SQL sql;
    private TableUtils_user utils_user;
    private List<UserInfo> userList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        sql=new SQL(this,"USER_DB");
        utils_user=new TableUtils_user(sql);
        userList.addAll(utils_user.getUserinfo_rawQuery("SELECT * FROM user"));
        ivHead = (ImageView) findViewById(R.id.iv_head);
        edUsername = (EditText) findViewById(R.id.ed_username);
        spUsers = (Spinner) findViewById(R.id.sp_users);
        edPassword = (EditText) findViewById(R.id.ed_password);
        btLogin = (Button) findViewById(R.id.bt_login);
        ivArgee = (ImageView) findViewById(R.id.iv_argee);
        tvTerms = (TextView) findViewById(R.id.tv_Terms);
        tvCreateNew = (TextView) findViewById(R.id.tv_createNew);
        tvFail = (TextView) findViewById(R.id.tv_fail);

        btLogin.setOnClickListener(this);
        ivArgee.setOnClickListener(this);
        tvCreateNew.setOnClickListener(this);
        tvFail.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userList.clear();
        userList.addAll(utils_user.getUserinfo_rawQuery("SELECT * FROM user"));
    }
    private boolean Judge(String u,String p){
        if(u.length()<0){
            Toast.makeText(MainActivity.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(p.length()<0){
            Toast.makeText(MainActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i = 0; i < userList.size(); i++) {
            if(u.equals(userList.get(i).getUsername())&&p.equals(userList.get(i).getPassword())){
                Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        Toast.makeText(this, "账号或密码不正确！", Toast.LENGTH_SHORT).show();
        return false;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                String username=edUsername.getText().toString();
                String password=edPassword.getText().toString();
                if(Judge(username,password)){
                    startActivity(new Intent(MainActivity.this,Lord_Activity.class));
                    finish();
                }
                break;
            case R.id.iv_argee:
                argee=!argee;
                if(argee){
                    ivArgee.setBackgroundResource(R.drawable.paf);
                    btLogin.setEnabled(true);
                    btLogin.setTextColor(Color.parseColor("#ffffff"));
                }
                else {
                    ivArgee.setBackgroundResource(R.drawable.pae);
                    btLogin.setEnabled(false);
                    btLogin.setTextColor(Color.parseColor("#A6A6A6"));
                }
                break;
            case R.id.tv_createNew:
                startActivity(new Intent(MainActivity.this,Registered_Activity.class));
                break;
            case R.id.tv_fail:
                break;
        }
    }
}
