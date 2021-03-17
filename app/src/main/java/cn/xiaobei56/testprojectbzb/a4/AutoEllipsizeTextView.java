package cn.xiaobei56.testprojectbzb.a4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb
 * Author: bzb
 * Date: 2021/3/15
 * Description:
 */
public class AutoEllipsizeTextView extends AppCompatTextView {
    Paint mPaint;

    public AutoEllipsizeTextView(@NonNull Context context) {
        super(context);
        mPaint = new Paint();
    }

    public AutoEllipsizeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    public void setContent(CharSequence text) {
        this.setText(text, 0);
    }

    public void setText(CharSequence text, int size) {
        setText(text);
        if (size != 0) {
            setTextSize(size);
        }
        postInvalidate();
        // FIXME: 2021/3/16 添加drawable对象
//        Drawable[] drawables = getCompoundDrawables();
//        if (drawables != null) {
//            if (drawables[0] != null) {
//                Log.e("-bzb- drawable w", drawables[0].getMinimumWidth() + "");
//            }
//        }
        this.post(new Runnable() {
            @Override
            public void run() {

                int maxLines = getMaxLines();
                if (maxLines == 0) {
                    maxLines = 1;
                }
                int tvWidth = getWidth();
                int tvContentSize = tvWidth - getPaddingLeft() - getPaddingRight();
                tvWidth = tvContentSize;
                if (tvWidth <= 0) {
                    return;
                }
//                        添加 drawable 宽度
//                        (getCompoundDrawables()[0]).getMinimumWidth();
//                Log.e("-bzb- tvContentSize", tvContentSize + "");
                String text = getText().toString();
                String showText = text;

                float textLength = getTextSize(showText);
                String temp = "空";
                float tempLength = getTextSize(temp);
                if (textLength / maxLines > tvWidth) {
//                    比例裁剪字符串
                    float den = (float) (textLength / tvWidth - 1 - 0.75);
                    if (den > 0) {
                        showText = text.substring(0, (int) (text.length() * den));
                    }
                    while (getTextSize(showText) > tvWidth) {
                        showText = showText.substring(0, showText.length() - 1);
                    }
                    int ems = (int) Math.floor(getTextSize(showText) / tempLength * 1.0);
                    setMaxEms(ems);
                    setEllipsize(TextUtils.TruncateAt.END);
                    Log.e("-bzb- maxEms:", ems + "");
                }
                Log.w("-bzb-tvWith:", "" + tvWidth);
                Log.e("-bzb-textSize:", "" + textLength);
                if (text.length() == 1) {
                    Log.i("-bzb-", "一个文字textView的边" + text + "：" + (tvWidth - textLength) / 2);
                } else {
                    Log.i("-bzb-", text.length() + "文字textView的" + text + "：" + (tvWidth - textLength));
                }

            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private float getTextSize(String showText) {
        mPaint.setTextSize(getTextSize());
        return mPaint.measureText(showText);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

}
