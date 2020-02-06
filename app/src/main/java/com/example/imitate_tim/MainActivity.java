package com.example.imitate_tim;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
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
                break;
            case R.id.tv_fail:
                break;
        }
    }
}
