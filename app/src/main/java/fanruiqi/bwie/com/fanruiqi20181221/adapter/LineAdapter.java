package fanruiqi.bwie.com.fanruiqi20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fanruiqi.bwie.com.fanruiqi20181221.R;
import fanruiqi.bwie.com.fanruiqi20181221.bean.GridBean;
import fanruiqi.bwie.com.fanruiqi20181221.bean.LineBean;

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.ViewHolder>{
    private Context mContext;
    private List<LineBean.DataBean> list;
    String urls;

    public LineAdapter(Context context, List<LineBean.DataBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_line, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface OnItemClickListener{  //接口回调
        void onItemClick(String urls);
    }

    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        LineBean.DataBean dataBean = list.get(position);
        List<LineBean.DataBean.ListBean> list1 = dataBean.getList();

        for (int k=0;k<list1.size();k++){

            LineBean.DataBean.ListBean listBean = list1.get(k);

            String str="";
            int length = listBean.getImages().length();

            for (int j=0;j<length;j++){
                if (listBean.getImages().substring(j,j+1).equals("|")){
                    str = listBean.getImages().substring(j + 1, length).trim();
                }
            }

            urls=str;
            Glide.with(mContext).load(str).into(holder.mImageView);
            holder.mTextView.setText(listBean.getTitle());
            holder.mTextView2.setText(listBean.getPrice()+"");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mOnItemClickListener.onItemClick(urls);
            }
        });


        //Glide.with(mContext).load(this.list.get(position).getIcon()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        TextView mTextView2;
        public ViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.item_line_img);
            mTextView = itemView.findViewById(R.id.item_line_title);
            mTextView2 = itemView.findViewById(R.id.item_line_price);
        }
    }
}
