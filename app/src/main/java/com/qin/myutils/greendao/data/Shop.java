package com.qin.myutils.greendao.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
@Entity
public class Shop {
    @Id
    private Long shopId;
    @Unique
    private String name;

    public Shop(String name, int price, String description, Long userInfoId, UserInfo userInfo) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.userInfoId = userInfoId;
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", userInfoId=" + userInfoId +
                ", userInfo=" + userInfo +
                ", userInfo__resolvedKey=" + userInfo__resolvedKey +
                '}';
    }

    @NotNull
    private int price;
    @Property(nameInDb = "dec")
    private String description;
    private Long userInfoId;
    @ToOne(joinProperty = "userInfoId")
    private UserInfo userInfo;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 173397329)
    private transient ShopDao myDao;
    @Generated(hash = 1987645117)
    public Shop(Long shopId, String name, int price, String description,
            Long userInfoId) {
        this.shopId = shopId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.userInfoId = userInfoId;
    }
    @Generated(hash = 633476670)
    public Shop() {
    }
    public Long getShopId() {
        return this.shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getUserInfoId() {
        return this.userInfoId;
    }
    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }
    @Generated(hash = 2066097151)
    private transient Long userInfo__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1002767315)
    public UserInfo getUserInfo() {
        Long __key = this.userInfoId;
        if (userInfo__resolvedKey == null || !userInfo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserInfoDao targetDao = daoSession.getUserInfoDao();
            UserInfo userInfoNew = targetDao.load(__key);
            synchronized (this) {
                userInfo = userInfoNew;
                userInfo__resolvedKey = __key;
            }
        }
        return userInfo;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 553424408)
    public void setUserInfo(UserInfo userInfo) {
        synchronized (this) {
            this.userInfo = userInfo;
            userInfoId = userInfo == null ? null : userInfo.getId();
            userInfo__resolvedKey = userInfoId;
        }
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
    @Generated(hash = 1040006210)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getShopDao() : null;
    }
}
