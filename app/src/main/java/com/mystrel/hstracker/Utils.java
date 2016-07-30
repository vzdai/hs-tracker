package com.mystrel.hstracker;

import android.content.Context;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Vivian on 2016-07-29.
 */
public class Utils {
    public static void saveData(String fileName, JSONObject data, Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            if(data != null) {
                outputStream.write(data.toString().getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject loadData(String fileName, Context context) {
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String jsonString = new String(buffer, "UTF-8");
            return new JSONObject(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
