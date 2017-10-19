package me.ghui.example_android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ghui.example_android.R;
import me.ghui.example_android.general.GlideApp;
import me.ghui.example_android.network.APIService;
import me.ghui.example_android.network.bean.NewsInfo;
import me.ghui.example_android.ui.base.BaseFragment;

public class NewsFragment extends BaseFragment {

    private NewsAdapter newsAdapter;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter();
        recyclerView.setAdapter(newsAdapter);
        APIService.get().homeNews("all")
                .compose(rx())
                .subscribe(newsInfo -> {
                    newsAdapter.setData(newsInfo);
                });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.recyclerview;
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private NewsInfo newsInfo;

        public void setData(NewsInfo newsInfo) {
            this.newsInfo = newsInfo;
            notifyDataSetChanged();
        }

        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.common_list_item, null);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
            NewsInfo.Item item = newsInfo.getItems().get(position);
            GlideApp.with(getContext())
                    .load(item.getAvatar())
                    .placeholder(R.drawable.avatar_placeholder_drawable)
                    .into(holder.avatar);
            holder.userName.setText(item.getUserName());
            holder.time.setText(item.getTime());
            holder.tag.setText(item.getTagName());
            holder.title.setText(item.getTitle());
            holder.comment.setText("评论" + item.getReplies());
        }

        @Override
        public int getItemCount() {
            try {
                return newsInfo.getItems().size();
            } catch (Exception e) {
                return 0;
            }
        }

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

    }

}
