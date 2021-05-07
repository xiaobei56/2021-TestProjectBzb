package cn.xiaobei56.testprojectbzb.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.xiaobei56.testprojectbzb.R;
import cn.xiaobei56.testprojectbzb.a6.StatusBarUtil;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb.activity
 * Author: bzb
 * Date: 2021/4/14
 * Description:
 */
public class A6StatusBarActivity extends BActivity {
    RecyclerView mRlv;
    List<String> mData;
    private LinearLayout mLlBarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //5.0 全透明实现
//            //getWindow.setStatusBarColor(Color.TRANSPARENT)
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        StatusBarUtil.setTranslucent(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mRlv = findViewById(R.id.rlv);
        mLlBarView=findViewById(R.id.ll_bar_view);
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mLlBarView.getLayoutParams();
        p.setMargins(0, getStatusBarHeightNoL(), 0, 0);
        mLlBarView.setLayoutParams(p);

    }

    @Override
    protected void initData() {
        if(mData==null){
            mData=new ArrayList<>();
        }else {
            mData.clear();
        }
        for (int i = 0; i < 100; i++) {
            mData.add("模拟数据第：" + i + " 个");
        }
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(new MyAdapter(this, mData));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.a6_activity;
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        List<String> mData;
        private Context mContext;

        public MyAdapter(Context context, List<String> data) {
            this.mContext = context;
            if (data == null) {
                data = new ArrayList<>();
            }
            this.mData = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.a6_item_view, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.tv.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
    public int getStatusBarHeightNoL() {
        int statusBarHeight = 0;
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) {
            try {
                //获取status_bar_height资源的ID
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    //根据资源ID获取响应的尺寸值
                    statusBarHeight = getResources().getDimensionPixelSize(resourceId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

}
