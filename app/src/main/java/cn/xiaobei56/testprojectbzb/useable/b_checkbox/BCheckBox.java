package cn.xiaobei56.testprojectbzb.useable.b_checkbox;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import cn.xiaobei56.testprojectbzb.R;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb.useable.b_checkbox
 * Author: bzb
 * Date: 2021/9/2
 * Description:
 */
public class BCheckBox extends LinearLayout {
    public Map<Integer, BButton.BButtonParams> mParamsMap = BButton.getDefaultParamMap();
    @LayoutRes
    int itemView;
    Context mContext;
    BCheckBoxAdapter bCheckBoxAdapter;
    List<BButton.ValueBean> valueBeans;
    BButton bButton;
    BCheckBoxClickListener bCheckBoxClickListener;
    int mItemSpace = 8;
    int checkBoxType;//0单选 1 多选

    public BCheckBox(@NonNull @NotNull Context context) {
        this(context, null);
    }

    public BCheckBox(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BCheckBox(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        bCheckBoxAdapter = new BCheckBoxAdapter();
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setAdapter(bCheckBoxAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(mItemSpace));
        addView(recyclerView);
    }

    public void setBButtonParamsMap(Map<Integer, BButton.BButtonParams> mParamsMap) {
        this.mParamsMap = mParamsMap;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() > 0) {
            View view = getChildAt(0);
            if (view != null) {
                view.layout(l, t + measuredHeight / 2 - view.getMeasuredHeight() / 2, l + view.getMeasuredWidth(), measuredHeight / 2 + view.getMeasuredHeight() / 2);
            }
        }
    }

    public void setItemSpace(int mItemSpace) {
        this.mItemSpace = mItemSpace;
    }

    public void setItemLayout(@LayoutRes int itemLayout) {
        this.itemView = itemLayout;
    }

    public void initBButtonParam(Map<Integer, BButton.BButtonParams> paramsMap) {
        this.mParamsMap = paramsMap;
        invalidate();
    }

    public void updateData(List<BButton.ValueBean> data) {
        this.valueBeans = data;
        if (bCheckBoxAdapter != null) {
            bCheckBoxAdapter.updateData(data);
        }

    }

    private BButton createBButton(Map<Integer, BButton.BButtonParams> paramsMap) {
        BButton bButton = new BButton(mContext);
        return bButton.createBButton(paramsMap);
    }

    public void setCheckBoxClickListener(BCheckBoxClickListener bCheckBoxClickListener) {
        this.bCheckBoxClickListener = bCheckBoxClickListener;
    }

    public interface BCheckBoxClickListener {
        void onBOnClick(BButton.ValueBean valueBean, BButton bButton);
    }

    class BViewHolder extends RecyclerView.ViewHolder {
        BButton bButton;

        public BViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
//            bButton = (BButton) itemView;
            bButton = itemView.findViewById(R.id.bbtn);
            if (mParamsMap != null) {
                bButton.initStatus(mParamsMap);
            }
        }

    }

    class BCheckBoxAdapter extends RecyclerView.Adapter<BViewHolder> {
        List<BButton.ValueBean> valueBeans;

        public BCheckBoxAdapter() {
        }

        public BCheckBoxAdapter(List<BButton.ValueBean> valueBeans) {
            this.valueBeans = valueBeans;
        }

        public void updateData(List<BButton.ValueBean> data) {
            this.valueBeans = data;
            notifyDataSetChanged();
        }

        @NonNull
        @NotNull
        @Override
        public BViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            mContext = parent.getContext();
            bButton = new BButton(mContext);
            if (mParamsMap != null) {
                bButton.initStatus(mParamsMap);
            }

            View view = LayoutInflater.from(mContext).inflate(R.layout.bcheckbox_item, parent, false);
            return new BViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull BCheckBox.BViewHolder holder, int position) {
            BButton.ValueBean bean = valueBeans.get(position);
            holder.bButton.setValueBean(valueBeans.get(position));
            holder.bButton.setBButtonClickListener((valueBean, v) -> {
                for (int i = 0; i < valueBeans.size(); i++) {
                    valueBeans.get(i).status = valueBeans.get(i).key.endsWith(bean.key) ? BButton.STATUS_SELECTED : BButton.STATUS_NORMAL;
                }
                updateData(valueBeans);
                Toast.makeText(mContext, "点击", Toast.LENGTH_SHORT).show();
                if (bCheckBoxClickListener != null) {
                    bCheckBoxClickListener.onBOnClick(valueBean, v);
                }
            });
        }

        @Override
        public int getItemCount() {
            return valueBeans == null ? 0 : valueBeans.size();
        }
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = 0;
            outRect.right = space;
            outRect.bottom = 0;

            // Add top margin only for the first item to avoid double space between items
//            if (parent.getChildAdapterPosition(view) == 0)
//                outRect.left = space;
        }
    }
}
