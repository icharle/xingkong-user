package us.xingkong.user.biz.dao;

import us.xingkong.user.biz.entity.User;

/**
 * @Author: Icharle
 * @Date: 2019-07-25 16:49
 */
public interface UserDao {

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
