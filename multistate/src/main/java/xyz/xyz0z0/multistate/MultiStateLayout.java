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
    private View tipView;


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
            progressView = inflater.inflate(R.layout.multi_state_progress_view, this, false);
            ProgressBar progressBar = progressView.findViewById(R.id.progress_wheel);
            View progressContentView = progressView.findViewById(R.id.progress_content);
            addView(progressView);
        }
        if (loadingTip != null && loadingTip.length() > 0) {
            TextView tvLoading = progressView.findViewById(R.id.tv_loading);
            tvLoading.setText(loadingTip);
        }
        currentShowingView.setVisibility(INVISIBLE);
        progressView.setVisibility(VISIBLE);
        currentShowingView = progressView;
    }


    public void showTip(String errorTip) {
        showTip(ID_NULL, errorTip, null, null);
    }


    public void showTip(String text, String retry, OnClickListener listener) {
        showTip(ID_NULL, text, retry, listener);
    }


    public void showTip(@DrawableRes int resId, String text, String retry, OnClickListener listener) {
        if (tipView == null) {
            tipView = inflater.inflate(R.layout.multi_state_tip_view, this, false);
            addView(tipView);
        }
        ImageView ivTip = tipView.findViewById(R.id.ivTip);
        TextView tvTip = tipView.findViewById(R.id.tvTip);
        TextView tvRetryTip = tipView.findViewById(R.id.tvRetryTip);
        if (resId != ID_NULL) {
            ivTip.setVisibility(VISIBLE);
            ivTip.setImageResource(resId);
        } else {
            ivTip.setVisibility(GONE);
        }
        if (text != null) {
            tvTip.setVisibility(VISIBLE);
            tvTip.setText(text);
        } else {
            tvTip.setVisibility(GONE);
        }
        if (retry != null) {
            tvRetryTip.setVisibility(VISIBLE);
            tvRetryTip.setText(retry);
        } else {
            tvRetryTip.setVisibility(GONE);
        }
        if (listener != null) {
            tvRetryTip.setOnClickListener(listener);
        }
        currentShowingView.setVisibility(INVISIBLE);
        tipView.setVisibility(VISIBLE);
        currentShowingView = tipView;
    }

}