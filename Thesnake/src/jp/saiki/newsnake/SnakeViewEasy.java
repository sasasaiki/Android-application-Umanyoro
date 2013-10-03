package jp.saiki.newsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;

public class SnakeViewEasy extends SnakeView{

	SnakeViewEasy(Context context, TextView scoreText, TextView gameOverText,SnakeMain sm) {
		super(context, scoreText,gameOverText, sm);
		MINSLEEP=20;
		// TODO 自動生成されたコンストラクター・スタブ
	}
	void doitemDraw(Canvas canvas){//Itemを描画あととったかどうかの判定も
		for(int i=0;i<ITEMMAX;i++){
			item[i].itemDraw(canvas);
			//item[i].itemtrancerate(canvas);
			if(!ItemHitFlag){
				ItemHitFlag=item[i].itemJudge();
			}

		}

	}

}
