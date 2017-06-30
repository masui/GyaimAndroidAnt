//
// ローカル辞書検索のテスト
//
// * JUnit 4.12を利用
//   - @Test というアノテーションをつけるとテスト関数と解釈される
//   - assertThat() みたいなのが使える
// * 日本語でわかりやすくしてみたり
//
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
	String 必須単語[] = { "漢字", "東京", "増井", "料理" };
	for(String 単語: 必須単語){
	    boolean 登録されてる = false;
	    for(LocalDict.DictEntry entry: localDict.dict()) {
		if(entry.word().equals(単語)) 登録されてる = true;
	    }
	    assertThat(登録されてる,is(true));
	}
    }

    @Test
    public void 検索テスト(){
	// localDict.search("kaNgae",new SearchTask());

	localDict.search("kangae",new SearchTask());
	assertTrue(Search.words.size() > 0);
	boolean 考えるが検索された = false;
	for(String word: Search.words) {
	    if(word.equals("考える")) 考えるが検索された = true;
	}
	assertTrue(考えるが検索された);

	localDict.search("atarashii",new SearchTask());
	assertTrue(Search.words.size() > 0);
	boolean 新しいが検索された = false;
	for(String word: Search.words) {
	    if(word.equals("新しい")) 新しいが検索された = true;
	}
	assertTrue(新しいが検索された);
    }
}
