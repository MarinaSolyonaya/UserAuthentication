package com.example.marina.userauthentication.SendToEmail;



import android.provider.ContactsContract;

import com.example.marina.userauthentication.DataModel.DataModel;
import com.example.marina.userauthentication.DataModel.DataModelImpl;

import java.util.ArrayList;


public class SendToEmailPresenterImpl implements SendToEmailPresenter {

    SendToEmailView sendView;
    DataModel dataModel;
    String  types, name;


    public SendToEmailPresenterImpl(SendToEmailView sendView, DataModel dataModel) {
        this.sendView = sendView;
        this.dataModel = dataModel;
    }


    @Override
    public ArrayList<Integer> getTimeTouch() {
        return dataModel.getTimeTouch();
    }

    @Override
    public ArrayList<Integer> getTimePress() {
        return dataModel.getTimePress();
    }


}
