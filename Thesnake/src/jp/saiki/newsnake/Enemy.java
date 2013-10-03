package jp.saiki.newsnake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy extends ItemS{


	Enemy(int x, int y, Bitmap[] imaBitmap, SnakeView snakeView, int W,
			int myNumber) {
		super(x, y, imaBitmap, snakeView, W, myNumber);

		// TODO 自動生成されたコンストラクター・スタブ
	}
	void itemDraw(Canvas canvas){
		x+=vx;
		y+=vy;
		Paint paint = new Paint();
		if(x+width>=snakeView.screenWidth||x<=0){
			vx*=-1;
		}
		if(y+width>=snakeView.screenHeight||y<=0){
			vy*=-1;
		}
		frame++;
		if(frame>=4){
			frame=0;
		}
			canvas.drawBitmap(image[frame], x, y, paint);

	}


}
