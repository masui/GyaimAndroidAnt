//
// LocalDict.javaのテスト用ダミー
//

package com.pitecan.gyaim;

import java.util.ArrayList;

public class Search {
    public static int ncands = 0;
    public static ArrayList<String> words = new ArrayList<String>();
    public static ArrayList<String> pats = new ArrayList<String>();

    public Search(){
    }
    
    public static void addCandidateWithLevel(String word, String pat, int level){
	words.add(word);
	pats.add(pat);
	ncands++;
    }
}
