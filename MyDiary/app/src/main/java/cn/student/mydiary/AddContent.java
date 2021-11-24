package cn.student.mydiary;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddContent extends AppCompatActivity {
    private EditText et_content,et_title;
    private NoteDb mDb;
    private SQLiteDatabase mSqldb;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        Intent intent=this.getIntent();
        //接受作者名
        name=intent.getStringExtra(NoteDb.AUTHOR);
        et_title = (EditText) findViewById(R.id.et_title);
        et_content = (EditText) this.findViewById(R.id.et_content);
        mDb = new NoteDb(this);
        mSqldb = mDb.getWritableDatabase();//创建可读、写的数据库对象

    }

    //"保存"按钮的方法
    public void save(View v) {
        DbAdd();
        finish();//跳转完以后结束活动
    }
    //"取消"按钮的方法
    public void cancle(View v) {
        et_title.setText("");
        et_content.setText("");
        finish();//跳转完以后结束活动
    }
    public void DbAdd() {
        //准备数据
        ContentValues cv = new ContentValues();
        cv.put(NoteDb.TITLE,et_title.getText().toString());
        cv.put(NoteDb.CONTENT,et_content.getText().toString());
        cv.put(NoteDb.AUTHOR,name);
        cv.put(NoteDb.TIME,getTime());
        //插入数据
        mSqldb.insert(NoteDb.TABLE_NAME,null,cv);
    }
    //获取当前系统时间
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }

}