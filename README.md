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
  <version>1.0.1</version>
</dependency>
```

使用方式:

```
public class WapiSDKTest {

      private static  WalletClient client;

    /**
     * 前置配置初始化
     */

    @BeforeAll
    public  static void callWalletServiceTest() throws Exception {
        WalletConfig cfg = new WalletConfig();
        cfg.setDomain("http://baas.hiwallet.pro/api/v1");
        cfg.setAppId("xxxxxxx");//商户 app id
        //商户私钥
        cfg.setUserPrivateKey("xxxxxx");
        //waas 公钥
        cfg.setWaasPublickKey("xxxxxx");
        client = WalletClientFactory.createClient(cfg);

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
        AddressCountInfoResult countInfoResult = client.getAddressRegister().checkAvailable("VLX");
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

