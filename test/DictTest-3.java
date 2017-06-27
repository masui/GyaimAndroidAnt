// JUnit 3.x の書き方

import junit.framework.*;

//import static org.junit.Assert.*;
//import org.junit.Test;

 public class DictTest extends TestCase {
    public DictTest(){
    	super();
    }

    public void testNum() {
	// assertEquals(10, 10);
	assertEquals("10じゃない!!",10, 10);
	assertEquals("10じゃない!!",10, 10);
    }
}
