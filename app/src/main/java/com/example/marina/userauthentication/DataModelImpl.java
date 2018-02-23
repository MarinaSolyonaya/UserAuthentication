package com.example.marina.userauthentication;


import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class DataModelImpl implements DataModel {
    final String LOG_TAG = "myLogs";
    final String DIR_SD = "MyData";
    final String FILENAME_SD = "DataTouch.txt";

    @Override
    public void writeToFile(ArrayList timePress, ArrayList timeTouch) {


        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        try {
            // получаем путь к SD
            File sdPath = Environment.getExternalStorageDirectory();

            // формируем объект File, который содержит путь к файлу
            File sdFile = new File(sdPath, FILENAME_SD);

            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));


            bw.write("\ntimePress\n");

            for (int i = 0; i < timePress.size(); i++) {
                bw.write(timePress.get(i).toString() + " ");
            }
            bw.write("\ntimeTouch\n");
            for (int i = 0; i < timeTouch.size(); i++) {
                bw.write(timeTouch.get(i).toString() + " ");
            }
            bw.write("\n");

            bw.close();
            Log.d(LOG_TAG, "Записано");
        } catch (IOException e) {
            Log.i("TAG",e.getMessage());
        }

    }
}
