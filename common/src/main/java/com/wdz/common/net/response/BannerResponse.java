package com.wdz.common.net.response;

import java.util.List;

public class BannerResponse {
    public List<Data> data;

    @Override
    public String toString() {
        return "BannerResponse{" +
                "data=" + data +
                '}';
    }

    class Data{
        public String desc;
        public int id;
        public String imagePath;
        public int isVisible;
        public int order;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "Data{" +
                    "desc='" + desc + '\'' +
                    ", id=" + id +
                    ", imagePath='" + imagePath + '\'' +
                    ", isVisible=" + isVisible +
                    ", order=" + order +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
    /*{
    "data": [
        {
            "desc": "一起来做个App吧",
            "id": 10,
            "imagePath": "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
            "isVisible": 1,
            "order": 1,
            "title": "一起来做个App吧",
            "type": 0,
            "url": "https://www.wanandroid.com/blog/show/2"
        },
        {
            "desc": "",
            "id": 6,
            "imagePath": "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
            "isVisible": 1,
            "order": 1,
            "title": "我们新增了一个常用导航Tab~",
            "type": 1,
            "url": "https://www.wanandroid.com/navi"
        },
        {
            "desc": "",
            "id": 20,
            "imagePath": "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png",
            "isVisible": 1,
            "order": 2,
            "title": "flutter 中文社区 ",
            "type": 1,
            "url": "https://flutter.cn/"
        }
    ],
    "errorCode": 0,
    "errorMsg": ""
}*/
}
