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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowContent = findViewById(R.id.btn_show_content);
        btnShowEmpty = findViewById(R.id.btn_show_empty);
        mslMain = findViewById(R.id.msl_main);
        btnShowContent.setOnClickListener(v -> {
            mslMain.showContent();
        });
        btnShowEmpty.setOnClickListener(v -> {
            mslMain.showEmpty("没有更多数据", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "重试", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
