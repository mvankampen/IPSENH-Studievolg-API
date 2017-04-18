import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Lorenzo Jokhan on 18/04/2017.
 */
public class MockTest extends TestCase {

    @Test
    public void fakeTestTrue() {
        String text = "HelloWorld";
        assertEquals("HelloWorld", text);
    }

//    public void fakeTestFalse() {
//        String text = "HelloWorldddd";
//        assertEquals("HelloWorld", text);
//    }
}
