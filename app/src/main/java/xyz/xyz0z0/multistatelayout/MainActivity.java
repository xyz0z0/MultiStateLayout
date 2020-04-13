package xyz.xyz0z0.multistatelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import xyz.xyz0z0.multistate.MultiStateLayout;

public class MainActivity extends AppCompatActivity {

    private MultiStateLayout mslMain;
    private Button btnShowContent;
    private Button btnShowEmpty;
    private Button btnShowEmptyRetry;
    private Button btnShowError;
    private Button btnShowErrorRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowContent = findViewById(R.id.btn_show_content);
        btnShowEmpty = findViewById(R.id.btn_show_empty);
        btnShowEmptyRetry = findViewById(R.id.btn_show_empty_retry);
        btnShowError = findViewById(R.id.btn_show_error);
        btnShowErrorRetry = findViewById(R.id.btn_show_error_retry);
        mslMain = findViewById(R.id.msl_main);
        btnShowContent.setOnClickListener(v -> {
            mslMain.showContent();
        });
        btnShowError.setOnClickListener(v -> mslMain.showError());
        btnShowErrorRetry.setOnClickListener(v -> mslMain
            .showError(R.drawable.ic_launcher_foreground, getString(R.string.error), "点击重试", new View.OnClickListener() {
                @Override public void onClick(View view) {
                    Toast.makeText(view.getContext(), "点击重试了1", Toast.LENGTH_SHORT).show();
                }
            }));
        btnShowEmpty.setOnClickListener(v -> {
            mslMain.showEmpty();
        });
        btnShowEmptyRetry.setOnClickListener(v -> {
            mslMain.showEmpty(R.drawable.ic_launcher_foreground, getString(R.string.empty), "点击重试", new View.OnClickListener() {
                @Override public void onClick(View view) {
                    Toast.makeText(view.getContext(), "点击重试了2", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
