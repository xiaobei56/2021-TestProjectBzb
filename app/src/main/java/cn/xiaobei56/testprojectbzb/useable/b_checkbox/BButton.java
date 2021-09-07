package cn.xiaobei56.testprojectbzb.useable.b_checkbox;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import cn.xiaobei56.testprojectbzb.R;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb.useable.b_checkbox
 * Author: bzb
 * Date: 2021/9/2
 * Description:
 */
public class BButton extends FrameLayout {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_PRESSED = 1;
    public static final int STATUS_UNABLE = 2;
    public static final int STATUS_SELECTED = 3;
    public static final int STATUS_UNSELECTED = 4;
    static int colorNorm = R.color.personcenter_c_222222;
    static int colorSelected = R.color.personcenter_c_3377ff;
    int bgDrawableSelected = R.drawable.personcenter_bbutton_back_selected;
    int bgDrawableNormal = R.drawable.personcenter_bbutton_back_normal;
    int paddingL = 12, paddingR = 12, paddingT = 4, paddingB = 4; //padding值
    ValueBean valueBean = new ValueBean();
    ClickListener mListener;
    private FrameLayout mFl;
    private TextView mTv;
    private ImageView mIv;//后期使用在添加  - 定义 imageVIew 属性类  width height
    private Map<Integer, BButtonParams> mParamsMap = new HashMap<>();
    // 按钮图片
    private BtnDrawableParams btnDrawableParams = new BtnDrawableParams();
    @BButtonStatus
    private int currentStatus;

    public BButton(Context context) {
        this(context, null);
    }

    public BButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTv = new TextView(context);

        if (mIv == null) {//后期使用drawable在添加
            BButtonParams p = mParamsMap.get(STATUS_NORMAL) == null ? getDefaultParam() : mParamsMap.get(STATUS_NORMAL);
            setPadding(paddingL, paddingT, paddingR, paddingB);
            mTv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            mTv.setGravity(Gravity.CENTER);
            updateView(p);
            addView(mTv);
        }
        setClickable(true);
        setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onBOnClick(valueBean, BButton.this);
            }
        });

    }

    public static Map<Integer, BButtonParams> getDefaultParamMap() {
        Map<Integer, BButtonParams> map = new HashMap<>();


        BButtonParams paramsNormal = new BButtonParams();
        paramsNormal.textSize = 12;
        paramsNormal.textColorNorRes = colorNorm;
        paramsNormal.typeface = Typeface.NORMAL;
        paramsNormal.bgDrawableNorRes = R.drawable.personcenter_bbutton_back_normal;
        map.put(STATUS_NORMAL, paramsNormal);

        BButtonParams paramsSelected = new BButtonParams();
        paramsSelected.textSize = 12;
        paramsSelected.textColorNorRes = colorSelected;
        paramsSelected.typeface = Typeface.BOLD;
        paramsSelected.bgDrawableNorRes = R.drawable.personcenter_bbutton_back_selected;
        map.put(STATUS_SELECTED, paramsSelected);

        return map;
    }

    private void updateView(BButtonParams p) {
        setBackgroundResource(p.bgDrawableNorRes);
        mTv.setTextColor(getResources().getColor(p.textColorNorRes));
        mTv.setTextSize(p.textSize);
        mTv.setTypeface(Typeface.defaultFromStyle(p.typeface));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int mViewGroupWidth = getMeasuredWidth();  //当前ViewGroup的总宽度

        int mPainterPosX = l;  //当前绘图光标横坐标位置
        int mPainterPosY = t;  //当前绘图光标纵坐标位置

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();
            if (mPainterPosX + width > mViewGroupWidth) {
                mPainterPosX = l;
                mPainterPosY += height;
            }
            childView.layout(mPainterPosX + (getMeasuredWidth() - paddingR - paddingL - width) / 2 + paddingL,
                    mPainterPosY + (getMeasuredHeight() - paddingB - paddingT) / 2 - height / 2 + paddingT,
                    mPainterPosX + (getMeasuredWidth() - paddingR - paddingL) / 2 + width / 2 + paddingL,
                    mPainterPosY + (getMeasuredHeight() - paddingB - paddingT) / 2 + height / 2 + paddingT);
            mPainterPosX += width;
        }
    }

    public void setText(CharSequence text) {
        if (mTv != null && text != null) {
            valueBean.name = text;
            mTv.setText(text);
        }

    }

    public void setValueBean(ValueBean valueBean) {
        this.valueBean = valueBean;
        setText(valueBean.name);
        changeStatus(valueBean.status);
    }

    public void changeStatus(@BButtonStatus int status) {
        this.currentStatus = status;
        updateView();
    }

    private void updateStatus(@BButtonStatus int status) {
        switch (status) {
            case STATUS_NORMAL:
                updateView();
        }
    }

    private void updateView() {
        BButtonParams currentParam;
        currentParam = mParamsMap.get(currentStatus);
        if (currentParam == null) {
            currentParam = getDefaultParam();
        }
        updateView(currentParam);

    }

    public BButtonParams getDefaultParam() {
        BButtonParams paramsNormal = new BButtonParams();
        paramsNormal.textSize = 12;
        paramsNormal.textColorNorRes = colorNorm;
        paramsNormal.typeface = Typeface.NORMAL;
        paramsNormal.bgDrawableNorRes = R.drawable.personcenter_bbutton_back_normal;
        return paramsNormal;
    }

    /**
     * 创建 属性
     *
     * @param text
     * @param textSize
     * @param color
     * @param typeface
     * @param bgDrawable
     * @param btnDrawable
     * @return
     */
    public BButtonParams createParams(String text, int textSize, @ColorInt int color, int typeface, @DrawableRes int bgDrawable, @DrawableRes int btnDrawable) {
        BButtonParams params = new BButtonParams();
        params.text = text;
        params.textSize = textSize;
        params.textColorNorRes = color;
        params.typeface = typeface;
        params.bgDrawableNorRes = bgDrawable;
        params.btnDrawableRes = btnDrawable;
        return params;
    }

    public BButton createBButton(Map<Integer, BButtonParams> paramsMap) {
        initStatus(paramsMap);
        return this;
    }

    public void initStatus(Map<Integer, BButtonParams> paramsMap) {
        this.mParamsMap = paramsMap;
        invalidate();
    }

    public void setBButtonClickListener(ClickListener listener) {
        this.mListener = listener;
    }

    public enum BButtonDrawablePos {
        POS_L(0),
        POS_T(1),
        POS_R(2),
        POS_B(3);
        int pos;

        BButtonDrawablePos(int pos) {
            this.pos = pos;
        }
    }

    /**
     * 按钮状态
     */
    @IntDef({STATUS_NORMAL, STATUS_PRESSED, STATUS_SELECTED, STATUS_UNABLE, STATUS_UNSELECTED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BButtonStatus {
    }

    public interface ClickListener {
        void onBOnClick(ValueBean valueBean, BButton bButton);
    }

    public static class ValueBean {
        public CharSequence name;
        public String key;
        @BButtonStatus
        public int status;

        public ValueBean() {
        }

        public ValueBean(String key, CharSequence name) {
            this.name = name;
            this.key = key;
        }

        public ValueBean(String key, CharSequence name, int status) {
            this.name = name;
            this.key = key;
            this.status = status;
        }
    }

    public static class BButtonParams {
        public String text;
        @ColorRes
        public int textColorNorRes;
        public int textSize;
        public int typeface;
        @DrawableRes
        public int bgDrawableNorRes;
        @DrawableRes
        public int btnDrawableRes;
    }

    public static class BtnDrawableParams {
        @DrawableRes
        public int btnDrawableRes;
        public int drawablePadding;
        public int btnDrawableWidth;
        public int btnDrawableHeight;
    }

    class Builder {

    }
}
