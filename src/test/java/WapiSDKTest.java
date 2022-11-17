
import com.alibaba.fastjson.JSONObject;
import com.githup.hicoincom.WalletClient;
import com.githup.hicoincom.WalletClientFactory;
import com.githup.hicoincom.WalletConfig;
import com.githup.hicoincom.bean.args.*;
import com.githup.hicoincom.bean.result.*;
import com.githup.hicoincom.util.HttpUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Test whether the packaged service is available.
 */
public class WapiSDKTest {

    private static  WalletClient client;

    /**
     * Pre-configuration initialization
     */

    @BeforeAll
    public  static void callWalletServiceTest() throws Exception {
        WalletConfig cfg = new WalletConfig();
        cfg.setDomain("http://baas.dw2nn.com/api/v1");
/*        cfg.setAppId("''");// app id
        //Merchant private key
        cfg.setUserPrivateKey("xxxxxx");
        //waas public key
        cfg.setWaasPublickKey("xxxxxx");*/
        cfg.setAppId("5a3d788537e2426bbe92ca86c14660f4");
        cfg.setUserPrivateKey("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsjgTYOSylVSFmENCirddLxQxmhG5qRFCVDarqqUnJI007q1IyfA8h1Af55Nu5V5Hogh+g0j8ZJiAbZ2zFhX9wTQQXBw7NJc3DdSruHA2cCs3U25dv/7YZI23nzNwPpB10JFEigKY2EvWxqkTgPRepleP6AFD/mTiwHkZoaFEIF6vV+ZZCjhF+sZC+VZADuwEifrbO3KT4zzyqqg6xgr3S9hP/NN2ygYxvO/Tw/hixmiSXsiuSpEkJiLfWRq8gJ41VR5kuToeNks3EBm8yT50kzX42LGxUFbEbS3IAF704J0oL1Kux6ItZT7fjM6wByjuc+YN4kgd2UbadeW8zpU7NAgMBAAECggEALZ5GLZu/7923vLIJVli7KG7pvliYkEOLQjygyPUEasaIbpP2YR5nKbxSd4BHjpJWIq/0CKJMyCtKJbSMDxQiAuB5ilT1Vsr9wQTC2rxT5ML+c52yRYvG6pJxRezk6MWse732LTbxui39AoFPQDEmhlD4OmnHACvTzsvSNVwOwCKCmQoBMalyhj5JbFbbnsiDAPj2kvZ83UtIobwX+fpSb/H2iO8EcLGF9yPi2+ao3PVlqzuLZDpxYPLJ2gwHye35g/7ErJB0hPAbsAb01H+ux6udszLFHgPOG1ESZxPzoGhqQtueijIURz3QuBRsnl0mqTWgikwXrCRLc3K5slZIwQKBgQDfInYcuV5O2zJQRmeIczHh2XHH5XTc/SUhfPmbACKfU5OvYggfYxgGUpxxpYVUdbnbjvoggz20zf4LdDBPmBJo6SJrhGp23vIK3+MlxzDdKlIb8XJOAG/toyYgIyrCqZk5DjqhRcasxue8SdOu6yJpu2qGzdzhPjadacXZX4Z8hQKBgQDF+GNR4iewrR5jgbT/LWt0yK5+vMSLUXLgYvV8vAJoKgkGGMN2ar9Ug/cK92gHhu6FA2G+w081xGPFwU1mRRCCzwTARpzNQXyrml5+h5HzQkjzHYGGEY7UiH/lyvunuF5zK2/hYPuBWUDUD709u/8gt71bLANmk+RCBADuynUfqQKBgQCN74XiT6fF0Xj3x1EkYuJgH8lYtoTX7c8oti6wgi2D284dyeYQ0R//JZ2OTRO63ggTuacx90nRMRdggdAZUUqkoWfbrKdsZfThJfl/lm1mwfzo+XUUWQLZzuX5m2Uf3nIw8PQqkhAG55+RDgUxRzDkwOrKRTkzrjdfemD4HOOXYQKBgEZi4sUpOF1bn9NXOYgEmREDjO7opKS4B7T52jhIDD8GuwCc61n96BrfdIVEo4sRkVpfcnBFtrLyKVsVODSlV5J6kkDemj9sCixlDTXFc8HejltJ4VROh87O3VJRUho2tfYSuouSVLmPhzoegnUhkzsJuNxikK725BAIpmBAQVZJAoGBAM3fUK7WSPaY1UJ9W+vgMqF9ls/Yc9BTLUdP7q9URIC4CgV22broupDw8qCaYb7PTVcpgJ4V9onQe3VKo+5s/wgPpH3BZ2G30LSP3ukyWeaZtZFcKmjMdDLTduUDYfaGQk1VeXqZRvJmaqDLklX2x2vwFTqAmyQe51n0ZKWyavEV");
        cfg.setWaasPublickKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2bzs3nH1uDjfeK/ZXcDYMyOBzMCChUuNSVew4h5q6gi+5ri/N3eyPtY1fNF7pkjxRcK7dla+hfmg3bRY/aHzq3xJ2aiJcHBGVgNxLQpMtd2kcwuyXujc1QuX3lpyi19cwGG7G0pdF+dHuimVAjHAg0a2Wbkrn7PyBpg/SA1Zkx9YhLUVG9fGMWBDeUPYQ8saGVcsQzRSfBEAYnMT00v5SRwDbRUBg7A/iM/jMIpySA6UzvBHRtlPcinDuJFyXY9kKTIydjEUxNtCyU4VvZTOMS33LK0fn3YUPsTWE1E0ue9mHva5NcuYazlufeVgwyL7CXNoFZJEx4lmEF2KTPtfWwIDAQAB");
        //cfg.setWaasPublickKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmY3x4DVuU2NV90HaXZWnxGj7XvuBtwGT4bvnCiDcpMVlNuQN1cr+Vg+WMPIUihWmWkupA9oXdZf4LzWtqv2SRlP3tIs+tWG4Df3FuuZ0W2CIxhtJNnWHIY146m/teif+H2v9G3GeY+P+z/LqGFyV0nLYJzs4WQZa3/RL0rh2IlVlfs1eHod+PX99o+Aog80kmUx8NF6ExsoO4qI9y3wW8CH+5tGVqHvq3NaD3jHyS3DYrRztfxuJw9k/YTfZ6rDJVXmD7onqvZe2leSe5h/ehu321y7nB7+2uTJ76i1YOmIrEBW8KupwQUU1JuZKvMCzcLowLjIOYysU8JfpxXH+MQIDAQAB");
        //cfg.setUserPrivateKey("MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDQ/Xw5xJGvaadYBbCfqFaUoLIKEFvxsXpfWAeIV93J6MeY+eeLrSPq4oQeEAHsv3HIKe+k/jhbOnu3GM19gX7xKJoVRU/mtrCKyIetxQfG8oQidV3KN4Tp1ScO+vtpwVOYXQlkJt/MMDfyXlbOcNXcBYRI6bsZ3/Q0/usKJkNuf7a46WsaqxhKLhwvAKeipERD79nus26DyXdWWq+/HphWPTHFaL/irPzJeVlXxoEA3WuyECRpNVuRaOKPq5IneUwdj30HkOqcX/kQCFhhz4ZOAAwvkoAd8diUX5j0YOUpmgFqLEgr9fce2AE5oggNoJkUtJBo7fqCEgSnKOd6mBcFAgMBAAECggEAcpe7aPWGqHc3QgWErlagesiONwR3VdRp9CORpcoAG7ke0JuewbDDNATWSqGeXJEws3+DHqYigqtTsLbR3hKGZ3dK3YTu5eOgc8i21KpntEe/+iD+t2Wv5INy8jzeZEOUMcQ55QeHyS9bTfM6h+HI8ea4fT5j0DFDd1ebyynl6YzyGYy4kccMSvfUhkY2NVbLH1EKCoNXDO1fOyxMZ2McNvDwFhmm9+RIWxgD+Jqso/t+eAFddkxZHzMKgvmvnS3ov5H+0dbhf/NGGinzv697YVF2+8iRFS9+dpcPGHKN8Pxfd+dASIAxAkRP+suLYAfBdnP5SdHP7PZTp0vHokgM1QKBgQDpagBKVZXfdwz2DGcVLHJezyhx0ND9ToMyW75LwAZqtCB5pStltnM1kCioQGqaIuvhMKBCq3EEAT7kxzJA6/1QFFwqaDjCIrhr0rtxcfzv027B5u8YFSr4Fe3Pk8Osrf3+Vh7DVYBnerknmYOn3D1sYxp0LCAuO3r6ilbA/mOdFwKBgQDlNng2UPqmJ/8ba4IuQIBijWfFnW3wmq5mQh4ZjZzt4WSzHhU8lUCt+U28HzKoziuKjUriOJJFkjqT+HlMgpeTgKDsHBDfioo40TcPhH29pdb0D340uNlQ93So6r5NNbMazmr/lBTvXNfFv7IMIhATuQWmBhV9AkbDHiSTQOoWQwKBgQDn8IhXVVJ3WxKLEOoB9Ue1BH85EfoKK9Fc4iNnnGoD7/fxSPqluLYk/JoaDuCfR6JDBBHMDhZgl2hK59H9B0ORJporHaOA7gV6R04xvcZM/jmp/nOJ58bp/MhyI3AmkEK7UBKDodvVd/Ky8e4MiGKU6Kmp1QspRbu/aGYqot6TgwKBgQCPszF0ZBpwFv0xtJn940RaBOr9EnC9ekxCIct7G7Q50qfuP7ryq2PMblLU0P5SpEbZ6zD6WJsjcMS2xf1OAUWEHQ1GWWYer11ut4N8gATQ4+q6QghOh2D/CNSjnd/GyhbKhITCTJU/Z4aDHGZEgwpvQv0OlBverpGse/ZUN0tKvwKBgQDme1R9fsRKrlNhXe1wXs8qDw10KOfAa9TC04RRlY3p9rLa6ZQhDrUQEHBZLeKOgWeiPHULt5emLcRlqm50mMBHHNoN61kHDIN+EdxZXmJnICD1TzD0P6yj+VjdbmGcz/mQ1+Msf0RT+/KGvjX+GenjpYiTQc7n2CwYypnN9ru8KA");
        client = WalletClientFactory.createClient(cfg);

    }

    @Test
    public void registerAddress() {
        //1.Registered address parameters
        AddressInfoArgs args = new AddressInfoArgs();
        //{"code":"1000014","msg":"only one address is allowed","sign":"","data":""}
/*        args.setSymbol("SRM");
        args.setAddresses("16ASftbAGPCSMpb1tU9GCxmWi59BPncttK");
        args.setMemo(true);*/
        //Register address results
        args.setSymbol("VLX");
        args.setAddresses("aaaaabbbbb");
        args.setMemo(false);
        SimpleResult addressRegisterResult = client.getAddressRegister().register(args);
        System.out.println("Register address results：" + addressRegisterResult.toJson());
        assertTrue(addressRegisterResult.getCode().equals("0"));
    }

    @Test
    public void checkAailableAddress() {
        //2.How many addresses are available
        AddressCountInfoResult countInfoResult = client.getAddressRegister().checkAvailable("NST");
        assertTrue(countInfoResult.getData().getSymbol().equals("NST"));
        System.out.println("available amount：" + countInfoResult.getData().getCount());
        System.out.println("available amount：" + countInfoResult.toJson());
        assertNotNull(countInfoResult.getData().getCount());
    }

    @Test
    public void depositNotify() {
        //3
        DepositArgs depositArgs = new DepositArgs();
        depositArgs.setRequestId("2343423242452");
        //depositArgs.setAddressFrom("aaaaabbbbb");
        depositArgs.setAddressTo("LZC5G3rWkzsDDRRJnMD9mfdefe2ncEFnNH");
        depositArgs.setAmount(new BigDecimal(123.00000));
        depositArgs.setBalance(new BigDecimal(12.0000));
        depositArgs.setConfirm(1000);//needs to be greater than6
        //depositArgs.setMemo("1");
        depositArgs.setStatus(1);
        depositArgs.setTxid("adb936e24130c3fffer294d607a865f467a906b96850e02c1a2771c759f0bfdsf");
        depositArgs.setSymbol("VLX");
        DepositNotifyResult depositNotifyResult = client.getDepositNotifyService().depositNotify(depositArgs);
        System.out.println("deposit interface：" + depositNotifyResult.toJson());
        assertTrue(depositNotifyResult.isSuccess());
       // assertEquals(depositNotifyResult.getData().getRequestId(), depositArgs.getRequestId());//"rawData":"{\"data\": {\"request_id\":\"requestId1\"}}"
    }

    @Test
    public void internalNotify() {
        //4.Internal notification interface call (temporarily unavailable for third parties)
        InternalNotifyArgs internalNotifyArgs = new InternalNotifyArgs();
        //internalNotifyArgs.setAddressFrom("");//not necessary
        internalNotifyArgs.setAddressTo("aaaaabbbbb");
        internalNotifyArgs.setAmount(new BigDecimal(234234));
        internalNotifyArgs.setBalance(new BigDecimal(12));
        internalNotifyArgs.setConfirm(1);
        //internalNotifyArgs.setMemo("1");//not necessary
        internalNotifyArgs.setFee(new BigDecimal(12));
        internalNotifyArgs.setTxid("adb936e24130c3fff9a6294d607a865f467a906b96850e02c1a2771c759ewwe");
        internalNotifyArgs.setSymbol("BTC");
        SimpleResult internalNotifyResult = client.getDepositNotifyService().internalNotify(internalNotifyArgs);
        System.out.println("internal notice Result：" + internalNotifyResult.toJson());
        assertTrue(internalNotifyResult.isSuccess());
    }

    @Test
    public void withdrawConsume() {
        //5.Withdrawal interface
        WithdrawConsumeResult withdrawConsumeResult = client.getWithdrawService().withdrawConsume("MGT1");//VLX
        System.out.println("withdraw api Result：" + withdrawConsumeResult.toJson());
        //assertTrue(withdrawConsumeResult.getData().get(0).getSymbol().equals("VLX"));
        assertTrue(withdrawConsumeResult.isSuccess());

    }
    @Test
    public void withdrawNotify() {
        //6. Withdrawal notification interface
        WithdrawNotifyArgs notifyArgs = new WithdrawNotifyArgs();
        //notifyArgs.setAddressFrom("");
        notifyArgs.setAddressTo("L111ZC5G3rWkzsDDRRJnMD9mfdefe2ncEF");
        notifyArgs.setAmount(new BigDecimal(234234));
        notifyArgs.setBalance(new BigDecimal(0));
        notifyArgs.setConfirm(1000);//
        //notifyArgs.setMemo("1");
        notifyArgs.setFee(new BigDecimal(12));
        notifyArgs.setTxid("adb936e243fa6294d607a865f467a906b96850e02c1a2771c759ewrweD");
        notifyArgs.setSymbol("VLX");
        notifyArgs.setTransId(50021);
        SimpleResult consumeResult = client.getWithdrawService().withdrawNotify(notifyArgs);
        System.out.println("Withdrawal notification Result：" + consumeResult.toJson());
        assertTrue(consumeResult.isSuccess());//When the id does not exist： {"code":"1000018","msg":"finance notify err","sign":"","success":false}

    }

    @Test
    public void withdrawCancel() {
        //7.rejection
        WithdrawCancelArgs cancelArgs = new WithdrawCancelArgs();
        cancelArgs.setMsg("wrong test");
        cancelArgs.setTransId(49560);// The wrong id may result in：{"code":"100002","msg":"system err","sign":"","success":false}
        cancelArgs.setSymbol("VLX");
        SimpleResult withdrawCancelResult = client.getWithdrawService().withdrawCancel(cancelArgs);
        System.out.println("rejection Result：" + withdrawCancelResult.toJson());
        assertTrue(withdrawCancelResult.isSuccess());
    }

    @Test
    public void jsonObjectTest() throws Exception {
        JSONObject jsonObject = JSONObject.parseObject("{\"code\":\"1000014\",\"msg\":\"only one address is allowed\",\"sign\":\"\",\"data\":\"\"}");
        jsonObject.put("rawData", "[{\"trans_id\":49560,\"symbol\":\"VLX\",\"amount\":\"0.1\",\"fee\":\"0\",\"address_to\":\"LeXQmkKyQanzngTNScd2JeSE3CDPLTYPMy\",\"memo\":\"\"},{\"trans_id\":50021,\"symbol\":\"VLX\",\"amount\":\"50\",\"fee\":\"0\",\"address_to\":\"L111ZC5G3rWkzsDDRRJnMD9mfdefe2ncEF\",\"memo\":\"\"}]");
        //ResultTest result=jsonObject.toJavaObject(ResultTest.class);
        //jsonObject.put("data", "{\"trans_id\":\"12344\",\"symbol\":\"123\"}");
        WithdrawConsumeResult test = jsonObject.toJavaObject(WithdrawConsumeResult.class);
        System.out.println(test.getData().get(0).getSymbol());
    }

    @Test
    public void timeOutTest() throws Exception {
        HttpUtils.sendPost("http://localhost:8088/addr/user_deposit_hash","");
    }
}
