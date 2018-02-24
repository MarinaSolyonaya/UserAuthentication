package com.example.marina.userauthentication.DataModel;

import java.util.ArrayList;

public class DataPresenterImpl implements DataPresenter {

    private DataView dataView;
    private ArrayList<Integer> timePress;
    private ArrayList<Integer> timeTouch;
    private DataModelImpl dataModel;


    public DataPresenterImpl(DataView dataView) {
        this.dataView = dataView;
        timePress = new ArrayList<>();
        timeTouch = new ArrayList<>();
        this.dataModel = new DataModelImpl();
    }

    public DataModelImpl getDataModel(){
        return dataModel;
    }

    @Override
    public void addTimePress() {
        timePress.add(dataView.getTimePress());
        dataModel.sendToData(timePress,timeTouch);

    }

    @Override
    public void addTimeTouch() {
        timeTouch.add(dataView.getTimeTouch());
        dataModel.sendToData(timePress,timeTouch);
    }

}
