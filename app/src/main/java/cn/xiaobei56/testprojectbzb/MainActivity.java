package cn.xiaobei56.testprojectbzb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.xiaobei56.testprojectbzb.activity.A1RvInRvActivity;
import cn.xiaobei56.testprojectbzb.activity.A3ImageViewTypeActivity;
import cn.xiaobei56.testprojectbzb.activity.A4AutoEllipsizeTextView;
import cn.xiaobei56.testprojectbzb.activity.A4V2;
import cn.xiaobei56.testprojectbzb.activity.A5FlowViewActivity;
import cn.xiaobei56.testprojectbzb.activity.A6StatusBarActivity;
import cn.xiaobei56.testprojectbzb.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onActionClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_1:
                startActivity(new Intent(this, A1RvInRvActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(this, A3ImageViewTypeActivity.class));
                break;

            case R.id.btn_4:
                startActivity(new Intent(this, A4AutoEllipsizeTextView.class));
                break;
            case R.id.btn_4_v2:
                startActivity(new Intent(this, A4V2.class));
                break;

            case R.id.btn_5:
                startActivity(new Intent(this, A5FlowViewActivity.class));
                break;
            case R.id.btn_6:
                startActivity(new Intent(this, A6StatusBarActivity.class));
                break;


        }
    }
}