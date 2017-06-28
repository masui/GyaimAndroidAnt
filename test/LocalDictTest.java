//
// ローカル辞書検索のテスト
//
// JUnit 4.12を利用
// compile:
//    javac -classpath 'jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar' ../src/com/pitecan/gyaim/LocalDict.java LocalDictTest.java
// test:
//    java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src" org.junit.runner.JUnitCore LocalDictTest
//
// @Test というアノテーションをつけるとテスト関数と解釈されるらしい
//

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.pitecan.gyaim.LocalDict;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class LocalDictTest {
    static final String DICTFILE = "../assets/dict.txt";
    static LocalDict localDict;

    public LocalDictTest(){
	File file = new File(DICTFILE);
	try {
	    FileInputStream is = new FileInputStream(file);
	    localDict = new LocalDict(is);
	} catch (IOException e) {
	    System.out.println("辞書読出し失敗");
	    e.printStackTrace();  
	}
    }

    @Test
    public void 辞書のサイズが充分大きい(){
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
	assertTrue(漢字が登録されてる && 増井が登録されてる);
    }
}
