package us.xingkong.user.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Icharle
 * @Date: 2019-07-30 16:03
 */
@Getter
@AllArgsConstructor
public enum ZFanUrlEnum {

    JW_CODE_URL("http://jwxt.gcu.edu.cn/CheckCode.aspx","验证码链接"),
    JW_LOGIN_URL("http://jwxt.gcu.edu.cn/default2.aspx", "登录页链接"),
    JW_SCORE_URL("http://jwxt.gcu.edu.cn/xscjcx.aspx", "成绩查询页链接"),
    JW_COURSE_URL("http://jwxt.gcu.edu.cn/xskbcx.aspx", "课表查询页链接"),
    JW_EXAM_URL("http://jwxt.gcu.edu.cn/xskscx.aspx", "考试安排查询页链接");

    private String url;
    private String desc;

}
