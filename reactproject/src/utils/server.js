import axios from 'axios';
import qs from 'qs';

axios.defaults.headers.post['Content-Type'] = 'application/json';

let http = {
    post: "",
    get: ""
};

http.get = function (api, data) {
    let params = qs.stringify(data);   //表示添在url后面
    console.log("params = ", params);
    return new Promise((resolve, reject) => {
        axios.get(api, params).then(res => {
            //返回的是Promise对象
            resolve(res)
        }).catch(error => {
           reject(error);
        })
    })
};

http.post = function (api, data) {
    let params = JSON.stringify(data);   //表示传入请求body里面
    console.log("params = ", params);
    return new Promise((resolve, reject) => {
        axios.post(api, params).then(res => {
            //返回的是Promise对象
            resolve(res)
        }).catch(error => {
            reject(error);
        })
    })
};

//请求后响应拦截
axios.interceptors.response.use(response => {
    //返回响应结果
    return response;
}, error => {
    console.log(" ---enter error---");
    if (error.response) {
        // console.log(error.response.headers);
        console.log("data = ", error.response.data);
        console.log("status = ", error.response.status);
        //返回后端异常的报文
        return error.response.data;
    } else if (error.request) {
        console.log(error.request);
    } else {
        console.log('Error Message: ', error.message);
    }
});

export default http;