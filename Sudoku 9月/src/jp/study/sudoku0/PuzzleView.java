package jp.study.sudoku0;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class PuzzleView extends View{
  private static final String TAG = "Sudoku";
  private final Game game;
  
  private float width;
  private float height;
  private int selX;
  private int selY;
  private final Rect selRect = new Rect();
  
  public PuzzleView(Context context){
    super(context);
    this.game = (Game) context;
    setFocusable(true);
    setFocusableInTouchMode(true);
  }
  
  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh){
    width = w / 9f;
    height = h /9f;
    getRect(selX, selY, selRect);
    Log.d(TAG, "onSizeChanged: whisth " + width + " , height " + height);
    super.onSizeChanged(w,  h , oldw, oldh);
  }
  private void getRect(int x, int y, Rect rect){
    rect.set((int) (x * width),
             (int) (y * height),
             (int) (x * width + width),
             (int) (y * height + height));
  }
  
  @Override
  protected void onDraw(Canvas canvas) {
    //背景を描画する
	Paint background = new Paint();
	background.setColor(getResources().getColor(
			R.color.puzzle_background));
	canvas.drawRect(0,0,getWidth(),getHeight(),background);
	
	//　盤面を描画する...
	//　盤面を描画する...
	Paint dark = new Paint();
	dark.setColor(getResources().getColor(R.color.puzzle_dark));
	  
	Paint hilite = new Paint();
	hilite.setColor(getResources().getColor(R.color.puzzle_hilite));
	  
	Paint light = new Paint();
	light.setColor(getResources().getColor(R.color.puzzle_light));
	  
	//マス目を区切る線
	for (int i=0; i<9; i++) {
		canvas.drawLine(0, i * height, getWidth() , i * height, light);
		canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
		canvas.drawLine(i * width, 0, i * width, getHeight(), light);
		canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
	}
	  
	// 3×3の三角形を区切る線
	for (int i=0; i< 9; i++){
		if (i % 3 != 0)
			continue;
		canvas.drawLine(0, i * height, getWidth() , i * height, dark);
		canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
		canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
		canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
	}
	  
	//　数値を描画する...
	//　数値の色とスタイルを定義する
//	Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
//	foreground.setColor(getResources().getColor(R.color.puzzle_foreground));
//	foreground.setStyle(Style.FILL);
//	foreground.setTextSize(height * 0.75f);
//	foreground.setTextScaleX(width / height);
//	foreground.setTextAlign(Paint.Align.CENTER);
//	
//	// マス目の中央に数字を描く
//	FontMetrics fm  = foreground.getFontMetrics();
//	// X軸方向でセンタリングする。中央のx座標に
//	float x = width / 2;
//	// Y軸方向でセンタリングする。まずアセント/ディセント (上半分と下半分)を調べる
//	float y = height / 2 - (fm.ascent + fm.descent) / 2;
//	for (int i=0; i<9; i++){
//	  for(int j=0; j<9; j++){
//	    canvas.drawText(this.game.getTileString(i,j),i * width + x, j * height + y, foreground);
//	  }
//	}
	//　ヒントを描画する...
	//　選択されたマスを描画する...
	Log.d(TAG, "selRext=" + selRect);
	Paint selected = new Paint();
	selected.setColor(getResources().getColor(R.color.puzzle_selected));
	canvas.drawRect(selRect,selected);
	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event){
//	  return true;  66P
//	}
    
  }
}
