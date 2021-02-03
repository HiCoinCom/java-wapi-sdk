package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.AddressInfoArgs;
import com.githup.hicoincom.bean.result.AddressCountInfoResult;
import com.githup.hicoincom.bean.result.SimpleResult;

/**
 * @Description: 地址注册的接口
 * @Author: ZPZ
 * @Version: 1.0
 * @Date: 2021-01-28 11:11
 **/
public interface IAddressRegisterService {
    /**
    * @Description: 向平台注册地址
    * @Param: [args] 地址信息
    * @Return: com.githup.hicoinsdk.bean.result.SimpleResult
    * @Author: ZPZ
    */
    SimpleResult register(AddressInfoArgs args);

    /**
    * @Description: 检查可用地址数量
    * @Param: [symbol] 币种信息
    * @Return: com.githup.hicoinsdk.bean.result.AddressCountInfoResult
    * @Author: ZPZ
    */
    AddressCountInfoResult checkAailable(String symbol);
}
