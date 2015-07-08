package me.kaede.sample;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ListView;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		ListView listView = (ListView) this.findViewById(R.id.list);
		MyAdapter adapter = new MyAdapter();
		listView.setAdapter(adapter);
		List<ListData> datas = new ArrayList<>();
		for (int i = 0;i<=50;i++){
			datas.add(new ListData("List Item " + i));
		}
		adapter.setDatas(datas);
	}

	public class ListData {
		public String text;

		public ListData(String text) {
			this.text = text;
		}
	}

	public class ViewHolder{
		public TagView tagView;
	}

	public class MyAdapter extends BaseAdapter{
		List<ListData> datas = new ArrayList<>();

		public void setDatas(List<ListData> datas){
			this.datas = datas;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return datas.size();
		}

		@Override
		public ListData getItem(int position) {
			return datas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null){
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tag, parent, false);
				holder = new ViewHolder();
				holder.tagView = (TagView) convertView.findViewById(R.id.tagview_item_list);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			if (holder!=null){
				holder.tagView.removeAllTags();
				Tag tag;
				tag = new Tag(getItem(position).text);
				tag.layoutColor =  Color.parseColor("#F06292");
				holder.tagView.addTag(tag);
				tag = new Tag(getItem(position).text);
				tag.layoutColor =  Color.parseColor("#90CAF9");
				holder.tagView.addTag(tag);
				tag = new Tag(getItem(position).text);
				tag.layoutColor =  Color.parseColor("#80DEEA");
				holder.tagView.addTag(tag);
			}
			return convertView;
		}
	}

}
