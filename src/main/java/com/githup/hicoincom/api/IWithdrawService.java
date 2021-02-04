package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.WithdrawCancelArgs;
import com.githup.hicoincom.bean.args.WithdrawNotifyArgs;
import com.githup.hicoincom.bean.result.SimpleResult;
import com.githup.hicoincom.bean.result.WithdrawConsumeResult;

/**
 * 提币相关接口
 *
 * @author ZPZ
 * @version 1.0
 **/
public interface IWithdrawService {
    /**
     * 提币拉取接口
     *
     * @param symbol 币种信息
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     */
    WithdrawConsumeResult withdrawConsume(String symbol);

    /**
     * 提币通知接口
     *
     * @param args 提币入参信息
     * @return com.githup.hicoinsdk.bean.result.WithdrawConsumeResult
     */
    SimpleResult withdrawNotify(WithdrawNotifyArgs args);

    /**
     * 提币打回接口
     *
     * @param args 提币打回入参信息
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     */
    SimpleResult withdrawCancel(WithdrawCancelArgs args);
}
