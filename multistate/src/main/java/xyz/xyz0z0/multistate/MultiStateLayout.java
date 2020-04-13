package xyz.xyz0z0.multistate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MultiStateLayout extends FrameLayout {

    public static final int ID_NULL = 0;
    private LayoutInflater inflater;
    private View currentShowingView;
    private View contentView;
    private View progressView;
    private View errorView;
    private View emptyView;

    public MultiStateLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MultiStateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(@NonNull Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1) {
            throw new EmptyContentException(MultiStateLayout.class);
        }
        contentView = getChildAt(0);
        currentShowingView = contentView;
    }

    public void showContent() {
        if (currentShowingView == contentView) {
            return;
        }
        currentShowingView.setVisibility(INVISIBLE);
        contentView.setVisibility(VISIBLE);
        currentShowingView = contentView;
    }

    public void showLoading() {
        showLoading(null);
    }

    public void showLoading(String loadingTip) {
        if (progressView == null) {
            progressView = inflater.inflate(R.layout.view_progress, this, false);
            ProgressBar progressBar = progressView.findViewById(R.id.progress_wheel);
            View progressContentView = progressView.findViewById(R.id.progress_content);
            addView(progressView);
        }
        if (loadingTip != null && loadingTip.length() > 0) {
            TextView tvLoading = emptyView.findViewById(R.id.tv_loading);
            tvLoading.setText(loadingTip);
        }
        currentShowingView.setVisibility(INVISIBLE);
        progressView.setVisibility(VISIBLE);
        currentShowingView = progressView;
    }

    public void showError() {
        showError(R.drawable.ic_error_18dp, getContext().getString(R.string.error), null, null);
    }

    public void showError(@DrawableRes int resId, String error, String retry, OnClickListener listener) {
        if (errorView == null) {
            errorView = inflater.inflate(R.layout.view_error, this, false);
            addView(errorView);
        }
        ImageView ivError = errorView.findViewById(R.id.iv_error);
        TextView tvError = errorView.findViewById(R.id.tv_error);
        TextView tvErrorRetry = errorView.findViewById(R.id.tv_error_retry);
        if (resId != ID_NULL) {
            ivError.setVisibility(VISIBLE);
            ivError.setImageResource(resId);
        } else {
            ivError.setVisibility(GONE);
        }
        if (error != null) {
            tvError.setVisibility(VISIBLE);
            tvError.setText(error);
        } else {
            tvError.setVisibility(GONE);
        }
        if (retry != null) {
            tvErrorRetry.setVisibility(VISIBLE);
            tvErrorRetry.setText(retry);
        } else {
            tvErrorRetry.setVisibility(GONE);
        }
        if (listener != null) {
            tvErrorRetry.setOnClickListener(listener);
        }
        currentShowingView.setVisibility(INVISIBLE);
        errorView.setVisibility(VISIBLE);
        currentShowingView = errorView;
    }

    public void showEmpty() {
        showError(R.drawable.ic_empty_18dp, getContext().getString(R.string.empty), null, null);
    }

    public void showEmpty(@DrawableRes int resId, String empty, String retry, OnClickListener listener) {
        if (emptyView == null) {
            emptyView = inflater.inflate(R.layout.view_empty, this, false);
            addView(emptyView);
        }
        ImageView ivEmpty = emptyView.findViewById(R.id.iv_empty);
        TextView tvEmpty = emptyView.findViewById(R.id.tv_empty);
        TextView tvEmptyRetry = emptyView.findViewById(R.id.tv_empty_retry);
        if (resId != ID_NULL) {
            ivEmpty.setVisibility(VISIBLE);
            ivEmpty.setImageResource(resId);
        } else {
            ivEmpty.setVisibility(GONE);
        }
        if (empty != null) {
            tvEmpty.setVisibility(VISIBLE);
            tvEmpty.setText(empty);
        } else {
            tvEmpty.setVisibility(GONE);
        }
        if (retry != null) {
            tvEmptyRetry.setVisibility(VISIBLE);
            tvEmptyRetry.setText(retry);
        } else {
            tvEmptyRetry.setVisibility(GONE);
        }
        if (listener != null) {
            tvEmptyRetry.setOnClickListener(listener);
        }
        currentShowingView.setVisibility(INVISIBLE);
        emptyView.setVisibility(VISIBLE);
        currentShowingView = emptyView;
    }
}