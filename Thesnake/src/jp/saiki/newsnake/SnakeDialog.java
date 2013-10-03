package jp.saiki.newsnake;

import jp.basicinc.gamefeat.ranking.android.sdk.controller.GFRankingController;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SnakeDialog extends Dialog implements android.view.View.OnClickListener {

	Button rankingB,noB;
	final int STANDARD = 0;
	final int HARD = 1;
	final int EASY = 2;
	int  MODE;

	public SnakeDialog(Context context,int MODE) {
		super(context);
		this.MODE=MODE;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("自己ベスト達成！");
		setContentView(R.layout.snake_dialog);
		rankingB=(Button)findViewById(R.id.rankingyes);
		noB=(Button)findViewById(R.id.rankingno);
		rankingB.setOnClickListener(this);
		noB.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch (v.getId()) {
		case R.id.rankingyes:
			if(MODE==HARD){
				GFRankingController.show(getContext(), "jp.saiki.snake.hard");
			}else if(MODE==EASY){
				GFRankingController.show(getContext(), "jp.saiki.snake.easy22");
			}else{
				GFRankingController.show(getContext(), "jp.saiki.snake.standard");
			}
			dismiss();
			break;
		case R.id.rankingno:
			dismiss();
			break;

		}
	}

}
