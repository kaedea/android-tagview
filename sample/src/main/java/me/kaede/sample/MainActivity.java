package me.kaede.sample;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import me.kaede.tagview.OnTagClickListener;
import me.kaede.tagview.OnTagDeleteListener;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;

import java.util.Random;


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
                    int r = random.nextInt(2);
                    if (r==0)tag.isDeletable=true;
                    r = random.nextInt(5);
                    tag.layoutColor =Color.parseColor(MainActivity.this.getResources().getStringArray(R.array.colors)[r]);
                    tagView.addTag(tag);
                    break;
                case R.id.tv_start_activity:
                    startActivityForResult(new Intent(MainActivity.this,SecondActivity.class),0);
                    break;
	            case R.id.tv_list_activity:
		            startActivity(new Intent(MainActivity.this,ListViewActivity.class));
		            break;
            }
        }
    };
    private TagView tagView;
    private EditText editText;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_add).setOnClickListener(listener);
        findViewById(R.id.tv_start_activity).setOnClickListener(listener);
        findViewById(R.id.tv_list_activity).setOnClickListener(listener);
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
        random = new Random();

	    String[] colors = this.getResources().getStringArray(R.array.colors);

	    for (int i = 1;i<colors.length;i++){
		    Tag tag = new Tag("Colorful Text");
		    tag.tagTextColor = Color.parseColor(colors[i]);
		    tagView.addTag(tag);
	    }

	    for (String item : colors){
		    Tag tag = new Tag("Colorful Background");
		    tag.layoutColor = Color.parseColor(item);
		    tagView.addTag(tag);
	    }

	    Tag tag = new Tag("Border");
	    tag.layoutBorderSize = 1f;
	    tagView.addTag(tag);

	    tag = new Tag("Border");
	    tag.layoutBorderSize = 2f;
	    tag.layoutBorderColor = Color.parseColor(colors[1]);
	    tagView.addTag(tag);

	    tag = new Tag("Border");
	    tag.layoutBorderSize = 3f;
	    tag.layoutBorderColor = Color.parseColor(colors[3]);
	    tagView.addTag(tag);

	    tag = new Tag("Round Corner");
	    tag.radius = 0f;
	    tagView.addTag(tag);

	    tag = new Tag("Round Corner");
	    tag.radius = 20f;
	    tagView.addTag(tag);

	    tag = new Tag("Round Corner");
	    tag.radius = 60f;
	    tagView.addTag(tag);

	    tag = new Tag("Deletable");
	    tag.isDeletable=true;
	    tagView.addTag(tag);

	    tag = new Tag("Custom Background");
	    tag.tagTextColor = Color.parseColor(colors[0]);
	    tag.background = this.getResources().getDrawable(R.drawable.bg_tag);
	    tagView.addTag(tag);

	    tag = new Tag("Detail Tag");
	    tag.tagTextColor = Color.parseColor("#FFFFFF");
	    tag.layoutColor =  Color.parseColor("#DDDDDD");
	    tag.layoutColorPress = Color.parseColor("#555555");
	    //or tag.background = this.getResources().getDrawable(R.drawable.custom_bg);
	    tag.radius = 20f;
	    tag.tagTextSize = 14f;
	    tag.layoutBorderSize = 1f;
	    tag.layoutBorderColor = Color.parseColor("#FFFFFF");
	    tag.isDeletable = true;
	    tagView.addTag(tag);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Tag tag = new Tag("ADD AFTER ACTIVITY RESULT");
            tagView.addTag(tag);
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if (id == R.id.action_github) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("https://github.com/kaedea/"));
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
