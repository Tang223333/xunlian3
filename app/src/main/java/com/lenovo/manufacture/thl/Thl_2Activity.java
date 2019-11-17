package com.lenovo.manufacture.thl;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.Broadcast.MyReceiver;
import com.lenovo.manufacture.thl.Util.ViewUtil;

public class Thl_2Activity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_11;
    EditText edit_6;
    int max_6, max_11;
    private EditText edit11;
    private EditText edit6;
    private Button btnDenglu;
    private EditText thl2Edit;
    private Button thl2Btn1;
    private Button thl2Btn2;
    private Button thl2Btn3;
    private Button thl2Btn4;
    private TextView thl2Txt;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thl_2);

        myReceiver=new MyReceiver();//初始化一个BroadcastTeceiver对象
        IntentFilter intentFilter1=new IntentFilter();//定义一个intent过滤器
        intentFilter1.addAction("start");
        registerReceiver(myReceiver,intentFilter1);//注册接收者

        edit_6 = findViewById(R.id.edit_6);
        edit_11 = findViewById(R.id.edit_11);//找到这两个输入框
        max_6 = ViewUtil.getMaxLength(edit_6);//edit_6的最大长度
        max_11 = ViewUtil.getMaxLength(edit_11);//edit_11的最大长度
        //添加监听
        //在输入回车是自动跳转到下一个控件
        edit_6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.length() == 0) {
                    return;
                }
                if (s.length() == 6 && max_6 == 6) {
                    InputMethodManager imm = (InputMethodManager) Thl_2Activity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) { // 软键盘如果已经打开则关闭之
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }
        });

        edit_11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.length() == 0) {
                    return;
                }
                if (s.length() == 11 && max_11 == 11) {
                    InputMethodManager imm = (InputMethodManager) Thl_2Activity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) { // 软键盘如果已经打开则关闭之
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }
        });
        initView();
    }

    private void initView() {
        edit11 = (EditText) findViewById(R.id.edit_11);
        edit6 = (EditText) findViewById(R.id.edit_6);
        btnDenglu = (Button) findViewById(R.id.btn_denglu);
        btnDenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thl_2Activity.this, Thl_3Activity.class);
                startActivity(intent);
                finish();
            }
        });
        thl2Edit = (EditText) findViewById(R.id.thl2_edit);
        thl2Btn1 = (Button) findViewById(R.id.thl2_btn1);
        thl2Btn2 = (Button) findViewById(R.id.thl2_btn2);
        thl2Btn3 = (Button) findViewById(R.id.thl2_btn3);
        thl2Btn4 = (Button) findViewById(R.id.thl2_btn4);
        thl2Txt = (TextView) findViewById(R.id.thl2_txt);
        thl2Btn1.setOnClickListener(this);
        thl2Btn2.setOnClickListener(this);
        thl2Btn3.setOnClickListener(this);
        thl2Btn4.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.thl2_btn1:
                //判断字符串是否为空
                boolean isEmpty = TextUtils.isEmpty(thl2Edit.getText());
                String desc = String.format("输入框的文本%s空的", isEmpty ? "是" : "不是");
                thl2Txt.setText(desc);
                break;
            case R.id.thl2_btn2:
                //获取字符串去除头尾空格之后的长度
                int length=TextUtils.getTrimmedLength(thl2Edit.getText());
                String desc2=String.format("输入框的文本去掉左右空格后的长度是%d",length);
                thl2Txt.setText(desc2);
                break;
            case R.id.thl2_btn3:
                //判断字符串是否全部由数字组成
                boolean isDigli=TextUtils.isDigitsOnly(thl2Edit.getText());
                String desc3=String.format("输入框的文本%s全部由数字组成",isDigli?"是":"不是");
                thl2Txt.setText(desc3);
                break;
            case R.id.thl2_btn4:
                //总共显示十个字符（因为省略号占了一个，所以还剩九个可显示汉字）
                float avail=thl2Edit.getTextSize()*10;
                //如果字符串超过十位，则返回在尾部截断并添加省略号的字符
                CharSequence ellips=TextUtils.ellipsize(thl2Edit.getText(),thl2Edit.getPaint(),avail, TextUtils.TruncateAt.END);
                thl2Txt.setText("输入框的文本加省略号的样式为:"+ellips);
                break;
        }
    }
}
