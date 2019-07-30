package us.xingkong.user.biz.dao;

import org.apache.ibatis.annotations.Param;
import us.xingkong.user.biz.entity.User;

import java.util.List;

/**
 * @Author: Icharle
 * @Date: 2019-07-25 16:49
 */
public interface UserDao {

    int deleteByuserId(Integer userId);

    int insert(User user);

    User selectByuserId(Integer userId);

    int updateByUserId(User user);

    List<User> pageQueryAll(@Param("pageNum") int pageNum,
                            @Param("pageSize") int pageSize,
                            @Param("user") User user);
}
