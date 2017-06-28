//
//	キー操作に対するアクションメインルーチン
//
package com.pitecan.gyaim;

import java.util.ArrayList;

import android.view.KeyEvent;

class KeyController {
    // Gyaim.onInitializeInterface で呼ばれる
    
    public Gyaim gyaim;

    public ArrayList<String> inputPatArray;

    public KeyController() {
	Message.message("Gyaim", "xxxx");
	resetInput();
    }

    void resetInput(){
	inputPatArray = new ArrayList<String>();
    }
    
    void reset(){
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	// gyaim.input("増井");
	if(keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z){
	    int code = 0x61 + (keyCode - KeyEvent.KEYCODE_A);
	    char[] charArray = Character.toChars(code);
	    String s = new String(charArray);
	    //Message.message("Gyaim","s = " + s);

	    inputPatArray.add(s);
	    gyaim.showComposingText();
	}
	if(keyCode == KeyEvent.KEYCODE_DEL){
	    int size = inputPatArray.size();
	    if(size > 0){
		inputPatArray.remove(size-1);
		gyaim.showComposingText();
	    }
	}
	// gyaim.showComposingText();
	return true;
    }
}
