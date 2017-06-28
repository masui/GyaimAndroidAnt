//
// ローカル辞書ファイルを使った変換
//
// このファイルにはAndroid依存のものを入れないようにしたいのだが
//
package com.pitecan.gyaim;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;

public class LocalDict {
    public class DictEntry {
	String pat, word;
	int inConnection, outConnection;
	int keyLink;
	int connectionLink;
	
	public DictEntry(String p, String w, int i, int o){
	    pat = p;
	    word = w;
	    inConnection = i;
	    outConnection = o;
	}

	public String word(){
	    return word;
	}
    }

    static ArrayList<DictEntry> dict = new ArrayList<DictEntry>();

    public LocalDict(InputStream is){
	try {
	    InputStreamReader in;
	    BufferedReader br;
	    
	    String line;

	    in = new InputStreamReader(is);
	    br = new BufferedReader(in);
            while ((line = br.readLine()) != null) {
		// Message.message("Gyaim",line);
		int c = line.charAt(0);
		if(c == '#' || c == ' ' || c == '\t') continue; // コメント行
		String[] a = line.split("\t",4);
		if(a[3] == null || a[3] == "" || a[3].length() == 0 || ("" + a[3] == "")) a[3] = "0";
		// Message.message("Gyaim",a[1]);
		dict.add(new DictEntry(a[0],a[1],Integer.valueOf(a[2]),Integer.valueOf(a[3])));
            }
            br.close();
	    in.close();
	    // Message.message("Gyaim","" + dict.size());
	    
	} catch (IOException e) {  
	    e.printStackTrace();  
	}
    }

    public ArrayList<DictEntry> dict(){
	return dict;
    }
}
