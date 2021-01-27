package com.wdz.common.net.response;

import java.util.List;

public class HotKeyResponse {
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
