package com.example.Userbase;

import com.example.utils.CheckReponseResult;
import com.example.utils.ConvertData;
import com.example.utils.HttpConfig;
import com.hs.user.base.proto.UserBaseServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@SpringBootTest
public class UserLoginTest extends AbstractTestNGSpringContextTests
{
    private static Integer channelId=1;
    private static String newPassword="1234567";

//    @Test(description = "1.用户修改密码" +
//            "          2.用户登录" +
//            "          3.用户忘记密码")
    public void test1(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ByteArrayEntity byteArrayEntity = null;
        URI uri=null;
        HttpPost post=null;
        try {
            //修改密码
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/user/pwd/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserPwdUpdateRequestConvertBuilder("3692080",channelId,newPassword);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
            //登录
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/info/pd/login", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserInfoPdLoginRequestConvertBuilder(channelId, "13892391987", newPassword,"86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            Map result = CheckReponseResult.checkResponseCodeAndObj(response, UserBaseServiceProto.userInfoPdCombine.class);
            //用户忘记登录密码
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/user/forget/pwd", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserPwdUpdateRequestConvertBuilder("3692080",channelId,newPassword);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            //校验结果
            CheckReponseResult.checkResponseCode(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
    }
}
