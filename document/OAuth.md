### OpenID Connect = OIDC = OpenId(即Authentication认证) + OAuth 2.0(即Authorization授权)
> 授权要在认证之后进行，只有确定用户身份只有才能授权。
> OIDC是遵循OAuth协议流程，在申请Access-Token的同时，也返回了ID-Token(用JWT格式包装)来验证用户身份。

### OIDC的流程由以下5个步骤构成：

1. RP(客户端，消费方)发送一个认证请求给OP(提供EU认证的服务方)；
2. OP对EU(一个人类用户)进行身份认证，然后提供授权；
3. OP把ID Token和Access Token（需要的话）返回给RP；
4. RP使用Access Token发送一个请求UserInfo EndPoint；
5. UserInfo EndPoint返回EU的Claims。

### 基于Authorization Code的认证请求,构建一个OIDC的Authentication Request需要提供如下的参数：

1. scope：必须。OIDC的请求必须包含值为“openid”的scope的参数。
2. response_type：必选。同OAuth2。
3. client_id：必选。同OAuth2。
4. redirect_uri：必选。同OAuth2。
5. state：推荐。同OAuth2。防止CSRF, XSRF。

### OidcDiscoveryUrl
`https://hyh.gacl.com/.well-known/openid-configuration`

### 客户端申请认证的URL
`https://hyh.gacl.com/auth?client_id=client-id&redirect_uri=http%3A%2F%2F127.0.0.1%3A3000%2Fcallback&response_type=code&scope=openid+email+groups+profile+offline_access&state=I%2Bwish%2Bto%2Bwash%2Bmy%2Birish%2Bwristwatch`

### OAuth 2.0
是一种授权协议，而不是认证协议。一个OAuth的过程通常包含在一些认证的过程中。在客户端和服务提供商之间设置了一个授权层，将用户与客户端分离。
涉及到的成员:
1. 第三方应用程序,也叫做客户端    
2. 服务提供商       
3. 资源所有者，也就是用户        
4. 认证服务器，服务提供商专门处理认证的服务器    
5. 资源服务器，服务提供商存放用户资源的服务器
6. 用户代理，一般指浏览器  
OAuth就是一种授权机制。数据的所有者告诉服务提供商系统，同意授权第三方应用获取数据。系统从而产生一个短期的进入令牌(token),用来代替密码供第三方应用使用。
> OAuth2 不是一种SSO框架，但是能够实现SSO功能(单点登录)。
> Token保存在客户端的session中。

### 认证(Authentication)和授权(Authorization)的区别:
> OAuth是一种原料，可以供多个东西使用，也可以自己使用; 认证是将多种原料以正确的方式汇集在一起，OAuth也许是这些成分之一,但也许根本不会参与其中。
> 认证与授权即是，先确定用户的身份，再确定用户是否有权限访问受保护的资源。认证主流的解决方案有CAS, SAML2, OAUTH2，授权主流的解决方案就是spring security和shiro。  
> oauth2 + spring security 就是一套包含认证与授权的完整的安全解决方案。  

### 客户端的授权模式
##### 授权码模式(authorization code),前后端分离。
通过该方式，客户端与服务提供商互动的整个过程:      
> 用户访问客户端，客户端将用户导向认证服务器；
> 用户选择是否授权给客户端；   
> 用户授权后，认证服务器将用户导向客户端事先指定好的“重定向URL”，同时返回一个授权码；   
> 客户端收到授权码，附上早先的“重定向URL”，向认证服务器申请令牌。这一步在客户端的后台服务器上完成，对用户不可见；    
> 认证服务器确定授权码和重定向URL无误后，向客户端发送令牌accessToken和刷新令牌refreshToken。   

```java
//以上步骤依次对应的请求

https://b.com/oauth/authorize?response_type=code&client_id=CLIENT_ID&redirect_uri=CALLBACK_URL&scope=read

https://a.com/callback?code=AUTHORIZATION_CODE

https://b.com/oauth/token?client_id=CLIENT_ID&client_secret=CLIENT_SECRET&grant_type=authorization_code&code=AUTHORIZATION_CODE&redirect_uri=CALLBACK_URL
```

参考示例:
用户想要通过GitHub的账号登录访问A网站。(这里GitHub就是服务提供商，A网站就是客户端)
1. A网站让用户跳转到GitHub；        
2. GitHub让用户进行登录，然后询问用户“A网站要求获取某权限"；          
3. 用户同意后，GitHub就会重定向回A网站，并且带上code；
4. A网站拿到code后，使用授权码向GitHub申请令牌；
5. GitHub验证后返回accessToken；    
6. A网站使用令牌，向GitHub请求用户数据。  

测试GitHub第三方登录代码流程:
1. 登录github，settings - Developer settings - OAuth Apps - Register a new App(homepage URL=http://localhost:8080; Authorization callback URL=http://localhost:8080/oauth/redirect)    
2. 获取code，GET请求 `https://github.com/login/oauth/authorize?client_id=xxx&redirect_uri=http://localhost:8080/oauth/redirect`
3. 弹出框输入user + 控制台随机密码        
4. 重定向地址带code返回 `http://localhost:8080/oauth/redirect?code=xxx`   
5. 获取accessToken，POST请求 `https://github.com/login/oauth/access_token?client_id=xxx&client_secret=xxx&code=xxx`, 返回`access_token=xxx&scope=&token_type=bearer`   
6. 向API请求数据，GET请求 `https://api.github.com/user`, 请求头带上Authorization:token 1bae17eeb91fed5370c28695ce86010a8f71a915，返回用户github上储存的信息

##### 隐藏模式(implicit), 没有后端，没有授权码，直接向前端颁发令牌。
1. A网站提供一个链接，让用户跳转至B网站，授权用户数据给A网站使用。
`https://b.com/oauth/authorize?response_type=token&client_id=CLIENT_ID&redirect_uri=CALLBACK_URL&scope=read`
2. 用户授权后，B网站跳回重定向的地址，并带上token参数。
`https://a.com/callback#token=ACCESS_TOKEN` 
令牌的位置是URL锚点（fragment），而不是查询字符串（querystring），降低了泄露风险。

##### 密码模式(password), 高度信任的应用
A网站要求用户提供B网站的用户名和密码。拿到后，直接向B请求令牌。
`https://oauth.b.com/token?grant_type=password&username=USERNAME&password=PASSWORD&client_id=CLIENT_ID`

##### 凭证模式(client credentials), 没有前端的应用，即在命令行下请求令牌。
`https://oauth.b.com/token?grant_type=client_credentials&client_id=CLIENT_ID&client_secret=CLIENT_SECRET`

### Spring Cloud Security
> spring cloud security就是在spring security的基础之上加了一个对oauth2认证的实现。
> spring security就是一组过滤器链，通过一层一层的filter之后，才能访问到资源信息。     
> spring security大致流程:
>- 请求到达受保护的接口之前，要经过FilterSecurityInterceptor; FilterSecurityInterceptor会从当前请求中获取SpringContext; SpringContext是从当前线程中拿到的，而当前线程中的是SecurityContextPersistenceFilter放进去的; SecurityContext中包含了当前登录用户的认证信息; 若当前用户未登录，会拿到匿名用户，FilterSecurityInterceptor根据配置信息判断，当前接口是否允许匿名访问; 若允许则调用API，若不允许则抛出相应异常。

### JWT(json web token)
1. 未使用jwt包装的普通token:
> 不携带用户信息，资源服务器无法进行本地验证。每次对于资源的访问，资源服务器都要向认证服务器发送请求，一是验证token的有效性，二是调用`https://api.xxx.com/user`获取用户信息。      
2. 使用jwt包装的token格式为(header.payload.signature):
> 通过验证签名，在本地完成token校验，无需连接认证服务器;  在payload中可以定义用户相关信息。   

**参考文档**:
`http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html`,  `http://www.ruanyifeng.com/blog/2019/04/oauth_design.html`