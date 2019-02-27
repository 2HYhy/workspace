import React, { Component } from 'react';
import { Layout, Button } from 'antd';
import request from 'superagent';


class Management extends Component {

    constructor(props) {
        super(props);
        this.state = {
            flag: false
        }
    };

    componentDidMount() {
        console.log("enter Management......");
    };

    //使用superagent的get请求
    getMessage = () => {
        let access_token = "ZTMxYzNkODcxZDI3MTMyYQ==:HfXi2-ushJQp5oxg39qnIamnxho=:eyJhcGlzIjpbImlzRXhpc3RlZCJdLCJleHBpcmVkVGltZSI6MTU1Mzk2MTYwMDAwMCwic2NvcGUiOiJVU0VSIn0=";
        request.get('/v2/user/isExisted').query(`accessToken=${access_token}`).query("value=1822058293").end(function(req, res) {
            if (res.statusCode === 200) {
                this.setState({
                   flag: res.text
                });
                console.log("this.state.flag = ", this.state.flag);
            } else {
                console.log("request has error");
                console.log("error = ", res.text);
            }
        });
    };

    //使用superagent的post请求
    postMessage = () => {
        let access_token = "ZTMxYzNkODcxZDI3MTMyYQ==:5qEZVF0P7_CNHvRfgO6txnx7WtA=:eyJhcGlzIjpbImxvZ2luIl0sImV4cGlyZWRUaW1lIjoxNTUzOTYxNjAwMDAwLCJzY29wZSI6IlVTRVIifQ==";
        request.post(`/v2/user/login?accessToken=${access_token}&livemode=0`)
            .send({ 'username':'18220582019', 'password':'123456Qq' })
            .set('Accept', 'application/json')
            .end(function(error, response) {
                console.log("response = ", response);
                if (error || !response.ok) {
                    console.log("error = ", response.text);
                } else {
                    console.log("JSON.stringify(response.body)");
                }
            });
    };

    render() {
        return(
            <Layout>
                <Layout style={{ background: '#fff', padding: 24, margin: 0 }}>
                    <h3>用户账号管理页面</h3>
                </Layout>
                <Layout style={{ background: '#fff', padding: 24, paddingTop: 10, marginTop: 16, minHeight: 360 }}>
                    <Button type="default" onClick={this.getMessage}>获取数据</Button>
                    <Button type="default" onClick={this.postMessage}>提交数据</Button>
                </Layout>
            </Layout>
        );
    }
}

export default Management;