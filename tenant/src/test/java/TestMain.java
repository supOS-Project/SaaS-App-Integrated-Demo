import com.alibaba.fastjson.JSONObject;
import com.bluetron.eco.sdk.api.SaaSApi;
import com.bluetron.eco.sdk.dto.common.ApiResponse;
import com.bluetron.eco.sdk.dto.user.UserReq;
import com.bluetron.eco.sdk.dto.user.UserRes;
import com.dev.happy.tenant.entity.Tenant;
import com.dev.happy.tenant.utils.RedisStandaloneUtils;
import org.junit.Before;
import org.junit.Test;


public class TestMain {
    private RedisStandaloneUtils redisStandaloneUtils;
    private Tenant tenant;
    private String accessToken;
    String host="127.0.0.1";
    Integer port=6379;
    @Before
    public void init(){
        redisStandaloneUtils=new RedisStandaloneUtils(host,port);
        String tenantId="3294bd07b7b04d2c926929dc92cc8680";
        accessToken = redisStandaloneUtils.get("access-token");
        tenant = JSONObject.parseObject(redisStandaloneUtils.get("TENANT-" + tenantId), Tenant.class);
        System.out.println("init....");
    }
    @Test
    public void testUserDetail(){

        String username="test";

        ApiResponse<UserRes> response=SaaSApi.userService.getInfo(tenant.getInstanceName(), tenant.getRegion(), accessToken, username);
        System.out.println(JSONObject.toJSONString(response));
    }
    @Test
    public void testUserAdd(){
        String userJson="{\"username\":\"test10027\",\"password\":\"Test1@45#\",\"userDesc\":\"普通用户\",\"timeZone\":\"GMT+0800\",\"personCode\":\"C00021\",\"companyCode\":\"default_org_company\",\"accountType\":0,\"roleNameList\":[\"R0001\"]}";
        UserReq userReq=JSONObject.parseObject(userJson,UserReq.class);
        ApiResponse<Boolean> response= SaaSApi.userService.add(tenant.getInstanceName(), tenant.getRegion(),accessToken,userReq);
        System.out.println(JSONObject.toJSONString(response));
    }
    @Test
    public void TestEnv(){
        System.out.println(System.getenv("M2_HOME"));
    }
}
