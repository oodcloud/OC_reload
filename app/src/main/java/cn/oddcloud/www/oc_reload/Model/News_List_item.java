package cn.oddcloud.www.oc_reload.Model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/1.
 */
public class News_List_item implements Serializable{
    private  String IMG;
    private String TITLE;
    private  String  TIME;
    private  String site;


    public String getIMG() {
        return IMG;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getTIME() {
        return TIME;
    }

    public String getSite() {
        return site;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
