package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcontroller.internal.Persistence;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.Assert.*;

// TODO: These tests aren't flexible with JSON formatting/ordering. Maybe parse the result to make sure it is valid and
// correct instead or comparing to another string.

// TODO: Add integration tests to account for the fact that can't access Context and therefore cannot read from or write
// to files. In the meantime, do manual testing on the actual phones.
public class PersistenceTest {
  @Before
  public void before() {
    Persistence.clear();
  }

  @Test
  public void saveFile() {
    Persistence.setKey("test_key", "test_value");

    String json = Persistence.saveFile();

    assertEquals("{\"test_key\":\"test_value\"}", json);
  }

  @Test
  public void loadFile() {
    BufferedReader reader = new BufferedReader(new StringReader("{\"test_key\":\"test_value\"}"));

    Persistence.loadFile(reader);

    assertEquals("test_value", Persistence.getKey("test_key", "not_test_value"));
  }

  /**
   * This tests both getKey and setKey
   */
  @Test
  public void getKey_setKey() {
    Persistence.setKey("test_key", "test_value");
    assertEquals("test_value", Persistence.getKey("test_key", "not_test_value"));
  }

  @Test
  public void setKey_ignoreNull() {
    Persistence.setKey(null, "test_value");
    Persistence.setKey("test_key", null);

    assertNotEquals("test_value", Persistence.getKey(null, "not_test_value"));
    assertNotEquals(null, Persistence.getKey("test_key", "not_test_value"));
  }

  @Test
  public void getKey_setDefault() {
    assertEquals("default", Persistence.getKey("test_key", "default"));
    // Second check makes sure that the default is really stored, not just returned and discarded
    assertEquals("default", Persistence.getKey("test_key", "not_default"));
  }

  /**
   * This tests the behavior of getKey if the key is null. The expected behavior is that an empty string is used in
   * place of the key.
   */
  @Test
  public void getKey_nullKey() {
    assertEquals("test_value", Persistence.getKey(null, "test_value"));
    assertEquals("test_value", Persistence.getKey("", "not_test_value"));
  }

  /**
   * This tests the behavior of getKey if the default value is null and the key does not exist. The expected behavior is
   * that an empty string is used in place of the default value, including being inserted into the map.
   */
  @Test
  public void getKey_nullDefaultNoRealValue() {
    assertEquals("", Persistence.getKey("nonexistent_key", null));
    assertEquals("", Persistence.getKey("nonexistent_key", "not_null_or_empty"));
  }
}
