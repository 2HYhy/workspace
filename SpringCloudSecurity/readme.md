##### 客户端授权的四种模式:
> 授权码模式（authorization code）   
> 简化模式（implicit）    
> 密码模式（resource owner password credentials）     
> 客户端模式（client credentials）

##### code模块:
1. 浏览器访问
`http://localhost:8080/oauth/authorize?client_id=c123456&response_type=code&redirect_uri=http://www.baidu.com`   
2. 输入默认的用户名`user`以及控制台随机生成的`password`(因为spring security默认情况下会对所有url添加basic auth认证)   
3. 同意授权后重定向至百度并返回`code`   
4. 通过code获取access_token
> postman-post请求,不再需要basic auth认证
`http://your_client_id:your_secret_id@localhost:8080/oauth/token`, `Content-Type = application/x-www-form-urlencoded`
{
  [{"key":"grant_type","value":"authorization_code"},
  {"key":"code","value":"VGtzPa"},
  {"key":"redirect_uri","value":"http://www.baidu.com"}]   
}                     
> 终端-curl直接请求
`curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=xxx&redirect_uri=http://www.baidu.com' "http://client:secret@localhost:8080/oauth/token"`
{
  "access_token": "37b61747-b722-4bcc-84b8-d9b71b2e0a6a",
  "token_type": "bearer",
  "expires_in": 43199,
  "scope": "app"
}   
    
     
##### security-two模块:
1. postman-get请求
`http://localhost:8080/oauth/token?username=user_a&password=123456&scope=select&grant_type=password`
`Basic Authorization(client_2+123456)`

`http://localhost:8080/oauth/token?grant_type=client_credentials&scope=select& client_id=client_1&client_secret=123456`
`Basic Authorization(client_1+123456)`

2. idea rest client(不需要basic auth)


##### security-four模块:
> /user资源和配置文件
1. 得到code `http://localhost:8080/hyh/oauth/authorize?response_type=code&client_id=201805&redirect_uri=http://www.baidu.com`
> 账号user，密码配置文件中的123456
2. 同样post得到access-token `http://201805:031055@localhost:8080/hyh/oauth/token`
3. 访问/user资源 `localhost:8080/hyh/user?access_token=f59bb8d7-23d8-4b19-865b-fbc46b0bc826`

##### security-six:
1. 获取code
`http://localhost:8080/hyh/oauth/authorize?client_id=201805&response_type=code&redirect_uri=http://www.baidu.com`
2. 获取access_token
post请求`http://201805:031055@localhost:8080/hyh/oauth/token`
Content-Type `application/x-www-form-urlencoded`
{
    "grant_type" : "authorization_code",
    "code" : "kK3vwQ",
    "redirect_uri" : "http://www.baidu.com"  
}
3. 访问/user接口(可选)
`http://localhost:8080/hyh/user?access_token=7d192771-71f9-4211-879d-1a7bdfda6088`
4. 访问/hyh/message接口
` http://localhost:8081//hyh/message?access_token=7d192771-71f9-4211-879d-1a7bdfda6088`
