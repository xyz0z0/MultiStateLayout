package xyz.xyz0z0.multistate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MultiStateLayout extends FrameLayout {

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
        if (progressView == null) {
            progressView = inflater.inflate(R.layout.view_progress, this, false);
            ProgressBar progressBar = progressView.findViewById(R.id.progress_wheel);
            View progressContentView = progressView.findViewById(R.id.progress_content);
            addView(progressView);
        }
        currentShowingView.setVisibility(INVISIBLE);
        progressView.setVisibility(VISIBLE);
        currentShowingView = progressView;
    }

    public void showError() {
        showError(null);
    }

    public void showError(OnClickListener listener) {
        if (errorView == null) {
            errorView = inflater.inflate(R.layout.view_error, this, false);
            View errorContentView = errorView.findViewById(R.id.error_content);
            TextView errorTextView = errorView.findViewById(R.id.errorTextView);
            addView(errorView);
        }
        if (listener != null) {
            errorView.setOnClickListener(listener);
        }
        currentShowingView.setVisibility(INVISIBLE);
        errorView.setVisibility(VISIBLE);
        currentShowingView = errorView;
    }

    public void showEmpty(String tip, OnClickListener listener) {
        if (emptyView == null) {
            emptyView = inflater.inflate(R.layout.view_empty, this, false);
            View emptyContentView = emptyView.findViewById(R.id.empty_content);
            TextView emptyTextView = emptyView.findViewById(R.id.tv_empty);
            addView(emptyView);
        }
        if (tip != null && tip.length() > 0) {
            TextView emptyTextView = emptyView.findViewById(R.id.tv_empty);
            emptyTextView.setText(tip);
        }
        if (listener != null) {
            emptyView.findViewById(R.id.tv_retry).setOnClickListener(listener);
        } else {
            emptyView.findViewById(R.id.tv_retry).setVisibility(INVISIBLE);
        }
        currentShowingView.setVisibility(INVISIBLE);
        emptyView.setVisibility(VISIBLE);
        currentShowingView = emptyView;
    }
}