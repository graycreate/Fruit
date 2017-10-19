package me.ghui.example_android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import me.ghui.example_android.R;
import me.ghui.example_android.general.GeneralConsumer;
import me.ghui.example_android.general.GlideApp;
import me.ghui.example_android.network.APIService;
import me.ghui.example_android.network.bean.DailyHotInfo;
import me.ghui.example_android.network.bean.NewsInfo;
import me.ghui.example_android.ui.base.BaseFragment;
import me.ghui.example_android.util.PreConditions;

public class DailyHotFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    public static DailyHotFragment newInstance() {
        Bundle args = new Bundle();
        DailyHotFragment fragment = new DailyHotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.recyclerview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DailyHotAdapter adapter = new DailyHotAdapter();
        recyclerView.setAdapter(adapter);

        APIService.get().dailyHot()
                .compose(rx())
                .subscribe(new GeneralConsumer<DailyHotInfo>() {
                    @Override
                    public void onConsume(DailyHotInfo items) {
                        adapter.setData(items);
                    }
                });
    }

    class DailyHotAdapter extends RecyclerView.Adapter<ViewHolder> {
        DailyHotInfo data;

        public void setData(DailyHotInfo data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.common_list_item, null);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            DailyHotInfo.Item item = data.get(position);
            GlideApp.with(getContext())
                    .load(item.getMember().getAvatar())
                    .placeholder(R.drawable.avatar_placeholder_drawable)
                    .into(holder.avatar);
            holder.userName.setText(item.getMember().getUserName());
            holder.time.setText(item.getTime());
            holder.tag.setText(item.getNode().getName());
            holder.title.setText(item.getTitle());
            holder.comment.setText("评论" + item.getReplies());
        }

        @Override
        public int getItemCount() {
            return PreConditions.isEmpty(data) ? 0 : data.size();
        }

    }

}
