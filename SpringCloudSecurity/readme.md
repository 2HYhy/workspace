##### 客户端授权的四种模式:
> 授权码模式（authorization code）   
> 简化模式（implicit）    
> 密码模式（resource owner password credentials）     
> 客户端模式（client credentials）

##### security-oauth模块:
> postman GET请求
`http://localhost:8080/oauth/token?username=user_a&password=123456&scope=select&grant_type=password`
`Basic Authorization(client_1+123456)`
{
    "access_token": "439ea2b3-a16c-4bf7-9f1b-d60bd3924f31",
    "token_type": "bearer",
    "refresh_token": "22df70ac-be47-47dd-a165-18a748cd273f",
    "expires_in": 43199,
    "scope": "select"
}

`http://localhost:8080/oauth/token?grant_type=client_credentials&scope=select& client_id=client_1&client_secret=123456`
`Basic Authorization(client_2+123456)`
{
    "access_token": "cedf599d-d8d6-4abe-81f5-6bde8ea5868f",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "select"
}

##### security-four模块:
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

##### security-one模块:
> 浏览器访问`http://localhost:8080/hyh/oauth/authorize?response_type=code&client_id=201906&redirect_uri=http://www.baidu.com`
> 输入账号密码monday+100001或者tuesday+900009
> 使用code，post请求获取token`http://201906:06042019@localhost:8080/hyh/oauth/token`
> 访问/user资源 `localhost:8080/hyh/user?access_token=xxx`




##### security-three模块:
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
