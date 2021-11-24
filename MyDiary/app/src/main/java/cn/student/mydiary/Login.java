package cn.student.mydiary;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText et_author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_author = (EditText)findViewById(R.id.et_author);


    }
    //“登录”按钮方法，在布局文件中通过标签绑定监听器
    public void login(View view){
        //获取SharedPreferences对象
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        //将键值对存入SharedPreferences文件
        if(TextUtils.isEmpty(et_author.getText().toString())){
            editor.putString(NoteDb.AUTHOR,"Xiaoming");
        }else {
            editor.putString(NoteDb.AUTHOR, et_author.getText().toString());
        }
        editor.commit();
        //使用显示Intent
        Intent intent = new Intent(Login.this, MainActivity.class);
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String  auther_name1= pref.getString(NoteDb.AUTHOR,"");
        //传递数据
        intent.putExtra(NoteDb.AUTHOR, auther_name1);
        startActivity(intent);
    }

}