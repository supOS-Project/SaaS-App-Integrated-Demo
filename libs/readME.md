1.在jar包所在目录执行下面的语句将sdk包安装到本地maven仓库
```shell
mvn install:install-file -Dfile="saas-sdk-1.0.0.jar" -DgroupId="com.supcon.supos" -DartifactId="saas-sdk" -Dversion="1.0.0" -Dpackaging=jar
```

2.在项目的pom.xml引入依赖
```xml
        <dependency>
            <groupId>com.supcon.supos</groupId>
            <artifactId>saas-sdk</artifactId>
            <version>1.0.0</version>
        </dependency>
```
租户接口实现
 创建一个Controller类 设置RequestMapping为"/open-api/app"（此值为固定值，不能设置为其他） 继承 ApiController并实现其中的4个接口  
   openTenant:租户开通    
   返回结构：
   ```json
   {
     "code": 200,
       "msg": "",
       "data": {
        "tenantId": "租户ID"
      }
   } 
   ```
   doQueryStatus：租户状态查询  
返回结构：  
```json
   {
       "code": 200,
       "msg": "成功",
       
       "data": {
           "tenantId": "77f93c8b963e4e0788e5a5b922b203c6",
           "tenantUrl": ""
       }
   }
```
   doRenew：租户续期   
返回结构：  
```json
  {
      "code": 200,
      "msg": "成功",
      
      "data": null
  }
```
   doStop：租户停止    
返回结构： 
```json
{
    "code": 200,
    "msg": "成功",
    "data": null
}
```

