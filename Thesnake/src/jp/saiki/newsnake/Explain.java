package jp.saiki.newsnake;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;


public class Explain extends Activity{
	//LinearLayout ll;
	ImageView iV;
	Bitmap image;
	int MAXPAGE=4;
	int imageIds[]=new int[MAXPAGE];
	Resources res;
	int PAGE=0;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.snake_explain);
		//ll=(LinearLayout)findViewById(R.id.explainll0);
		iV=(ImageView)findViewById(R.id.explainimage);
		initImage();





	}

	void initImage(){
		imageIds[0]=R.drawable.explain11;
		imageIds[1]=R.drawable.explain2;
		imageIds[2]=R.drawable.explain4;
		imageIds[3]=R.drawable.explain3;


		res = getApplicationContext().getResources();
	    image=BitmapFactory.decodeResource(res, imageIds[0]);
	    iV.setImageBitmap(image);


	}

	void next(){
		PAGE++;
		if(PAGE>=MAXPAGE){
			finish();
			image.recycle();
			image=null;
			iV.setImageDrawable(null);
		}else{
			image=BitmapFactory.decodeResource(res, imageIds[PAGE]);
			iV.setImageDrawable(null);
			iV.setImageBitmap(image);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			next();
			return true;
		}
		else{return super.onTouchEvent(event);}
	}

	@Override
	public void finish() {
		// TODO 自動生成されたメソッド・スタブ
		Intent intent=new Intent(getApplicationContext(),SnakeStart.class);
    	startActivity(intent);
		super.finish();
	}


}
