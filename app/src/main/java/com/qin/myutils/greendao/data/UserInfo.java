package com.qin.myutils.greendao.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class UserInfo {
    @Id
    private Long id;
    private String age;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", shopList=" + shopList +
                '}';
    }

    public UserInfo(String age, String name, List<Shop> shopList) {

        this.age = age;
        this.name = name;
        this.shopList = shopList;
    }

    private String name;
    @ToMany(referencedJoinProperty = "shopId")
    private List<Shop> shopList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 437952339)
    private transient UserInfoDao myDao;
    @Generated(hash = 1302857165)
    public UserInfo(Long id, String age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1673285693)
    public List<Shop> getShopList() {
        if (shopList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ShopDao targetDao = daoSession.getShopDao();
            List<Shop> shopListNew = targetDao._queryUserInfo_ShopList(id);
            synchronized (this) {
                if (shopList == null) {
                    shopList = shopListNew;
                }
            }
        }
        return shopList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 490959295)
    public synchronized void resetShopList() {
        shopList = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 821180768)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserInfoDao() : null;
    }
}
