package us.xingkong.user.biz.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import us.xingkong.user.api.service.User;
import us.xingkong.user.api.service.UserService;
import us.xingkong.user.biz.dao.UserDao;

/**
 * @Author: Icharle
 * @Date: 2019-07-26 16:35
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public String sayHello(String name) {
        return name;
    }

    public String queryById(Integer id) {
        return userDao.selectByPrimaryKey(id).getMobilePhone();
    }
}
