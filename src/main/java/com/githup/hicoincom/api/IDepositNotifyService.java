package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.DepositArgs;
import com.githup.hicoincom.bean.args.InternalNotifyArgs;
import com.githup.hicoincom.bean.result.DepositNotifyResult;
import com.githup.hicoincom.bean.result.SimpleResult;

/**
 * @Description: 通知服务，包括充值通知和内部通知
 * @Author: ZPZ
 * @Version: 1.0
 * @Date: 2021-01-29 17:42
 **/
public interface IDepositNotifyService {
    /**
    * @Description:  充值通知
    * @Param: [args] 充值信息
    * @Return: com.githup.hicoinsdk.bean.result.DepositNotifyResult
    * @Author: ZPZ
    */
    DepositNotifyResult depositNotify(DepositArgs args);
    /**
    * @Description: 内部通知
    * @Param: [args]
    * @Return: com.githup.hicoinsdk.bean.result.SimpleResult
    * @Author: ZPZ
    */
    SimpleResult internalNotify(InternalNotifyArgs args);
}
