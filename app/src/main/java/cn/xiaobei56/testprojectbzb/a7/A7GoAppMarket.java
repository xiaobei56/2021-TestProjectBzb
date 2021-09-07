package cn.xiaobei56.testprojectbzb.a7;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import cn.xiaobei56.testprojectbzb.R;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb.a7
 * Author: bzb
 * Date: 2021/8/30
 * Description:
 */
public class A7GoAppMarket extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a7_item_view);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);

                    intent.setData(Uri.parse("market://details?id=com.lkk.travel"));//其中的com.lkk.travel是自己app的包名

                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
