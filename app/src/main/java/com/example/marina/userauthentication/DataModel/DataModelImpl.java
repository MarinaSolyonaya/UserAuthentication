package com.example.marina.userauthentication.DataModel;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class DataModelImpl extends AppCompatActivity implements DataModel, Parcelable {
    final String LOG_TAG = "myLogs";
    final String DIR_SD = "MyData";
    final String FILENAME_SD = "DataTouch.txt";

    private ArrayList<Integer> timePress = new ArrayList<>();
    private ArrayList<Integer> timeTouch = new ArrayList<>();


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
            Log.i("TAG", e.getMessage());
        }

    }

    @Override
    public void sendToData(ArrayList timePress, ArrayList timeTouch) {
        this.timePress = timePress;
        this.timeTouch = timeTouch;
    }

    public DataModelImpl() {
    }

    @Override
    public ArrayList<Integer> getTimePress() {
        return timePress;
    }

    @Override
    public ArrayList<Integer> getTimeTouch() {
        return timeTouch;
    }


    /*public void sendToEmail(String types, String name) {
        String contact = "marina.solyonaya@yandex.ru";
        String subject = name;
        String message = types + "\nPress \t";
        for (int i = 0; i < timePress.size(); i++) {
            message += timePress.get(i).toString() + " ";
        }
        message += "\ntimeTouch\n";
        for (int i = 0; i < timeTouch.size(); i++) {
            message += timeTouch.get(i).toString() + " ";
        }

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO); // отправляем письмо
        emailIntent.setType("plain/text"); // устанавливаем тип содержимого
        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{contact});
        // Зачем
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            DataModelImpl.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //startActivity(emailIntent);
        } else {
            Toast.makeText(this, "Error.", Toast.LENGTH_SHORT).show();
        }
    }
    */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel p, int flags) {
        p.writeInt(timePress.size());
        for (int i = 0; i < timePress.size(); i++) {
            p.writeInt(timePress.get(i));
        }
        p.writeInt(timeTouch.size());
        for (int i = 0; i < timeTouch.size(); i++) {
            p.writeInt(timeTouch.get(i));
        }
    }

    public static final Parcelable.Creator<DataModelImpl> CREATOR = new Parcelable.Creator<DataModelImpl>() {
        // распаковываем объект из Parcel
        public DataModelImpl createFromParcel(Parcel in) {
            ;
            return new DataModelImpl(in);
        }

        public DataModelImpl[] newArray(int size) {
            return new DataModelImpl[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private DataModelImpl(Parcel p) {
        int count = p.readInt();
        for (int i = 0; i < count; i++) {
            timePress.add(p.readInt());
        }
        count = p.readInt();
        for (int i = 0; i < count; i++) {
            timeTouch.add(p.readInt());
        }
    }
}
