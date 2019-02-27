import React, { Component } from 'react';
import { Layout, Button } from 'antd';


import server from '../../utils/server';
// import axios from 'axios';

class DetailData extends Component {

    constructor(props) {
        super(props);
        this.state = {
        }
    };

    componentDidMount() {
        console.log("enter DetailData......");
    };

    getData = () => {
       let access_token_123 = "ZTMxYzNkODcxZDI3MTMyYQ==:HfXi2-ushJQp5oxg39qnIamnxho=:eyJhcGlzIjpbImlzRXhpc3RlZCJdLCJleHBpcmVkVGltZSI6MTU1Mzk2MTYwMDAwMCwic2NvcGUiOiJVU0VSIn0=";

       //method1: with跨域
        server.get(`/v2/user/isExisted?accessToken=${access_token_123}&value=1822058201`)
            .then(response => {
                const data = response.data;
                if (data !== undefined) {
                    console.log("请求成功，data = ", data);
                } else {
                    const info = response.info;
                    const des = response.des;
                    console.log("请求失败，info & des = ", info, des);
                }
            });

        //结合package.json中的代理proxy,浏览器以为请求的是http://localhost:3000,所以不会产生跨域问题，但实际被转发到了proxy代理的后端地址。
        //method2: no跨域
        // axios.get(`/v2/user/isExisted?accessToken$=${access_token_123}&value=18220582019`).then(response => {
        //     //返回请求正确的结果
        //     console.log("response = ", response);
        // }).catch(function (error) {
        //     if (error.response) {
        //         console.log(error.response.data);
        //         console.log(error.response.status);
        //         console.log(error.response.headers);
        //     } else if (error.request) {
        //             console.log(error.request);
        //     } else {
        //             console.log('Error', error.message);
        //     }
        //     // console.log(error.config);
        // });

    };

    postData = () => {
        const access_token_456 = "ZTMxYzNkODcxZDI3MTMyYQ==:5qEZVF0P7_CNHvRfgO6txnx7WtA=:eyJhcGlzIjpbImxvZ2luIl0sImV4cGlyZWRUaW1lIjoxNTUzOTYxNjAwMDAwLCJzY29wZSI6IlVTRVIifQ==";
        server.post(`/v2/user/login?accessToken=${access_token_456}`, {
            username: "18220582019", password: "123456Qq"
        })
            .then(function (response) {
                const data = response.data;
                if (data !== undefined) {
                    console.log("请求成功，data = ", data);
                } else {
                    const info = response.info;
                    const des = response.des;
                    console.log("请求失败，info & des = ", info, des);
                }
            })
    };

    render() {
        return(
            <Layout>
                <Layout style={{ background: '#fff', padding: 24, margin: 0 }}>
                    <h3>用户注册明细页面</h3>
                </Layout>
                <Layout style={{ background: '#fff', padding: 24, paddingTop: 10, marginTop: 16, minHeight: 360 }}>
                    <Button type="primary" onClick={this.getData}>查询数据</Button>
                    <Button type="default" onClick={this.postData}>提交数据</Button>
                </Layout>
            </Layout>
        );
    }
}

export default DetailData;