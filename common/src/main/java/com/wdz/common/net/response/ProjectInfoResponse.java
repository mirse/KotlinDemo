package com.wdz.common.net.response;

import java.util.List;

public class ProjectInfoResponse {
    public int curPage;
    public List<Datas> datas;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;

    @Override
    public String toString() {
        return "Data{" +
                "curPage=" + curPage +
                ", datas=" + datas +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                '}';
    }

    public class Datas{
        public String apkLink;
        public int audit;
        public String author;
        public boolean canEdit;
        public int chapterId;
        public String chapterName;
        public boolean collect;
        public int courseId;
        public String desc;
        public String descMd;
        public String envelopePic;
        public boolean fresh;
        public int id;
        public String link;
        public String niceDate;
        public String niceShareDate;
        public String origin;
        public String prefix;
        public String projectLink;
        public long publishTime;
        public int realSuperChapterId;
        public int selfVisible;
        public long shareDate;
        public String shareUser;
        public int superChapterId;
        public String superChapterName;
        // TODO: 2021/3/19 Object 为对象
        public List<Object> tags;
        public String title;
        public int type;
        public int userId;
        public int visible;
        public int zan;

        @Override
        public String toString() {
            return "Datas{" +
                    "apkLink='" + apkLink + '\'' +
                    ", audit=" + audit +
                    ", author='" + author + '\'' +
                    ", canEdit=" + canEdit +
                    ", chapterId=" + chapterId +
                    ", chapterName='" + chapterName + '\'' +
                    ", collect=" + collect +
                    ", courseId=" + courseId +
                    ", desc='" + desc + '\'' +
                    ", descMd='" + descMd + '\'' +
                    ", envelopePic='" + envelopePic + '\'' +
                    ", fresh=" + fresh +
                    ", id=" + id +
                    ", link='" + link + '\'' +
                    ", niceDate='" + niceDate + '\'' +
                    ", niceShareDate='" + niceShareDate + '\'' +
                    ", origin='" + origin + '\'' +
                    ", prefix='" + prefix + '\'' +
                    ", projectLink='" + projectLink + '\'' +
                    ", publishTime=" + publishTime +
                    ", realSuperChapterId=" + realSuperChapterId +
                    ", selfVisible=" + selfVisible +
                    ", shareDate=" + shareDate +
                    ", shareUser='" + shareUser + '\'' +
                    ", superChapterId=" + superChapterId +
                    ", superChapterName='" + superChapterName + '\'' +
                    ", tags=" + tags +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", userId=" + userId +
                    ", visible=" + visible +
                    ", zan=" + zan +
                    '}';
        }
    }
}
