package officeapp.zdd.com.recyclerviewdemo.entity;

import java.io.Serializable;

/**
 * Created by Admin on 2017/7/19.
 * 第一级的菜单显示
 */

public class TypeOneEntity implements Serializable {
    private static final long serialVersionUID = -713298514563307993L;
    private String currentDate;
    private String CurrentPeople;
    private String classify;
    private String todayWeather;

    public TypeOneEntity() {
    }

    public TypeOneEntity(String currentDate, String currentPeople, String classify, String todayWeather) {
        this.currentDate = currentDate;
        CurrentPeople = currentPeople;
        this.classify = classify;
        this.todayWeather = todayWeather;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentPeople() {
        return CurrentPeople;
    }

    public void setCurrentPeople(String currentPeople) {
        CurrentPeople = currentPeople;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(String todayWeather) {
        this.todayWeather = todayWeather;
    }
}
