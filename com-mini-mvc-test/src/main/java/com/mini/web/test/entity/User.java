package com.mini.web.test.entity;

import com.mini.jdbc.SQLBuilder;
import java.io.Serializable;
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.annotation.Nonnull;

/**
 * User.java 
 * @author xchao 
 */
public class User implements Serializable {
  /**
   *  表名称 user_info 
   */
  public static final String TABLE = "user_info";

  /**
   * 用户ID 
   */
  public static final String ID = "user_id" ;

  /**
   * 用户名 
   */
  public static final String NAME = "user_name" ;

  /**
   * MD5(密码) 
   */
  public static final String PASSWORD = "user_password" ;

  /**
   * 用户手机号 
   */
  public static final String PHONE = "user_phone" ;

  /**
   * 0-未认证，1-已谁 
   */
  public static final String PHONE_AUTH = "user_phone_auth" ;

  /**
   * 用户姓名 
   */
  public static final String FULL_NAME = "user_full_name" ;

  /**
   * 用户邮箱地址 
   */
  public static final String EMAIL = "user_email" ;

  /**
   * 0-未认证，1-已认证 
   */
  public static final String EMAIL_AUTH = "user_email_auth" ;

  /**
   * 用户头像地址 
   */
  public static final String HEAD_URL = "user_head_url" ;

  /**
   * 用户所属地区ID 
   */
  public static final String REGION_ID = "user_region_id" ;

  /**
   * 用户注册时间 
   */
  public static final String CREATE_TIME = "user_create_time" ;

  private long id;

  private String name;

  private String password;

  private String phone;

  private int phoneAuth;

  private String fullName;

  private String email;

  private int emailAuth;

  private String headUrl;

  private int regionId;

  private Date createTime;

  public User() {
  }

  private User(Builder builder) {
    setId(builder.id);
    setName(builder.name);
    setPassword(builder.password);
    setPhone(builder.phone);
    setPhoneAuth(builder.phoneAuth);
    setFullName(builder.fullName);
    setEmail(builder.email);
    setEmailAuth(builder.emailAuth);
    setHeadUrl(builder.headUrl);
    setRegionId(builder.regionId);
    setCreateTime(builder.createTime);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getPhoneAuth() {
    return phoneAuth;
  }

  public void setPhoneAuth(int phoneAuth) {
    this.phoneAuth = phoneAuth;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getEmailAuth() {
    return emailAuth;
  }

  public void setEmailAuth(int emailAuth) {
    this.emailAuth = emailAuth;
  }

  public String getHeadUrl() {
    return headUrl;
  }

  public void setHeadUrl(String headUrl) {
    this.headUrl = headUrl;
  }

  public int getRegionId() {
    return regionId;
  }

  public void setRegionId(int regionId) {
    this.regionId = regionId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static Builder newBuilder(User copy) {
    Builder builder = new Builder();
    builder.id = copy.getId();
    builder.name = copy.getName();
    builder.password = copy.getPassword();
    builder.phone = copy.getPhone();
    builder.phoneAuth = copy.getPhoneAuth();
    builder.fullName = copy.getFullName();
    builder.email = copy.getEmail();
    builder.emailAuth = copy.getEmailAuth();
    builder.headUrl = copy.getHeadUrl();
    builder.regionId = copy.getRegionId();
    builder.createTime = copy.getCreateTime();
    return builder;
  }

  public static User mapper(ResultSet rs, int number) throws SQLException {
    Builder builder = User.newBuilder();
    builder.id = rs.getLong(ID);
    builder.name = rs.getString(NAME);
    builder.password = rs.getString(PASSWORD);
    builder.phone = rs.getString(PHONE);
    builder.phoneAuth = rs.getInt(PHONE_AUTH);
    builder.fullName = rs.getString(FULL_NAME);
    builder.email = rs.getString(EMAIL);
    builder.emailAuth = rs.getInt(EMAIL_AUTH);
    builder.headUrl = rs.getString(HEAD_URL);
    builder.regionId = rs.getInt(REGION_ID);
    builder.createTime = rs.getDate(CREATE_TIME);
    return builder.build();
  }

  public static final class Builder {
    private long id;

    private String name;

    private String password;

    private String phone;

    private int phoneAuth;

    private String fullName;

    private String email;

    private int emailAuth;

    private String headUrl;

    private int regionId;

    private Date createTime;

    private Builder() {
    }

    public final Builder id(long id) {
      this.id = id;
      return this;
    }

    public final Builder name(String name) {
      this.name = name;
      return this;
    }

    public final Builder password(String password) {
      this.password = password;
      return this;
    }

    public final Builder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public final Builder phoneAuth(int phoneAuth) {
      this.phoneAuth = phoneAuth;
      return this;
    }

    public final Builder fullName(String fullName) {
      this.fullName = fullName;
      return this;
    }

    public final Builder email(String email) {
      this.email = email;
      return this;
    }

    public final Builder emailAuth(int emailAuth) {
      this.emailAuth = emailAuth;
      return this;
    }

    public final Builder headUrl(String headUrl) {
      this.headUrl = headUrl;
      return this;
    }

    public final Builder regionId(int regionId) {
      this.regionId = regionId;
      return this;
    }

    public final Builder createTime(Date createTime) {
      this.createTime = createTime;
      return this;
    }

    @Nonnull
    public final User build() {
      return new User(this);
    }
  }

  public static class UserBuilder extends SQLBuilder {
    protected UserBuilder() {
      select(ID);
      select(NAME);
      select(PASSWORD);
      select(PHONE);
      select(PHONE_AUTH);
      select(FULL_NAME);
      select(EMAIL);
      select(EMAIL_AUTH);
      select(HEAD_URL);
      select(REGION_ID);
      select(CREATE_TIME);
      select(TABLE);
    }
  }
}
