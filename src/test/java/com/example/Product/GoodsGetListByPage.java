package com.example.Product;
import com.example.utils.ConvertData;
import com.example.utils.HttpConfig;
import com.hs.productservice.api.proto.getdetailbyid.ProductServiceApiGetDetailById;
import com.hs.productservice.api.proto.getdetailbyidlist.ProductServiceApiGetDetailByIdList;
import com.hs.productservice.api.proto.getlistbypage.ProductServiceApiGetListByPage;
import com.hs.productservice.api.proto.lockuserstock.ProductServiceApiStockService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.net.URI;

public class GoodsGetListByPage {

    static CloseableHttpClient httpClient =null;
    static ByteArrayEntity byteArrayEntity=null;
    static URI uri=null;
    static HttpPost post=null;
    static HttpResponse response=null;

    @org.testng.annotations.Test(description = "分页查询商品列表")
    public void test1(){
        try{
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.Servicescheme, null, HttpConfig.Serviceurl, HttpConfig.Serviceport, "/productservice/productapi/hszy/goods/getListByPage", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.GetListByPageRequestDTO(1,10,2);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);

            if(response.getStatusLine().getStatusCode()==200){
                ProductServiceApiGetListByPage.GetListByPageResponseJsonResult resp = ProductServiceApiGetListByPage.GetListByPageResponseJsonResult.parseFrom(response.getEntity().getContent());
                if(resp==null){
                    System.out.println(resp);
                }else{
                    System.out.println(resp);
                }
            }else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @org.testng.annotations.Test(description = "根据ID获取商品详情")
    public void test2(){
        try{
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.Servicescheme, null, HttpConfig.Serviceurl, HttpConfig.Serviceport, "/productservice/productapi/hszy/goods/getDetailById", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.GetDetailByIdRequestDTO(1910241023325207231L);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                ProductServiceApiGetDetailById.GetDetailByIdResponseJsonResult resp = ProductServiceApiGetDetailById.GetDetailByIdResponseJsonResult.parseFrom(response.getEntity().getContent());
                if(resp==null){
                    System.out.println(resp);
                }else{
                    System.out.println(resp.getData());
                    System.out.println(resp.getMsg());
                }
            }else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @org.testng.annotations.Test(description = "根据ID集合获取商品详情")
    public  void test3(){
        try{
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.Servicescheme, null, HttpConfig.Serviceurl, HttpConfig.Serviceport, "/productservice/productapi/hszy/goods/getDetailByIdList", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.GetDetailByIdListRequestDTO(1,1910241023325207231L,"1905081410388277622");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                ProductServiceApiGetDetailByIdList.GetDetailByIdListResponseJsonResult resp = ProductServiceApiGetDetailByIdList.GetDetailByIdListResponseJsonResult.parseFrom(response.getEntity().getContent());
                if(resp==null){
                    System.out.println(resp);
                }else{
                    System.out.println(resp);
                    System.out.println(resp.getDataMap());
                    System.out.println(resp.getMsg());
                }
            }else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @org.testng.annotations.Test(description = "库存锁定-库存扣减")
    public void test4(){
        try{
            httpClient = HttpClients.createDefault();
            //锁定库存
            uri = new URI(HttpConfig.Servicescheme, null, HttpConfig.Serviceurl, HttpConfig.Serviceport, "/productservice/productapi/hszy/goods/lockUserStockByList", "", null);
            post = new HttpPost(uri);
            String orderid="8e2d3a7066d845318ee128153a7140c2_"+System.currentTimeMillis();
            System.out.println(orderid);
            byteArrayEntity = ConvertData.LockStockByListRequestDto(orderid,1910241023325207231L,1910241023325207231L,ProductServiceApiStockService.GoodStockOperateEnum.USER_LOCK_ADD);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                ProductServiceApiStockService.LockStockByListResponseJsonResult resp =
                        ProductServiceApiStockService.LockStockByListResponseJsonResult.parseFrom(response.getEntity().getContent());
                if(resp==null){
                    System.out.println(resp);
                }else{
                    System.out.println(resp.getStatus());
                    System.out.println(resp.getMsg());
                    //扣减库存
                    uri = new URI(HttpConfig.Servicescheme, null, HttpConfig.Serviceurl, HttpConfig.Serviceport, "/productservice/productapi/hszy/goods/lockUserStockByList", "", null);
                    post = new HttpPost(uri);
                    byteArrayEntity = ConvertData.LockStockByListRequestDto(orderid,1910241023325207231L,1910241023325207231L,ProductServiceApiStockService.GoodStockOperateEnum.USER_ADD);
                    post.setEntity(byteArrayEntity);
                    post.setHeader("Content-Type", "application/x-protobuf");
                    response = httpClient.execute(post);
                    System.out.println(resp);
                    System.out.println(resp.getMsg());
                }
            }else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
