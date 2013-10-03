package jp.saiki.newsnake;


import jp.basicinc.gamefeat.android.sdk.controller.GameFeatAppController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SnakeBook extends Activity implements OnClickListener {

	Button Back,Front,runChoice;;

	/**************評価画像の入ったクラス*********************/
	BookImage BI;




	int page=0;
	TextView text;
	TextView name;
	ImageView im;



	SharedPreferences pref;
	Editor e;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.snake_book);

		im=(ImageView) findViewById(R.id.imageView1);
		text=(TextView)findViewById(R.id.introduce);
		name=(TextView)findViewById(R.id.umaname);

		BI=new BookImage(getApplicationContext());

		pref=getSharedPreferences("Score",MODE_PRIVATE);
		e=pref.edit();






		setButton();
		initImage();



	}

	void initImage(){
	     setImageAndText();
	}


	void setButton(){
		Front=(Button)findViewById(R.id.next);
		Back=(Button)findViewById(R.id.back);
		runChoice=(Button)findViewById(R.id.runchoice);
		Front.setOnClickListener(this);
		Back.setOnClickListener(this);
		runChoice.setOnClickListener(this);
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.snake_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch (v.getId()) {
		case R.id.next:
			page++;
			if(page==BI.UMAMAXNUM){
				page=0;
			}
			setImageAndText();
			break;
		case R.id.back:
			page--;
			if(page==-1){
				page=BI.UMAMAXNUM-1;
			}
			setImageAndText();
			break;
		case R.id.runchoice:
			e.putInt("run", page);
			e.commit();
			System.out.println(pref.getInt("run", 0));
			break;

		default:
			break;
		}

	}

	void setImageAndText(){
		im.setImageDrawable(null);
		if(pref.getInt(""+page, 0)==1){
			im.setImageBitmap(BI.getImage(page));
			text.setText(BI.umaText[page]);
			name.setText(BI.name[page]);
			if(page==BI.KAWAIIUMA||page==BI.TIGER||page==BI.OUJI||page==BI.DEBUUMA){
				runChoice.setVisibility(View.VISIBLE);
			}
			else{
				runChoice.setVisibility(View.GONE);
			}
		}
		else{
			runChoice.setVisibility(View.GONE);
			im.setImageBitmap(BI.getHatena(page));
			text.setText(BI.hintText[page]);
			name.setText("？？？？？？？");
		}
	}
	@Override
	public void finish() {
		// TODO 自動生成されたメソッド・スタブ
	//	for(int i=0;i<BI.UMAMAXNUM;i++){
			BI.Umaimage.recycle();
			BI.Umaimage=null;
	//	}
		im.setImageDrawable(null);
		Intent intent=new Intent(getApplicationContext(),SnakeStart.class);
		startActivity(intent);
		System.out.println("fifnish");
		super.finish();
	}







}
