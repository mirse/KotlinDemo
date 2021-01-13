package com.wdz.common.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LoginResponse implements Parcelable {
    private boolean admin;
    private List<String> chapterTops;
    private int coinCount;
    private List<String> collectIds;
    private String email;
    private String icon;
    private int id;
    private String nickname;
    private String password;
    private String publicName;
    private String token;
    private int type;
    private String username;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<String> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List<String> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public List<String> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<String> collectIds) {
        this.collectIds = collectIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.admin ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.chapterTops);
        dest.writeInt(this.coinCount);
        dest.writeStringList(this.collectIds);
        dest.writeString(this.email);
        dest.writeString(this.icon);
        dest.writeInt(this.id);
        dest.writeString(this.nickname);
        dest.writeString(this.password);
        dest.writeString(this.publicName);
        dest.writeString(this.token);
        dest.writeInt(this.type);
        dest.writeString(this.username);
    }

    public LoginResponse() {
    }

    protected LoginResponse(Parcel in) {
        this.admin = in.readByte() != 0;
        this.chapterTops = in.createStringArrayList();
        this.coinCount = in.readInt();
        this.collectIds = in.createStringArrayList();
        this.email = in.readString();
        this.icon = in.readString();
        this.id = in.readInt();
        this.nickname = in.readString();
        this.password = in.readString();
        this.publicName = in.readString();
        this.token = in.readString();
        this.type = in.readInt();
        this.username = in.readString();
    }

    public static final Parcelable.Creator<LoginResponse> CREATOR = new Parcelable.Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel source) {
            return new LoginResponse(source);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };
}
