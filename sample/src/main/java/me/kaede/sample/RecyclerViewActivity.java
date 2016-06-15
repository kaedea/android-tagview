package me.kaede.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends ActionBarActivity {
    private String[] mDataSet = new String[]{"TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT",
            "TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        MyAdapter adapter = new MyAdapter(mDataSet);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataSet;

        public MyAdapter(String[] dataSet) {
            this.mDataSet = dataSet;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;
            public TagView tagView;
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder vh;
            if (viewType == 0) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_text, parent, false);
                vh = new ViewHolder(v);
                vh.textView = (TextView) v.findViewById(R.id.tv);
            } else {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tag, parent, false);
                vh = new ViewHolder(v);
                vh.tagView = (TagView) v.findViewById(R.id.tagview_item_list);
            }
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder.textView != null){
                holder.textView.setText(mDataSet[position]);
            } else if (holder.tagView != null){
                holder.tagView.removeAllTags();
                List<Tag> tagList = new ArrayList<>();
                tagList.add(new Tag("FOOTER 1"));
                tagList.add(new Tag("FOOTER 2"));
                holder.tagView.addTags(tagList);
            }
        }

        @Override
        public int getItemCount() {
            return mDataSet.length + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mDataSet.length)
                return 0;
            else {
                return 1;
            }
        }
    }
}
