package cn.student.mydiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;




import java.util.List;
//组件包
class ViewHolder{
    TextView titleView;
    TextView authorView;
    TextView timeView;

}//适配器
public class MAdapter extends BaseAdapter {
    private Context mContext;
    //数据集合
    private List<Record> mList;
    private int layoutId;

    public MAdapter(Context mContext,List<Record> mList,int layoutId) {
        super();
        this.mContext = mContext;
        this.mList=mList;//事物列表
        this.layoutId=layoutId;//界面id
    }
    public MAdapter(Context mContext, List<Record> mList){
        super();
        this.mContext = mContext;
        this.mList=mList;
    }
    @Override
    public int getCount() {
        if (mList!=null&&mList.size()>0)
            //返回列表大小
            return mList.size();
        else
            return 0;
    }
    //移除事件
    public void removeItem(int position){
        this.mList.remove(position);
    }
    //得到某事件
    @Override
    public Object getItem(int position) {
        if (mList!=null&&mList.size()>0)
            return mList.get(position);
        else
            return null;
    }
    //得到事件
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(
                    mContext.getApplicationContext()).inflate(R.layout.item, viewGroup,
                    false);
            viewHolder  = new ViewHolder();
            //定义viewHolder用来查找特定目录
            viewHolder.titleView = view.findViewById(R.id.list_title);
            viewHolder.authorView = view.findViewById(R.id.list_author);
            viewHolder.timeView = view.findViewById(R.id.list_time);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Record record = mList.get(position);
        String tile = record.getTitle1(); //设置标题
        viewHolder.titleView.setText((position+1)+"."+(tile.length()>7?tile.substring(0,7)+"...":tile));

        String author =record.getAuthor1();//设置作者
        viewHolder.authorView.setText(author);

        String createTime = record.getCreate_time1();  //设置创建时间
        viewHolder.timeView.setText(createTime);
        return view;
    }
}
