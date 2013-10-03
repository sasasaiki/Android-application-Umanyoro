package jp.saiki.newsnake;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.media.MediaPlayer;
import android.os.Handler;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class SnakeView extends View implements Runnable {
	float screenWidth, screenHeight;// View の高さと幅
	/*************** hundler ****************/
	Handler hn;
	int sleepTime = 50;// スレッドが休む時間(速度を増すため)
	int MINSLEEP = 10;
	// ------------------------------------------------------------
	/*************** 向きを表す変数 *********************/
	final int WEST = 1;
	final int SOUTH = 2;
	final int EAST = 3;
	final int NORTH = 4;
	int DIRECTION = 4;
	int changeDirection = 0;
	int LEFT = 1;
	int RIGHT = 2;
	// --------------------------------------------------------------

	/*************** 主役画像 *********************/
	int MAXFRAM = 8;
	Bitmap[] image = new Bitmap[MAXFRAM];
	Bitmap itaiyo;
	int[] uma = new int[MAXFRAM];
	int imageWidth;
	/*************** 背景画像 *********************/

	/*************** 主役クラス *********************/
	final int HEROMAXNUM = 10000;
	List<Hero> heroList = new ArrayList<Hero>();
	int snakeNumber = 0;// 現在のスネークの数
	int V;// sokudo
	int frameRete = 300;
	int itaiyoIds[]=new int[4];
	/*************** Item *********************/
	int ITEMMAX = 3;
	ItemS item[] = new ItemS[ITEMMAX];
	boolean ItemHitFlag = false;
	int hitItemNum = 0;

	Bitmap[] imageEnemy=new Bitmap[8];
	ArrayList<Enemy> enemyList=new ArrayList<Enemy>();

	/********************************************/

	TextView scoreText;
	TextView gameOverText;
	int score = 1;
	SnakeMain sm;
	BookImage BI;

	boolean flagFirst = true;
	boolean flagGameover = true;

	Context context;

	Resources res;

	int hiritu;

	MediaPlayer bgm;
	MediaPlayer segameover;

	// コンストラクタ
	SnakeView(Context context) {
		super(context);
	}

	SnakeView(Context context, TextView scoreText,TextView gameOverText, SnakeMain sm) {
		super(context);
		this.scoreText = scoreText;
		this.sm = sm;
		this.context = context;
		this.gameOverText=gameOverText;


		BI = new BookImage(context);
		initImage();

		hn = new Handler();

		// V=imageWidth/5;//速度
		V = 5;

		// System.out.println(screenWidth/2);
		System.out.println(imageWidth);

		DisplayMetrics metrics = new DisplayMetrics();
		sm.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		hiritu =(int)metrics.scaledDensity;

		System.out.println("hiritu"+hiritu);

		 bgm = MediaPlayer.create(getContext(), R.raw.run);
		 bgm.setLooping(true);
		 bgm.start();





		 segameover= MediaPlayer.create(getContext(), R.raw.garn);




	}

	void initImage() {
		System.out.println("RUN===" + sm.RUN + "BI.TI" + BI.TIGER);
		if (sm.RUN == BI.TIGER) {
			uma[0] = R.drawable.righttiger1;
			uma[1] = R.drawable.righttiger2;
			uma[2] = R.drawable.lefttiger1;
			uma[3] = R.drawable.lefttiger2;
			uma[4] = R.drawable.uetiger1;
			uma[5] = R.drawable.uetiger2;
			uma[6] = R.drawable.sitatiger1;
			uma[7] = R.drawable.sitatiger2;

		} else if (sm.RUN == BI.OUJI) {
			uma[0] = R.drawable.rightouji1;
			uma[1] = R.drawable.rightouji2;
			uma[2] = R.drawable.leftouji1;
			uma[3] = R.drawable.leftouji2;
			uma[4] = R.drawable.ueouji1;
			uma[5] = R.drawable.ueouji2;
			uma[6] = R.drawable.sitaouji1;
			uma[7] = R.drawable.sitaouji2;
		} else if (sm.RUN == BI.DEBUUMA) {
			uma[0] = R.drawable.deburight1;
			uma[1] = R.drawable.deburight2;
			uma[2] = R.drawable.debuleft1;
			uma[3] = R.drawable.debuleft2;
			uma[4] = R.drawable.uedebu1;
			uma[5] = R.drawable.uedebu2;
			uma[6] = R.drawable.sitadebu1;
			uma[7] = R.drawable.sitadebu2;

		} else {
			uma[0] = R.drawable.raightuma1;
			uma[1] = R.drawable.rightuma2;
			uma[2] = R.drawable.leftuma1;
			uma[3] = R.drawable.leftuma2;
			uma[4] = R.drawable.ue1;
			uma[5] = R.drawable.ue2;
			uma[6] = R.drawable.sita1;
			uma[7] = R.drawable.sita2;
		}
		res = sm.getApplicationContext().getResources();
		for (int i = 0; i < MAXFRAM; i++) {
			image[i] = BitmapFactory.decodeResource(res, uma[i]);
		}


		imageWidth = image[0].getWidth();
		itaiyoIds[0]=R.drawable.itai;
		itaiyoIds[1]=R.drawable.itaimigi;
		itaiyoIds[2]=R.drawable.itaihidari;
		 itaiyo=BitmapFactory
					.decodeResource(res,itaiyoIds[0] );
	}

	@Override
	protected void onDraw(Canvas canvas) {// ////////////////////////////全体の処理////////////////////////////////
		// TODO 自動生成されたメソッド・スタブ

		if (flagFirst) {
			init();
		}
		super.onDraw(canvas);
		// if(flagGameover){
		centerLine(canvas);// ////背景と線
		// }
	//	if (sm.MODE != sm.GAMEOVER) {

			doitemDraw(canvas);// itemの描画
			if (ItemHitFlag) {// itemをとったときの処理
				addSnake();// snakeを足す
				makeItem(hitItemNum);// itemを作成
				score();// scoreをプラス、表示
				sleepTime();// sleepTimeを減らし速度アップ



				ItemHitFlag = false;
			}

			for (Hero hero : heroList) {
				transfar(hero);// heroの座標変更
				doDraw(hero, canvas);// heroの描画
				hero.changeD();// 戦闘以外の方向を変える

				if (hero.judgeHead()) {// 先頭がぶつかったときの処理(ゲームover)
					gameover(canvas);
				}

			}

			// invalidate();
			if (hn != null) {
				hn.postDelayed((Runnable) this, sleepTime);
			}
		}
//	}

	void gameover(Canvas canvas){
		if (flagGameover) {

			bgm.stop();



			segameover.start();

			sm.scoreSave(score);
			sm.MODE = sm.GAMEOVER;
			gameOverText.setVisibility(View.VISIBLE);
			gameOverText.setTextColor(Color.RED);

			hn = null;

			Paint paint = new Paint();
			int d= heroList.get(0).direction;
			if(d==NORTH){
				itaiyo=BitmapFactory
						.decodeResource(res,itaiyoIds[2] );
				canvas.drawBitmap(itaiyo, heroList.get(0).x-50,  heroList.get(0).y, paint);

			}else if(d==SOUTH){
				itaiyo=BitmapFactory
						.decodeResource(res,itaiyoIds[1] );
				canvas.drawBitmap(itaiyo, heroList.get(0).x+50,  heroList.get(0).y, paint);
			}else{
				canvas.drawBitmap(itaiyo, heroList.get(0).x,  heroList.get(0).y-50, paint);
			}

			flagGameover = false;
		}
	}

	void score() {
		score++;
		scoreText.setText(score + " 頭");
	}

	void sleepTime() {
		if (sleepTime > MINSLEEP) {
			sleepTime -= 1;
		}
	}

	void centerLine(Canvas canvas) {
		//Paint paint = new Paint();
		/*
		 * paint.setColor(Color.argb(50, 0, 250, 0)); paint.setStrokeWidth(5);
		 * canvas.drawColor(Color.argb(200, 0, 255, 51));
		 * canvas.drawLine(screenWidth/2 ,0, screenWidth/2,screenHeight ,
		 * paint);
		 */
		//canvas.drawBitmap(background, 0, 0, paint);

	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		invalidate();
	}

	void transfar(Hero hero) {// 先頭の向きを変える
		// System.out.println("transfar");
		if (hero.direction == NORTH) {
			hero.y -= V*hiritu;
		} else if (hero.direction == WEST) {
			hero.x -= V*hiritu;
		} else if (hero.direction == SOUTH) {
			hero.y += V*hiritu;
		} else {
			hero.x += V*hiritu;
		}
	}

	void doDraw(Hero hero, Canvas canvas) {
		// System.out.println("do");

		hero.heroDraw(canvas);
	}

	void init() {// 幅と高さの取得
		flagFirst = false;
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		heroList.add(new Hero((int) (screenWidth / 2),
				(int) (screenHeight / 2), true, EAST, 0, this, image));

		ItemInit();

		System.out.println("heady==" + heroList.get(0).y);
		System.out.println(screenWidth);
	}

	public void addSnake() {

		sm.se();

		//seget.start();
		// TODO 自動生成されたメソッド・スタブ
		int prevx = heroList.get(snakeNumber).x;
		int prevy = heroList.get(snakeNumber).y;
		int prevd = heroList.get(snakeNumber).direction;

		snakeNumber++;

		if (prevd == NORTH) {
			heroList.add(new Hero(prevx, prevy + 25*hiritu, true, prevd, snakeNumber,
					this, image));
		} else if (prevd == WEST) {
			heroList.add(new Hero(prevx + 25*hiritu, prevy, true, prevd, snakeNumber,
					this, image));
		} else if (prevd == SOUTH) {
			heroList.add(new Hero(prevx, prevy - 25*hiritu, true, prevd, snakeNumber,
					this, image));
		} else {
			heroList.add(new Hero(prevx - 25*hiritu, prevy, true, prevd, snakeNumber,
					this, image));
		}

	}

	void makeItem(int hitnum) {// Itemを作る

		item[hitnum] = new ItemS(
				(int) (Math.random() * (screenWidth - imageWidth)),
				(int) (Math.random() * (screenHeight - imageWidth)), image,
				this, imageWidth, hitItemNum);

	}

	void ItemInit() {// Itemをinit
		for (int i = 0; i < ITEMMAX; i++) {
			item[i] = new ItemS(
					(int) (Math.random() * (screenWidth - imageWidth)),
					(int) (Math.random() * (screenHeight - imageWidth)), image,
					this, imageWidth, i);
		}
	}

	void doitemDraw(Canvas canvas) {// Itemを描画あととったかどうかの判定も
		for (int i = 0; i < ITEMMAX; i++) {
			// item[i].itemDraw(canvas);
			item[i].itemtrancerate(canvas);
			if (!ItemHitFlag) {
				ItemHitFlag = item[i].itemJudge();
			}

		}

	}

}
