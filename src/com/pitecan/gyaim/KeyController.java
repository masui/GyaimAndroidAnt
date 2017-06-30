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
    public boolean useGoogle = false;
    int nthCandSelected = 0;

    public ArrayList<String> inputPatArray;

    private SearchTask searchTask = null;

    private boolean japaneseInputMode = true;
    
    public KeyController(Gyaim gyaim, CandView candView){
	this.gyaim = gyaim;
	this.candView = candView;
	resetInput();
    }

    void resetInput(){
	inputPatArray = new ArrayList<String>();
	nthCandSelected = 0;
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
    
    public String inputPat(){
	return TextUtils.join("",inputPatArray);
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	if(keyCode == KeyEvent.KEYCODE_SYM){
	    // SYMキーのデフォルト動作(?)は変なダイアログが出るので
	    // モード切り換えに利用してみる
	    japaneseInputMode = !japaneseInputMode;;
	    candView.setVisibility(japaneseInputMode ? View.VISIBLE : View.GONE);
	    return true;
	}
	if(keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT){ // 右シフトキーで日本語モード/素通しモード切替
	    japaneseInputMode = !japaneseInputMode;;
	    candView.setVisibility(japaneseInputMode ? View.VISIBLE : View.GONE);
	}
	if(! japaneseInputMode){
	    // 日本語モードでないときはfalseを返して素通しデフォルト動作(?)させる
	    return false;
	}
	//
	// これ以降は日本語入力モードの挙動
	// 状態遷移を特に記述するほどではないという理解で
	//
	if(keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z){
	    int code = 0x61 + (keyCode - KeyEvent.KEYCODE_A);
	    char[] charArray = Character.toChars(code);
	    String s = new String(charArray);
	    
	    inputPatArray.add(s);
	    gyaim.showComposingText(inputPat());
	    
	    searchAndDispCand();
	}
	if(keyCode == KeyEvent.KEYCODE_SPACE){
	    nthCandSelected += 1;
	    gyaim.showComposingText(Search.candidates[nthCandSelected-1].word);
	}
	if(keyCode == KeyEvent.KEYCODE_ENTER){
	    if(nthCandSelected > 0){ // 候補選択状態
		gyaim.input(Search.candidates[nthCandSelected-1].word);
	    }
	    else {
		gyaim.input(inputPat());
	    }
	    resetInput();
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
	    }
	    else {
		int size = inputPatArray.size();
		if(size > 0){
		    inputPatArray.remove(size-1);
		    gyaim.showComposingText(inputPat());
		    searchAndDispCand();
		}
		else {
		    return false; // 変換中でないときはデフォルト動作
		}
	    }
	}
	return true;
    }
}
