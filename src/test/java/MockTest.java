import junit.framework.TestCase;

/**
 * Created by Lorenzo Jokhan on 18/04/2017.
 */
public class MockTest extends TestCase {

    public void fakeTestTrue() {
        String text = "HelloWorld";
        assertEquals("HelloWorld", text);
    }

    public void fakeTestFalse() {
        String text = "HelloWorldddd";
        assertEquals("HelloWorld", text);
    }
}
