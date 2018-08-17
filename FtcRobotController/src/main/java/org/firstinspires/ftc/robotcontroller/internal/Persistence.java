package org.firstinspires.ftc.robotcontroller.internal;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to access the persistence file. How this works:
 *  - The file is stored as JSON on the phone
 *  - It is read in the onCreate method of the FtcRobotControllerActivity so that the app starts with the data
 *  - It is written back in the onDestroy method of the FtcRobotControllerActivity so that the data is saved
 */
// TODO: Test the functionality of this class
public class Persistence {
  // This map stores the JSON data while the app is running.
  private static Map<String, String> map = new HashMap<>();

  /**
   * Retrieves the value for a specific key and sets that key/value pair if it is not already set.
   * @param key The key to retrieve the value for.
   * @param defaultValue The value to set for key if it does not exist.
   * @return The value at the key, which may be defaultValue.
   */
  public static String getKey(String key, String defaultValue) {
    if (!map.containsKey(key)) {
      if (key == null) key = "";
      if (defaultValue == null) defaultValue = "";
      setKey(key, defaultValue);
    }
    return map.get(key);
  }

  /**
   * Set the value at the key and creates the entry if it did not already exist.
   * @param key The key to set the value of.
   * @param newValue Te value to place at key.
   */
  public static void setKey(String key, String newValue) {
    // Ignore wrong values because the code calling this shouldn't crash. It's also safe because of the default values.
    if (key == null || newValue == null) return;
    map.put(key, newValue);
  }

  /**
   * Loads the persistence file in the assets folder and stores it into memory. This should be called
   * before any other method in this class because it overrides the internal map.
   * Sample file:
   *
   * persistence.json
   * {
   *   "example_key": "example_value",
   *   "the_file_should": "be key value",
   *   "with_string_keys": "and string values"
   * }
   *
   * @param context The Android context that has access to the assets.
   * @param filename The name of the file to load. persistence.json is recommended.
   */
  public static void loadFile(Context context, String filename) {
    try {
      InputStream is = context.openFileInput(filename);
      BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      loadFile(reader);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads the persistence file in the assets folder and stores it into memory. This should be called
   * before any other method in this class because it overrides the internal map.
   * Sample file:
   *
   * persistence.json
   * {
   *   "example_key": "example_value",
   *   "the_file_should": "be key value",
   *   "with_string_keys": "and string values"
   * }
   *
   * @param reader The buffer to read from
   */
  public static void loadFile(BufferedReader reader) {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    // TODO: Find a way to remove this warning
    map = gson.fromJson(reader, map.getClass());
  }

  /**
   * Save the file back
   * @param context The Android context that has access to the assets.
   * @param filename The name of the file to save to. persistence.json is recommended.
   */
  public static void saveFile(Context context, String filename) {
    FileOutputStream os;
    try {
      os = context.openFileOutput(filename, Context.MODE_PRIVATE);
    } catch (FileNotFoundException e) {
      // TODO: Handle this error by creating the file and setting os properly instead of returning
      e.printStackTrace();
      return;
    }

    try {
      new OutputStreamWriter(os).write(saveFile());
    } catch (IOException e) {
      // Probably not much can be done at this point
      e.printStackTrace();
    }
  }

  /**
   * Save the file back
   * @return The JSON representing the file
   */
  public static String saveFile() {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    return gson.toJson(map);
  }

  /**
   * Clear the map so there is no data.
   */
  public static void clear() {
    map.clear();
  }
}
