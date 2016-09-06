/**
  * Copyright 2016 aTool.org 
  */
package cn.oddcloud.www.oc_reload.Model;
import java.util.List;
/**
 * Auto-generated: 2016-08-31 14:39:34
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Contentlist {


    private List<Alllist> alllist;

    private String pubdate;
    private String title;

    private String channelname;
    private List<Imageurls> imageurls;
    private String desc;
    private String source;
    private String channelid;
    private String link;
    public void setAlllist(List<Alllist> alllist) {
         this.alllist = alllist;
     }
     public List<Alllist> getAlllist() {
         return alllist;
     }

    public void setPubdate(String pubdate) {
         this.pubdate = pubdate;
     }
     public String getPubdate() {
         return pubdate;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setChannelname(String channelname) {
         this.channelname = channelname;
     }
     public String getChannelname() {
         return channelname;
     }

    public void setImageurls(List<Imageurls> imageurls) {
         this.imageurls = imageurls;
     }
     public List<Imageurls> getImageurls() {
         return imageurls;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

    public void setSource(String source) {
         this.source = source;
     }
     public String getSource() {
         return source;
     }

    public void setChannelid(String channelid) {
         this.channelid = channelid;
     }
     public String getChannelid() {
         return channelid;
     }

    public void setLink(String link) {
         this.link = link;
     }
     public String getLink() {
         return link;
     }

}