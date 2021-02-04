package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.DepositArgs;
import com.githup.hicoincom.bean.args.InternalNotifyArgs;
import com.githup.hicoincom.bean.result.DepositNotifyResult;
import com.githup.hicoincom.bean.result.SimpleResult;

/**
 * @author ZPZ
 * @version 1.0
 * 通知服务，包括充值通知和内部通知
 * @date 2021-01-29 17:42
 **/
public interface IDepositNotifyService {
    /**
     * 充值通知
     *
     * @param args 充值信息
     * @return com.githup.hicoinsdk.bean.result.DepositNotifyResult
     * @author ZPZ
     */
    DepositNotifyResult depositNotify(DepositArgs args);

    /**
     * 内部通知
     *
     * @param args
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     * @author ZPZ
     */
    SimpleResult internalNotify(InternalNotifyArgs args);
}
