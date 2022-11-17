package com.githup.hicoincom.api;

import com.githup.hicoincom.bean.args.AddressInfoArgs;
import com.githup.hicoincom.bean.result.AddressCountInfoResult;
import com.githup.hicoincom.bean.result.SimpleResult;

/**
 * Interface for address registration
 *
 * @author ZPZ
 * @version 1.0
 **/
public interface IAddressRegisterService {
    /**
     * Register an address under the platform
     *
     * @param args Address information
     * @return com.githup.hicoinsdk.bean.result.SimpleResult
     */
    SimpleResult register(AddressInfoArgs args);

    /**
     * query the number of available address
     *
     * @param symbol Currency information
     * @return com.githup.hicoinsdk.bean.result.AddressCountInfoResult
     */
    AddressCountInfoResult checkAvailable(String symbol);
}
