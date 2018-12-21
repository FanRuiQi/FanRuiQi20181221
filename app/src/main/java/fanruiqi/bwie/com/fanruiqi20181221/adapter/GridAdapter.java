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

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder>{
    private Context mContext;
    private List<GridBean.DataBean> list;

    public GridAdapter(Context context, List<GridBean.DataBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_grid, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mTextView.setText(list.get(position).getName());

        Glide.with(mContext).load(list.get(position).getIcon()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.item_grid_img);
            mTextView = itemView.findViewById(R.id.item_grid_text);
        }
    }
}
