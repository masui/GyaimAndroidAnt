//
//	Gyaimfor Android
//
//

package com.pitecan.gyaim;

import android.inputmethodservice.InputMethodService;
////import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import android.os.Bundle;
import android.os.Build; // for Build.VERSION.SDK_INT
import android.view.View;
import android.view.KeyEvent;
import android.widget.Button;
import android.util.Log;

import android.widget.AbsoluteLayout; // これがあるとコンパイラに文句を言われる

import android.content.Context;
////import android.text.ClipboardManager;
// Android3.0以上の場合こちら
// import android.content.ClipData;
// import android.content.ClipboardManager;

////import android.net.ConnectivityManager;
/////import android.net.NetworkInfo;

// 単語書き出し用
////import java.io.FileOutputStream;
////import java.io.IOException;
////import java.io.FileNotFoundException;
////import java.util.Date;
////import java.text.SimpleDateFormat;

public class Gyaim extends InputMethodService 
{
    //private Keys keys;
    private CandView candView;
    
    ////private KeyView keyView;
    ////private KeyController keyController;
    ////private LocalDict dict;
    ////private SQLDict sqlDict;
    ////private Search search;

    ////private ClipboardManager cm;
    ////private String clipboardText;

    static final int MAXCANDS = 50;

    /**
     * Main initialization of the input method component.  Be sure to call
     * to super class.
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    /**
     * This is the point where you can do all of your UI initialization.  It
     * is called after creation and any configuration change.
     */
    @Override public void onInitializeInterface() {
	super.onInitializeInterface(); // 必要??
    }
    
    /**
     * This is the main point where we do our initialization of the input method
     * to begin operating on an application.  At this point we have been
     * bound to the client, and are now receiving all of the detailed information
     * about the target of our edits.
     */
    @Override public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);
    }

    /**
     * Called by the framework when your view for creating input needs to
     * be generated.  This will be called the first time your input method
     * is displayed, and every time it needs to be re-created such as due to
     * a configuration change.
     */
    @Override public View onCreateInputView() {
	super.onCreateInputView(); // 必要??
        candView = (CandView) getLayoutInflater().inflate(R.layout.input, null);
	Log.v("Gyaim-----------","candView = " + candView);
	return candView;
	// return null;
    }

    @Override public View onCreateCandidatesView() {
	Log.v("Gyaim-----------","onCreateCandidatesView");
	return super.onCreateCandidatesView();
    }
	
    /*
    @Override public void onStartInputView(EditorInfo info, boolean restarting) {
	super.onStartInputView(info,restarting); // 必要??
    }

    @Override public void onFinishInput() {
	super.onFinishInput();
    }
    */

    /*
    public void input(String s){
    }

    public void keyDownUp(int keyEventCode) { // キー入力
    }

    public void showComposingText(){
    }

    public void hide(){
    }
    */

    //
    // 新規登録用にクリップボードの単語を返す
    //
    public void clearRegWord(){
    }

    public String getRegWord(){
	return "";
    }

    //
    // キーボードが表示されているときBackボタンが押されたら対応する
    //
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	Log.v("Gyaim","onKeyDown - keyCode = "+keyCode);
	//if(Build.VERSION.SDK_INT >= 11 && keyCode == KeyEvent.KEYCODE_BACK && isInputViewShown()){
	if(Build.VERSION.SDK_INT >= 11 && keyCode == KeyEvent.KEYCODE_BACK){ // 画面の←キー
	    ////keyController.backKey();
	    return false;
	}
	else {
	    InputConnection ic = getCurrentInputConnection();
	    if(ic != null) ic.commitText("漢字",1); // 入力貼り付け
	    //// return super.onKeyDown(keyCode,event);
	}
	return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
	Log.v("Gyaim","onKeyUp - keyCode = "+keyCode);
	return false;
    }

    //
    // ConnectivityManagerというのを使って、ネットが使えるかどうかを判断し、
    // ネットがあるときは常にGoogleIMEを使うようにしてみる
    // http://yife.hateblo.jp/entry/2012/10/29/203330
    // http://wada811.blog.fc2.com/?tag=ConnectivityManager
    // AndroidManifest.xmlに以下の追加が必要である
    // <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    //
    // メソッドがActivityの中でしか使えないのでここで定義する。
    //
    public Boolean isConnected(){
	return false;
    }

    public void logWord(String word){
    }
}
