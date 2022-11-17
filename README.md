# java-sdk

chain wapi client sdk

Compile jar package for  project

```
mvn clean install -DskipTests=true
```

maven project direct reference
```
<dependency>
  <groupId>com.github.hicoincom</groupId>
  <artifactId>wapi-sdk</artifactId>
  <version>1.0.1</version>
</dependency>
```

How to use:

```
public class WapiSDKTest {

      private static  WalletClient client;

    /**
     * Pre-configuration initialization
     */

    @BeforeAll
    public  static void callWalletServiceTest() throws Exception {
        WalletConfig cfg = new WalletConfig();
        cfg.setDomain("http://baas.hiwallet.pro/api/v1");
        cfg.setAppId("xxxxxxx");// app id
        //Merchant private key
        cfg.setUserPrivateKey("xxxxxx");
        //waas public key
        cfg.setWaasPublickKey("xxxxxx");
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
        //Registered address results
        args.setSymbol("VLX");
        args.setAddresses("aaaaabbbbb");
        args.setMemo(false);
        SimpleResult addressRegisterResult = client.getAddressRegister().register(args);
        System.out.println("Registered address results：" + addressRegisterResult.toJson());
        assertTrue(addressRegisterResult.getCode().equals("0"));
    }

    @Test
    public void checkAailableAddress() {
        //2.How many addresses are still available in the pushed addresses
        AddressCountInfoResult countInfoResult = client.getAddressRegister().checkAvailable("VLX");
        assertTrue(countInfoResult.getData().getSymbol().equals("VLX"));
        System.out.println("Number of available addresses：" + countInfoResult.getData().getCount());
        System.out.println("Available address results：" + countInfoResult.toJson());
        assertNotNull(countInfoResult.getData().getCount());
    }

    @Test
    public void depositNotify() {
        //3. Recharge interface call
        DepositArgs depositArgs = new DepositArgs();
        depositArgs.setRequestId("2343423242452");
        //depositArgs.setAddressFrom("aaaaabbbbb");
        depositArgs.setAddressTo("LZC5G3rWkzsDDRRJnMD9mfdefe2ncEFnNH");
        depositArgs.setAmount(new BigDecimal(123.00000));
        depositArgs.setBalance(new BigDecimal(12.0000));
        depositArgs.setConfirm(1000);//needs to be greater than 6
        //depositArgs.setMemo("1");
        depositArgs.setStatus(1);
        depositArgs.setTxid("adb936e24130c3fffer294d607a865f467a906b96850e02c1a2771c759f0bfdsf");
        depositArgs.setSymbol("VLX");
        DepositNotifyResult depositNotifyResult = client.getDepositNotifyService().depositNotify(depositArgs);
        System.out.println("Recharge interface：" + depositNotifyResult.toJson());
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
        internalNotifyArgs.setSymbol("VLX");
        SimpleResult internalNotifyResult = client.getDepositNotifyService().internalNotify(internalNotifyArgs);
        System.out.println("Internal notification interface Result：" + internalNotifyResult.toJson());
        assertTrue(internalNotifyResult.isSuccess());
    }

    @Test
    public void withdrawConsume() {
        //5.Withdrawal records interface
        WithdrawConsumeResult withdrawConsumeResult = client.getWithdrawService().withdrawConsume("VLX");//VLX
        System.out.println("Withdrawal records interface Result：" + withdrawConsumeResult.toJson());
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
        System.out.println("Withdrawal notification interface Result：" + consumeResult.toJson());
        assertTrue(consumeResult.isSuccess());//When the transaction id does not exist： {"code":"1000018","msg":"finance notify err","sign":"","success":false}

    }

    @Test
    public void withdrawCancel() {
        //7.to reject withdraw request 
        WithdrawCancelArgs cancelArgs = new WithdrawCancelArgs();
        cancelArgs.setMsg("wrong test");
        cancelArgs.setTransId(49560);// Incorrect transfer id may result in：{"code":"100002","msg":"system err","sign":"","success":false}
        cancelArgs.setSymbol("VLX");
        SimpleResult withdrawCancelResult = client.getWithdrawService().withdrawCancel(cancelArgs);
        System.out.println("Withdrawal and return interface Result：" + withdrawCancelResult.toJson());
        assertTrue(withdrawCancelResult.isSuccess());
    }

}
```

