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

    private final int buttonTextSize = 50;
    private Paint buttonTextPaint;

    public CandView(Context context, AttributeSet attrs) {
	super(context,attrs);
	// 候補「ボタン」の初期化
	candButtons = new CandButton[Gyaim.MAXCANDS];
	for(int i=0;i<candButtons.length;i++){
	    CandButton button = new CandButton();
	    candButtons[i] = button;
	}
    }

    private void initGraphics(){
	// 候補ボタンのテキスト色
	buttonTextPaint = new Paint();
	buttonTextPaint.setAntiAlias(true);
        buttonTextPaint.setTextSize(buttonTextSize); //  * expand);
        buttonTextPaint.setColor(0xff000000); // 黒
    }

    private int bgcolor;
    
    public void drawDefault(){
	bgcolor = 0xffd0d0ff;
	invalidate();
    }

    @Override public void onDraw(Canvas canvas) {
	Log.v("Gyaim","onDraw----------------------------");
	canvas.drawColor(0xffddddff);
	
	setY(0); // よくわからない

	CandButton button;
	float textPos = 20;
	float textWidth;
	
	for(int i=0;i<5 && i<Search.ncands;i++){
	    button = candButtons[i + KeyController.nthCandSelected];
	    //if(! button.visible) continue; どうなってるのか
	    textWidth = buttonTextPaint.measureText(button.text);
	    canvas.drawText(button.text, textPos, 60, buttonTextPaint);
	    textPos += (textWidth + 20.0);
	    Message.message("Gyaim","text = " + button.text);
	}
	
    }

    // よくわからないがこれを設定するとViewの大きさを決められるようだ...
    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	int width = MeasureSpec.getSize(widthMeasureSpec);
	int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(1200,80);

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

	initGraphics();

	//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
   }
}

