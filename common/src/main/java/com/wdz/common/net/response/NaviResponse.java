package com.wdz.common.net.response;

import java.util.List;

public class NaviResponse {
    public List<Data> data;

    @Override
    public String toString() {
        return "NaviResponse{" +
                "data=" + data +
                '}';
    }

    class Data{
        public List<Articles> articles;
        public int cid;
        public String name;

        @Override
        public String toString() {
            return "Data{" +
                    "articles=" + articles +
                    ", cid=" + cid +
                    ", name='" + name + '\'' +
                    '}';
        }

        class Articles{
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
            public int publishTime;
            public int realSuperChapterId;
            public int selfVisible;
            public int shareDate;
            public String shareUser;
            public int superChapterId;
            public String superChapterName;
            public List<String> tags;
            public String title;
            public int type;
            public int userId;
            public int visible;
            public int zan;

            @Override
            public String toString() {
                return "Articles{" +
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
}
