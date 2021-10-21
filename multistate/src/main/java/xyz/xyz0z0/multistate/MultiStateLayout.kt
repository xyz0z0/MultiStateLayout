package xyz.xyz0z0.multistate

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.DrawableRes

class MultiStateLayout : FrameLayout {

    private lateinit var mLayoutInflater: LayoutInflater
    private lateinit var mCurrentShowingView: View
    private lateinit var mContentView: View
    private lateinit var mProgressView: View
    private lateinit var mTipView: View

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }


    private fun initView(context: Context) {
        mLayoutInflater = LayoutInflater.from(context)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount != 1) {
            throw EmptyContentException(MultiStateLayout::class.java)
        }
        mContentView = getChildAt(0)
        mCurrentShowingView = mContentView
    }

    fun showContent() {
        if (mCurrentShowingView === mContentView) {
            return
        }
        mCurrentShowingView.visibility = INVISIBLE
        mContentView.visibility = VISIBLE
        mCurrentShowingView = mContentView
    }

    @JvmOverloads
    fun showLoading(loadingTip: String? = null) {
        if (!this::mProgressView.isInitialized) {
            mProgressView = mLayoutInflater.inflate(R.layout.multi_state_progress_view, this, false)
            val progressBar = mProgressView.findViewById<ProgressBar>(R.id.progress_wheel)
            val progressContentView = mProgressView.findViewById<View>(R.id.progress_content)
            addView(mProgressView)
        }
        if (loadingTip != null && loadingTip.isNotEmpty()) {
            val tvLoading = mProgressView.findViewById<TextView>(R.id.tv_loading)
            tvLoading.text = loadingTip
        }
        mCurrentShowingView.visibility = INVISIBLE
        mProgressView.visibility = VISIBLE
        mCurrentShowingView = mProgressView
    }

    fun showTip(errorTip: String?) {
        showTip(NO_ID, errorTip, null, null)
    }

    fun showTip(text: String?, retry: String?, listener: OnClickListener?) {
        showTip(NO_ID, text, retry, listener)
    }

    fun showTip(@DrawableRes resId: Int, text: String?, retry: String?, listener: OnClickListener?) {
        if (!this::mTipView.isInitialized) {
            mTipView = mLayoutInflater.inflate(R.layout.multi_state_tip_view, this, false)
            addView(mTipView)
        }
        val ivTip = mTipView.findViewById<ImageView>(R.id.ivTip)
        val tvTip = mTipView.findViewById<TextView>(R.id.tvTip)
        val tvRetryTip = mTipView.findViewById<TextView>(R.id.tvRetryTip)
        if (resId != NO_ID) {
            ivTip.visibility = VISIBLE
            ivTip.setImageResource(resId)
        } else {
            ivTip.visibility = GONE
        }
        if (text != null) {
            tvTip.visibility = VISIBLE
            tvTip.text = text
        } else {
            tvTip.visibility = GONE
        }
        if (retry != null) {
            tvRetryTip.visibility = VISIBLE
            tvRetryTip.text = retry
        } else {
            tvRetryTip.visibility = GONE
        }
        if (listener != null) {
            mTipView.setOnClickListener(listener)
        }
        mCurrentShowingView.visibility = INVISIBLE
        mTipView.visibility = VISIBLE
        mCurrentShowingView = mTipView
    }
}