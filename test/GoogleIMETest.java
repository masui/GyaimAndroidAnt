import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.pitecan.gyaim.GoogleIME;
import com.pitecan.gyaim.Message;

public class GoogleIMETest {
    String 変換例リスト[][] = {
        {"べんきょう", "勉強"},
        {"ますい", "増井"},
        {"いちのもと", "櫟本"},
        {"むさしぼうべんけい","武蔵坊弁慶"},
        {"かまくらのかいがん", "鎌倉の海岸"}
    };
    
    @Test
    public void Google変換チェック() {
        for (String[] 変換例: 変換例リスト) {
            boolean found = false;
            for(String word: GoogleIME.convert(変換例[0])){
                if(word.equals(変換例[1])) found = true;
            }
            assertTrue(found);
            // assertTrue(GoogleIME.convert(変換例[0]).contains(変換例[1]));
        }
    }
}
