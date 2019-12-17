package com.example.Userbase;

import com.example.utils.CheckReponseResult;
import com.example.utils.ConvertData;
import com.example.utils.HttpConfig;
import com.hs.user.base.proto.UserAddressServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;
import java.util.Map;

public class UserAddressTest {

    private static Integer channelId=1;
    private static String channelUserid="17702015334";
    private static String address="广州海珠区你老母2号";
    private static String addressid="774195ceb7ce455b95c69d2beb1f5723";
    private static String username="xiaoming";

    static CloseableHttpClient httpClient =null;
    static ByteArrayEntity byteArrayEntity=null;
    static URI uri=null;
    static HttpPost post=null;
    static HttpResponse response=null;

//    @org.testng.annotations.Test(description = "1.添加收货地址" +
//            "                              2.获取收货地址" +
//            "                              3.更新收货地址"+
//            "                              4.删除收货地址")
    public void test1(){
        try{
            //添加收货地址
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/address/add", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressInfoAddRequest(channelUserid,channelId,address);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            //到这步已经判断好一定有返回内容，只需要去取就可以了
            Map<String, Object> stringObjectMap = CheckReponseResult.checkResponseCodeAndObj(response, UserAddressServiceProto.UserAddressInfoResponse.class);
            //{"addressId": "774195ceb7ce455b95c69d2beb1f5723","channelUserId": "17702015334","channelId": 1,"address": "广州海珠区你老母2号"}
            //获取收货地址
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/address/getByAddressId", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressRequest(channelUserid,channelId,addressid);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCodeAndObj(response,UserAddressServiceProto.UserAddressInfoResponse.class);
            //更新收货地址
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/address/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressInfoUpdateRequest(channelUserid,channelId,addressid,username);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
            //删除收货地址
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/address/delete", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressRequest(channelUserid,channelId,addressid);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCode(response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //@org.testng.annotations.Test(description ="分页查询用户收货地址列表")
    public void test2(){
        try{
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/address/query", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressPageRequest("17702015334",channelId,1,1);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCodeAndObj(response,UserAddressServiceProto.UserAddressPage.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    }



























