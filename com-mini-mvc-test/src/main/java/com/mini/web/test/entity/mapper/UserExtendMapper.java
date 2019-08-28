package com.mini.web.test.entity.mapper;

import com.mini.jdbc.SQLBuilder;
import com.mini.jdbc.mapper.IMapper;
import com.mini.web.test.entity.Region;
import com.mini.web.test.entity.User;
import com.mini.web.test.entity.UserExtend;

import javax.inject.Named;
import javax.inject.Singleton;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserMapper.java
 * @author xchao
 */
@Singleton
@Named("userMapper")
public class UserExtendMapper implements IMapper<UserExtend> {
    @Override
    public UserExtend get(ResultSet rs, int number) throws SQLException {
        UserExtend userExtend = new UserExtend();
        // 用户ID
        userExtend.setId(rs.getLong(User.ID));
        // 用户名
        userExtend.setName(rs.getString(User.NAME));
        // MD5(密码)
        userExtend.setPassword(rs.getString(User.PASSWORD));
        // 用户手机号
        userExtend.setPhone(rs.getString(User.PHONE));
        // 0-未认证，1-已谁
        userExtend.setPhoneAuth(rs.getInt(User.PHONE_AUTH));
        // 用户姓名
        userExtend.setFullName(rs.getString(User.FULL_NAME));
        // 用户邮箱地址
        userExtend.setEmail(rs.getString(User.EMAIL));
        // 0-未认证，1-已认证
        userExtend.setEmailAuth(rs.getInt(User.EMAIL_AUTH));
        // 用户头像地址
        userExtend.setHeadUrl(rs.getString(User.HEAD_URL));
        // 用户所属地区ID
        userExtend.setRegionId(rs.getInt(User.REGION_ID));
        // 用户注册时间
        userExtend.setCreateTime(rs.getDate(User.CREATE_TIME));
        userExtend.setRegionName(rs.getString(Region.NAME));
        userExtend.setRegionIdUri(rs.getString(Region.ID_URI));
        userExtend.setRegionNameUri(rs.getString(Region.NAME_URI));
        return userExtend;
    }

    public abstract static class UserExtendBuilder extends SQLBuilder {
        public UserExtendBuilder() {
            // 用户ID
            select(User.ID);
            // 用户名
            select(User.NAME);
            // MD5(密码)
            select(User.PASSWORD);
            // 用户手机号
            select(User.PHONE);
            // 0-未认证，1-已谁
            select(User.PHONE_AUTH);
            // 用户姓名
            select(User.FULL_NAME);
            // 用户邮箱地址
            select(User.EMAIL);
            // 0-未认证，1-已认证
            select(User.EMAIL_AUTH);
            // 用户头像地址
            select(User.HEAD_URL);
            // 用户所属地区ID
            select(User.REGION_ID);
            // 用户注册时间
            select(User.CREATE_TIME);
            // 地区名称
            select(Region.NAME);
            // 地区ID URI
            select(Region.ID_URI);
            // 地区Name URI
            select(Region.NAME_URI);
            // 表名称
            from(User.TABLE);
            // 联合地区表
            join("%s ON %s = %s", Region.TABLE, User.REGION_ID, Region.ID);
        }
    }
}
