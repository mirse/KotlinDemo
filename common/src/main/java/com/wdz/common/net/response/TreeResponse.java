package com.wdz.common.net.response;

import java.util.List;

public class TreeResponse {
    public List<Data> data;

    @Override
    public String toString() {
        return "TreeResponse{" +
                "data=" + data +
                '}';
    }

    class Data{
        public List<Children> children;
        public int courseId;
        public int id;
        public String name;
        public int order;
        public int parentChapterId;
        public boolean userControlSetTop;
        public int visible;
        
        class Children{
            public List<String> children;
            public int courseId;
            public int id;
            public String name;
            public int order;
            public int parentChapterId;
            public boolean userControlSetTop;
            public int visible;

            @Override
            public String toString() {
                return "Children{" +
                        "children=" + children +
                        ", courseId=" + courseId +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        ", order=" + order +
                        ", parentChapterId=" + parentChapterId +
                        ", userControlSetTop=" + userControlSetTop +
                        ", visible=" + visible +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Data{" +
                    "children=" + children +
                    ", courseId=" + courseId +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", order=" + order +
                    ", parentChapterId=" + parentChapterId +
                    ", userControlSetTop=" + userControlSetTop +
                    ", visible=" + visible +
                    '}';
        }
    }
}
