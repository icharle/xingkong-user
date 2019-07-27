package us.xingkong.user.biz.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import us.xingkong.user.biz.entity.User;

import java.util.List;

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

    List<User> pageQueryAll(@Param("pageNum") int pageNum,
                            @Param("pageSize") int pageSize,
                            @Param("user") User user);
}
