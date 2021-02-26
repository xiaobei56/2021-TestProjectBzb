package cn.xiaobei56.testprojectbzb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowView flowView = findViewById(R.id.flow_view);
        flowView.setOnItemClickedListener(new FlowView.OnItemClickedListener() {
            @Override
            public void onItemClicked(FlowView.MyTextView view) {
                Toast.makeText(MainActivity.this, view.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onActionClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btn_1:
                startActivity(new Intent(this, A1RvInRvActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(this, A3ImageViewTypeActivity.class));
                break;
        }
    }
}