package cn.xiaobei56.testprojectbzb.a4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb.a4
 * Author: bzb
 * Date: 2021/4/14
 * Description:
 */
public class AutoEllipsizeTextViewV2 extends FrameLayout {
    private static final int LAYOUT_TYPE_RELATIVELAYOUT = 2;
    private static final int LAYOUT_TYPE_FRAMELAYOUT = 4;
    private static final int LAYOUT_TYPE_CONSTRAINTLAYOUT = 3;
    private static final int LAYOUT_TYPE_LINEARLAYOUT = 1;
    private int parentViewType;

    public AutoEllipsizeTextViewV2(@NonNull Context context) {
        this(context, null);
    }

    public AutoEllipsizeTextViewV2(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoEllipsizeTextViewV2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                parentViewType = getParentViewType();
                initView();
            }
        }, 100);
    }

    private void initView() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        switch (parentViewType) {
            case LAYOUT_TYPE_LINEARLAYOUT:

                break;
            case LAYOUT_TYPE_RELATIVELAYOUT:
                break;
            case LAYOUT_TYPE_CONSTRAINTLAYOUT:
                break;
            case LAYOUT_TYPE_FRAMELAYOUT:
                break;
        }
    }

    private int getParentViewType() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup instanceof LinearLayout) {
            return LAYOUT_TYPE_LINEARLAYOUT;
        }
        if (viewGroup instanceof RelativeLayout) {
            return LAYOUT_TYPE_RELATIVELAYOUT;
        }
        if (viewGroup instanceof ConstraintLayout) {
            return LAYOUT_TYPE_CONSTRAINTLAYOUT;
        }
        if (viewGroup instanceof FrameLayout) {
            return LAYOUT_TYPE_FRAMELAYOUT;
        }
        return 0;
    }
}
