package cn.student.mydiary;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    public Integer id1;
    public String title1;
    public String content1;
    public String author1;
    public String create_time1=getTime();
    public Record(){ }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public void setCreate_time1(String create_time1) {
        this.create_time1 = create_time1;
    }

    public Integer getId1() {
        return id1;
    }

    public String getTitle1() {
        return title1;
    }

    public String getContent1() {
        return content1;
    }

    public String getAuthor1() {
        return author1;
    }

    public String getCreate_time1() {
        return create_time1;
    }
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }
}
