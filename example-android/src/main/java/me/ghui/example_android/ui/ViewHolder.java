package me.ghui.example_android.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ghui.example_android.R;

/**
 * Created by ghui on 19/10/2017.
 */
class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.user_name_tv)
    public TextView userName;
    @BindView(R.id.time_tv)
    public TextView time;
    @BindView(R.id.tagview)
    public TextView tag;
    @BindView(R.id.title_tv)
    public TextView title;
    @BindView(R.id.avatar_img)
    public ImageView avatar;
    @BindView(R.id.comment_num_tv)
    public TextView comment;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
