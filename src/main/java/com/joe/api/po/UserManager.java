package com.joe.api.po;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员Bean
 */
@Data
public class UserManager implements Serializable {

    //管理员编号
    private Integer managerId;

    //头像
    private String headPortrait;

    //管理员姓名
    private String managerName;

    //性别
    private String gender;

    //手机号
    private String mobile;

    //邮箱
    private String email;

    //盐
    private String salt;

    //密码
    private String password;

    //上级
    private Integer parentId;

    //创建者
    private Integer createBy;

    //创建时间
    private Date createTime;

    //更新者
    private Integer updateBy;

    //更新时间
    private Date updateTime;

    //可用
    private Boolean enable;

}