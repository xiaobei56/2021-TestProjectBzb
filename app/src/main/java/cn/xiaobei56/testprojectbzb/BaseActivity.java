package cn.xiaobei56.testprojectbzb;

import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb
 * Author: bzb
 * Date: 2021/2/16
 * Description:
 */
public class BaseActivity extends AppCompatActivity {
    private boolean mCloseDoubleClick = true;
    private boolean isCloseDoubleClickCheck() {
        return mCloseDoubleClick;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (ev.getAction() == MotionEvent.ACTION_DOWN && DoubleClickUtil.isFastDoubleClick() && !isCloseDoubleClickCheck()) {
                return true;
            }
        } catch (Exception pE) {

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            if (event.getAction() == MotionEvent.ACTION_DOWN && DoubleClickUtil.isFastDoubleClick() && !isCloseDoubleClickCheck()) {
                return true;
            }
        } catch (Exception pE) {

        }
        return super.onTouchEvent(event);
    }
}
