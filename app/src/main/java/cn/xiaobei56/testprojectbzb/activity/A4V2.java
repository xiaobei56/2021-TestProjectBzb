package cn.xiaobei56.testprojectbzb.activity;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import cn.xiaobei56.testprojectbzb.R;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb.activity
 * Author: bzb
 * Date: 2021/4/14
 * Description:
 */
public class A4V2 extends BActivity {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        TextView tv=findViewById(R.id.tv);
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder();
        String s="测试测试测试测试测试测试测试啊111";
        spannableStringBuilder.append(s);
//        spannableStringBuilder.setSpan(new SpanS);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.a4_v2_activity;
    }
}
