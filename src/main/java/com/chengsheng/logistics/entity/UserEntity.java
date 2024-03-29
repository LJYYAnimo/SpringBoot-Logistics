package com.chengsheng.logistics.entity;

import com.chengsheng.logistics.converter.ProjectEnumConverter;
import com.chengsheng.logistics.enums.ProjectEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "chengsheng_user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME_CN")
    private String nameCn;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "USER_LEVEL")
    @Convert(converter = ProjectEnumConverter.class)
    private ProjectEnum userLevel;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATE_ID")
    private Integer createId;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_ID")
    private Integer updateId;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    @Column(name = "LAST_LOGIN_IP")
    private String lastLoginIp;

    @Column(name = "REMOVE")
    @Convert(converter = ProjectEnumConverter.class)
    private ProjectEnum remove;

    @Column(name = "REMARK")
    private String remark;

}
