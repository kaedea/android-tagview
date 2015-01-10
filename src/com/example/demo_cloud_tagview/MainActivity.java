package com.example.demo_cloud_tagview;

import me.kaede.tagview.OnTagClickListener;
import me.kaede.tagview.OnTagDeleteListener;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
//		TagView tagView = new TagView(this, null);
		TagView tagView = (TagView) this.findViewById(R.id.tagview);
		//tagView.setDeletable(true);
		Tag tag1=new Tag( "TAG1");
		tagView.add(tag1);
		Tag tag2=new Tag( "TAG2 TAG2");
		tagView.add(tag2);
		Tag tag3=new Tag( "TAG3 TAG3 TAG3");
		tagView.add(tag3);
		Tag tag4=new Tag( "TAG4 TAG4 TAG4 TAG4 TAG4 TAG4");
		tagView.add(tag4);
		
		Tag tag5=new Tag( "TAG5");
		tag5.text ="I'm TAG5";
		tag5.layoutColor=Color.parseColor("#BE5CD6");
		tag5.layoutColorPress=Color.parseColor("#4EC6E4");
		tagView.add(tag5);
		
		Tag tag6 =new Tag(123, "TAG6", "#D52B2B");
		tagView.add(tag6);
		
		Tag tag7 =new Tag(123, "TAG7", "#48EBA9");
		tag7.tagTextSize=30;
		tagView.add(tag7);
		
		Tag tag8 =new Tag(123, "TAG8", "#947EB5");
		tag8.tagTextSize=20;
		tag8.radius=100;
		tagView.add(tag8);
		
		Tag tag9 =new Tag(123, "TAG9", "#D65CBE");
		tag9.isDeletable=false;
		tag9.radius=0;
		tag9.tagTextSize=40;
		tagView.add(tag9);
		
		Tag tag10 =new Tag(123, "TAG10", "#48EBA9");
		tag10.tagTextSize=4;
		tagView.add(tag10);
		tagView.setOnTagClickListener(new OnTagClickListener() {
			
			@Override
			public void onTagSelected(Tag tag, int position) {
				Toast.makeText(MainActivity.this, "click tag id="+tag.id+" position="+position, Toast.LENGTH_SHORT).show();
			}
		});
		tagView.setOnTagDeleteListener(new OnTagDeleteListener() {
			
			@Override
			public void onTagDeleted(Tag tag, int position) {
				Toast.makeText(MainActivity.this, "delete tag id="+tag.id+" position="+position, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	
}
