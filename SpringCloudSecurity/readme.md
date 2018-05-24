security-three:
1. get请求访问
`localhost:8080/oauth/authorize?client_id=your_client_id&response_type=code&redirect_uri=http://www.baidu.com`
> 输入默认的用户名`user`以及控制台随机生成的密码`xxx-xxxxxx-xxx`(因为spring security默认情况下会对所有url添加basic auth认证)
> 同意授权后重定向至百度并返回`code`

2. 通过code获取access_token
> postman-post请求(不再需要basic auth 认证)
`http://your_client_id:your_secret_id@localhost:8080/oauth/token`, `Content-Type = application/x-www-form-urlencoded`    
```java
[{"key":"grant_type","value":"authorization_code"},
{"key":"code","value":"VGtzPa"},
{"key":"redirect_uri","value":"http://www.baidu.com"}]
```
> 终端-curl直接请求
`curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=Li4NZo&redirect_uri=http://www.baidu.com' "http://client:secret@localhost:8080/oauth/token"`
> 响应报文:
```java
{
    "access_token": "37b61747-b722-4bcc-84b8-d9b71b2e0a6a",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "app"
}
```

客户端授权的四种模式:
> 授权码模式（authorization code）   
> 简化模式（implicit）    
> 密码模式（resource owner password credentials）     
> 客户端模式（client credentials）   

第三方客户端：经过认证服务器授权后即可访问资源服务器的REST API来获取资源所有者即用户的信息    
认证服务器：验证第三方客户端合法后给其颁布token，第三方通过token来调用资源服务器的API         
authorization_code：客户端先调用/oauth/authorize/ 进到用户授权界面，用户授权后返回 code ，客户端然后根据code和 appSecret 获取 access token     
password：用户向客户端提供用户名和密码，客户端将用户名和密码发给认证服务器，向后者请求令牌，适用于资源服务器、认证服务器与客户端具有完全的信任关系     
client_credentials：不需要用户参与，用于不同服务之间的对接     

```java
//client结果
{
   "access_token":"311c5cd9-18dd-44dc-85b1-80ce7724bb1e","token_type":"bearer","refresh_token":"81fc0f8d-ae64-4a69-bac3-15149a18c74c","expires_in":43199,"scope":"select"
}
//password结果
{
    "access_token": "311c5cd9-18dd-44dc-85b1-80ce7724bb1e",
    "token_type": "bearer",
    "refresh_token": "81fc0f8d-ae64-4a69-bac3-15149a18c74c",
    "expires_in": 37099,
    "scope": "select"
}
```

security-two:(console没有随机生成的password)
1. postman
```java
GET http://localhost:8080/oauth/token?username=user_1&password=123456&scope=select&grant_type=password
Basic Authorization(client_2+123456)
GET http://localhost:8080/oauth/token?grant_type=client_credentials&scope=select& client_id=client_1&client_secret=123456
Basic Authorization(client_1+123456)
```
2. idea rest client(不需要basic auth)

security-four:
> /user资源和配置文件
1. 得到code `http://localhost:8080/hyh/oauth/authorize?response_type=code&client_id=201805&redirect_uri=http://www.baidu.com`
> 账号user，密码配置文件中的123456
2. 同样post得到access-token `http://201805:031055@localhost:8080/hyh/oauth/token`
3. 访问/user资源 `ocalhost:8080/hyh/user?access_token=f59bb8d7-23d8-4b19-865b-fbc46b0bc826`
```java
//返回结果
{"authorities":[{"authority":"ROLE_USER"}],"details":{"remoteAddress":"0:0:0:0:0:0:0:1",
"sessionId":"1A734707AF8825A1CC83E1FE01648E37","tokenValue":"f59bb8d7-23d8-4b19-865b-fbc46b0bc826",
"tokenType":"Bearer","decodedDetails":null},"authenticated":true,"userAuthentication":{"authorities":[{"authority":"ROLE_USER"}],
"details":{"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":"73B1727870CC93C12AEC5F86787B8985"},"authenticated":true,
"principal":{"password":null,"username":"user","authorities":[{"authority":"ROLE_USER"}],
"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true},"credentials":null,"name":"user"},"credentials":"","principal":{"password":null,"username":"user",
"authorities":[{"authority":"ROLE_USER"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true},"oauth2Request":{"clientId":"201805","scope":["openid"],
"requestParameters":{"response_type":"code","redirect_uri":"http://www.baidu.com","code":"Scd6FQ","grant_type":"authorization_code","client_id":"201805"},"resourceIds":[],"authorities":[{"authority":"ROLE_USER"}],
"approved":true,"refresh":false,"redirectUri":"http://www.baidu.com","responseTypes":["code"],"extensions":{},"grantType":"authorization_code","refreshTokenRequest":null},"clientOnly":false,"name":"user"}
```

security-six:
1. 获取code
`http://localhost:8080/hyh/oauth/authorize?client_id=201805&response_type=code&redirect_uri=http://www.baidu.com`
> 返回code=kK3vwQ

2. 获取access_token
```java
post 
  http://201805:031055@localhost:8080/hyh/oauth/token
Content-Type
  "application/x-www-form-urlencoded"
body 
  "grant_type" : "authorization_code",
  "code" : "kK3vwQ",
  "redirect_uri" : "http://www.baidu.com"  
```
> 返回
```java
{
    "access_token": "7d192771-71f9-4211-879d-1a7bdfda6088",
    "token_type": "bearer",
    "refresh_token": "7c5a97dd-2cd7-4dff-b80a-be88d1abdcd0",
    "expires_in": 43199,
    "scope": "read write"
}
```
3. 访问/user接口(可选)
`http://localhost:8080/hyh/user?access_token=7d192771-71f9-4211-879d-1a7bdfda6088`
4. 访问/hyh/message接口
` http://localhost:8081//hyh/message?access_token=7d192771-71f9-4211-879d-1a7bdfda6088`

