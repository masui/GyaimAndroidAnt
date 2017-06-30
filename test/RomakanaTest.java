import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.pitecan.gyaim.Romakana;

public class RomakanaTest {

    public RomakanaTest(){
    }

    @Test
    public void ますい(){
	assertTrue(Romakana.roma2hiragana("masui").equals("ますい"));
    }
    
    public void violin(){
	assertTrue(Romakana.roma2hiragana("vaiorin").equals("う゛ぁいおりん"));
	assertTrue(Romakana.roma2hiragana("vaiorin").equals("ヴァイオリン"));
    }
    
    @Test
    public void ハンニャ(){
	assertTrue(Romakana.roma2katakana("hannnya").equals("ハンニャ"));
    }
}

