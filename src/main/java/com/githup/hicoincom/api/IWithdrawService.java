package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.WithdrawCancelArgs;
import com.githup.hicoincom.bean.args.WithdrawNotifyArgs;
import com.githup.hicoincom.bean.result.SimpleResult;
import com.githup.hicoincom.bean.result.WithdrawConsumeResult;

/**
 * Withdrawal related interface
 *
 * @author ZPZ
 * @version 1.0
 **/
public interface IWithdrawService {
    /**
     * Withdrawal records interface
     *
     * @param symbol Currency information
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     */
    WithdrawConsumeResult withdrawConsume(String symbol);

    /**
     * Withdrawal notification interface
     *
     * @param args Withdrawal information
     * @return com.githup.hicoinsdk.bean.result.WithdrawConsumeResult
     */
    SimpleResult withdrawNotify(WithdrawNotifyArgs args);

    /**
     * Withdrawal and return interface
     *
     * @param args Withdrawal and return input parameter information
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     */
    SimpleResult withdrawCancel(WithdrawCancelArgs args);
}
