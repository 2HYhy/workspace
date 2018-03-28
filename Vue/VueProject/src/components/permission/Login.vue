<template>
  <div>
    <img src="../../assets/logo.png"/>
    <!--登录前-->
    <div v-if="authenticated == false">
      <h3>请先登录！</h3>
      <p>
        <input type="text" placeholder="用户名" v-model="username"/>
      </p>
      <p>
        <input type="password" placeholder="密码" v-model="password"/>
      </p>
      <p>
        <button @click="login()">登录</button>
      </p>
    </div>
    <div v-else>
      <h3>{{message}}</h3>
      <button style="background-color: antiquewhite" @click="logout">退出</button>
      <br/>
      <button style="background-color: chartreuse" @click="getInfo()">GET INFO</button>
      <br/>
      <button style="background-color: aquamarine" @click="jump()">JUMP TO OTHER</button>
    </div>
    <h4 style="color: brown">{{information}}</h4>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        username: null,
        password: null,
        authenticated: false,
        message: '您已成功登录！',
        information: null
      }
    },
    created () {
      // 加载时校验token是否存在,若不存在则进行登录操作
      var token = window.localStorage.getItem('token')
      console.log('token= ', token)
      if (token) {
        this.authenticated = true
        console.log('return is true')
      } else {
        this.authenticated = false
        console.log('return is false')
      }
    },
    methods: {
      login () {
        const user = {
          username: this.username,
          password: this.password
        }
        this.$http.post('http://127.0.0.1:8081/user/login', user).then(response => {
          const code = response.status
          const token = response.bodyText
          if (code >= 200 && code <= 300) {
            console.log('登录成功token= ', token)
            // 将登录所得token保存至本地
            window.localStorage.setItem('token', token)
            this.authenticated = true
          } else {
            console.log('login fail')
          }
        })
      },
      logout () {
        // 删除登录的token
        window.localStorage.removeItem('token')
        this.$router.push('/logout')
      },
      getInfo () {
        // 用户在登录状态下获取后端服务器数据，将token带过去
        const token = localStorage.getItem('token')
        this.$http.get(`http://localhost:8081/user/getInfo?Authorization=${token}`).then(function (data) {
          this.information = data.bodyText
        }, function () {
          console.log('get information error')
        })
      },
      jump () {
        // 登录状态下才可查看其他页面
        this.$router.push('/home')
      }
    }
  }
</script>
