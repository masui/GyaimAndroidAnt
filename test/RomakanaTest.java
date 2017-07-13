//
// ローマ字かな変換のユニットテスト
//

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.pitecan.gyaim.Romakana;

public class RomakanaTest {

    String testdata[][] = {
	{ "masui", "ますい", "マスイ" },
	{ "dhisukushisutemu", "でぃすくしすてむ", "ディスクシステム" },
        { "dexisukusisutemu", "でぃすくしすてむ", "ディスクシステム" },
        { "shachou", "しゃちょう", "シャチョウ" },
        { "syatyou", "しゃちょう", "シャチョウ" },
        { "vaiorin", "う゛ぁいおりん", "ヴァイオリン" },
	{ "hannnya", "はんにゃ", "ハンニャ"}
    };

    @Test
    public void かな変換テスト(){
	for(String[] data: testdata){
	    assertTrue(Romakana.roma2hiragana(data[0]).equals(data[1]));
	    assertTrue(Romakana.roma2katakana(data[0]).equals(data[2]));
	}
    }
}

