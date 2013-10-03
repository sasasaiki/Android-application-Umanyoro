package jp.saiki.newsnake;

import android.graphics.Bitmap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Hero {
	int x;
	int y;
	int direction;
	int fakeDirection;
	int frame = 0;
	int Maxframe = 2;
	boolean flag;
	Bitmap[] image = new Bitmap[Maxframe];
	//Bitmap[] leftImage=new Bitmap[Maxframe];
//	Bitmap[] drawImage=new Bitmap[Maxframe];
	int W;//Width

	int TIME = 5;//
	// int directionTime = TIME;// 向きを変えるタイミング

	int[] directionTime = new int[10];// 向きを変えるタイミング
	int[] prevDirection = new int[10];
	Point[] prevP = new Point[10];
	int DIndex = 0;
	int pointer = 0;
	Point headP;// 曲がった瞬間の座標


	int myNumber;
	// boolean directionFlag = false;
	boolean[] directionFlag = new boolean[10];
	SnakeView snakeView;

	int[] dQueue = new int[10];

	Hero(int x, int y, boolean flag, int direction, int myNumber,
			SnakeView snakeView, Bitmap[] image) {
		this.x = x;
		this.y = y;
		this.flag = flag;
		this.direction = direction;
		this.myNumber = myNumber;
		this.snakeView = snakeView;
		fakeDirection = direction;
		this.image = image;
	/*	if(myNumber>0){
			int prevD=snakeView.heroList.get(myNumber-1).direction;
			if(prevD==snakeView.SOUTH){
				drawImage[0]=image[6];
				drawImage[1]=image[7];
			}
			else if(prevD==snakeView.NORTH){
				drawImage[0]=image[4];
				drawImage[1]=image[5];
			}
			else if(prevD==snakeView.WEST){
				drawImage[0]=image[2];
				drawImage[1]=image[3];
			}
			else{
				drawImage[0]=image[0];
				drawImage[1]=image[1];
			}

		}*/
		//this.leftImage=leftImage;
		//this.drawImage=drawImage;
		W=snakeView.imageWidth;
		// TIME=snakeView.imageWidth/snakeView.v;
		//leftImage[0]=image

		for (int i = 0; i < 10; i++) {
			directionTime[i] = TIME;
			prevDirection[i] = 0;
			directionFlag[i] = false;
			prevP[i] = new Point();
		}

	}

	void left() {
		direction++;
		if (direction > 4) {
			direction = 1;
		}
		headP = new Point(x, y);

	}


	void right() {
		direction--;
		if (direction < 1) {
			direction = 4;
		}
		headP = new Point(x, y);
	}

	void heroDraw(Canvas canvas) {
		Paint paint = new Paint();
		if(direction==snakeView.SOUTH){
			canvas.drawBitmap(image[frame+6], x, y, paint);
		//	drawImage[0]=image[6];
		//	drawImage[1]=image[7];
		}
		else if(direction==snakeView.NORTH){
			canvas.drawBitmap(image[frame+4], x, y, paint);
		//	drawImage[0]=image[4];
		//	drawImage[1]=image[5];
		}
		else if(direction==snakeView.WEST){
			canvas.drawBitmap(image[frame+2], x, y, paint);
		//	drawImage[0]=image[2];
		//	drawImage[1]=image[3];
		}
		else{
			canvas.drawBitmap(image[frame], x, y, paint);
		//	drawImage[0]=image[0];
		//	drawImage[1]=image[1];
		}



		//canvas.drawBitmap(drawImage[frame], x, y, paint);
		frame++;
		if (frame > 1) {
			frame = 0;
		}
	}

	void changeD() {//先頭以外の向きを変えるメソッド
		if (myNumber != 0) {
			if (snakeView.heroList.get(myNumber - 1).direction != fakeDirection) {
				directionFlag[DIndex] = true;
				prevDirection[DIndex] = snakeView.heroList.get(myNumber - 1).direction;
				prevP[DIndex] = snakeView.heroList.get(myNumber - 1).headP;

				// System.out.println("prevy===" + prevP[DIndex].y);
				// System.out.println("prevx===" + prevP[DIndex].x);

				fakeDirection = prevDirection[DIndex];
				directionTime[DIndex] = TIME;
				DIndex++;
				if (DIndex == 9) {
					DIndex = 0;
				}
			}
			for (int i = 0; i < 10; i++) {
				if (directionFlag[i]) {
					directionTime[i]--;
					// System.out.println("x==" + x + ", y==" + y);
				if (prevP[i].x == x && prevP[i].y == y) {
						direction = prevDirection[i];
						directionTime[i] = TIME;
						directionFlag[i] = false;
						headP = new Point(x, y);
					}
					/*if (directionTime[i] == 0) {
						direction = prevDirection[i];
						directionTime[i] = TIME;
						directionFlag[i] = false;
						headP = new Point(x, y);

					}*/
				}

			}
		}
	}



	boolean judgeHead() {//各スネークと頭とのぶつかり判定
		if (myNumber  >2) {
			int headx = snakeView.heroList.get(0).x;
			int heady = snakeView.heroList.get(0).y;
			int tx=x+W/4;//当たり判定の左のx(画像より少し狭める)
			int dx=x-W/4;
			int ty=y+W/4;
			int dy=y-W/4;
			if(headx<dx+W&&tx<headx+W&&heady<dy+W&&ty<heady+W){//四角の当たり判定
				System.out.println("Hit"+myNumber);
				return true;
			}
		}
		else if(myNumber==0){//頭だったら壁との当たり判定
			if((x+W/8)<0||x+W-W/8>snakeView.screenWidth||y+W/8<0||y+W-W/8>snakeView.screenHeight){
				return true;
			}
		}
		return false;

	}

}
