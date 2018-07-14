package com.joe.api.service;

import com.joe.api.dao.UserManagerMapper;
import com.joe.api.po.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@CacheConfig(cacheNames = "userManagerCache")
public class UserManagerService {

    @Autowired
    UserManagerMapper userManagerMapper;


    /**
     * @param userManager
     * @return
     */
    public int addUserManager(UserManager userManager) {

        userManager.setCreateTime(new Date());
        userManager.setUpdateTime(new Date());
        userManager.setEnable(false);
        userManagerMapper.insertSelective(userManager);
        return userManager.getManagerId();
    }

    /**
     * 修改管理员信息
     *
     * @param userManager
     * @return
     */
    @CacheEvict(key = "#userManager.managerId") //移除命名空间里特定缓存 注意没有{}
//    @CacheEvict(allEntries = true)  移除命名空间里所有缓存
    public int modifyManager(UserManager userManager) {

        return userManagerMapper.updateByPrimaryKeySelective(userManager);
    }


    /**
     * 删除管理员（逻辑删除）
     *
     * @param managerId
     * @return
     */
    public int dropManager(Integer managerId) {
        if (managerId == null || managerId == 0) {
            return 0;
        }
        return userManagerMapper.deleteByPrimaryKey(managerId);
    }


    @Cacheable(key = "#managerId")
    public UserManager queryUserManagerById(Integer managerId) {
        if (managerId == null || managerId == 0) {
            return null;
        }

        return userManagerMapper.selectByPrimaryKey(managerId);
    }
}
