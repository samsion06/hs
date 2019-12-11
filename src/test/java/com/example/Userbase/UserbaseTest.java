package com.example.Userbase;

import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.CheckReponseResult;
import com.example.utils.ConvertData;
import com.example.utils.HttpConfig;
import com.example.utils.MD5Util;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserBaseServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

//https://blog.csdn.net/qq_16605855/article/details/81183990  testNg集成springboot框架
@SpringBootTest
public class UserbaseTest extends AbstractTestNGSpringContextTests {

   //@Autowired
   //private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

   static CloseableHttpClient httpClient = HttpClients.createDefault();
   static ByteArrayEntity byteArrayEntity = null;
   static URI uri = null;
   static HttpPost post = null;
   static HttpResponse response = null;
   static Long mobile=17702015336l;
   static Long pwd=123456l;

//    @org.testng.annotations.Test(description = "1.用户登录" +
//            "                              2.修改昵称" +
//            "                              3.修改头像" +
//            "                              4.修改手机号" +
//            "                              5.修改密码" +
//            "                              6.再次登录"+
//            "                              7.用户注册")
//          Map result = CheckReponseResult.checkResponseCodeAndObj(response, UserBaseServiceProto.userInfoPdCombine.class);
//          ResultResponse.ResultSet resp = (ResultResponse.ResultSet)result.get("resultObject");
//          String userId = resp.getData().unpack(UserBaseServiceProto.userInfoPdCombine.class).getUserId();
    public void test7(){
        System.out.println(mobile);
        System.out.println(pwd);
        try {
            //登录
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/info/pd/login", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserInfoPdLoginRequestConvertBuilder(1, mobile.toString(), pwd.toString(),"86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String result = CheckReponseResult.checkResponseResult(response, UserBaseServiceProto.userInfoPdCombine.class);
            String channelUserId = CheckReponseResult.substring(result, "userId", 10, ",", 1);
            //修改昵称
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/nick/name/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserNickNameUpdateRequestConvertBuilder(1, channelUserId, "FUCK6");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
            //修改头像
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/head/img/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserHeadImgUpdateRequestConvertBuilder(1, channelUserId, "http://images.huasheng100.com/public/1565334681520252.jpg");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
            mobile++;
            System.out.println(mobile);
            //修改手机号
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/mobile/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserMobileUpdateRequestConvertBuilder(1, mobile.toString(), channelUserId,"86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
            //转换密码成md5
            pwd++;
            String md5pwd=MD5Util.toMD5(pwd.toString()).toUpperCase();
            //修改密码
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/user/pwd/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserPwdUpdateRequestConvertBuilder(channelUserId,1,md5pwd);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
            //再次登录
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/info/pd/login", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserInfoPdLoginRequestConvertBuilder(1, mobile.toString(), pwd.toString(),"86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseResult(response, UserBaseServiceProto.userInfoPdCombine.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //@org.testng.annotations.Test(description = "根据邀请码获取用户信息")
    public void test9(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ByteArrayEntity byteArrayEntity = null;
        URI uri = null;
        HttpPost post = null;
        HttpResponse response = null;
        try{
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/get/by/invite/code", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserInviteCodeQueryRequest("p88vcdo",1);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCodeAndObj(response, UserBaseServiceProto.UserInfoInviteCodeResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   // @org.testng.annotations.Test(description = "修改用户标签")
    public void test10(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ByteArrayEntity byteArrayEntity = null;
        URI uri = null;
        HttpPost post = null;
        HttpResponse response = null;






    }



}