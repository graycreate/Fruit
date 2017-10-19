package me.ghui.example_android.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;
import me.ghui.example_android.R;
import me.ghui.example_android.general.IViewLoading;
import me.ghui.example_android.util.RxUtils;

public abstract class BaseFragment extends Fragment implements IViewLoading {

    private ViewGroup mRootView;
    private View mLoadingView;

    /**
     * bind a layout resID to the content of this page
     *
     * @return
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = LayoutInflater.from(getContext()).inflate(attachLayoutRes(), container, false);
        mRootView = new FrameLayout(getContext());
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRootView.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void showLoading() {
        if (mLoadingView != null && mLoadingView.getVisibility() == View.VISIBLE) return;
        onCreateLoadingView();
        mLoadingView.setVisibility(View.VISIBLE);
    }

    protected View onCreateLoadingView() {
        if (mLoadingView == null) {
            mLoadingView = LayoutInflater.from(getActivity()).inflate(R.layout.base_loading_view, mRootView, false);
            mRootView.addView(mLoadingView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
            mLoadingView.bringToFront();
        }
        return mLoadingView;
    }

    @Override
    public void hideLoading() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.INVISIBLE);
        }
    }

    protected <K> ObservableTransformer<K, K> rx() {
        return RxUtils.rxFragment(this);
    }

}
