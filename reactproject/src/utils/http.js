import axios from 'axios';

//配置axios要访问的默认后端url,需要解决跨域问题
axios.defaults.baseURL = "http://127.0.0.1:9999/";

//配置允许跨域携带cookie,若设为true,则不允许跨域的主机ip设为"*",即("Access-Control-Allow-Origin", "*")会报错,设为false可行
axios.defaults.withCredentials = true;

//配置超时时间为20s
axios.defaults.timeout = 20000;

//配置请求拦截器
axios.interceptors.request.use(config => {
   return config;
}, error => {
    return Promise.reject(error);
});

//配置响应拦截器
axios.interceptors.response.use(response => {
   return response;
}, error => {
    console.log("------interceptors failed------");
    return Promise.reject(error);
});
export default {
    //get请求
    get(url, param) {
        return new Promise((resolve, reject) => {
            axios({
                method: 'get',
                url,
                params: param
            }).then(res => {
                resolve(res);
            }).catch(error => {
                reject(error);
            })
        })
    },
    //post请求
    post(url, param) {
        if(!param || param === '') param = {};
        return new Promise((resolve,reject) => {
            axios({
                method: 'post',
                url,
                headers: {
                    'X-Requested-With': 'XMLHttpRequest',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(param)
            }).then(res=>{
                resolve(res)
            }).catch(err=>{
                reject(err)
            })
        })
    }
}