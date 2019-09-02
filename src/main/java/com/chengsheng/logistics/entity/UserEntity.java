package com.chengsheng.logistics.entity;

import com.chengsheng.logistics.converter.ProjectEnumConverter;
import com.chengsheng.logistics.enums.ProjectEnum;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 刘金泳
 * @Date 2019/9/2
 */
@Entity
@Table(name = "chengsheng_user", schema = "chengsheng", catalog = "")
public class UserEntity {
    private int id;
    private String userName;
    private String password;
    private String nameCn;
    private String mobile;
    private String tel;

    @Convert(converter = ProjectEnumConverter.class)
    private ProjectEnum userLevel;

    private Integer status;
    private Integer createId;
    private Timestamp createTime;
    private Integer updateId;
    private Timestamp updateTime;
    private Timestamp lastLoginTime;
    private String lastLoginIp;

    @Convert(converter = ProjectEnumConverter.class)
    private ProjectEnum remove;

    private String remark;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "NAME_CN")
    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    @Basic
    @Column(name = "MOBILE")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "TEL")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "USER_LEVEL")
    public ProjectEnum getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(ProjectEnum userLevel) {
        this.userLevel = userLevel;
    }

    @Basic
    @Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CREATE_ID")
    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "UPDATE_ID")
    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    @Basic
    @Column(name = "UPDATE_TIME")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "LAST_LOGIN_TIME")
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column(name = "LAST_LOGIN_IP")
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Basic
    @Column(name = "REMOVE")
    public ProjectEnum getRemove() {
        return remove;
    }

    public void setRemove(ProjectEnum remove) {
        this.remove = remove;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                remove == that.remove &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(nameCn, that.nameCn) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(userLevel, that.userLevel) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createId, that.createId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateId, that.updateId) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(lastLoginTime, that.lastLoginTime) &&
                Objects.equals(lastLoginIp, that.lastLoginIp) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, nameCn, mobile, tel, userLevel, status, createId, createTime, updateId, updateTime, lastLoginTime, lastLoginIp, remove, remark);
    }
}
