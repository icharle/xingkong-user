package us.xingkong.user.api.service;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userId;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 密码
     */
    private String studentPw;

    /**
     * 学院
     */
    private String studentFaculty;

    /**
     * 专业
     */
    private String studentProfession;

    /**
     * 班级
     */
    private String studentClass;

    /**
     * 手机号码
     */
    private String mobilePhone;

    /**
     * 是否被禁用  0：无效  1：有效
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;
}