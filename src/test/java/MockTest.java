import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lorenzo Jokhan on 18/04/2017.
 */
public class MockTest {

  @Test
  public void fakeTestTrue() {
    String text = "HelloWorld";
    Assert.assertEquals("HelloWorld", text);
  }
}
