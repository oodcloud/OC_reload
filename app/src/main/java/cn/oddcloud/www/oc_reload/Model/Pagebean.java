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
public class Pagebean {


    private int allpages;
    private List<Contentlist> contentlist;

    private int currentpage;

    private int allnum;

    private int maxresult;
    public void setAllpages(int allpages) {
         this.allpages = allpages;
     }
     public int getAllpages() {
         return allpages;
     }

    public void setContentlist(List<Contentlist> contentlist) {
         this.contentlist = contentlist;
     }
     public List<Contentlist> getContentlist() {
         return contentlist;
     }

    public void setCurrentpage(int currentpage) {
         this.currentpage = currentpage;
     }
     public int getCurrentpage() {
         return currentpage;
     }

    public void setAllnum(int allnum) {
         this.allnum = allnum;
     }
     public int getAllnum() {
         return allnum;
     }

    public void setMaxresult(int maxresult) {
         this.maxresult = maxresult;
     }
     public int getMaxresult() {
         return maxresult;
     }

}