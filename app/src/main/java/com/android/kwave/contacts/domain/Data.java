package com.android.kwave.contacts.domain;

/**
 * Created by kwave on 2017-06-01.
 */

// 주소록 데이터 클래스  - POJO(Pure Old Java Object)
public class Data {
    private int id;
    private String name;
    private String tel;
    private String address;
    private String email;
    // Getter/Setter를 만든다면 변수들은 private로 해주는 것이 좋다


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
