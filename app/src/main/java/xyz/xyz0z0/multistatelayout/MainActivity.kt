package xyz.xyz0z0.multistatelayout

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import xyz.xyz0z0.multistate.MultiStateLayout

class MainActivity : AppCompatActivity() {
    private lateinit var mslMain: MultiStateLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var btnShowContent: Button
    private lateinit var btnShowError: Button
    private lateinit var btnShowErrorRetry: Button
    private lateinit var btnLoading: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findView()
        mslMain.showLoading("test")
        btnLoading.setOnClickListener { mslMain.showLoading() }
        btnShowContent.setOnClickListener {
            swipeRefreshLayout.isRefreshing = false
            mslMain.showContent()
        }
        btnShowError.setOnClickListener { mslMain.showTip("获取数据错误，请退出重试") }
        btnShowErrorRetry.setOnClickListener {
            mslMain
                .showTip(R.drawable.ic_information_18dp, "页面加载失败", "点击重试", View.OnClickListener { view -> Toast.makeText(view.context, "点击重试了1", Toast.LENGTH_SHORT).show() })
        }
    }

    private fun findView() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        btnLoading = findViewById(R.id.btnLoading)
        btnShowContent = findViewById(R.id.btn_show_content)
        btnShowError = findViewById(R.id.btn_show_error)
        btnShowErrorRetry = findViewById(R.id.btn_show_error_retry)
        mslMain = findViewById(R.id.mslMain)
    }
}