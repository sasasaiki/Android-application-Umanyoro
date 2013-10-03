package jp.saiki.newsnake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class ItemS {
	int x,y,width,myNumber,vx,vy;

	Bitmap[] image = new Bitmap[8];
	SnakeView snakeView;
	int frame=0;
	int MaxOfFrame=2;
	ItemS(int x,int y,Bitmap[] imaBitmap, SnakeView snakeView,int W,int myNumber){
		this.x=x;
		this.y=y;
		this.image=imaBitmap;
		this.snakeView=snakeView;
		this.width=W;
		this.myNumber=myNumber;
		vx=(int) (Math.random()*6)-3;
		vy=(int) (Math.random()*6)-3;
	}
	void itemDraw(Canvas canvas){
		Paint paint = new Paint();
		frame++;
		if(frame>=MaxOfFrame){
			frame=0;
		}
		if(vx>=0){
			canvas.drawBitmap(image[frame], x, y, paint);
		}else{
			canvas.drawBitmap(image[frame+2], x, y, paint);
		}
	}

	void itemtrancerate(Canvas canvas){
		x+=vx;
		y+=vy;
		if(x+width>=snakeView.screenWidth||x<=0){
			vx*=-1;
		}
		if(y+width>=snakeView.screenHeight||y<=0){
			vy*=-1;
		}
		itemDraw(canvas);
	}

	boolean itemJudge(){
		int headx = snakeView.heroList.get(0).x;
		int heady = snakeView.heroList.get(0).y;
		int tx=x+width/4;//当たり判定の上のx(画像より少し狭める)
		int dx=x-width/4;
		int ty=y+width/4;
		int dy=y-width/4;
		if(headx<dx+width&&tx<headx+width&&heady<dy+width&&ty<heady+width){
			//snakeView.addSnake();
			snakeView.hitItemNum=myNumber;
			System.out.println("hittttt");

			return true;
		}

		return false;

	}

}
