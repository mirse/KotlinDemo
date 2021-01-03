package com.wdz.common.net.response;

public class CollectWebResponse {
    public Data data;

    @Override
    public String toString() {
        return "CollectWebResponse{" +
                "data=" + data +
                '}';
    }

    class Data{
        public String desc;

        public String icon;

        public int id;

        public String link;

        public String name;

        public int order;

        public int userId;

        public int visible;

        @Override
        public String toString() {
            return "Data{" +
                    "desc='" + desc + '\'' +
                    ", icon='" + icon + '\'' +
                    ", id=" + id +
                    ", link='" + link + '\'' +
                    ", name='" + name + '\'' +
                    ", order=" + order +
                    ", userId=" + userId +
                    ", visible=" + visible +
                    '}';
        }
    }
}
