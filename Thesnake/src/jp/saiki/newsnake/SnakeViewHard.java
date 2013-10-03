package jp.saiki.newsnake;




import android.content.Context;
import android.content.res.Resources;


import android.graphics.BitmapFactory;
import android.graphics.Canvas;


import android.widget.TextView;

public class SnakeViewHard extends SnakeView{
//
//	float screenWidth, screenHeight;// View の高さと幅
//	/*************** hundler ****************/
//	Handler hn;
//	int sleepTime = 50;// スレッドが休む時間(速度を増すため)
//	// ------------------------------------------------------------
//	/*************** 向きを表す変数 *********************/
//	final int WEST = 1;
//	final int SOUTH = 2;
//	final int EAST = 3;
//	final int NORTH = 4;
//	int DIRECTION = 4;
//	int changeDirection=0;
//	int LEFT=1;
//	int RIGHT=2;
//	// --------------------------------------------------------------
//
//	/*************** 主役画像 *********************/
//	Bitmap[] image=new Bitmap[8];
//	int[] uma = new int[8];
//	int imageWidth;
//	/*************** 主役クラス *********************/
//	final int HEROMAXNUM = 10000;
//	List<Hero> heroList = new ArrayList<Hero>();
//	int snakeNumber=0;//現在のスネークの数
//	int V;//sokudo
//	int frameRete=300;
//	/*************** Item *********************/
//	ItemS item;
//	boolean ItemHitFlag=false;
//
//	TextView scoreText;
//	int score=1;
//	SnakeMain sm;
//
//
//
//	boolean flagFirst=true;
//
//



    int[] enemyId = new int[8];
    int enemyWidth;
    boolean flagOfEnemyHit=false;
    boolean flagOfBarthEnemy=true;//敵の無限発生を防ぐ

	// コンストラクタ
	SnakeViewHard(Context context, TextView scoreText,TextView gameOverText,SnakeMain sm) {
		super(context, scoreText, gameOverText,sm);
		initEnemyImage();
		System.out.println("HARDMODE");


	}

	protected void onDraw(Canvas canvas) {//////////////////////////////全体の処理////////////////////////////////
		// TODO 自動生成されたメソッド・スタブ
		if(flagFirst){
			init();
		}
		centerLine(canvas);//////背景と線
		doitemDraw(canvas);//itemの描画
		if(ItemHitFlag){//itemをとったときの処理
			addSnake();//snakeを足す
			makeItem(hitItemNum);//itemを作成
			score();//scoreをプラス、表示
			sleepTime();//sleepTimeを減らし速度アップ
			flagOfBarthEnemy=true;
			ItemHitFlag=false;
		}

		for(Hero hero:heroList){
			transfar(hero);//heroの座標変更
			doDraw(hero,canvas);//heroの描画
			hero.changeD();//戦闘以外の方向を変える

			if(hero.judgeHead()||flagOfEnemyHit){//先頭がぶつかったときの処理(ゲームover)
				if(flagGameover){
					 gameover(canvas);
				}
			}
		}





		if(score%5==0&&flagOfBarthEnemy){
			makeEnemy();
			flagOfBarthEnemy=false;
		}

		enemyDraw(canvas);


	//	invalidate();
		if(hn!=null){
			hn.postDelayed((Runnable) this, sleepTime);
		}

	}

	void makeEnemy(){
		int x=heroList.get(0).x;
		int y=heroList.get(0).y;
		double r=0;
		while(r<=100){
			x=(int) (Math.random()*(screenWidth-enemyWidth));
			y=(int) (Math.random()*(screenHeight-enemyWidth));
			r=Math.sqrt((Math.pow(x-heroList.get(0).x,2)+Math.pow(y-heroList.get(0).y,2)));
		}
		//enemyList.add(new Enemy((int)(Math.random()*(screenWidth-enemyWidth)),(int)(Math.random()*(screenHeight-enemyWidth)), imageEnemy, this, enemyWidth, 0));
		enemyList.add(new Enemy(x, y, imageEnemy, this, enemyWidth, 0));
	}

	void initEnemyImage(){
		 enemyId[0]=R.drawable.lion1;
		 enemyId[1]=R.drawable.lion1;
		 enemyId[2]=R.drawable.lion22;
		 enemyId[3]=R.drawable.lion22;
	     Resources res = this.getResources();
	     for(int i=0;i<4;i++){
	    	 imageEnemy[i] = BitmapFactory.decodeResource(res, enemyId[i]);
	     }
	     enemyWidth=imageEnemy[0].getWidth();
	}

	void enemyDraw(Canvas canvas){//Itemを描画あととったかどうかの判定も
		for(Enemy ene:enemyList){
			ene.itemDraw(canvas);
			flagOfEnemyHit=ene.itemJudge();
			if(flagOfEnemyHit){
				break;
			}
		}
	}






}

