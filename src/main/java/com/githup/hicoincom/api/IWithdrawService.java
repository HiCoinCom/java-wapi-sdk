package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.WithdrawCancelArgs;
import com.githup.hicoincom.bean.args.WithdrawNotifyArgs;
import com.githup.hicoincom.bean.result.SimpleResult;
import com.githup.hicoincom.bean.result.WithdrawConsumeResult;

/**
 * @author ZPZ
 * @version 1.0
 * 提币相关接口
 * @date 2021-01-29 19:10
 **/
public interface IWithdrawService {
    /**
     * 提币拉取接口
     *
     * @param symbol 币种信息
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     * @author ZPZ
     */
    WithdrawConsumeResult withdrawConsume(String symbol);

    /**
     * 提币通知接口
     *
     * @param args 提币入参信息
     * @return com.githup.hicoinsdk.bean.result.WithdrawConsumeResult
     * @author ZPZ
     */
    SimpleResult withdrawNotify(WithdrawNotifyArgs args);

    /**
     * 提币打回接口
     *
     * @param args 入参信息
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     * @author ZPZ
     */
    SimpleResult withdrawCancel(WithdrawCancelArgs args);
}
