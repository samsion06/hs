package com.example.Userbase;

import com.example.utils.CheckReponseResult;
import com.example.utils.ConvertData;
import com.example.utils.HttpConfig;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserAliPayAuthServiceProto;
import com.hs.user.base.proto.UserIdCardIdentifyServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.io.IOException;
import java.net.URI;

@SpringBootTest
public class UseraliPayTest extends AbstractTestNGSpringContextTests {

    private static String channelUserId="5201315";
    private static Integer channelId=1;
    private static String alipayRealname="周雄鑫";
    private static String alipayAccount="17702015335";
    private static String alipayUserId="5201315";

//    @org.testng.annotations.Test(description = "1.绑定支付宝" +
//            "                              2.用户支付宝授权" +
//            "                              3.用户支付宝取消授权 OK")
    public void test5(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ByteArrayEntity byteArrayEntity = null;
        URI uri=null;
        HttpPost post=null;
        try {
            //绑定支付宝
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/aliPay/binding", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAliPayBidingRequest(channelUserId,channelId,alipayRealname,alipayAccount,alipayUserId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
            //用户支付宝授权
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/aliPay/auth", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAliPayAuthRequest(channelUserId,channelId,alipayUserId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
            //用户取消授权
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/aliPay/auth/cancel", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAliPayAuthRequest(channelUserId,channelId,alipayUserId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
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

//    @org.testng.annotations.Test(description = "1.实名认证" +
//            "                              2.实名认证查询")
    public void test6(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI uri = null;
        HttpPost post = null;
        HttpResponse response = null;
        ByteArrayEntity byteArrayEntity = null;
        String channeluserId="177392";
        try {
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/user/idCard/identify", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserIdCardIdentifyRequestConvertBuilder(channeluserId, 1, "向亚运","431224199009227572","http://www.baidu.com");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/user/idCard/queryStatus", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserIdCardStatusQueryRequestConvertBuilder(channeluserId, 1);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCodeAndObj(response,UserIdCardIdentifyServiceProto.UserIdCardIdentifyInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
    }


   // @org.testng.annotations.Test(description = "用户支付宝授权信息查询")
    public void test8(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI uri = null;
        HttpPost post = null;
        HttpResponse response = null;
        ByteArrayEntity byteArrayEntity = null;
        JsonFormat jsonFormat=null;
        String channeluserId="180409";
        try {
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/aliPay/auth/info", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAliPayAuthInfoRequest(channeluserId, 1);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse resp=  UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse.parseFrom(response.getEntity().getContent());
                System.out.println("resp"+resp);
            }else{
                System.out.println(response.getStatusLine().getStatusCode());
            }
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




