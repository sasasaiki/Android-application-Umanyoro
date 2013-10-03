package jp.saiki.newsnake;


import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;

import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SnakeMain extends Activity implements OnClickListener {
	SnakeView mMainDrawArea;
	FrameLayout fl;

	Button buttonAdd;// test
	Button stopB;

	boolean stopF = false;

	TextView scoreText;
	TextView gameOverText;

	/**************** score管理 ***************************/
	SharedPreferences pref;
	Editor e;

	/*********************************/

	int MODE = 0;
	final int GAME = 0;
	final int GAMEOVER = -1;

	/*************************** 走る画像設定用の変数 ************************/
	int RUN;

	SoundPool soundPool;
	int sound1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_snake_main);

		buttonAdd = (Button) findViewById(R.id.buttonAdd);
		stopB = (Button) findViewById(R.id.stop);
		scoreText = (TextView) findViewById(R.id.gamescore);
		gameOverText = (TextView) findViewById(R.id.textgameove);

		pref = getSharedPreferences("Score", MODE_PRIVATE);
		e = pref.edit();
		fl = (FrameLayout) findViewById(R.id.FrameLayout1);

		// 作成したMainSurfaceViewクラスをインスタンス化
		RUN = pref.getInt("run", 0);
		System.out.println("RUN===" + RUN);

		if (pref.getInt("MODE", 0) == 1) {
			mMainDrawArea = new SnakeViewHard(getApplicationContext(),
					scoreText, gameOverText, this);
		} else if (pref.getInt("MODE", 0) == 2) {
			mMainDrawArea = new SnakeViewEasy(getApplicationContext(),
					scoreText, gameOverText, this);
		} else {
			mMainDrawArea = new SnakeView(getApplicationContext(), scoreText,
					gameOverText, this);
		}
		fl.addView(mMainDrawArea);

		buttonEvents();

		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		sound1 = soundPool.load(getApplicationContext(), R.raw.pope, 1);



	}
	void se(){
		soundPool.play(sound1, 1.0f, 1.0f, 1, 0, 1.0f);
	}

	private void buttonEvents() {

		buttonAdd.setOnClickListener(this);
		stopB.setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch (v.getId()) {

		case R.id.buttonAdd:
			mMainDrawArea.ItemHitFlag = true;
			break;
		case R.id.stop:
			if (stopF) {
				mMainDrawArea.hn = new Handler();
				mMainDrawArea.run();
				stopF = false;
			} else {
				mMainDrawArea.hn = null;

				stopF = true;
			}

		default:

			break;
		}

	}

	public void onWindowFocusChanged(boolean hasFocus) {// ここで幅と高さを取得

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if (MODE == GAME) {// /ゲーム中のタッチ処理
			if (!stopF) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (event.getX() < mMainDrawArea.screenWidth / 2) {
						mMainDrawArea.heroList.get(0).left();
					} else {
						mMainDrawArea.heroList.get(0).right();
					}
					return true;
				}
			}
		}

		if (MODE == GAMEOVER) {// 負けたときのタッチ処理
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				finish();

				Intent i = new Intent(getApplicationContext(), SnakeEnd.class);
				startActivity(i);
				return true;
			}

		}
		return super.onTouchEvent(event);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	void scoreSave(int score) {
		e.putInt("nowScore", score);
		e.commit();
		System.out.println("score=" + score);

	}

	@Override
	public void finish() {
		// TODO 自動生成されたメソッド・スタブ

		endView();

		super.finish();
	}

	void endView() {
		// for(int i=0;i<mMainDrawArea.MAXFRAM;i++){
		// mMainDrawArea.image[i].recycle();
		// }
		// mMainDrawArea.BI=null;

		soundPool.release();

		mMainDrawArea.bgm.stop();
		mMainDrawArea.bgm.reset();	 //次郎
		mMainDrawArea.bgm.release();





		mMainDrawArea.segameover.reset();	 //次郎
		mMainDrawArea.segameover.release();

		for (int i = 0; i < 8; i++) {
			mMainDrawArea.heroList.get(0).image[i].recycle();
			mMainDrawArea.heroList.get(0).image[i] = null;
		}
		// for(Hero hero:mMainDrawArea.heroList){
		/*
		 * for(int i=0;i<8;i++){ hero.image[i].recycle(); hero.image[i]=null; }
		 */
		/*
		 * for(int i=0;i<2;i++){ hero.drawImage[i].recycle();
		 * hero.drawImage[i]=null;
		 *
		 * }
		 */

		// hero=null;
		// }
		// for(int i=0;i<8;i++){
		// mMainDrawArea.image[i].recycle();
		// }
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {

				// mMainDrawArea.item[i].image[j].recycle();
				mMainDrawArea.item[i].image[j] = null;
			}
			mMainDrawArea.item[i] = null;
		}
		mMainDrawArea.BI.Umaimage = null;

		mMainDrawArea.itaiyo.recycle();

		if (pref.getInt("MODE", 0) == 1) {
			for (int i = 0; i < 4; i++) {
				mMainDrawArea.imageEnemy[i].recycle();
				mMainDrawArea.imageEnemy[i]=null;
			}

		}

		mMainDrawArea = null;





		fl.removeAllViews();


	}
	  public boolean onKeyDown(int keyCode, KeyEvent event) {
		    if(keyCode==KeyEvent.KEYCODE_BACK){

				finish();
				Intent intent=new Intent(getApplicationContext(),SnakeStart.class);
				startActivity(intent);

		      return true;
		    }
		    return false;
		  }

}


