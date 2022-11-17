package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.DepositArgs;
import com.githup.hicoincom.bean.args.InternalNotifyArgs;
import com.githup.hicoincom.bean.result.DepositNotifyResult;
import com.githup.hicoincom.bean.result.SimpleResult;

/**
 * Notification services, including deposit notifications and internal notifications
 *
 * @author ZPZ
 * @version 1.0
 **/
public interface IDepositNotifyService {
    /**
     * Recharge notice
     *
     * @param args Recharge information
     * @return com.githup.hicoinsdk.bean.result.DepositNotifyResult
     */
    DepositNotifyResult depositNotify(DepositArgs args);

    /**
     * internal notification
     *
     * @param args Notification parameters
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     */
    SimpleResult internalNotify(InternalNotifyArgs args);
}
