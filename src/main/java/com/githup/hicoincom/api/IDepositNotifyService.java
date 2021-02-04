package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.DepositArgs;
import com.githup.hicoincom.bean.args.InternalNotifyArgs;
import com.githup.hicoincom.bean.result.DepositNotifyResult;
import com.githup.hicoincom.bean.result.SimpleResult;

/**
 * 通知服务，包括充值通知和内部通知
 *
 * @author ZPZ
 * @version 1.0
 **/
public interface IDepositNotifyService {
    /**
     * 充值通知
     *
     * @param args 充值信息
     * @return com.githup.hicoinsdk.bean.result.DepositNotifyResult
     */
    DepositNotifyResult depositNotify(DepositArgs args);

    /**
     * 内部通知
     *
     * @param args 通知参数
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     */
    SimpleResult internalNotify(InternalNotifyArgs args);
}
