package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.AddressInfoArgs;
import com.githup.hicoincom.bean.result.AddressCountInfoResult;
import com.githup.hicoincom.bean.result.SimpleResult;

/**
 * @author ZPZ
 * 地址注册的接口
 * @version 1.0
 * @date 2021-01-28 11:11
 **/

public interface IAddressRegisterService {
    /**
     * 向平台注册地址
     *
     * @param args 地址信息
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     * @author ZPZ
     */
    SimpleResult register(AddressInfoArgs args);

    /**
     * 检查可用地址数量
     *
     * @param symbol 币种信息
     * @return com.githup.hicoinsdk.bean.result.AddressCountInfoResult
     * @author ZPZ
     */
    AddressCountInfoResult checkAvailable(String symbol);
}
