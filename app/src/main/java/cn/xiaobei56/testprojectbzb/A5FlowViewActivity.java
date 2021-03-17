package cn.xiaobei56.testprojectbzb;

import android.widget.TextView;
import android.widget.Toast;

import cn.xiaobei56.testprojectbzb.a5.FlowView;
import cn.xiaobei56.testprojectbzb.a5.FlowView.MyTextView;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb
 * Author: bzb
 * Date: 2021/3/15
 * Description:
 */
public class A5FlowViewActivity extends BActivity {


    @Override
    protected void initView() {
        FlowView flowView = findViewById(R.id.flow_view);
        flowView.setOnItemClickedListener(new FlowView.OnItemClickedListener() {
            @Override
            public void onItemClicked(MyTextView view) {
                Toast.makeText(A5FlowViewActivity.this, view.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.a5_activity;
    }
}
