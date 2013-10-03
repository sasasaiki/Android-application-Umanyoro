package jp.saiki.newsnake;

import mediba.ad.sdk.android.openx.MasAdView;
import jp.basicinc.gamefeat.android.sdk.controller.GameFeatAppController;
import jp.basicinc.gamefeat.ranking.android.sdk.controller.GFRankingController;
import jp.maru.mrd.IconCell;
import jp.maru.mrd.IconLoader;
import android.os.Bundle;
import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

@SuppressLint("CommitPrefEdits")
public class SnakeEnd extends Activity implements OnClickListener {
	SharedPreferences pref;
	Editor e, booke;
	int nowScore;
	int nowRank;

	Intent retryI;

	TextView t[] = new TextView[5];

	Button retryB, backB, rankingB, adB;

	/************** 評価画像 *********************/
	BookImage BI;
	boolean imageflag = true;
	int imageWidth;
	ImageView im;
	LinearLayout ImageFL;
	LinearLayout buttonLayout;
	LinearLayout adll;
	TextView name;
	TextView coment;
	TextView scoreText;

	/******************** MODE ****************************/
	final int STANDARD = 0;
	final int HARD = 1;
	final int EASY = 2;
	int MODE;

	MasAdView mad;
	IconLoader<Integer> myIconLoader;

	MediaPlayer bgm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.snake_end);

		bgm = MediaPlayer.create(getBaseContext(), R.raw.ahonoko);
		bgm.setLooping(true);
		bgm.start();

		im = (ImageView) findViewById(R.id.kekka);
		ImageFL = (LinearLayout) findViewById(R.id.imageFL2);
		buttonLayout = (LinearLayout) findViewById(R.id.endbuttonlayout);
		adll = (LinearLayout) findViewById(R.id.endadll);

		name = (TextView) findViewById(R.id.rankname);
		coment = (TextView) findViewById(R.id.rankcoment);
		scoreText = (TextView) findViewById(R.id.rankscore);

		BI = new BookImage(getApplicationContext());

		pref = getSharedPreferences("Score", MODE_PRIVATE);
		e = pref.edit();

		e.putInt("" + BI.STAFF, 1);
		e.commit();

		MODE = pref.getInt("MODE", 0);
		System.out.println("MODE== " + MODE);

		mad = (MasAdView) findViewById(R.id.endad);
		mad.setSid("ce53dad02031bb707b5d84305244dbf4626a8def5d90b919");
		mad.start();

		if (myIconLoader == null) {
			// 1. IconLoader を生成。 __MEDIA_CODE__ はアプリを表すコードです。
			myIconLoader = new IconLoader<Integer>("ast00897cpt77t2745ou", this);
			((IconCell) findViewById(R.id.endasuta1))
					.addToIconLoader(myIconLoader);
			((IconCell) findViewById(R.id.endasuta2))
					.addToIconLoader(myIconLoader);// 2.
			myIconLoader.setRefreshInterval(10);
		}
		onResume();

		getScore();
		initImage();

		initText();

		setButton();

		rirek();

	}

	@Override
	public void onResume() {
		super.onResume();
		myIconLoader.startLoading();// 4.
	}

	// //////////////////////////////////////init////////////////////////////////////////////////

	void initImage() {
		// imageWidth = BI.getImage(0).getWidth();
		int number = choiceImage();
		scoreText.setText("記録 " + nowScore + " 頭！！");
		name.setText(BI.getName(number) + "から一言！！");
		coment.setText("「" + BI.getComent(number) + "」");
		im.setImageBitmap(BI.getImage(number));
	}

	void getScore() {
		nowScore = pref.getInt("nowScore", 0);
		System.out.println("endNowScore=" + nowScore);

	}

	void initText() {
		for (int i = 0; i < 5; i++) {
			t[i] = new TextView(getApplicationContext());
		}

		t[0] = (TextView) findViewById(R.id.textView2);
		t[1] = (TextView) findViewById(R.id.TextView3);
		t[2] = (TextView) findViewById(R.id.TextView4);
		t[3] = (TextView) findViewById(R.id.TextView5);
		t[4] = (TextView) findViewById(R.id.TextView6);
	}

	void setButton() {
		retryB = (Button) findViewById(R.id.retry);
		retryI = new Intent(getApplicationContext(), SnakeMain.class);
		backB = (Button) findViewById(R.id.endback);
		rankingB = (Button) findViewById(R.id.enadrankB);
		adB = (Button) findViewById(R.id.endanother);

		retryB.setOnClickListener(this);
		backB.setOnClickListener(this);
		rankingB.setOnClickListener(this);
		adB.setOnClickListener(this);
	}

	// //////////////////////////////////////////////////////choiceImage///////////////////////////////////////////////////////
	int choiceImage() {
		int choiceScore;
		if (MODE == HARD) {
			choiceScore = nowScore * 2;
		} else {
			choiceScore = nowScore;
		}
		if (choiceScore == 15) {
			e.putInt("" + BI.ITIGOUMA, 1);
			e.commit();
			return BI.ITIGOUMA;

		} else if (choiceScore == 3) {
			e.putInt("" + BI.HAGE, 1);
			e.commit();
			return BI.HAGE;

		} else if (choiceScore == 73) {
			e.putInt("" + BI.SARARIMAN, 1);
			e.commit();
			return BI.SARARIMAN;

		} else if (choiceScore >= 100) {
			e.putInt("" + BI.TIGER, 1);
			e.commit();
			// e.putInt("" + BI.SARARIMAN, 1);
			// e.commit();
			return BI.TIGER;

		} else if (choiceScore >= 90) {
			double p = Math.random();
			if (p < 0.7) {
				e.putInt("" + BI.DARK, 1);
				e.commit();
				return BI.DARK;
			} else {
				e.putInt("" + BI.BRAIDARU, 1);
				e.commit();
				return BI.BRAIDARU;
			}
		} else if (choiceScore >= 80) {
			double p = Math.random();
			if (p < 0.7) {
				e.putInt("" + BI.HAKUBA, 1);
				e.commit();
				return BI.HAKUBA;
			} else {
				e.putInt("" + BI.OUJI, 1);
				e.commit();
				return BI.OUJI;
			}

		}

		else if (choiceScore >= 60) {
			double p = Math.random();
			if (p < 0.8) {
				e.putInt("" + BI.SYAKIN, 1);
				e.commit();
				return BI.SYAKIN;

			} else {
				e.putInt("" + BI.YASEUMA, 1);
				e.commit();
				return BI.YASEUMA;

			}

		} else if (choiceScore >= 40) {
			double p = Math.random();
			if (p < 0.8) {
				e.putInt("" + BI.UMAKO, 1);
				e.commit();
				return BI.UMAKO;

			} else {
				e.putInt("" + BI.TUKEMA, 1);
				e.commit();
				return BI.TUKEMA;
			}

		} else if (choiceScore >= 20) {
			double p = Math.random();
			if (p < 0.8) {
				e.putInt("" + BI.KAWAIIUMA, 1);
				e.commit();
				return BI.KAWAIIUMA;

			} else {

				e.putInt("" + BI.DEBUUMA, 1);
				e.commit();
				return BI.DEBUUMA;
			}

		} else {
			e.putInt("" + BI.SYOBON, 1);
			e.commit();
			return BI.SYOBON;

		}

	}

	// ////////////////////////////////////////////onTouchEvent////////////////////////////////////////////////////////////////////////

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if (event.getAction() == MotionEvent.ACTION_DOWN && imageflag) {
			ImageFL.setVisibility(View.GONE);
			adll.setVisibility(View.VISIBLE);
			buttonLayout.setVisibility(View.VISIBLE);
			im.setImageDrawable(null);

			if (MODE == HARD) {
				String mode = "hard";
				ranking(mode);
				setRankingText(mode);
			} else if (MODE == EASY) {
				String mode = "easy";
				ranking(mode);
				setRankingText(mode);
			} else {
				String mode = "s";
				ranking(mode);
				setRankingText(mode);
			}

			// setRankingText();
			imageflag = false;
			return true;
		}

		return super.onTouchEvent(event);
	}

	void ranking(String mode) {// /////////////////////////////////////////////////////////////////////端末内ランキングに入れる
		for (int i = 1; i <= 5; i++) {
			if (pref.getInt(mode + i, 0) <= nowScore) {
				if (i == 1) {
					if (MODE == HARD) {
						worldRankingHard();
						System.out.println("rankingHARD");
					} else if (MODE == EASY) {
						System.out.println("rankingEASY");
						worldRankingEasy();
					} else {
						System.out.println("rankingS");
						worldRankingStandard();
					}
					SnakeDialog d = new SnakeDialog(this, MODE);
					d.show();

				}
				System.out.println("<=nowScore");
				for (int j = 4; j >= i; j--) {
					e.putInt(mode + (j + 1), pref.getInt(mode + j, 0));
					e.commit();
				}
				e.putInt(mode + i, nowScore);
				e.commit();
				System.out.println(i + "番目に" + nowScore + "を入れた");
				nowRank = i;

				break;
			}
		}

	}

	void setRankingText(String mode) {
		int j;
		for (int i = 1; i <= 5; i++) {
			j = pref.getInt(mode + i, 0);
			System.out.println(i + "番　" + j + "頭");
			if (i == nowRank) {
				t[i - 1].setTextColor(Color.RED);
				t[i - 1].setText(i + "位  " + j + "  頭    ランクイン！！");
			} else {
				t[i - 1].setText(i + "位  " + j + "  頭      ");
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////世界ランキング////////////////////////////////////////

	void worldRankingStandard() {
		String[] gameIds = { "jp.saiki.snake.standard" };
		String[] scores = { "" + nowScore };
		GFRankingController appController = GFRankingController
				.getIncetance(SnakeEnd.this);
		appController.sendScore(gameIds, scores);
	}

	void worldRankingHard() {
		String[] gameIds = { "jp.saiki.snake.hard" };
		String[] scores = { "" + nowScore };
		GFRankingController appController = GFRankingController
				.getIncetance(SnakeEnd.this);
		appController.sendScore(gameIds, scores);
	}

	void worldRankingEasy() {
		String[] gameIds = { "jp.saiki.snake.easy22" };
		String[] scores = { "" + nowScore };
		GFRankingController appController = GFRankingController
				.getIncetance(SnakeEnd.this);
		appController.sendScore(gameIds, scores);
	}

	void rirek() {
		String[] scores = { "" + nowScore };
		if (MODE == HARD) {
			String[] gameIds = { "jp.saiki.snake.hard" };

			GFRankingController appController = GFRankingController
					.getIncetance(SnakeEnd.this);
			appController.sendHistoryScore(gameIds, scores);

		} else if (MODE == EASY) {
			String[] gameIds = { "jp.saiki.snake.easy22" };

			GFRankingController appController = GFRankingController
					.getIncetance(SnakeEnd.this);
			appController.sendHistoryScore(gameIds, scores);

		} else {
			String[] gameIds = { "jp.saiki.snake.standard" };

			GFRankingController appController = GFRankingController
					.getIncetance(SnakeEnd.this);
			appController.sendHistoryScore(gameIds, scores);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.snake_main, menu);
		return true;
	}

	// //////////////////////////////////////////////////////////////button//////////////////////////////////////////////

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch (v.getId()) {
		case R.id.retry:
			startActivity(retryI);

			finish();
			break;
		case R.id.endback:

			finish();
			Intent intent = new Intent(getApplicationContext(),
					SnakeStart.class);
			startActivity(intent);
			break;
		case R.id.enadrankB:
			if (MODE == HARD) {
				GFRankingController.show(SnakeEnd.this, "jp.saiki.snake.hard");
			} else if (MODE == EASY) {
				GFRankingController
						.show(SnakeEnd.this, "jp.saiki.snake.easy22");
			} else {
				GFRankingController.show(SnakeEnd.this,
						"jp.saiki.snake.standard");
			}
			break;
		case R.id.endanother:
			GameFeatAppController.show(SnakeEnd.this);
			break;

		default:
			break;
		}

	}

	@Override
	public void finish() {
		// TODO 自動生成されたメソッド・スタブ
		// for(int i=0;i<BI.UMAMAXNUM;i++){
		BI.Umaimage.recycle();
		BI.Umaimage = null;
		bgm.stop();
		bgm.reset(); // 次郎
		bgm.release();

		// BI.hatena[i].recycle();
		// }

		super.finish();
	}

	/*
	 * public boolean dispatchKeyEvent(KeyEvent e) {
	 *
	 * // 戻るボタンが押されたとき if(e.getKeyCode() == KeyEvent.KEYCODE_BACK) {
	 *
	 *
	 *
	 * } return true;
	 *
	 * //return super.dispatchKeyEvent(e);
	 *
	 * }
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			finish();

			Intent intent = new Intent(getApplicationContext(),
					SnakeStart.class);
			startActivity(intent);

			return true;
		}
		return false;
	}

}
