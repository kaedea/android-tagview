package me.kaede.sample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;

public class RecyclerViewActivity extends ActionBarActivity {

    String[] dataSet = new String[]{"TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        MyAdapter adapter = new MyAdapter(dataSet);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private String[] dataSet;

        public MyAdapter(String[] myDataset) {
            dataSet = myDataset;
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
                TextView tv = (TextView) v.findViewById(R.id.tv);
                vh.textView = tv;
            } else {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tag, parent, false);
                vh = new ViewHolder(v);
                TagView tagView = (TagView) v.findViewById(R.id.tagview_item_list);
                vh.tagView = tagView;
            }
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder.tagView == null){
                holder.textView.setText(dataSet[position]);
            } else {
                holder.tagView.removeAllTags();
                holder.tagView.addTag(new Tag("FOOTER 1"));
                holder.tagView.addTag(new Tag("FOOTER 2"));
                /*if (position == 0){
                    holder.tagView.addTag(new Tag("HEADER 1"));
                    holder.tagView.addTag(new Tag("HEADER 2"));
                } else {
                    holder.tagView.addTag(new Tag("FOOTER 1"));
                    holder.tagView.addTag(new Tag("FOOTER 2"));
                }*/
            }
        }

        @Override
        public int getItemCount() {
            return dataSet.length + 1;
        }

        @Override
        public int getItemViewType(int position) {
            // if (position == 0) return 1;
            if (position < dataSet.length)
                return 0;
            else return 1;
        }
    }
}
