import React, { Component } from 'react';
import { Layout } from 'antd';

class LoginLog extends Component {

    constructor(props) {
        super(props)
        this.state = {

        }
    };

    componentDidMount() {
        console.log("enter LoginLog......");
    };

    render() {
        return(
            <Layout>
                <Layout style={{ background: '#fff', padding: 24, margin: 0 }}>
                    <h3>用户登录日志页面</h3>
                </Layout>
                <Layout style={{ background: '#fff', padding: 24, paddingTop: 10, marginTop: 16, minHeight: 360 }}>
                    ...Content...
                </Layout>
            </Layout>
        );
    }
}

export default LoginLog;