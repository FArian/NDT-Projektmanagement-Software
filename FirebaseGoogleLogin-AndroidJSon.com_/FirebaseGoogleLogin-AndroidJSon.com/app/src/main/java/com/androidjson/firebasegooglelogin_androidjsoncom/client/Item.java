package com.androidjson.firebasegooglelogin_androidjsoncom.client;

import com.androidjson.firebasegooglelogin_androidjsoncom.R;

/**
 * Created by F.Arian on 07.01.18.
 */

public class Item {

    private String info;
    private String value;
    private String type;
    private int safetyCoverDrawableId;
    private boolean warning=false;


    public Item(String type, String info, String value, int safetyCoverDrawableId) {
        this.info = info;
        this.value = value;
        this.type=type;
        this.safetyCoverDrawableId = safetyCoverDrawableId;
    }
    public Item(String type, String info, int safetyCoverDrawableId) {
        this.info = info;
        this.value = "";
        this.type=type;
        this.safetyCoverDrawableId = safetyCoverDrawableId;
    }
    public Item(){
        this.info="";
        this.value="";
        this.safetyCoverDrawableId= R.drawable.report_logo;

    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSafetyCoverDrawableId() {
        return safetyCoverDrawableId;
    }

    public void setSafetyCoverDrawableId(int safetyCoverDrawableId) {
        this.safetyCoverDrawableId = safetyCoverDrawableId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }


}
