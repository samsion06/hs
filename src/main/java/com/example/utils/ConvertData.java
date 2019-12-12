package com.example.utils;

import com.google.common.collect.Lists;
import com.hs.productservice.api.proto.getdetailbyid.ProductServiceApiGetDetailById;
import com.hs.productservice.api.proto.getdetailbyidlist.ProductServiceApiGetDetailByIdList;
import com.hs.productservice.api.proto.getlistbypage.ProductServiceApiGetListByPage;
import com.hs.productservice.api.proto.lockuserstock.ProductServiceApiStockService;
import com.hs.user.base.proto.*;
import org.apache.http.entity.ByteArrayEntity;

import java.util.ArrayList;
import java.util.List;


public class ConvertData {
    //花生日记登录用根据手机号,密码
    public static ByteArrayEntity UserInfoPdLoginRequestConvertBuilder(Integer ChannelId, String Mobile, String Pwd,String mobileareacode ){
        UserBaseServiceProto.UserInfoPdLoginRequest.Builder builder= UserBaseServiceProto.UserInfoPdLoginRequest.newBuilder();
        builder.setChannelId(ChannelId);
        builder.setMobile(Mobile);
        builder.setPwd(Pwd);
        builder.setMobileAreaCode(mobileareacode);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //修改昵称
    public static ByteArrayEntity UserNickNameUpdateRequestConvertBuilder(Integer ChannelId, String ChannelUserId, String NickName){
        UserBaseServiceProto.UserNickNameUpdateRequest.Builder builder = UserBaseServiceProto.UserNickNameUpdateRequest.newBuilder();
        builder.setChannelId(1);
        builder.setChannelUserId(ChannelUserId);
        builder.setNickName(NickName);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //用户注册
    public static ByteArrayEntity UserBaseRegisterRequestConvertBuilder(Integer ChannelId, String ChannelUserId, String mobile) {
        UserBaseServiceProto.UserBaseRegisterRequest.Builder builder = UserBaseServiceProto.UserBaseRegisterRequest.newBuilder();
        UserBaseServiceProto.UserBaseRegister.Builder userBaseRegisterBuilder = UserBaseServiceProto.UserBaseRegister.newBuilder();
        userBaseRegisterBuilder.setChannelId(ChannelId);
        userBaseRegisterBuilder.setChannelUserId(ChannelUserId);
        userBaseRegisterBuilder.setMobile(mobile);
        builder.setUserBaseRegister(userBaseRegisterBuilder);
        System.out.println(builder);
        ByteArrayEntity bytes = new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //修改头像
    public static ByteArrayEntity UserHeadImgUpdateRequestConvertBuilder(Integer ChannelId, String ChannelUserId, String HeadImageUrl){
        UserBaseServiceProto.UserHeadImgUpdateRequest.Builder builder= UserBaseServiceProto.UserHeadImgUpdateRequest.newBuilder();
        builder.setChannelId(ChannelId);
        builder.setChannelUserId(ChannelUserId);
        builder.setHeadImageUrl(HeadImageUrl);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //获取用户信息
    public static ByteArrayEntity UserInfoUnionIdOpenIdRequestConvertBuilder(Integer channelId, String unionId, String openId){
        UserBaseServiceProto.UserInfoUnionIdOpenIdRequest.Builder builder= UserBaseServiceProto.UserInfoUnionIdOpenIdRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setOpenId(openId);
        builder.setUnionId(unionId);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //修改手机号码
    public static ByteArrayEntity UserMobileUpdateRequestConvertBuilder(Integer channelId,String mobileNew,String ChannelUserId,String MobileAreaCodeNew){
        UserBaseServiceProto.UserMobileUpdateRequest.Builder builder=  UserBaseServiceProto.UserMobileUpdateRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setMobileNew(mobileNew);
        builder.setChannelUserId(ChannelUserId);
        builder.setMobileAreaCodeNew(MobileAreaCodeNew);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //修改密码
    public static ByteArrayEntity UserPwdUpdateRequestConvertBuilder(String channelUserId,Integer channelId,String loginPwdNew){
        UserLoginInfoServiceProto.UserPwdUpdateRequest.Builder builder=UserLoginInfoServiceProto.UserPwdUpdateRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setLoginPwdNew(loginPwdNew);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //用户实名认证
    public static ByteArrayEntity UserIdCardIdentifyRequestConvertBuilder(String channelUserId, Integer channelId, String realName, String idCardNum, String attachmentUrl){
        UserIdCardIdentifyServiceProto.UserIdCardIdentifyRequest.Builder builder = UserIdCardIdentifyServiceProto.UserIdCardIdentifyRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setRealName(realName);
        builder.setIdCardNum(idCardNum);
        builder.setAttachmentUrl(attachmentUrl);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //用户实名认证查询
    public static ByteArrayEntity UserIdCardStatusQueryRequestConvertBuilder(String ChannelUserId,Integer ChannelId){
        UserIdCardIdentifyServiceProto.UserIdCardStatusQueryRequest.Builder builder=UserIdCardIdentifyServiceProto.UserIdCardStatusQueryRequest.newBuilder();
        builder.setChannelUserId(ChannelUserId);
        builder.setChannelId(ChannelId);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //邀请码注册
    public static ByteArrayEntity UserInviteCodeQueryRequest(String inviteCode,Integer channelId){
        UserBaseServiceProto.UserInviteCodeQueryRequest.Builder builder = UserBaseServiceProto.UserInviteCodeQueryRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setInviteCode(inviteCode);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //用户忘记登录密码
    public static ByteArrayEntity UserForgetPwdRequest(Integer channelId,String loginPwd,String mobile){
        UserLoginInfoServiceProto.UserForgetPwdRequest.Builder builder=UserLoginInfoServiceProto.UserForgetPwdRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setLoginPwd(loginPwd);
        builder.setMobile(mobile);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //微信绑定
    public static ByteArrayEntity UserWeChatAuthRequest(String AppId,Integer ChannelId,String ChannelUserId,String openId){
        UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
        builder.setAppId(AppId);
        builder.setChannelId(ChannelId);
        builder.setChannelUserId(ChannelUserId);
        builder.setOpenId(openId);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //微信一键登录
    public static ByteArrayEntity UserWeChatOneKeyLoginRequest(Integer ChannelId,String mobile,String inviteChannelUserId,String mobileareacode){
        UserWeChatAuthServiceProto.UserWeChatOneKeyLoginRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatOneKeyLoginRequest.newBuilder();
        builder.setChannelId(ChannelId);
        builder.setMobile(mobile);
        builder.setInviteChannelUserId(inviteChannelUserId);
        builder.setMobileAreaCode(mobileareacode);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //微信解绑
    public static ByteArrayEntity UserWeChatAuthUnBindRequest(String openId,Integer channelId,String channelUserId,String appId ){
        UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.newBuilder();
        builder.setOpenId(openId);
        builder.setChannelId(channelId);
        builder.setChannelUserId(channelUserId);
        builder.setAppId(appId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //绑定支付宝
    public static ByteArrayEntity  UserAliPayBidingRequest(String channelUserId,Integer channelId,String alipayRealname,String alipayAccount,String  alipayUserId){
        UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder=UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAlipayAccount(alipayAccount);
        builder.setAlipayRealname(alipayRealname);
        builder.setAlipayUserId(alipayUserId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //支付宝授权
    public static ByteArrayEntity UserAliPayAuthRequest(String channelUserId,Integer channelId,String alipayUserId){
        UserAliPayAuthServiceProto.UserAliPayAuthRequest.Builder builder=UserAliPayAuthServiceProto.UserAliPayAuthRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAlipayUserId(alipayUserId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //用户支付宝取消授权
    public static ByteArrayEntity UserAliPayAuthCancelRequest(String channelUserId,Integer channelId){
        UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.Builder builder=UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.newBuilder();
        builder.setChannelId(channelId);
        builder.setChannelUserId(channelUserId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //用户支付宝授权查询
    public static ByteArrayEntity UserAliPayAuthInfoRequest(String channelUserId,Integer channelId){
        UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.Builder builder =  UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    //添加用户收货地址
    public static ByteArrayEntity UserAddressInfoAddRequest(String channelUserId,Integer channelId,String address){
        UserAddressServiceProto.UserAddressInfoAddRequest.Builder builder=UserAddressServiceProto.UserAddressInfoAddRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAddress(address);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //获取用户收货地址&删除用户收货地址
    public static ByteArrayEntity UserAddressRequest(String channelUserId,Integer channelId,String addressId){
        UserAddressServiceProto.UserAddressRequest.Builder builder=UserAddressServiceProto.UserAddressRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAddressId(addressId);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //更新用户收货地址
    public static ByteArrayEntity UserAddressInfoUpdateRequest(String channelUserId,Integer channelId,String addressId,String username){
        UserAddressServiceProto.UserAddressInfoUpdateRequest.Builder builder=UserAddressServiceProto.UserAddressInfoUpdateRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAddressId(addressId);
        builder.setUserName(username);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //更新用户收货地址标签
    public static ByteArrayEntity UserAddressTagRequest(String channelUserId,Integer channelId,String addressId,Integer addressTag){
        UserAddressServiceProto.UserAddressTagRequest.Builder builder= UserAddressServiceProto.UserAddressTagRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setAddressId(addressId);
        builder.setAddressTag(addressTag);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }
    //分页查询户收货地址列表
    public static ByteArrayEntity UserAddressPageRequest(String channelUserId,Integer channelId,Integer pageSize,Integer pageNum){
        UserAddressServiceProto.UserAddressPageRequest.Builder builder=UserAddressServiceProto.UserAddressPageRequest.newBuilder();
        builder.setChannelUserId(channelUserId);
        builder.setChannelId(channelId);
        builder.setPageNum(pageNum);
        builder.setPageSize(pageSize);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    //获取省市区域树
    public static ByteArrayEntity UserSysSubAreaRequest(String parentId){
        UserAddressServiceProto.UserSysSubAreaRequest.Builder builder=UserAddressServiceProto.UserSysSubAreaRequest.newBuilder();
        builder.setParentId(parentId);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    /**
     * 商品服务
     * @param currentPage
     * @param pageSize
     * @param group  1-社区团购 2.直邮 3.课代表
     * @param group  1-普通商品 2-秒杀商品 3-新人专属 4-团长专属
     * @return
     */

    //分页查询商品列表
    public static ByteArrayEntity GetListByPageRequestDTO(Integer currentPage,Integer pageSize,Integer group){
        ProductServiceApiGetListByPage.GetListByPageRequestDTO.Builder builder= ProductServiceApiGetListByPage.GetListByPageRequestDTO.newBuilder();
        builder.setCurrentPage(currentPage);
        builder.setPageSize(pageSize);
        builder.setGroup(group);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    //根据ID获取商品详情
    public static ByteArrayEntity GetDetailByIdRequestDTO(Long id){
        ProductServiceApiGetDetailById.GetDetailByIdRequestDTO.Builder builder= ProductServiceApiGetDetailById.GetDetailByIdRequestDTO.newBuilder();
        builder.setId(id);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    //根据ID集合获取商品详情
    public static ByteArrayEntity GetDetailByIdListRequestDTO(Integer mode,Long id,String StoreId){
        ProductServiceApiGetDetailByIdList.GetDetailByIdListRequestDTO.Builder builder=ProductServiceApiGetDetailByIdList.GetDetailByIdListRequestDTO.newBuilder();
        List<ProductServiceApiGetDetailByIdList.GoodIdDTO> goodIdDTOList = Lists.newArrayList();
        ProductServiceApiGetDetailByIdList.GoodIdDTO.Builder goodIdDTOBuilder = ProductServiceApiGetDetailByIdList.GoodIdDTO.newBuilder();
        for (int i=0; i<2; i++) {
            goodIdDTOBuilder.clear();
            goodIdDTOBuilder.setGoodId(id);
            goodIdDTOBuilder.setSkuId(id);
            goodIdDTOBuilder.setStoreId(StoreId);
            goodIdDTOBuilder.setGroup(2);
            goodIdDTOList.add(goodIdDTOBuilder.build());
        }
        builder.addAllGoods(goodIdDTOList);
        //传1
        builder.setMode(mode);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return bytes;
    }

    //库存增-减-锁
    public static ByteArrayEntity LockStockByListRequestDto(String PorderId,Long GoodId,Long SkuId,ProductServiceApiStockService.GoodStockOperateEnum Enum){
        System.out.println(Enum);
        ProductServiceApiStockService.LockStockByListRequestDto.Builder builder = ProductServiceApiStockService.LockStockByListRequestDto.newBuilder();
        builder.setOrderId(PorderId);
        ProductServiceApiStockService.LockUserStockDTO.Builder userstockdto = ProductServiceApiStockService.LockUserStockDTO.newBuilder();
        userstockdto.setGoodId(GoodId);
        userstockdto.setSkuId(SkuId);
        userstockdto.setNum(1);
        userstockdto.setIsRemoveAdd(false);
        userstockdto.setOrderId(PorderId);
        userstockdto.setStockOperate(Enum);
        ProductServiceApiStockService.LockUserStockDTO dto = userstockdto.build();
        //将对象放进集合
        List<ProductServiceApiStockService.LockUserStockDTO> lockUserStockDtoList = new ArrayList<>();
        lockUserStockDtoList.add(dto);
        //将集合放进请求Builder上
        builder.addAllLockUserStockDtos(lockUserStockDtoList);
        System.out.println(builder);
        ByteArrayEntity bytes=new ByteArrayEntity(builder.build().toByteArray());
        return  bytes;
    }




}
