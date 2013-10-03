package jp.saiki.newsnake;



import mediba.ad.sdk.android.openx.MasAdView;
import jp.basicinc.gamefeat.android.sdk.controller.GameFeatAppController;
import jp.basicinc.gamefeat.android.sdk.controller.GameFeatIconAdLoader;
import jp.maru.mrd.IconLoader;
import jp.maru.mrd.IconCell;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SnakeStart extends Activity implements OnClickListener {
	Intent intentStandard,bookIntent,explainIntent;;
	Button standardB;
	Button bookB;
	Button adB;
	Button hard;
	Button easy;
	Button explain;
	Button lusy;
	LinearLayout rootLayout;
	LinearLayout adll,adll2;
	ImageView IMview;
	boolean flagTheme=true;

	SharedPreferences pref;
	Editor e;

	final int STANDARD = 0;
	final int HARD = 1;
	final int EASY = 2;


	//GameFeatIconAdLoader[] myIconAdLoader=new GameFeatIconAdLoader[5];
	GameFeatIconAdLoader myIconAdLoader=new GameFeatIconAdLoader();

	MasAdView mad;

	IconLoader<Integer> myIconLoader;

	MediaPlayer bgm;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.snake_start);

		/** アイコンの更新間隔を10秒に設定 **/
		myIconAdLoader.setRefreshInterval(10);
		/** アイコン下のテキスト色を設定 **/
		myIconAdLoader.setIconTextColor(Color.rgb(0, 0, 0));

		mad = (MasAdView)findViewById(R.id.startad);
	    mad.setSid("ce53dad02031bb707b5d84305244dbf4626a8def5d90b919");
	    mad.start();

	    if (myIconLoader == null) {
	        // 1. IconLoader を生成。 __MEDIA_CODE__ はアプリを表すコードです。
	        myIconLoader = new IconLoader<Integer>("ast00897cpt77t2745ou", this);
	        ((IconCell)findViewById(R.id.startasuta1)).addToIconLoader(myIconLoader);
	        ((IconCell)findViewById(R.id.startasuta2)).addToIconLoader(myIconLoader);
	        ((IconCell)findViewById(R.id.startasuta3)).addToIconLoader(myIconLoader);
	        ((IconCell)findViewById(R.id.startasuta4)).addToIconLoader(myIconLoader);// 2.
	        myIconLoader.setRefreshInterval(10);
	    }
	    onResume();

		/*((GameFeatIconView)findViewById(R.id.sgf1)).addLoader(myIconAdLoader);
		myIconAdLoader.loadAd(SnakeStart.this);*/

		intentStandard=new Intent(getApplicationContext(),SnakeMain.class);
		bookIntent=new Intent(getApplicationContext(),SnakeBook.class);
		explainIntent=new Intent(getApplicationContext(),Explain.class);

		standardB=(Button)findViewById(R.id.standard);
		bookB=(Button)findViewById(R.id.book);
		adB=(Button)findViewById(R.id.ad);
		lusy=(Button)findViewById(R.id.lucytan);
	    rootLayout=(LinearLayout)findViewById(R.id.root);
	    hard=(Button)findViewById(R.id.tecnikal);
	    easy=(Button)findViewById(R.id.easy);
	    explain=(Button)findViewById(R.id.manual);
	    IMview=(ImageView)findViewById(R.id.themeimage);
	    adll=(LinearLayout)findViewById(R.id.startadbotton);
	    adll2=(LinearLayout)findViewById(R.id.startadtop);


	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*   for(int i=0;i<5;i++){
	    	myIconAdLoader[i] = new GameFeatIconAdLoader();
	    	   /** アイコンの更新間隔を10秒に設定 **/
	    	//   myIconAdLoader[i].setRefreshInterval(10);
	    	   /** アイコン下のテキスト色を設定 **/
	    	//   myIconAdLoader[i].setIconTextColor(Color.rgb(0, 0, 0));
	 /*   }

	    ((GameFeatIconView)findViewById(R.id.sgf1)).addLoader(myIconAdLoader[0]);
	    ((GameFeatIconView)findViewById(R.id.sgf2)).addLoader(myIconAdLoader[1]);
	    ((GameFeatIconView)findViewById(R.id.sgf3)).addLoader(myIconAdLoader[2]);
	    ((GameFeatIconView)findViewById(R.id.sgf4)).addLoader(myIconAdLoader[3]);
	    ((GameFeatIconView)findViewById(R.id.sgf5)).addLoader(myIconAdLoader[4]);

	    onResume();*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    //モード変更用
	    pref=getSharedPreferences("Score",MODE_PRIVATE);
		e=pref.edit();

		/////////////////////////////////////////////////////////////


		/////////////////////////////////////////////////////////////

		standardB.setOnClickListener(this);
		bookB.setOnClickListener(this);
		adB.setOnClickListener(this);
		hard.setOnClickListener(this);
		easy.setOnClickListener(this);
		explain.setOnClickListener(this);
		lusy.setOnClickListener(this);



		 bgm = MediaPlayer.create(getBaseContext(), R.raw.opening);
		 bgm.setLooping(true);

	}

	 @Override public void onResume() {
		    super.onResume();
		    myIconLoader.startLoading();// 4.
		  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		 switch(v.getId()) {
		    case R.id.standard:
		    	finish();
		    	e.putInt("MODE", STANDARD);
		    	e.commit();
		    	startActivity(intentStandard);
				//finish();
				break;
		    case R.id.book:
		    	finish();
		    	startActivity(bookIntent);
		    	//finish();
		    	break;
		    case R.id.ad:
		    	GameFeatAppController.show(SnakeStart.this);
		    	//finish();
		    	break;
		    case R.id.tecnikal:
		    	finish();
		    	e.putInt("MODE", HARD);
		    	e.commit();
		    	startActivity(intentStandard);
		    	break;
		    case R.id.easy:
		    	finish();
		    	e.putInt("MODE", EASY);
		    	e.commit();
		    	startActivity(intentStandard);
		    	break;
		    case R.id.manual:
		    	finish();
		    	startActivity(explainIntent);
		    	break;
		    case R.id.lucytan:
		    	Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.loverlaboratory.SlaughterGirl");

		    	//アクションはACTION_VIEW指定し、URIを設定します。
		    	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		    	startActivity(intent);
	        }

	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if(event.getAction() == MotionEvent.ACTION_DOWN&&flagTheme){
			 bgm.start();
			rootLayout.setVisibility(View.VISIBLE);
			adll.setVisibility(View.VISIBLE);
			adll2.setVisibility(View.VISIBLE);
			IMview.setVisibility(View.GONE);
			IMview.setImageDrawable(null);


		}
		return super.onTouchEvent(event);
	}

	  public boolean onKeyDown(int keyCode, KeyEvent event) {
		    if(keyCode==KeyEvent.KEYCODE_BACK){

		    	GameFeatAppController.showPopupAd(SnakeStart.this);

		    	finish();
		      return true;
		    }
		    return false;
		  }

	  @Override
	public void finish() {
		// TODO 自動生成されたメソッド・スタブ
		  if(bgm.isPlaying()){
			  bgm.stop();
			  bgm.reset();	 //次郎
			  bgm.release();	//三郎
		  }
		super.finish();
	}

}
