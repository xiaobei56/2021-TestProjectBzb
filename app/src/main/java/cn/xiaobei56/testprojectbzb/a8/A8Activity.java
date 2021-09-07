package cn.xiaobei56.testprojectbzb.a8;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cn.xiaobei56.testprojectbzb.R;
import cn.xiaobei56.testprojectbzb.useable.b_checkbox.BButton;
import cn.xiaobei56.testprojectbzb.useable.b_checkbox.BCheckBox;


/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb.a8
 * Author: bzb
 * Date: 2021/9/2
 * Description:
 */
public class A8Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a8_activity);
        BButton bButton = findViewById(R.id.bbtn);
        bButton.setText("测试是");
        BCheckBox bCheckBox = findViewById(R.id.b_check_box);
        List<BButton.ValueBean> beans = new ArrayList<>();
        beans.add(new BButton.ValueBean("1", "测试", BButton.STATUS_SELECTED));
        beans.add(new BButton.ValueBean("2", "测试2"));
        beans.add(new BButton.ValueBean("3", "测试3"));
        bCheckBox.setItemSpace(20);
        bCheckBox.updateData(beans);
        bCheckBox.setCheckBoxClickListener(new BCheckBox.BCheckBoxClickListener() {
            @Override
            public void onBOnClick(BButton.ValueBean valueBean, BButton bButton) {
                Log.i("1", "onBOnClick: ");
            }
        });
    }
}
