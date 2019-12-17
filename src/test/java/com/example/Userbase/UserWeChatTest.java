package com.example.Userbase;

import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.CheckReponseResult;
import com.example.utils.ConvertData;
import com.example.utils.HttpConfig;
import com.hs.user.base.proto.UserBaseServiceProto;
import com.hs.user.base.proto.UserWeChatAuthServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import java.io.IOException;
import java.net.URI;

@SpringBootTest
public class UserWeChatTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

    private static String AppId="Appid01";
    private static Integer ChannelId=1;
    private static String ChannelUserId="3692091";
    private static String openId="oBrt31Sg6EqD9DJxB0Mz9EOl-Pp5";

    static CloseableHttpClient httpClient;
    static ByteArrayEntity byteArrayEntity;
    static URI uri;
    static HttpPost post;
    static HttpResponse response;

//    @org.testng.annotations.Test(description = "1.微信绑定" +
//            "                                   2.微信解绑 OK")
    public void test2(){
        try {
            //微信绑定
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/weChat/binding", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserWeChatAuthRequest(AppId,ChannelId,ChannelUserId,openId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            //校验状态码,是否需要加数据库判断？
            CheckReponseResult.checkResponseCode(response);
            //解除绑定
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/weChat/unBinding", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserWeChatAuthUnBindRequest(openId,ChannelId,ChannelUserId,AppId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //@org.testng.annotations.Test(description = "用户微信一键登录")
    public void test1(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ByteArrayEntity byteArrayEntity=null;
        URI uri=null;
        HttpPost post=null;
        try{
            //微信绑定
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/weChat/loginByOneKey", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserWeChatOneKeyLoginRequest(1,"17702015334","177417","86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            //校验状态码,是否需要加数据库判断？
            CheckReponseResult.checkResponseCodeAndObj(response,UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据微信ID和OPENID获取用户信息
    @org.testng.annotations.Test(timeOut = 50000)
    public void test3() {
          System.out.println(userBaseInfoMapper.queryUserBaseInfo());
         // uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/info/pd/get/by/unionId/openId", "", null);
         //uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/info/pd/get/by/unionId/openId","");
        try {
            httpClient=HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/info/pd/get/by/unionId/openId","");
            System.out.println(uri);
            post = new HttpPost(uri);;
            byteArrayEntity = ConvertData.UserInfoUnionIdOpenIdRequestConvertBuilder(1, "ox-FY1f0_ub3FnM_v9n7ITb1q-f0", "oBrt31Sg6EqD9DJxB0Mz9EOl-Pp4");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponse(response, UserBaseServiceProto.userInfoPdCombine.class);
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
}
