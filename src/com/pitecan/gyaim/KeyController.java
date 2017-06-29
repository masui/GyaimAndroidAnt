//
//	キー操作に対するアクションメインルーチン
//
package com.pitecan.gyaim;

import java.util.ArrayList;

import android.view.View;
import android.view.KeyEvent;

class KeyController {
    // Gyaim.onInitializeInterface で呼ばれる
    
    public Gyaim gyaim;
    public CandView candView;
    public boolean useGoogle = false;

    public ArrayList<String> inputPatArray;

    private SearchTask searchTask = null;
    
    public KeyController(Gyaim gyaim, CandView candView){
	Message.message("Gyaim", "xxxx");
	this.gyaim = gyaim;
	this.candView = candView;
	resetInput();
    }

    void resetInput(){
	inputPatArray = new ArrayList<String>();
    }
    
    void reset(){
    }
    
    private void searchAndDispCand(){
	Message.message("Gyaim","searchAndDispCand()");
	searchTask = new SearchTask(candView,useGoogle);
	searchTask.execute(inputPat());
    }
    
    public String inputPat(){
	String pat = "";
	for(int i=0;i<inputPatArray.size();i++){
	    pat = pat + inputPatArray.get(i);
	}
	return pat;
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	if(keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z){
	    int code = 0x61 + (keyCode - KeyEvent.KEYCODE_A);
	    char[] charArray = Character.toChars(code);
	    String s = new String(charArray);
	    //Message.message("Gyaim","s = " + s);

	    inputPatArray.add(s);
	    gyaim.showComposingText();

	    searchAndDispCand();
	}
	if(keyCode == KeyEvent.KEYCODE_DEL){
	    int size = inputPatArray.size();
	    if(size > 0){
		inputPatArray.remove(size-1);
		gyaim.showComposingText();
		searchAndDispCand();
		return true;
	    }
	    else {
		return false;
	    }
	}
	return true;
    }
}
