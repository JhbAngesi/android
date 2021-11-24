package cn.student.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowContent extends AppCompatActivity {
    Button update;
    private TextView time,tv_title,showtext;
    private NoteDb mDb;
    private SQLiteDatabase mSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);
        update=(Button)findViewById(R.id.save) ;
        tv_title = (TextView)this.findViewById(R.id.tv_title);
        time = (TextView)this.findViewById(R.id.tv_createtime);
        showtext = (EditText)this.findViewById(R.id.showtext);
        mDb = new NoteDb(this);
        mSql = mDb.getWritableDatabase();//创建可读、写的数据库对象
        //保存按钮，匿名内部类作为监听器
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                //准备数据
                values.put(NoteDb.CONTENT,showtext.getText().toString());
                //根据主键ID修改日记内容
                String id = getIntent().getStringExtra(NoteDb.ID);
                mSql.update(NoteDb.TABLE_NAME,values,NoteDb.ID+"=?",new String[]{id});
                //跳转完以后结束活动
                finish();
            }
        });
        //显示修改后日记标题、内容、创建时间
        tv_title.setText(getIntent().getStringExtra(NoteDb.TITLE));
        showtext.setText(getIntent().getStringExtra(NoteDb.CONTENT));
        time.setText(getIntent().getStringExtra(NoteDb.TIME));

    }
    //“删除”按钮
    public void delete(View v) {
        //根据日记Id删除
        String id = getIntent().getStringExtra(NoteDb.ID);
        mSql.delete(NoteDb.TABLE_NAME,NoteDb.ID+"=?" ,new String[]{id});
        //跳转完以后结束活动
        finish();
    }
}