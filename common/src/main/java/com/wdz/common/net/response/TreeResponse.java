package com.wdz.common.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class TreeResponse implements Parcelable {
    public List<Children> children;
    public int courseId;
    public int id;
    public String name;
    public int order;
    public int parentChapterId;
    public boolean userControlSetTop;
    public int visible;

    public static class Children implements Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeStringList(this.children);
            dest.writeInt(this.courseId);
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeInt(this.order);
            dest.writeInt(this.parentChapterId);
            dest.writeByte(this.userControlSetTop ? (byte) 1 : (byte) 0);
            dest.writeInt(this.visible);
        }

        public void readFromParcel(Parcel source) {
            this.children = source.createStringArrayList();
            this.courseId = source.readInt();
            this.id = source.readInt();
            this.name = source.readString();
            this.order = source.readInt();
            this.parentChapterId = source.readInt();
            this.userControlSetTop = source.readByte() != 0;
            this.visible = source.readInt();
        }

        public Children() {
        }

        protected Children(Parcel in) {
            this.children = in.createStringArrayList();
            this.courseId = in.readInt();
            this.id = in.readInt();
            this.name = in.readString();
            this.order = in.readInt();
            this.parentChapterId = in.readInt();
            this.userControlSetTop = in.readByte() != 0;
            this.visible = in.readInt();
        }

        // TODO: 2021/3/9  必须是static 不然ARouter传参会失败
        public static final Creator<Children> CREATOR = new Creator<Children>() {
            @Override
            public Children createFromParcel(Parcel source) {
                return new Children(source);
            }

            @Override
            public Children[] newArray(int size) {
                return new Children[size];
            }
        };
    }

    @Override
    public String toString() {
        return "TreeResponse{" +
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.children);
        dest.writeInt(this.courseId);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.order);
        dest.writeInt(this.parentChapterId);
        dest.writeByte(this.userControlSetTop ? (byte) 1 : (byte) 0);
        dest.writeInt(this.visible);
    }

    public void readFromParcel(Parcel source) {
        this.children = new ArrayList<Children>();
        source.readList(this.children, Children.class.getClassLoader());
        this.courseId = source.readInt();
        this.id = source.readInt();
        this.name = source.readString();
        this.order = source.readInt();
        this.parentChapterId = source.readInt();
        this.userControlSetTop = source.readByte() != 0;
        this.visible = source.readInt();
    }

    public TreeResponse() {
    }

    protected TreeResponse(Parcel in) {
        this.children = new ArrayList<Children>();
        in.readList(this.children, Children.class.getClassLoader());
        this.courseId = in.readInt();
        this.id = in.readInt();
        this.name = in.readString();
        this.order = in.readInt();
        this.parentChapterId = in.readInt();
        this.userControlSetTop = in.readByte() != 0;
        this.visible = in.readInt();
    }

    public static final Parcelable.Creator<TreeResponse> CREATOR = new Parcelable.Creator<TreeResponse>() {
        @Override
        public TreeResponse createFromParcel(Parcel source) {
            return new TreeResponse(source);
        }

        @Override
        public TreeResponse[] newArray(int size) {
            return new TreeResponse[size];
        }
    };
}
