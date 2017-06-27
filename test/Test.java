import com.pitecan.gyaim.LocalDict;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

public class Test {
    
    public static void main(String[] args){

	LocalDict dict;

	File file = new File("./dict.txt");
	System.out.println(file);

	try {
	    FileInputStream is = new FileInputStream(file);
	    System.out.println(is);
	    dict = new LocalDict(is);
	} catch (IOException e) {
	    System.out.println("exception");
	    e.printStackTrace();  
	}
    }
}
