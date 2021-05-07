package cn.xiaobei56.testprojectbzb.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.xiaobei56.testprojectbzb.R;
import cn.xiaobei56.testprojectbzb.a4.AutoEllipsizeTextView;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb
 * Author: bzb
 * Date: 2021/3/15
 * Description:
 */
public class A4AutoEllipsizeTextView extends BActivity {
    AutoEllipsizeTextView mTv;
    TextView mNormalTv;

    @Override
    protected void initView() {
        mTv = findViewById(R.id.aeTv);
        mNormalTv = findViewById(R.id.tv_normal);
        mNormalTv.setLayoutParams(mTv.getLayoutParams());
    }
    @Override
    protected void initData() {

    }
    @Override
    protected int getLayoutId() {
        return R.layout.a4_activity;
    }


    public void onActionClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                mTv.setText(mTv.getText() + "你", 0);
                mNormalTv.setText(mTv.getText() + "你");
                break;
            case R.id.btn_add_blank:
                mTv.setText(mTv.getText() + " ", 0);
                mNormalTv.setText(mTv.getText() + " ");
                break;
            case R.id.btn_add_c:
                mTv.setText(mTv.getText() + "a", 0);
                mNormalTv.setText(mTv.getText() + "a");
                break;
            case R.id.btn_add_extra:
                mTv.setText(mTv.getText() + "+", 0);
                mNormalTv.setText(mTv.getText() + "+");
                break;

            case R.id.btn_expand_tv:
                ViewGroup.LayoutParams layoutParams = mTv.getLayoutParams();
                layoutParams.width = layoutParams.width + 10;
                mTv.setLayoutParams(layoutParams);
                mNormalTv.setLayoutParams(layoutParams);
                break;
            case R.id.btn_reduce_tv:
                ViewGroup.LayoutParams layoutParams2 = mTv.getLayoutParams();
                layoutParams2.width = layoutParams2.width - 5;
                mTv.setLayoutParams(layoutParams2);
                mNormalTv.setLayoutParams(layoutParams2);
                break;


            case R.id.btn_delete:
                String s = mTv.getText().toString();
                if (s.length() > 1) {
                    s = s.substring(0, s.length() - 1);
                    mTv.setText(s, 0);
                    mNormalTv.setText(s);
                }
                break;


            case R.id.btn_get_ellipsize:
                Toast.makeText(this, ((TextView) findViewById(R.id.tv_result)).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_get_text:
                Toast.makeText(this, mTv.getText(), Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
