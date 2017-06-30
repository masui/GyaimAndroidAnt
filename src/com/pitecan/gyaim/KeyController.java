//
//  Gyaimのキー操作に対するアクションメインルーチン
//
package com.pitecan.gyaim;

import java.util.ArrayList;

import android.view.View;
import android.view.KeyEvent;
import android.text.TextUtils;

class KeyController {

    public Gyaim gyaim;
    public CandView candView;
    public boolean useGoogle = true;
    static public int nthCandSelected = 0; // 0のときは候補選択前

    public static ArrayList<String> inputPatArray;

    private SearchTask searchTask = null;

    private boolean japaneseInputMode = true;
    private boolean exactMode = false;
    private boolean shift = false;
    private boolean alt = false;
    
    public KeyController(Gyaim gyaim, CandView candView){
	this.gyaim = gyaim;
	this.candView = candView;
	resetInput();
    }

    void resetInput(){
	inputPatArray = new ArrayList<String>();
	nthCandSelected = 0;
	exactMode = false;
    }
    
    void reset(){
    }
    
    private void searchAndDispCand(){
	Message.message("Gyaim","searchAndDispCand()");
	//
	// バックグラウンドで検索を実行させる
	//
	searchTask = new SearchTask(candView,useGoogle);
	searchTask.execute(inputPat());
    }
    
    public static String inputPat(){
	return TextUtils.join("",inputPatArray);
    }

    private void fix(){
	if(nthCandSelected > 0){ // 候補選択状態
	    String word = Search.candidates[nthCandSelected-1].word;
	    gyaim.input(word);
	    Search.sqlDict.add(word,inputPat());
	    Search.sqlDict.limit(1000); // 1000個以上になれば古いエントリを消す
	}
	else {
	    gyaim.input(inputPat());
	}
	resetInput();
	Search.reset();
	candView.invalidate();
    }
    
    boolean onKeyDown(int keyCode, KeyEvent event) {
	if(keyCode == KeyEvent.KEYCODE_BACK){ // 画面上の左矢印キー
	    return false;
	}
	if(keyCode == KeyEvent.KEYCODE_SYM){
	    // SYMキーのデフォルト動作(?)は変なダイアログが出るので
	    // モード切り換えに利用してみる
	    japaneseInputMode = !japaneseInputMode;;
	    candView.setVisibility(japaneseInputMode ? View.VISIBLE : View.GONE);
	    resetInput();
	    fix();
	}
	if(keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT){
	    // 右シフトキーで日本語モード/素通しモード切替
	    japaneseInputMode = !japaneseInputMode;
	    candView.setVisibility(japaneseInputMode ? View.VISIBLE : View.GONE);
	    resetInput();
	    fix();
	}
	if(! japaneseInputMode){
	    // 日本語モードでないときはfalseを返して素通しデフォルト動作(?)させる
	    return false;
	}
	//
	// これ以降は日本語入力モードの挙動
	// 状態遷移を特に記述するほどではないという理解で
	//
	if(keyCode == KeyEvent.KEYCODE_SHIFT_LEFT){
	    shift = true;
	}
	if(keyCode == KeyEvent.KEYCODE_ALT_LEFT){
	    alt = true;
	}
	if(keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z && !shift && !alt){
	    int code = 0x61 + (keyCode - KeyEvent.KEYCODE_A);
	    char[] charArray = Character.toChars(code);
	    String s = new String(charArray);

	    if(nthCandSelected > 0){ // 候補選択状態だったら確定する
		fix();
	    }
		
	    inputPatArray.add(s);
	    gyaim.showComposingText(inputPat());
	    
	    searchAndDispCand();
	}
	if(alt){
	    String c = "";
	    if(keyCode == KeyEvent.KEYCODE_A) c = "*";
	    if(keyCode == KeyEvent.KEYCODE_B) c = "!";
	    if(keyCode == KeyEvent.KEYCODE_C) c = "9";
	    if(keyCode == KeyEvent.KEYCODE_D) c = "5";
	    if(keyCode == KeyEvent.KEYCODE_E) c = "2";
	    if(keyCode == KeyEvent.KEYCODE_F) c = "6";
	    if(keyCode == KeyEvent.KEYCODE_G) c = "/";
	    if(keyCode == KeyEvent.KEYCODE_H) c = ":";
	    if(keyCode == KeyEvent.KEYCODE_I) c = "-";
	    if(keyCode == KeyEvent.KEYCODE_J) c = ";";
	    if(keyCode == KeyEvent.KEYCODE_K) c = "'";
	    if(keyCode == KeyEvent.KEYCODE_L) c = "\"";
	    if(keyCode == KeyEvent.KEYCODE_M) c = ".";
	    if(keyCode == KeyEvent.KEYCODE_N) c = ",";
	    if(keyCode == KeyEvent.KEYCODE_O) c = "+";
	    if(keyCode == KeyEvent.KEYCODE_P) c = "@";
	    if(keyCode == KeyEvent.KEYCODE_Q) c = "#";
	    if(keyCode == KeyEvent.KEYCODE_R) c = "3";
	    if(keyCode == KeyEvent.KEYCODE_S) c = "4";
	    if(keyCode == KeyEvent.KEYCODE_T) c = "(";
	    if(keyCode == KeyEvent.KEYCODE_U) c = "_";
	    if(keyCode == KeyEvent.KEYCODE_V) c = "?";
	    if(keyCode == KeyEvent.KEYCODE_W) c = "1";
	    if(keyCode == KeyEvent.KEYCODE_X) c = "8";
	    if(keyCode == KeyEvent.KEYCODE_Y) c = ")";
	    if(keyCode == KeyEvent.KEYCODE_Z) c = "7";
	    if(keyCode == KeyEvent.KEYCODE_0) c = "0";
	    if(c.length() == 1){
		if(nthCandSelected > 0){ // 候補選択状態だったら確定する
		    fix();
		}
		inputPatArray.add(c);
		gyaim.showComposingText(inputPat());
		searchAndDispCand();
	    }
	}
	if(keyCode == KeyEvent.KEYCODE_SPACE){ // 候補選択
	    nthCandSelected += 1;
	    gyaim.showComposingText(Search.candidates[nthCandSelected-1].word);
	    candView.invalidate(); // 候補表示更新
	}
	if(keyCode == KeyEvent.KEYCODE_ENTER){ // 確定
	    if(inputPatArray.size() == 0){
		return false;
	    }
	    if(nthCandSelected > 0 || exactMode){
		fix();
		exactMode = false;
		LocalDict.exactMode = false;
	    }
	    else {
		exactMode = true;
		LocalDict.exactMode = true;
		searchAndDispCand();

		nthCandSelected += 1;
		//gyaim.showComposingText(Search.candidates[nthCandSelected-1].word);
		gyaim.showComposingText(Romakana.roma2hiragana(inputPat())); // これは苦しい
		candView.invalidate(); // 候補表示更新
	    }
	}
	if(keyCode == KeyEvent.KEYCODE_DEL){
	    if(nthCandSelected > 0){ // 候補選択状態
		nthCandSelected -= 1;
		if(nthCandSelected > 0){
		    gyaim.showComposingText(Search.candidates[nthCandSelected-1].word);
		}
		else {
		    gyaim.showComposingText(inputPat());
		}
		candView.invalidate(); // 候補表示更新
	    }
	    else {
		int size = inputPatArray.size();
		if(size > 0){
		    inputPatArray.remove(size-1);
		    gyaim.showComposingText(inputPat());
		    searchAndDispCand();
		}
		else {
		    return false; // 変換中でないのでデフォルト動作
		}
	    }
	}
	return true;
    }
    
    boolean onKeyUp(int keyCode, KeyEvent event) {
	if(japaneseInputMode){
	    if(keyCode == KeyEvent.KEYCODE_SHIFT_LEFT){
		shift = false;
	    }
	    if(keyCode == KeyEvent.KEYCODE_ALT_LEFT){
		alt = false;
	    }
	    return true;
	}
	else {
	    return false;
	}
    }
}
