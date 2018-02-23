package com.example.marina.userauthentication;

import java.util.ArrayList;

public class DataPresenterImpl implements DataPresenter {

    private DataView dataView;
    private DataModel dataModel;
    private ArrayList<Integer> timePress;
    private ArrayList<Integer> timeTouch;


    public DataPresenterImpl(DataView dataView) {
        this.dataView = dataView;
        timePress = new ArrayList<>();
        timeTouch = new ArrayList<>();
        this.dataModel = new DataModelImpl();
    }

    @Override
    public void addTimePress() {
        timePress.add(dataView.getTimePress());
        dataModel.writeToFile(timePress,timeTouch);

    }

    @Override
    public void addTimeTouch() {
        timeTouch.add(dataView.getTimeTouch());
        dataModel.writeToFile(timePress,timeTouch);
    }

    public ArrayList<Integer> getTimePress() {
        return timePress;
    }

    public ArrayList<Integer> getTimeTouch() {
        return timeTouch;
    }
}
