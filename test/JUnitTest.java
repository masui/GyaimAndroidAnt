import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JUnitTest {

    @Test
    public void testNum() {
	// assertEquals(10, 10);
	assertEquals("10じゃない!!",10, 10);
	assertEquals("10じゃない!!",10, 10);
    }

    @Test
    public void checkAllOf() {
	// 全てのMatcher条件が一致していればOK
	assertThat(
		   "Hello World",
		   allOf(
			 is("Hello World"),
			 containsString(" "),
			 startsWith("Hello")
			 )
		   );
    }
}
