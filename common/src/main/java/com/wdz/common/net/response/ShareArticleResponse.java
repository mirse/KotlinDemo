package com.wdz.common.net.response;

import java.util.List;

public class ShareArticleResponse {
    public Data data;

    @Override
    public String toString() {
        return "ShareArticleResponse{" +
                "data=" + data +
                '}';
    }

    class Data {
        public CoinInfo coinInfo;

        public ShareArticles shareArticles;

        @Override
        public String toString() {
            return "Data{" +
                    "coinInfo=" + coinInfo +
                    ", shareArticles=" + shareArticles +
                    '}';
        }

        class ShareArticles {
            public int curPage;

            public List<Datas> datas;

            public int offset;

            public boolean over;

            public int pageCount;

            public int size;

            public int total;

            @Override
            public String toString() {
                return "ShareArticles{" +
                        "curPage=" + curPage +
                        ", datas=" + datas +
                        ", offset=" + offset +
                        ", over=" + over +
                        ", pageCount=" + pageCount +
                        ", size=" + size +
                        ", total=" + total +
                        '}';
            }

            class Datas {
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

                public String host;

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
                            ", host='" + host + '\'' +
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

        class CoinInfo {
            public int coinCount;

            public int level;

            public String nickname;

            public String rank;

            public int userId;

            public String username;

            @Override
            public String toString() {
                return "CoinInfo{" +
                        "coinCount=" + coinCount +
                        ", level=" + level +
                        ", nickname='" + nickname + '\'' +
                        ", rank='" + rank + '\'' +
                        ", userId=" + userId +
                        ", username='" + username + '\'' +
                        '}';
            }
        }
    }
}
