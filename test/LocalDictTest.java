//
// ローカル辞書検索のテスト
//
// * JUnit 4.12を利用
//   - @Test というアノテーションをつけるとテスト関数と解釈される
//   - assertThat() みたいなのが使える
// * 日本語でわかりやすくしてみたり
//

// package com.pitecan.gyaim;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.pitecan.gyaim.LocalDict;
import com.pitecan.gyaim.SearchTask;
import com.pitecan.gyaim.Search;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class LocalDictTest {
    static final String 単語辞書ファイル = "../assets/dict.txt";
    static LocalDict localDict;

    public LocalDictTest(){
	File file = new File(単語辞書ファイル);
	try {
	    FileInputStream is = new FileInputStream(file);
	    localDict = new LocalDict(is);
	} catch (IOException e) {
	    System.out.println("辞書読出し失敗");
	    e.printStackTrace();  
	}
    }

    @Test
    public void 単語辞書のサイズが充分大きい(){
	assertTrue(localDict.dict().size() > 10000);
    }

    @Test
    public void 単語登録チェック(){
	boolean 漢字が登録されてる = false;
	boolean 増井が登録されてる = false;
	for(LocalDict.DictEntry entry: localDict.dict()) {
	    if(entry.word().equals("漢字")) 漢字が登録されてる = true;
	    if(entry.word().equals("増井")) 増井が登録されてる = true;
	}
	assertThat(漢字が登録されてる,is(true));
	assertThat(増井が登録されてる,is(true));
    }

    @Test
    public void 検索テスト(){
	localDict.search("kaNgae",new SearchTask());
	assertTrue(Search.words.size() > 0);
	boolean 考えるが検索された = false;
	for(String word: Search.words) {
	    if(word.equals("考える")) 考えるが検索された = true;
	}
	assertThat(考えるが検索された,is(true));
	assertTrue(考えるが検索された);
    }
}
