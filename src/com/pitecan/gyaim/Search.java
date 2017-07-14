//
// いろんな辞書を検索するものをまとめる
// その後でまとめてAsyncTaskにする。
// 
package com.pitecan.gyaim;

import java.util.Arrays;
//import java.util.ArrayList;
import android.util.Log;

import android.content.res.AssetManager;
import java.io.InputStream;
import java.io.IOException;

class Candidate {
    String pat, word;
    int weight;
    
    public Candidate(String pat, String word, int weight){
        this.pat = pat;
        this.word = word;
        this.weight = weight;
    }
}

public class Search {
    private static Gyaim gyaim;
    
    public static Candidate[] candidates = new Candidate[Gyaim.MAXCANDS];  // 候補単語リスト
    public static int ncands = 0;
    public static boolean useGoogle = true; // GoogleIMEで検索するかどうか
    
    public Search(Gyaim gyaim){
        this.gyaim = gyaim;
        //
        // 内蔵固定辞書
        //
        try {
            AssetManager as = gyaim.getResources().getAssets();
            InputStream is = as.open("dict.txt");
            new LocalDict(is);
        } catch (IOException e) {  
            //e.printStackTrace();  
        }
        //
        // 学習辞書(SQL)
        //
        new SQLDict(gyaim);
        
        for(int i=0;i<Gyaim.MAXCANDS;i++){
            candidates[i] = new Candidate("","",0);
        }
    }
    
    public static void reset(){
        ncands = 0;
    }
    
    //
    // いろんな辞書を使った検索!
    //
    public static Candidate[] search(String pat, SearchTask searchTask){
        Message.message("Gyaim","Search - pat="+pat);
        ncands = 0;
        
        //
        // 完全一致モードではひらがな/カタカナを候補に出す
        //
        if(LocalDict.exactMode){
            String hiragana = Romakana.roma2hiragana(pat);
            String katakana = Romakana.roma2katakana(pat);
            addCandidateWithLevel(hiragana,pat,-100);
            addCandidateWithLevel(katakana,pat,-99);
        }
        
        //
        // クリップボードの単語を候補に出す (新規登録用)
        //
        if(!LocalDict.exactMode){
            String s = gyaim.getNewClipboardText();
            if(s != "" && s.length() < 10){ // コピー文字列が短い場合だけ候補にする
                addCandidate(s,pat);
            }
        }
        
        //
        // SQLの学習辞書検索
        //
        String[][] sqldata = SQLDict.search(pat,LocalDict.exactMode);
        for(int k=0;k<sqldata.length;k++){
            addCandidateWithLevel(sqldata[k][0],sqldata[k][1],-50+k);
        }
        
        //
        // 通常のローカル辞書を検索
        // 時間がかかることがあるのでSearchTaskでバックグラウンド動作させている。
        // 何かキー入力があれば isCancelled() がtrueになる。
        //
        LocalDict.search(pat,searchTask);
        
        //
        // GoogleIME
        //
        Message.message("Gyaim","UseGoogle = " + useGoogle);
        if(useGoogle && !searchTask.isCancelled() && gyaim.isConnected()){
            // Google日本語入力APIを利用
            Message.message("Gyaim","isConnected() = " + gyaim.isConnected());
            String[] words = GoogleIME.convert(Romakana.roma2hiragana(pat));
            Log.v("Gyaim","length="+words.length);
            for(String word: words){
                Message.message("Gyaim","Use Google ... word = "+word);
                addCandidateWithLevel(word,KeyController.inputPat(),50);
            }
        }
            
        // 優先度に従って候補を並べなおし
        //for(int j=ncands;j<Gyaim.MAXCANDS;j++){
        //  candidates[j].weight = 100;
        //}
        // ソートをやめてみたが全く違いがわからない... 要るのだろうか?? (2012/12/11 08:58:42)
        //Arrays.sort(candidates, new CandidateComparator());
        
        return candidates;
    }
    
    public static void addCandidate(String word, String pat){
        addCandidateWithLevel(word,pat,0);
    }
    
    public static void addCandidateWithLevel(String word, String pat, int level){
        int i;
        //Message.message("Gyaim","addCandidate: word="+word+" pat="+pat+" ncands="+ncands+" level="+level);
        if(ncands >= Gyaim.MAXCANDS) return;
        for(i=0;i<ncands;i++){
            if(candidates[i].word == null) break;
            if(candidates[i].word.equals(word)) break;
        }
        if(i >= ncands){
            candidates[ncands].pat = pat;
            candidates[ncands].word = word;
            candidates[ncands].weight = level;
            //Message.message("Gyaim", "Add "+word+" to candidates");
            ncands++;
        }
    }
}
