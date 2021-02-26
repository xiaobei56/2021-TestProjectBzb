package cn.xiaobei56.testprojectbzb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: app.carmodel
 * Package Name: com.bitauto.invoice.widget
 * Author: bzb
 * Date: 2021/2/7
 * Description:
 */

/**
 * Project Name: app.carmodel
 * Package Name: com.bitauto.invoice.widget
 * Author: bzb
 * Date: 2021/2/7
 * Description:
 */
public class FlowView extends ViewGroup {
    static OnItemClickedListener mListener;
    private final Context mContext;
    int mItemMargin = 8;
    int mViewLines;
    List<List<TextView>> viewShows = new ArrayList<>();
    //    暂时使用默认属性
    private int mTextSize = 12;
    private int mTextColor = getResources().getColor(R.color.invoice_c_646464);
    private int mLineMargin = 12;
    private List<TextView> mItemViews;

    public FlowView(Context context) {
        this(context, null);
    }

    public FlowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initViewAndData();
    }

    private void initViewAndData() {
        mockData();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int totalWidth = 0, totalHeight = 0; //总宽度和总高度
        int currentLineTotalWidth = 0;//当前行的总宽度
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            //此处必须要用measureChildWithMargins,不能用measureChild,否则如果子view的宽度超过了父控件的宽度，layout_margin的右间距或者layout_marginRight会失效
            measureChildWithMargins(childAt, widthMeasureSpec, 0, heightMeasureSpec, 0);//测量每个子view的宽高(测量之后才可以拿到子view的宽高)
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int childWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (childWidth + currentLineTotalWidth > widthSize) { //超过了总宽度,开始折行
                totalWidth = Math.max(currentLineTotalWidth, childWidth);//获取这一行的总宽度
                totalHeight = totalHeight + childHeight; //加上这一行的高度
                currentLineTotalWidth = childWidth;//重置当前行的宽
            } else {  //不用折行
                totalHeight = Math.max(totalHeight, childHeight);//对比取最大高度，防止子view少时没有设置总高度
                currentLineTotalWidth = currentLineTotalWidth + childWidth;//累加宽度
                totalWidth = Math.max(currentLineTotalWidth, childWidth);//获取这一行的总宽度
            }
        }
        //固定宽高则就使用默认的测量值即可，否则使用计算出来的宽和高
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : totalWidth,
                heightMode == MeasureSpec.EXACTLY ? heightSize : totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int top = 0, left = 0;
        int currentLineHeight = 0;
        int currentLineWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int childWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (currentLineWidth + childWidth > getMeasuredWidth()) { //换行
                top = top + currentLineHeight;
                left = 0;
                currentLineWidth = childWidth;
                currentLineHeight = childHeight;
            } else {
                currentLineWidth = currentLineWidth + childWidth;
                currentLineHeight = Math.max(childHeight, currentLineHeight);
            }
            int realLeft = left + layoutParams.leftMargin;
            int realTop = top + layoutParams.topMargin;
            int realRight = realLeft + childAt.getMeasuredWidth();
            int realBottom = realTop + childAt.getMeasuredHeight();
            childAt.layout(realLeft, realTop, realRight, realBottom);
            left = left + childWidth;
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(this.getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    private void mockData() {
        if (!BuildConfig.DEBUG) {
            return;
        }
        List dataMock = new ArrayList();
        dataMock.add("000");
        dataMock.add("12c测试c测试c测试c测试c测试");
        dataMock.add("23c测试c测试c测试c测试c测试c测试c测试");
        dataMock.add("45c测试");
        dataMock.add("ac测试c测试");
        dataMock.add("bc测试c测试c测试");
        dataMock.add("cc测试c测试");
        dataMock.add("dc测试c测试c测试c测试");
        dataMock.add("dddc测试");
        dataMock.add("ccccc测试");
        dataMock.add("eeeec测试");
        dataMock.add("0000000");

        setData(dataMock);

    }


    public void setData(List<String> data) {
        if (data == null) {
            return;
        }
        if (mItemViews == null) {
            mItemViews = new ArrayList<>();
        } else {
            mItemViews.clear();
        }
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                MyTextView textView = new MyTextView(mContext);
                MarginLayoutParams params = new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, Utils.dp2px(mItemMargin), Utils.dp2px(mLineMargin));
                textView.setBackground(getResources().getDrawable(R.drawable.invoice_flow_item_bg));
                textView.setTextColor(mTextColor);
                textView.setTextSize(mTextSize);
                textView.setText(data.get(i));
                textView.setLayoutParams(params);
                textView.setEnabled(true);
                textView.setClickable(true);
                addView(textView);
            }
        }
        invalidate();
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        mListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void onItemClicked(MyTextView view);
    }

     static class MyTextView extends androidx.appcompat.widget.AppCompatTextView {

        public MyTextView(@NonNull Context context) {
            super(context);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            super.dispatchTouchEvent(event);
            return true;
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                FlowView.mListener.onItemClicked(this);
            }
            return super.onTouchEvent(event);
        }
    }
}