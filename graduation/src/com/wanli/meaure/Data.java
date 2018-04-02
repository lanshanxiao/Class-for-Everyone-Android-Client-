package com.wanli.meaure;

/**
 * Created by windows on 2018/1/2.
 */
public class Data {

    private int ID;
    private String temperature ;//温度
    private String humidity;//湿度
    private String equipment;//设备

    public int getID() {
        return ID;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
