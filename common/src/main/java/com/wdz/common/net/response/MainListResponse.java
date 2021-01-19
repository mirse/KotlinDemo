package com.wdz.common.net.response;

import java.util.ArrayList;
import java.util.List;

public class MainListResponse {
    public int curPage;
    public List<Datas> datas;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;


    @Override
    public String toString() {
        return "MainListResponse{" +
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
        public List<Tag> tags;
        public String title;
        public int type;
        public int userId;
        public int visible;
        public int zan;
        public class Tag{
            public String tag;

            @Override
            public String toString() {
                return "Tag{" +
                        "tag='" + tag + '\'' +
                        '}';
            }
        }

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


/*{
    "data": {
        "curPage": 1,
        "datas": [
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 502,
                "chapterName": "自助",!
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": true,
                "id": 16627,
                "link": "https://juejin.cn/post/6910524131407003656/", !
                "niceDate": "1天前",
                "niceShareDate": "1天前",!
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608982882000,
                "realSuperChapterId": 493,
                "selfVisible": 0,
                "shareDate": 1608982882000,
                "shareUser": "lcfspark",
                "superChapterId": 494,
                "superChapterName": "广场Tab",!
                "tags": [],
                "title": "重学系统---类加载机制（Java&amp;Android）",!
                "type": 0,
                "userId": 28772,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 502,
                "chapterName": "自助",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16603,
                "link": "https://juejin.cn/post/6909812895878643720",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608856805000,
                "realSuperChapterId": 493,
                "selfVisible": 0,
                "shareDate": 1608817951000,
                "shareUser": "hoholiday",
                "superChapterId": 494,
                "superChapterName": "广场Tab",
                "tags": [],
                "title": "内存泄漏这个锅Glide不背——聊聊Glide到底把context怎么了",
                "type": 0,
                "userId": 84014,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 502,
                "chapterName": "自助",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16620,
                "link": "https://xuexiangjys.blog.csdn.net/article/details/111659723",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608833991000,
                "realSuperChapterId": 493,
                "selfVisible": 0,
                "shareDate": 1608833991000,
                "shareUser": "xuexiang",
                "superChapterId": 494,
                "superChapterName": "广场Tab",
                "tags": [],
                "title": "你想要的Android全量版本更新功能,我这儿都有!",
                "type": 0,
                "userId": 42190,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 510,
                "chapterName": "大厂分享",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16612,
                "link": "https://juejin.cn/post/6908517174667804680",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608833778000,
                "realSuperChapterId": 509,
                "selfVisible": 0,
                "shareDate": 1608833546000,
                "shareUser": "鸿洋",
                "superChapterId": 510,
                "superChapterName": "大厂对外分享",
                "tags": [],
                "title": "抖音 Android 性能优化系列: Java 内存优化篇",
                "type": 0,
                "userId": 2,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 296,
                "chapterName": "阅读",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16614,
                "link": "https://juejin.cn/post/6908861370297499655",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608833756000,
                "realSuperChapterId": 180,
                "selfVisible": 0,
                "shareDate": 1608833578000,
                "shareUser": "鸿洋",
                "superChapterId": 202,
                "superChapterName": "延伸技术",
                "tags": [],
                "title": "给 2020 一份答卷 | 掘金年度征文",
                "type": 0,
                "userId": 2,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 228,
                "chapterName": "辅助 or 工具类",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16615,
                "link": "https://juejin.cn/post/6908862707374489607",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608833741000,
                "realSuperChapterId": 156,
                "selfVisible": 0,
                "shareDate": 1608833583000,
                "shareUser": "鸿洋",
                "superChapterId": 135,
                "superChapterName": "项目必备",
                "tags": [],
                "title": "聊聊获取屏幕高度这件事",
                "type": 0,
                "userId": 2,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 93,
                "chapterName": "基础知识",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16616,
                "link": "https://juejin.cn/post/6908937508625580039",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608833712000,
                "realSuperChapterId": 37,
                "selfVisible": 0,
                "shareDate": 1608833598000,
                "shareUser": "鸿洋",
                "superChapterId": 126,
                "superChapterName": "自定义控件",
                "tags": [],
                "title": "TextView 的文字是怎么自动换行的",
                "type": 0,
                "userId": 2,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 484,
                "chapterName": "okhttp",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16617,
                "link": "https://juejin.cn/post/6909445385266135048",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608833701000,
                "realSuperChapterId": 460,
                "selfVisible": 0,
                "shareDate": 1608833603000,
                "shareUser": "鸿洋",
                "superChapterId": 461,
                "superChapterName": "常见开源库源码解析",
                "tags": [],
                "title": "【长文预警⚠️】从一次请求开始，深入探索OkHttp",
                "type": 0,
                "userId": 2,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 126,
                "chapterName": "绘图相关",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16618,
                "link": "https://juejin.cn/post/6909347047887880199",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608833686000,
                "realSuperChapterId": 37,
                "selfVisible": 0,
                "shareDate": 1608833641000,
                "shareUser": "鸿洋",
                "superChapterId": 126,
                "superChapterName": "自定义控件",
                "tags": [],
                "title": "是Android的自定义View-基础知识-坐标系",
                "type": 0,
                "userId": 2,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 100,
                "chapterName": "RecyclerView",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16619,
                "link": "https://juejin.cn/post/6909363022980972552",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608833677000,
                "realSuperChapterId": 39,
                "selfVisible": 0,
                "shareDate": 1608833646000,
                "shareUser": "鸿洋",
                "superChapterId": 192,
                "superChapterName": "5.+高新技术",
                "tags": [],
                "title": "【Android】自定义无限循环的LayoutManager",
                "type": 0,
                "userId": 2,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "xuexiangjys",
                "canEdit": false,
                "chapterId": 294,
                "chapterName": "完整项目",
                "collect": false,
                "courseId": 13,
                "desc": "Android空壳模版工程，快速搭建（集成了XUI、XUtil、XAOP、XPage、XUpdate、XHttp2、友盟统计和walle多渠道打包)",
                "descMd": "",
                "envelopePic": "https://www.wanandroid.com/blogimgs/56e0e941-bc6b-49ac-955e-b79484f58fd4.png",
                "fresh": false,
                "id": 16607,
                "link": "https://www.wanandroid.com/blog/show/2858",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "https://github.com/xuexiangjys/TemplateAppProject",
                "publishTime": 1608826915000,
                "realSuperChapterId": 293,
                "selfVisible": 0,
                "shareDate": 1608826915000,
                "shareUser": "",
                "superChapterId": 294,
                "superChapterName": "开源项目主Tab",
                "tags": [
                    {
                        "name": "项目",
                        "url": "/project/list/1?cid=294"
                    }
                ],
                "title": "快速上手系列--Android应用开发模版",
                "type": 0,
                "userId": -1,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "baiyuliang",
                "canEdit": false,
                "chapterId": 294,
                "chapterName": "完整项目",
                "collect": false,
                "courseId": 13,
                "desc": "Kotlin+MVVM+Retrofit+协程+ViewBinding+EventBus，准备入门kotlin和mvvm的完全可以以本项目为基础，强大、简单、易用！\r\n",
                "descMd": "",
                "envelopePic": "https://www.wanandroid.com/resources/image/pc/default_project_img.jpg",
                "fresh": false,
                "id": 16606,
                "link": "https://www.wanandroid.com/blog/show/2857",
                "niceDate": "2天前",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "https://github.com/baiyuliang/MVVM",
                "publishTime": 1608826723000,
                "realSuperChapterId": 293,
                "selfVisible": 0,
                "shareDate": 1608826723000,
                "shareUser": "",
                "superChapterId": 294,
                "superChapterName": "开源项目主Tab",
                "tags": [
                    {
                        "name": "项目",
                        "url": "/project/list/1?cid=294"
                    }
                ],
                "title": "Kotlin+MVVM+Retrofit+协程+ViewBinding+EventBus",
                "type": 0,
                "userId": -1,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 502,
                "chapterName": "自助",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16602,
                "link": "https://juejin.cn/post/6907620425837051917",
                "niceDate": "2020-12-24 20:56",
                "niceShareDate": "2020-12-24 20:56",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608814600000,
                "realSuperChapterId": 493,
                "selfVisible": 0,
                "shareDate": 1608814600000,
                "shareUser": "思忆(George)",
                "superChapterId": 494,
                "superChapterName": "广场Tab",
                "tags": [],
                "title": "致敬Glide-借用其思想设计一个拍照选图控件",
                "type": 0,
                "userId": 25965,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 502,
                "chapterName": "自助",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16601,
                "link": "https://juejin.cn/post/6909778303976996872/",
                "niceDate": "2020-12-24 19:11",
                "niceShareDate": "2020-12-24 19:11",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608808299000,
                "realSuperChapterId": 493,
                "selfVisible": 0,
                "shareDate": 1608808299000,
                "shareUser": "彭旭锐",
                "superChapterId": 494,
                "superChapterName": "广场Tab",
                "tags": [],
                "title": "Java | 难以置信！Java 居然有第五种引用类型",
                "type": 0,
                "userId": 30587,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 502,
                "chapterName": "自助",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16596,
                "link": "https://mp.weixin.qq.com/s/WgFRWGgMVX2KTdl-249jSg",
                "niceDate": "2020-12-24 15:06",
                "niceShareDate": "2020-12-24 15:06",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608793589000,
                "realSuperChapterId": 493,
                "selfVisible": 0,
                "shareDate": 1608793589000,
                "shareUser": "徐公码字",
                "superChapterId": 494,
                "superChapterName": "广场Tab",
                "tags": [],
                "title": "Gson 和 Kotlin data class 的避坑指南",
                "type": 0,
                "userId": 5339,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 502,
                "chapterName": "自助",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16595,
                "link": "https://www.jianshu.com/p/1dae1284b7c8",
                "niceDate": "2020-12-24 14:39",
                "niceShareDate": "2020-12-24 14:39",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608791997000,
                "realSuperChapterId": 493,
                "selfVisible": 0,
                "shareDate": 1608791997000,
                "shareUser": "孙强 Jimmy",
                "superChapterId": 494,
                "superChapterName": "广场Tab",
                "tags": [],
                "title": "自定义 Lint 检查实践指南",
                "type": 0,
                "userId": 24124,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "SlamDunk007",
                "canEdit": false,
                "chapterId": 294,
                "chapterName": "完整项目",
                "collect": false,
                "courseId": 13,
                "desc": "根据wanandroid网站开源api开发的flutter项目",
                "descMd": "",
                "envelopePic": "https://www.wanandroid.com/blogimgs/60db7d4d-a1ac-465d-be7e-23d05da73759.png",
                "fresh": false,
                "id": 16584,
                "link": "https://www.wanandroid.com/blog/show/2854",
                "niceDate": "2020-12-24 00:38",
                "niceShareDate": "2020-12-24 00:38",
                "origin": "",
                "prefix": "",
                "projectLink": "https://github.com/SlamDunk007/playflutter",
                "publishTime": 1608741505000,
                "realSuperChapterId": 293,
                "selfVisible": 0,
                "shareDate": 1608741505000,
                "shareUser": "",
                "superChapterId": 294,
                "superChapterName": "开源项目主Tab",
                "tags": [
                    {
                        "name": "项目",
                        "url": "/project/list/1?cid=294"
                    }
                ],
                "title": "wanandroid的Flutter学习",
                "type": 0,
                "userId": -1,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "鸿洋",
                "canEdit": false,
                "chapterId": 408,
                "chapterName": "鸿洋",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16604,
                "link": "https://mp.weixin.qq.com/s/9W4imOoZ5s4vlTVKyKIIHg",
                "niceDate": "2020-12-24 00:00",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608739200000,
                "realSuperChapterId": 407,
                "selfVisible": 0,
                "shareDate": 1608826589000,
                "shareUser": "",
                "superChapterId": 408,
                "superChapterName": "公众号",
                "tags": [
                    {
                        "name": "公众号",
                        "url": "/wxarticle/list/408/1"
                    }
                ],
                "title": "面试官：为什么 Activity.finish() 之后 10s 才 onDestroy ？",
                "type": 0,
                "userId": -1,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "郭霖",
                "canEdit": false,
                "chapterId": 409,
                "chapterName": "郭霖",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16605,
                "link": "https://mp.weixin.qq.com/s/5hK2JFghfh4JTnxrqBurHg",
                "niceDate": "2020-12-24 00:00",
                "niceShareDate": "2天前",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608739200000,
                "realSuperChapterId": 407,
                "selfVisible": 0,
                "shareDate": 1608826605000,
                "shareUser": "",
                "superChapterId": 408,
                "superChapterName": "公众号",
                "tags": [
                    {
                        "name": "公众号",
                        "url": "/wxarticle/list/409/1"
                    }
                ],
                "title": "用烂的LruCache，你真的完全懂了么？",
                "type": 0,
                "userId": -1,
                "visible": 1,
                "zan": 0
            },
            {
                "apkLink": "",
                "audit": 1,
                "author": "",
                "canEdit": false,
                "chapterId": 502,
                "chapterName": "自助",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "descMd": "",
                "envelopePic": "",
                "fresh": false,
                "id": 16578,
                "link": "https://juejin.cn/post/6909110365356261389",
                "niceDate": "2020-12-23 09:11",
                "niceShareDate": "2020-12-23 09:11",
                "origin": "",
                "prefix": "",
                "projectLink": "",
                "publishTime": 1608685891000,
                "realSuperChapterId": 493,
                "selfVisible": 0,
                "shareDate": 1608685891000,
                "shareUser": "wildma",
                "superChapterId": 494,
                "superChapterName": "广场Tab",
                "tags": [],
                "title": "6+3 种单例模式详解！",
                "type": 0,
                "userId": 33655,
                "visible": 1,
                "zan": 0
            }
        ],
        "offset": 0,
        "over": false,
        "pageCount": 489,
        "size": 20,
        "total": 9762
    },
    "errorCode": 0,
    "errorMsg": ""
}*/

}

