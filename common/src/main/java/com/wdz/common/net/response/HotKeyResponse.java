package com.wdz.common.net.response;

import java.util.List;

public class HotKeyResponse {
    public List<Data> data;

    @Override
    public String toString() {
        return "HotKeyResponse{" +
                "data=" + data +
                '}';
    }

    public class Data{
        public int id;
        public String link;
        public String name;
        public int order;
        public int visible;

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", link='" + link + '\'' +
                    ", name='" + name + '\'' +
                    ", order=" + order +
                    ", visible=" + visible +
                    '}';
        }
    }
}
