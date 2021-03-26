package com.wdz.main.main.bean;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableField;

/**
 * @Author dezhi.wang
 * @Date 2021/1/18 16:16
 */
public class MainArticle {
    public String link;
    public String chapterName;
    public String niceShareDate;
    public String title;
    public int id;
    public boolean collect;


    @Override
    public boolean equals(@Nullable Object obj) {
        if(this==obj){
            return true;
        }
        //instanceof比较判断是否是同一类或者子父类关系
        if(! (obj instanceof MainArticle)){
            return false;
        }
        MainArticle ob1 = (MainArticle)obj;
        if (this.link.equals(ob1.link) && this.chapterName.equals(ob1.chapterName) && this.niceShareDate.equals(ob1.niceShareDate)
        && this.title.equals(ob1.title)  && this.id == ob1.id
        ){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public String toString() {
        return "MainArticle{" +
                "link='" + link + '\'' +
                ", chapterName='" + chapterName + '\'' +
                ", niceShareDate='" + niceShareDate + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
