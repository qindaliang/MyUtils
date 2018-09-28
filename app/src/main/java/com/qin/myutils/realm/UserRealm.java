package com.qin.myutils.realm;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class UserRealm extends RealmObject{


    @Required
    private String name;
    private String age;
    private Date birth;
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
