package cn.xiaobei56.testprojectbzb.activity;

import cn.xiaobei56.testprojectbzb.R;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb
 * Author: bzb
 * Date: 2021/2/24
 * Description:
 */
public class A2ConstrainLayoutActivity extends BActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    //    暂时：对超长TextView 挤占问题不好处理。同一行 多个TextView 建议使用LinearLayout
    @Override
    protected int getLayoutId() {
        return R.layout.a2_activity;
    }
}
