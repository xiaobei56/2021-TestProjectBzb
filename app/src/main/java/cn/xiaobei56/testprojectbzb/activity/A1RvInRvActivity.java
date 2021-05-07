package cn.xiaobei56.testprojectbzb.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.xiaobei56.testprojectbzb.R;

/**
 * Project Name: TestProjectBzb
 * Package Name: cn.xiaobei56.testprojectbzb
 * Author: bzb
 * Date: 2021/2/24
 * Description:
 */
public class A1RvInRvActivity extends Activity {
    RecyclerView mRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_in_rv);
        mRv = findViewById(R.id.rv_main);
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MainAdapter mainAdapter=new MainAdapter(mockData());
        mRv.setAdapter(mainAdapter);


    }

    private List<String> mockData() {
        List<String> stringList=new ArrayList<>();
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        stringList.add("测试");
        return stringList;
    }

    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
        ItemAdapter mItemAdapter;

        Context mContext;
        List<String> mData;

        public void setmData(List<String> mData) {
            this.mData = mData;
        }

        public MainAdapter(List<String> mData) {
            this.mData = mData;
        }
        private List<String> mockData() {
            List<String> stringList=new ArrayList<>();
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            stringList.add("测试");
            return stringList;
        }
        @NonNull
        @Override
        public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            mContext = parent.getContext();
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_rv_in_rv, parent, false);
            return new MainHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MainHolder holder, int position) {
            if (mItemAdapter == null) {
                mItemAdapter = new ItemAdapter(mockData());
            }
            holder.mRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            holder.mRecycler.setAdapter(mItemAdapter);
            Log.i("--BZB-A1RvInRvActivity ",mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MainHolder extends RecyclerView.ViewHolder {
            RecyclerView mRecycler;

            public MainHolder(@NonNull View itemView) {
                super(itemView);
                mRecycler = itemView.findViewById(R.id.rv_main_item);
            }
        }

        /**
         * item 的adapter
         */
        public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
            List<String> mData=new ArrayList<>();

            public ItemAdapter(List<String> mData) {
                this.mData = mData;
            }

            @NonNull
            @Override
            public ItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item_main_rv_in_rv, parent, false);
                return new ItemAdapter.ItemHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ItemAdapter.ItemHolder holder, int position) {
                holder.mTv.setText("Item" + mData.get(position));
            }

            @Override
            public int getItemCount() {
                return mData.size();
            }

            class ItemHolder extends RecyclerView.ViewHolder {
                TextView mTv;

                public ItemHolder(@NonNull View itemView) {
                    super(itemView);
                    mTv = itemView.findViewById(R.id.tv);
                }
            }

        }


    }

}
