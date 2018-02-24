package com.example.marina.userauthentication.SendToEmail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marina.userauthentication.DataModel.DataModel;
import com.example.marina.userauthentication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendToEmailActivity extends AppCompatActivity implements SendToEmailView {


    @BindView(R.id.wayTypes) EditText wayTypes;
    @BindView(R.id.name) EditText nameText;
    String contact, text, name;
    SendToEmailPresenter sendToEmailPresenter;
    DataModel dataModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_data_to_email);
        dataModel = getIntent().getParcelableExtra("dataModel");
        sendToEmailPresenter = new SendToEmailPresenterImpl(this, dataModel);
        ButterKnife.bind(this);
    }


    @Override
    public String getWayType() {
        return text;
    }

    @Override
    public String getName() {
        return name;
    }

    @OnClick(R.id.buttonSend)
    void sendToMail(){
        text = wayTypes.getText().toString();
        name = nameText.getText().toString();
        sendToEmail(text,name);

    }

    public void sendToEmail(String types, String name) {
        String contact = "marina.solyonaya@yandex.ru";
        String subject = name;
        String message = types + "\nPress \t";
        for (int i = 0; i < sendToEmailPresenter.getTimePress().size(); i++) {
            message += sendToEmailPresenter.getTimePress().get(i).toString() + " ";
        }
        message += "\ntimeTouch\n";
        for (int i = 0; i < sendToEmailPresenter.getTimeTouch().size(); i++) {
            message += sendToEmailPresenter.getTimeTouch().get(i).toString() + " ";
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND); // отправляем письмо
        emailIntent.setType("plain/text"); // устанавливаем тип содержимого
        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{contact});
        // Зачем
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
        if (emailIntent.resolveActivity(getPackageManager()) != null){
            this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //startActivity(emailIntent);
        }
        else{
            Toast.makeText(this, "Error.", Toast.LENGTH_SHORT).show();
        }


    }
}
