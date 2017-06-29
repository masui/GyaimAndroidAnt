//
// FontMetricsは以下を参照
// http://wikiwiki.jp/android/?%A5%C6%A5%AD%A5%B9%A5%C8%A4%CE%C9%C1%B2%E8%28FontMetrics%29
//
package com.pitecan.gyaim;

import android.view.View;
import android.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint.FontMetrics;
import android.content.res.Resources;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

class CandButton {
    Rect rect;
    String text;
    String pat;
    boolean valid;
    boolean visible;
    int row;

    public CandButton() {
	rect = new Rect(0,0,0,0);
	text = "";
	pat = "";
	valid = false;
	visible = false;
	row = 0;
    }
}

public class CandView extends View {
    public static CandButton[] candButtons;

    public CandView(Context context, AttributeSet attrs) {
	super(context,attrs);
	Log.v("Gyaim-----------","candView called");

	// 候補「ボタン」の初期化
	candButtons = new CandButton[Gyaim.MAXCANDS];
	for(int i=0;i<candButtons.length;i++){
	    CandButton button = new CandButton();
	    candButtons[i] = button;
	}
    }

    private void initGraphics(){
	/*
	// キートップ色
	keyPaint = new Paint();
	keyPaint.setAntiAlias(true);
        keyPaint.setTextSize(largeKeyTextSize * expand);
        keyPaint.setColor(0xff000000); // argb 黒
	smallKeyPaint = new Paint();
	smallKeyPaint.setAntiAlias(true);
	smallKeyPaint.setTextSize(smallKeyTextSize * expand);
        smallKeyPaint.setColor(0xff000000); // 黒
	tinyKeyPaint = new Paint();
	tinyKeyPaint.setAntiAlias(true);
	tinyKeyPaint.setTextSize(tinyKeyTextSize * expand);
        tinyKeyPaint.setColor(0xff000000); // 黒

	// 背景色
	buttonPaint = new Paint();
        buttonPaint.setColor(0xffc0c0c0);
	
	// 候補ボタンのテキスト色
	buttonTextPaint = new Paint();
	buttonTextPaint.setAntiAlias(true);
        buttonTextPaint.setTextSize(buttonTextSize * expand);
        buttonTextPaint.setColor(0xff000000); // 黒
	*/
    }

    /*
    private int bgcolor;

    public void draw2(Key keypat[], Key selectedKey, Key selectedKey2, int candPage){
	this.keypat = keypat;
	this.selectedKey = selectedKey;
	this.selectedKey2 = selectedKey2;
	this.candPage = candPage;
	bgcolor = 0xffd0d0d0;
	invalidate();
    }

    public void draw(Key keypat[], Key selectedKey, Key selectedKey2, int candPage){
	this.keypat = keypat;
	this.selectedKey = selectedKey;
	this.selectedKey2 = selectedKey2;
	this.candPage = candPage;
	bgcolor = 0xfff0f0f0;

	if(keyController.useGoogle && !keyController.googleDisplayed){
	    bgcolor = 0xffd0d0d0;
	    //!!!!keyController.googleDisplayed = true;
	}
	invalidate();
    }

    public void drawDefault(){
	this.keypat = keys.keypat0;
	this.selectedKey = null;
	this.selectedKey2 = null;
	this.candPage = 1;
	bgcolor = 0xfff0f0f0;
	invalidate();
    }

    //public void drawDefault2(){
    //	this.keypat = keys.keypat0;
    //	this.selectedKey = null;
    //	this.selectedKey2 = null;
    //	this.candPage = 1;
    //	bgcolor = 0xffd0d0d0;
    //	invalidate();
    //}

    //
    // 候補バッファを12行ぶん用意しておいて3行ずつ表示する
    //
    private void layoutCandButtons(){
	float x, y, w, h;   // 候補ボタンの矩形
	int buttonIndex = 0;
	//int[] leftlimit = {0, 0, 0, 24, 24, 24, 24, 24, 24, 24, 24, 24};
	int[] leftlimit = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	//int[] rightlimit = {296, 296, 296, 296, 296, 296, 296, 296, 296, 296, 296, 296}; 
	int[] rightlimit = {320, 268, 268, 320, 268, 268, 320, 268, 268, 320, 320, 320};
	int[] curright = new int[12];
	for(int i=0;i<12;i++){
	    curright[i] = leftlimit[i] + buttonMarginX;
	}

	for(;buttonIndex<candButtons.length;buttonIndex++){
	    CandButton button = candButtons[buttonIndex];
	    String s = button.text;
	    if(s == "") break;
	    float textWidth = buttonTextPaint.measureText(s) / expand;
	    w = textWidth + ((float)buttonMarginX * 2); // ボタン幅
	    h = buttonHeight;                           // ボタン高さ
	    // 空いている候補領域に候補ボタンを詰める
	    int i;
	    for(i=0;i<12;i++){
		if(curright[i] + w + buttonMarginX <= rightlimit[i]){
		    x = curright[i];
		    y = buttonMarginY + (i % 3) * (buttonHeight+buttonMarginY);
		    button.rect.left = (int)x;
		    button.rect.top = (int)y;
		    button.rect.right = (int)(x+w);
		    button.rect.bottom = (int)(y+h);
		    button.row = i;
		    button.valid = true;
		    curright[i] += (w + buttonMarginX);
		    break;
		}
	    }
	    if(i >= 12){
		break;
	    }
	}
	for(;buttonIndex<candButtons.length;buttonIndex++){
	    CandButton button = candButtons[buttonIndex];
	    button.valid = false;
	    button.visible = false;
	}
	for(candLines=0;candLines<12;candLines++){
	    if(curright[candLines] == leftlimit[candLines] + buttonMarginX) break;
	}
	for(;buttonIndex<candButtons.length;buttonIndex++){
	    CandButton button = candButtons[buttonIndex];
	    button.valid = false;
	}
    }
    */

    @Override public void onDraw(Canvas canvas) {
	Log.v("Gyaim","onDraw");
	canvas.drawColor(0xffddddff);
	
	/*
	Bitmap image;
			    
	if(keypat == null) keypat = keys.keypat0; // Viewを作った瞬間はkeypatが設定されてない

	//Log.v("Gyaim","onDraw - length="+keypat.length);

	//canvas.drawColor(0x80f0f0f0); 背景を透明にしたい場合 - ブラウザしか有効じゃないみたいだけど
	canvas.drawColor(bgcolor);
	for(int i=0;i<keypat.length;i++){
	    Key key = keypat[i];
	    int width = key.rect.right - key.rect.left;
	    int height = key.rect.bottom - key.rect.top;
	    Paint paint = (width <= 24 ? tinyKeyPaint : width <= 32 ? smallKeyPaint : keyPaint);
	    FontMetrics fontMetrics = paint.getFontMetrics();
	    float ascent = fontMetrics.ascent; // これはマイナス値
	    float descent = fontMetrics.descent;
	    float shadewidth = 6;

	    image = ((selectedKey != null && key.str == selectedKey.str) ||
		     (selectedKey2 != null && key.str == selectedKey2.str) ?
		     (width == 32 ? keyfg32x53 : (height == 106 ? keyfg53x106 : keyfg53x53)) :
		     (width == 32 ? keybg32x53 : (height == 106 ? keybg53x106 : keybg53x53))
		     );
	    if(! (key.str == "→" && (candPage == 0 || candPage == 4))){
		int left = (int)(key.rect.left * expand);
		int top = (int)(key.rect.top * expand);
		int right = (int)(key.rect.right * expand);
		int bottom = (int)(key.rect.bottom * expand);
		Rect dst = new Rect(left,top,right,bottom);
		canvas.drawBitmap(image,null,dst,null); // 何故か第2引数がkey.rectだとうまくいかない
		//
		// キー文字描画
		//
		float textWidth = paint.measureText(key.str);
		float baseX = left + (right-left-(shadewidth*expand) - textWidth)/2;
		float baseY = top + (bottom-top-(shadewidth*expand) - (ascent+descent))/2;
		canvas.drawText(key.str,baseX,baseY,paint);
	    }
	}
	if(candPage > 0){
	    CandButton button;
	    FontMetrics fontMetrics = buttonTextPaint.getFontMetrics();
	    float ascent = fontMetrics.ascent / expand;
	    float descent = fontMetrics.descent / expand;
	    layoutCandButtons();
	    for(int i=0;i<candButtons.length;i++){
		button = candButtons[i];
		candButtons[i].visible = button.valid && ((candPage-1) * 3 <= button.row && button.row < candPage * 3);
	    }
	    for(int i=0;i<candButtons.length;i++){
		button = candButtons[i];
		if(! button.visible) continue;
		float y = buttonMarginY + (button.row % 3) * (buttonHeight+buttonMarginY);
		canvas.drawRect((float)button.rect.left * expand,
				(float)button.rect.top * expand,
				(float)button.rect.right * expand,
				(float)(y+button.rect.bottom-button.rect.top) * expand,
				buttonPaint);
		canvas.drawText(button.text,
				(button.rect.left + buttonTextMargin) * expand,
				(button.rect.top + (buttonHeight-(ascent+descent))/2) * expand,
				buttonTextPaint);
	    }
	}
	*/
    }

    // よくわからないがこれを設定するとViewの大きさを決められるようだ...
    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	int width = MeasureSpec.getSize(widthMeasureSpec);
	int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(300,300);

	// self.layout()
	
	//Log.v("Gyaim","onMeasure = width = "+widthMeasureSpec+" height="+heightMeasureSpec);
	Log.v("Gyaim","onMeasure = width = "+width+" height="+height);
	//int specMode = MeasureSpec.getMode(widthMeasureSpec);
	//int specSize = MeasureSpec.getSize(widthMeasureSpec);
	//Log.v("Gyaim","modeandsize = "+specMode+", "+specSize);
	//specMode = MeasureSpec.getMode(heightMeasureSpec);
	//specSize = MeasureSpec.getSize(heightMeasureSpec);
	//Log.v("Gyaim","modeandsize = "+specMode+", "+specSize);

	// Android.manifestで以下のような記述をしておけば勝手にスケールしてくれる
	// http://y-anz-m.blogspot.com/2010/02/andro
	// SDK version 4以降でこれが必要らしい
	//    <supports-screens  
	//       android:smallScreens="true"  
	//       android:normalScreens="true"  
	//       android:largeScreens="true"  
	//       android:anyDensity="false" />  
	// ... と思ったがうまくスケールしてくれないのできちんと倍率を計算して処理する

	// Nexus5のとき、何故かheightに変な値が返ってきてしまう。
	// height=585, width=1080 になってしまったりすることがあるので
	// とりあえず width > height で orientation を判定するのをやめる。
	// これにより landscape だとSlimeが画面一杯になってしまうのだが...
	//
	/*
	Log.v("Gyaim","onMeasure = width = "+width);
	Log.v("Gyaim","onMeasure = height = "+height);
	int imeWidth = width;
	//	if(width > height) imeWidth = height;
	expand = (float)imeWidth / (float)320.0;
        setMeasuredDimension((int)(320 * expand),(int)(216 * expand));
	*/

	////initGraphics();

	//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
   }
}

