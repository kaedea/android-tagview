package me.kaede.sample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import me.kaede.tagview.OnTagClickListener;
import me.kaede.tagview.OnTagDeleteListener;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;


public class MainActivity extends ActionBarActivity {
    View.OnClickListener listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_add:
                    String string ="ADD A TAG";
                    if (editText.getText().toString()!=null&&!editText.getText().toString().equals(""))
                        string=editText.getText().toString();
                    Tag tag = new Tag(string);
                    tagView.addTag(tag);
                    break;
                case R.id.tv_start_activity:
                    startActivityForResult(new Intent(MainActivity.this,SecondActivity.class),0);
                    break;
            }
        }
    };
    private TagView tagView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_add).setOnClickListener(listener);
        findViewById(R.id.tv_start_activity).setOnClickListener(listener);
        editText = (EditText) findViewById(R.id.edit_tag);

        tagView = (TagView) this.findViewById(R.id.tagview);
        //SET LISTENER
        tagView.setOnTagClickListener(new OnTagClickListener() {

            @Override
            public void onTagClick(Tag tag, int position) {
                Toast.makeText(MainActivity.this, "click tag id=" + tag.id + " position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        tagView.setOnTagDeleteListener(new OnTagDeleteListener() {

            @Override
            public void onTagDeleted(Tag tag, int position) {
                Toast.makeText(MainActivity.this, "delete tag id=" + tag.id + " position=" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //ADD TAG
        String[] tags = getResources().getStringArray(R.array.continents);
        tagView.addTags(tags);


		/*Tag tag1=new Tag( "TAG1");
        tagView.addTag(tag1);
		Tag tag2=new Tag( "TAG2 TAG2");
		tagView.addTag(tag2);
		Tag tag3=new Tag( "TAG3 TAG3 TAG3");
		tagView.addTag(tag3);
		Tag tag4=new Tag( "TAG4 TAG4 TAG4 TAG4 TAG4 TAG4");
		tagView.addTag(tag4);

		Tag tag5=new Tag( "TAG5");
		tag5.text ="I'm TAG5";
		tag5.layoutColor= Color.parseColor("#BE5CD6");
		tag5.layoutColorPress=Color.parseColor("#4EC6E4");
		tagView.addTag(tag5);

		Tag tag6 =new Tag(123, "TAG6", "#D52B2B");
		tagView.addTag(tag6);

		Tag tag7 =new Tag(123, "TAG7", "#48EBA9");
		tag7.tagTextSize=30;
		tagView.addTag(tag7);

		Tag tag8 =new Tag(123, "TAG8", "#947EB5");
		tag8.tagTextSize=20;
		tag8.radius=100;
		tagView.addTag(tag8);

		Tag tag9 =new Tag(123, "TAG9", "#D65CBE");
		tag9.isDeletable=false;
		tag9.radius=0;
		tag9.tagTextSize=40;
		tagView.addTag(tag9);

		Tag tag10 =new Tag(123, "TAG10", "#48EBA9");
		tag10.tagTextSize=4;
		tagView.addTag(tag10);

		Tag tag11 = new Tag("TAG11", "#FFC125");
		tagView.addTag(tag11);

		Tag tag12 = new Tag("TAG122", "#FFC125");
		tagView.addTag(tag12);

		Tag tag13 = new Tag("TAG1333", "#FFC125");
		tagView.addTag(tag13);

		Tag tag14 = new Tag("TAG14444", "#FFC125");
		tagView.addTag(tag14);

		Tag tag15 = new Tag("TAG155555", "#FFC125");
		tagView.addTag(tag15);

		Tag tag16 = new Tag("TAG1666666", "#FFC125");
		tagView.addTag(tag16);




		//You can also create TagView by
		TagView tagview2 = new TagView(this);
		tagview2.setLineMargin(20f);//dp
		tagview2.setTagMargin(20f);
		tagview2.setTextPaddingLeft(20f);
		tagview2.setTextPaddingTop(20f);
		tagview2.setTextPaddingRight(20f);
		tagview2.setTexPaddingBottom(20f);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Tag tag = new Tag("ADD AFTER ACTIVITY RESULT");
            tagView.addTag(tag);
        }
    }
}
