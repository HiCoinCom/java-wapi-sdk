# java-sdk

chain wapi client sdk

普通项目编译 jar 包

```
mvn clean install -DskipTests=true
```

maven项目直接引用
```
<dependency>
  <groupId>com.github.hicoincom</groupId>
  <artifactId>wapi-sdk</artifactId>
  <version>1.0.0</version>
</dependency>
```

使用方式:

```
public class WapiSDKTest {

    private WalletClient client;

    /**
     * 前置配置初始化
     */

    @Before
    public void callWalletServiceTest() throws Exception {
        WalletConfig cfg = new WalletConfig();
        cfg.setDomain("http://baas.hiwallet.pro/api/v1");//接口域名
        cfg.setAppId("8ee3794a7e5bd188c6af4fd7dda191f3");//商户 app id
        //商户私钥
        cfg.setUserPrivateKey("MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDQ/Xw5xJGvaadYBbCfqFaUoLIKEFvxsXpfWAeIV93J6MeY+eeLrSPq4oQeEAHsv3HIKe+k/jhbOnu3GM19gX7xKJoVRU/mtrCKyIetxQfG8oQidV3KN4Tp1ScO+vtpwVOYXQlkJt/MMDfyXlbOcNXcBYRI6bsZ3/Q0/usKJkNuf7a46WsaqxhKLhwvAKeipERD79nus26DyXdWWq+/HphWPTHFaL/irPzJeVlXxoEA3WuyECRpNVuRaOKPq5IneUwdj30HkOqcX/kQCFhhz4ZOAAwvkoAd8diUX5j0YOUpmgFqLEgr9fce2AE5oggNoJkUtJBo7fqCEgSnKOd6mBcFAgMBAAECggEAcpe7aPWGqHc3QgWErlagesiONwR3VdRp9CORpcoAG7ke0JuewbDDNATWSqGeXJEws3+DHqYigqtTsLbR3hKGZ3dK3YTu5eOgc8i21KpntEe/+iD+t2Wv5INy8jzeZEOUMcQ55QeHyS9bTfM6h+HI8ea4fT5j0DFDd1ebyynl6YzyGYy4kccMSvfUhkY2NVbLH1EKCoNXDO1fOyxMZ2McNvDwFhmm9+RIWxgD+Jqso/t+eAFddkxZHzMKgvmvnS3ov5H+0dbhf/NGGinzv697YVF2+8iRFS9+dpcPGHKN8Pxfd+dASIAxAkRP+suLYAfBdnP5SdHP7PZTp0vHokgM1QKBgQDpagBKVZXfdwz2DGcVLHJezyhx0ND9ToMyW75LwAZqtCB5pStltnM1kCioQGqaIuvhMKBCq3EEAT7kxzJA6/1QFFwqaDjCIrhr0rtxcfzv027B5u8YFSr4Fe3Pk8Osrf3+Vh7DVYBnerknmYOn3D1sYxp0LCAuO3r6ilbA/mOdFwKBgQDlNng2UPqmJ/8ba4IuQIBijWfFnW3wmq5mQh4ZjZzt4WSzHhU8lUCt+U28HzKoziuKjUriOJJFkjqT+HlMgpeTgKDsHBDfioo40TcPhH29pdb0D340uNlQ93So6r5NNbMazmr/lBTvXNfFv7IMIhATuQWmBhV9AkbDHiSTQOoWQwKBgQDn8IhXVVJ3WxKLEOoB9Ue1BH85EfoKK9Fc4iNnnGoD7/fxSPqluLYk/JoaDuCfR6JDBBHMDhZgl2hK59H9B0ORJporHaOA7gV6R04xvcZM/jmp/nOJ58bp/MhyI3AmkEK7UBKDodvVd/Ky8e4MiGKU6Kmp1QspRbu/aGYqot6TgwKBgQCPszF0ZBpwFv0xtJn940RaBOr9EnC9ekxCIct7G7Q50qfuP7ryq2PMblLU0P5SpEbZ6zD6WJsjcMS2xf1OAUWEHQ1GWWYer11ut4N8gATQ4+q6QghOh2D/CNSjnd/GyhbKhITCTJU/Z4aDHGZEgwpvQv0OlBverpGse/ZUN0tKvwKBgQDme1R9fsRKrlNhXe1wXs8qDw10KOfAa9TC04RRlY3p9rLa6ZQhDrUQEHBZLeKOgWeiPHULt5emLcRlqm50mMBHHNoN61kHDIN+EdxZXmJnICD1TzD0P6yj+VjdbmGcz/mQ1+Msf0RT+/KGvjX+GenjpYiTQc7n2CwYypnN9ru8KA");
        //waas 公钥
        cfg.setWaasPublickKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmY3x4DVuU2NV90HaXZWnxGj7XvuBtwGT4bvnCiDcpMVlNuQN1cr+Vg+WMPIUihWmWkupA9oXdZf4LzWtqv2SRlP3tIs+tWG4Df3FuuZ0W2CIxhtJNnWHIY146m/teif+H2v9G3GeY+P+z/LqGFyV0nLYJzs4WQZa3/RL0rh2IlVlfs1eHod+PX99o+Aog80kmUx8NF6ExsoO4qI9y3wW8CH+5tGVqHvq3NaD3jHyS3DYrRztfxuJw9k/YTfZ6rDJVXmD7onqvZe2leSe5h/ehu321y7nB7+2uTJ76i1YOmIrEBW8KupwQUU1JuZKvMCzcLowLjIOYysU8JfpxXH+MQIDAQAB");
        client = WalletClientFactory.CreateClient(cfg);
    }

    @Test
    public void registerAddress() {
        //1.注册地址参数
        AddressInfoArgs args = new AddressInfoArgs();
        //{"code":"1000014","msg":"only one address is allowed","sign":"","data":""}
/*        args.setSymbol("SRM");
        args.setAddresses("16ASftbAGPCSMpb1tU9GCxmWi59BPncttK");
        args.setMemo(true);*/
        //注册地址结果
        args.setSymbol("VLX");
        args.setAddresses("aaaaabbbbb");
        args.setMemo(false);
        SimpleResult addressRegisterResult = client.getAddressRegister().register(args);
        System.out.println("地址注册结果：" + addressRegisterResult.toJson());
        assertTrue(addressRegisterResult.getCode().equals("0"));
    }

    @Test
    public void checkAailableAddress() {
        //2.推送的地址中还有多少可用地址
        AddressCountInfoResult countInfoResult = client.getAddressRegister().checkAailable("VLX");
        assertTrue(countInfoResult.getData().getSymbol().equals("VLX"));
        System.out.println("可用地址数量：" + countInfoResult.getData().getCount());
        System.out.println("可用地址结果：" + countInfoResult.toJson());
        assertNotNull(countInfoResult.getData().getCount());
    }

    @Test
    public void depositNotify() {
        //3. 充值接口调用
        DepositArgs depositArgs = new DepositArgs();
        depositArgs.setRequestId("2343423242452");
        //depositArgs.setAddressFrom("aaaaabbbbb");
        depositArgs.setAddressTo("LZC5G3rWkzsDDRRJnMD9mfdefe2ncEFnNH");
        depositArgs.setAmount(new BigDecimal(123.00000));
        depositArgs.setBalance(new BigDecimal(12.0000));
        depositArgs.setConfirm(1000);//需要大于6
        //depositArgs.setMemo("1");
        depositArgs.setStatus(1);
        depositArgs.setTxid("adb936e24130c3fffer294d607a865f467a906b96850e02c1a2771c759f0bfdsf");
        depositArgs.setSymbol("VLX");
        DepositNotifyResult depositNotifyResult = client.getDepositNotifyService().depositNotify(depositArgs);
        System.out.println("充值接口：" + depositNotifyResult.toJson());
        assertTrue(depositNotifyResult.isSuccess());
       // assertEquals(depositNotifyResult.getData().getRequestId(), depositArgs.getRequestId());//"rawData":"{\"data\": {\"request_id\":\"requestId1\"}}"
    }

    @Test
    public void internalNotify() {
        //4.内部通知接口调用（第三方暂时用不到）
        InternalNotifyArgs internalNotifyArgs = new InternalNotifyArgs();
        //internalNotifyArgs.setAddressFrom("");//非必须
        internalNotifyArgs.setAddressTo("aaaaabbbbb");
        internalNotifyArgs.setAmount(new BigDecimal(234234));
        internalNotifyArgs.setBalance(new BigDecimal(12));
        internalNotifyArgs.setConfirm(1);
        //internalNotifyArgs.setMemo("1");//非必须
        internalNotifyArgs.setFee(new BigDecimal(12));
        internalNotifyArgs.setTxid("adb936e24130c3fff9a6294d607a865f467a906b96850e02c1a2771c759ewwe");
        internalNotifyArgs.setSymbol("VLX");
        SimpleResult internalNotifyResult = client.getDepositNotifyService().internalNotify(internalNotifyArgs);
        System.out.println("内部通知接口 Result：" + internalNotifyResult.toJson());
        assertTrue(internalNotifyResult.isSuccess());
    }

    @Test
    public void withdrawConsume() {
        //5.提币拉取接口
        WithdrawConsumeResult withdrawConsumeResult = client.getWithdrawService().withdrawConsume("VLX");//VLX
        System.out.println("提币接口 Result：" + withdrawConsumeResult.toJson());
        //assertTrue(withdrawConsumeResult.getData().get(0).getSymbol().equals("VLX"));
        assertTrue(withdrawConsumeResult.isSuccess());

    }
    @Test
    public void withdrawNotify() {
        //6. 提币通知接口
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
        System.out.println("提币通知接口 Result：" + consumeResult.toJson());
        assertTrue(consumeResult.isSuccess());//流水id不存在时： {"code":"1000018","msg":"finance notify err","sign":"","success":false}

    }

    @Test
    public void withdrawCancel() {
        //7.提币打回
        WithdrawCancelArgs cancelArgs = new WithdrawCancelArgs();
        cancelArgs.setMsg("wrong test");
        cancelArgs.setTransId(49560);// 流水id传错可能会：{"code":"100002","msg":"system err","sign":"","success":false}
        cancelArgs.setSymbol("VLX");
        SimpleResult withdrawCancelResult = client.getWithdrawService().withdrawCancel(cancelArgs);
        System.out.println("提币打回接口 Result：" + withdrawCancelResult.toJson());
        assertTrue(withdrawCancelResult.isSuccess());
    }

}
```

