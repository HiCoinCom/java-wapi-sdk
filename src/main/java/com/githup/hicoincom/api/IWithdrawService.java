package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.WithdrawCancelArgs;
import com.githup.hicoincom.bean.args.WithdrawNotifyArgs;
import com.githup.hicoincom.bean.result.SimpleResult;
import com.githup.hicoincom.bean.result.WithdrawConsumeResult;

/**
 * @Description: 提币相关接口
 * @Author: ZPZ
 * @Version: 1.0
 * @Date: 2021-01-29 19:10
 **/
public interface IWithdrawService {
    /**
    * @Description: 提币拉取接口
    * @Param: [symbol] 币种信息
    * @Return: com.githup.hicoinsdk.bean.result.SimpleResult
    * @Author: ZPZ
    */
    WithdrawConsumeResult withdrawConsume(String symbol);
    /**
    * @Description: 提币通知接口
    * @Param: [args] 提币入参信息
    * @Return: com.githup.hicoinsdk.bean.result.WithdrawConsumeResult
    * @Author: ZPZ
    */
    SimpleResult withdrawNotify(WithdrawNotifyArgs args);
    /**
    * @Description: 提币打回接口
    * @Param: [args] 入参信息
    * @Return: com.githup.hicoinsdk.bean.result.SimpleResult
    * @Author: ZPZ
    */
    SimpleResult withdrawCancel(WithdrawCancelArgs args);
}
