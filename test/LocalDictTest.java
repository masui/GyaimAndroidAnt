import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import com.pitecan.gyaim.LocalDict;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LocalDictTest {

    @Test
    public void open(){
	LocalDict dict;
	File file = new File("../assets/dict.txt");
	System.out.println(file);
	try {
	    FileInputStream is = new FileInputStream(file);
	    System.out.println("is = " + is);
	    //dict = new LocalDict(is);
	} catch (IOException e) {
	    System.out.println("FileInputStream trouble");
	    e.printStackTrace();  
	}
	assertEquals(10, 10);
    }
}
