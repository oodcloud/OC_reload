package cn.oddcloud.www.oc_reload.Model;

import cn.bmob.v3.BmobObject;

/**
 * Created by wangyujie on 2016/9/6.
 */
public class Liulan_Page extends BmobObject{



    String title;
    String time;
    String site;
    String content;
    String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
