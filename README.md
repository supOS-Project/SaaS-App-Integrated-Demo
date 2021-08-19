该Demo主要为了ISV快速集成基于java开发的SaaS App到SupOS上。  
该Demo实现了SupOS的oauth2鉴权及常用的open-api(用户、组织等)  
  * 基于spring boot 2.3.10.RELEASE  
  * 持久层ORM采用mybatis-plus
  * 缓存使用redis，client使用redisTemplate
代码clone后配置相关信息就能够运行，需修改的配置如下  
    1）bluetron-saas-sdk.properties
    ```properties
        # 开放平台创建的saas app的AppID
        boss.app.id=
        # 开放平台创建的saas app的AppSecret
        boss.secret.key=
        # 租户接口签名校验的公钥
        boss.rsa.public.key=
    ```
    2)application.yml
    ```yaml
        spring:
          datasource:
            driver-class-name: com.mysql.jdbc.Driver
            url: jdbc:mysql://xxx.xxx.xxx.xxx:xxx/xxxx?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false
            username: xxx
            password: xxx
          redis:
            host: xxx.xx.xx.xxx
            port: 6379
        app:
         home-url: http://localhost:30001/home #oauth2授权后跳转的地址
    ```
代码层次：  
  ```text  
  ├─saas-sdk
│  └─src
│      └─main
│          ├─java
│          │  └─com
│          │      └─bluetron
│          │          └─eco
│          │              └─sdk
│          │                  ├─api
│          │                  ├─config
│          │                  ├─controller
│          │                  ├─dto
│          │                  │  ├─auth
│          │                  │  ├─common
│          │                  │  └─tenant
│          │                  ├─service
│          │                  └─util
│          └─resources
└─tenant
    ├─ddl
    └─src
        └─main
            ├─java
            │  └─com
            │      └─dev
            │          └─happy
            │              └─tenant
            │                  ├─config
            │                  ├─constant
            │                  ├─controller
            │                  ├─entity
            │                  │  └─ext
            │                  ├─enums
            │                  ├─filters
            │                  ├─mapper
            │                  ├─service
            │                  ├─utils
            │                  └─vo
            └─resources
                ├─db
                │  └─migration
                ├─mapper
                ├─static
                │  ├─css
                │  ├─js
                │  │  └─manage
                │  └─layui
                │      ├─css
                │      │  └─modules
                │      │      ├─laydate
                │      │      │  └─default
                │      │      └─layer
                │      │          └─default
                │      └─font
                └─templates
                    └─manage
  ```
oauth2鉴权及权限校验：com.dev.happy.tenant.filters.AuthFilter
租户接口实现：com.dev.happy.tenant.controller.TenantApiController
webhook消息订阅：com.dev.happy.tenant.controller.MessageController

