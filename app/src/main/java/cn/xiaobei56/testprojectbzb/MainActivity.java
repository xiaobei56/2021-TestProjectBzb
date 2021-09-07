package cn.xiaobei56.testprojectbzb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.xiaobei56.testprojectbzb.a7.A7GoAppMarket;
import cn.xiaobei56.testprojectbzb.a8.A8Activity;
import cn.xiaobei56.testprojectbzb.activity.A1RvInRvActivity;
import cn.xiaobei56.testprojectbzb.activity.A3ImageViewTypeActivity;
import cn.xiaobei56.testprojectbzb.activity.A4AutoEllipsizeTextView;
import cn.xiaobei56.testprojectbzb.activity.A4V2;
import cn.xiaobei56.testprojectbzb.activity.A5FlowViewActivity;
import cn.xiaobei56.testprojectbzb.activity.A6StatusBarActivity;
import cn.xiaobei56.testprojectbzb.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        View btnPop = findViewById(R.id.btn_pop);
        btnPop.setOnClickListener(v -> {
            popupWindow.showAsDropDown(btnPop);
        });
        popupWindow = new PopupWindow(this);
        ListView popList = (ListView) LayoutInflater.from(this).inflate(R.layout.main_pop_view, null);
        popList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return addFunData().size();
            }

            @Override
            public Object getItem(int position) {
                return addFunData().get(position);
            }

            @Override
            public long getItemId(int position) {
                return addFunData().get(position).id;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                @SuppressLint("ViewHolder") TextView view = (TextView) LayoutInflater.from(MainActivity.this).inflate(R.layout.main_pop_view_item, null, false);
                view.setText(addFunData().get(position).id + "-" + addFunData().get(position).name);
                return view;
            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(popList);

        popList.setOnItemClickListener((parent, view, position, id) -> {
            popupWindow.dismiss();
            funAction((int) id);
            FunBean fun = addFunData().get(position);
            Toast.makeText(MainActivity.this, fun.des, Toast.LENGTH_LONG).show();

        });
        popList.setOnItemLongClickListener((parent, view, position, id) -> {
            FunBean fun = addFunData().get(position);
            Toast.makeText(MainActivity.this, fun.des, Toast.LENGTH_LONG).show();
            return true;
        });
    }

    private List<FunBean> addFunData() {
        List<FunBean> list = new ArrayList<>();
        list.add(new FunBean(8, "BButton", ""));
        return list;
    }

    private void funAction(int id) {
        switch (id) {
            case 8: {
//                startActivity(TestActivity.openActivity(this));
                startActivity(new Intent(this, A8Activity.class));
                break;
            }
        }
    }

    public void onActionClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_1:
                startActivity(new Intent(this, A1RvInRvActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(this, A3ImageViewTypeActivity.class));
                break;

            case R.id.btn_4:
                startActivity(new Intent(this, A4AutoEllipsizeTextView.class));
                break;
            case R.id.btn_4_v2:
                startActivity(new Intent(this, A4V2.class));
                break;

            case R.id.btn_5:
                startActivity(new Intent(this, A5FlowViewActivity.class));
                break;
            case R.id.btn_6:
                startActivity(new Intent(this, A6StatusBarActivity.class));
                break;
            case R.id.btn_7:
                startActivity(new Intent(this, A7GoAppMarket.class));
                break;


        }
    }

    class FunBean {
        int id;
        String name; //标题
        String des;//描述

        public FunBean(int id, String name, String des) {
            this.id = id;
            this.name = name;
            this.des = des;
        }
    }
}