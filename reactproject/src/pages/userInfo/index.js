import React, { Component } from 'react';
import { Layout } from 'antd';


class UserInfo extends Component {

    constructor(props) {
        super(props);
        this.state = {

        }
    };

    componentDidMount() {
        console.log("enter UserInfo......");
    };

    render() {
        return(
            <Layout>
                <Layout style={{ background: '#fff', padding: 24, margin: 0 }}>
                    <h3>用户基本信息页面</h3>
                </Layout>
                <Layout style={{ background: '#fff', padding: 24, paddingTop: 10, marginTop: 16, minHeight: 360 }}>
                    ...content...
                </Layout>
            </Layout>
        );
    }
}

export default UserInfo;