package com.example.mapper;

import com.example.Obj.UserBaseInfo;
import com.example.Obj.User_info;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserBaseInfoMapper {

    public List<UserBaseInfo> queryUserBaseInfo();

    public List<User_info> queryUserInfo();

}
