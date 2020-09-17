package xyz.xyz0z0.multistatelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import xyz.xyz0z0.multistate.MultiStateLayout;

public class MainActivity extends AppCompatActivity {

    private MultiStateLayout mslMain;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button btnShowContent;
    private Button btnShowError;
    private Button btnShowErrorRetry;
    private Button btnLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        btnLoading = findViewById(R.id.btnLoading);
        btnShowContent = findViewById(R.id.btn_show_content);
        btnShowError = findViewById(R.id.btn_show_error);
        btnShowErrorRetry = findViewById(R.id.btn_show_error_retry);
        mslMain = findViewById(R.id.mslMain);
        mslMain.showLoading("test");
        btnLoading.setOnClickListener(v -> {
            mslMain.showLoading();
        });
        btnShowContent.setOnClickListener(v -> {
            swipeRefreshLayout.setRefreshing(false);
            mslMain.showContent();
        });
        btnShowError.setOnClickListener(v ->
            mslMain.showTip("获取数据错误，请退出重试"));
        btnShowErrorRetry.setOnClickListener(v -> mslMain
            .showTip(R.drawable.ic_information_18dp, "页面加载失败", "点击重试", new View.OnClickListener() {
                @Override public void onClick(View view) {
                    Toast.makeText(view.getContext(), "点击重试了1", Toast.LENGTH_SHORT).show();
                }
            }));

    }
}
