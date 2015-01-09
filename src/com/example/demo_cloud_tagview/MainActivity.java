package com.example.demo_cloud_tagview;

import me.kaede.tagview.OnTagClickListener;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
//		TagView tagView = new TagView(this, null);
		TagView tagView = (TagView) this.findViewById(R.id.tagview);
		//tagView.setDeletable(true);
		Tag tag1=new Tag( "TAG1");
		tagView.add(tag1);
		Tag tag2=new Tag( "TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2 TAG2");
		tagView.add(tag2);
		Tag tag3=new Tag( "TAG3","#123456");
		tagView.add(tag3);
		//setContentView(tagView);
		
		tagView.setOnTagClickListener(new OnTagClickListener() {
			
			@Override
			public void onTagSelected(Tag tag, int position) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
			}
		});
		/*tagView.setOnTagDeleteListener(new OnTagDeleteListener() {
			
			@Override
			public void onTagDeleted(Tag tag, int position) {
				// TODO Auto-generated method stub
				
			}
		});*/
		
		//tagView.setCornerRadius(0);
	}

	
}
