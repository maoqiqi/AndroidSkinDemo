package com.codearms.maoqiqi.skin.demo;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * 演示RecyclerView换肤属性
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/11/6 16:45
 */
public class RecyclerViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("RecyclerView");
        setSupportActionBar(toolbar);

        String[] data = getResources().getStringArray(R.array.schools);
        List<String> list = Arrays.asList(data);

        RecyclerView recyclerView1 = findViewById(R.id.recycler_view_1);
        RecyclerView recyclerView2 = findViewById(R.id.recycler_view_2);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(new MyAdapter(list));
        recyclerView2.setAdapter(new MyAdapter(list));
    }

    private final class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<String> data;

        MyAdapter(List<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_text, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tv.setText(data.get(position));
            holder.tv.setBackgroundResource(R.drawable.bg_item_1);
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }
    }

    private final class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}