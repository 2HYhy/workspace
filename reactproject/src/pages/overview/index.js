import React, { Component } from 'react';
import { Layout, Button, message, Table } from 'antd';
import http from '../../utils/http';
import MessageSvg from '../../image/untitled.svg';

class Overview extends Component {

    constructor(props) {
        super(props);
        this.state = {
            dataList: [],
            browsers: ['Chrome', 'other', 'Google']
        }
    };

    componentDidMount() {
        console.log("enter Overview......");
    };

    getData = () => {
      http.get('/admin/auth/count_browsers?appId=1104&start=2019-01-21&end=2019-01-25').then(response => {
          // console.log("response = ", response);
          const flag = response.data.success;
          const data = response.data.data;
          // console.log("data = ", data);
          if (flag === true && data != null) {
              let list = this.state.browsers.map(key => {
                  if(data[key] !== undefined) {
                      let temp = {};
                      temp.name = key;
                      temp.num = data[key];
                      return temp;
                  } else {
                      let temp = {};
                      temp.name = key;
                      temp.num = 0;
                      return temp;
                  }
              });
              this.setState({
                  dataList: list
              }, ()=>{
                  console.log("dataList = ", this.state.dataList);
              })
          } else {
              message.error("后端请求出错:", response.data.message);
          }
      })
    };

    render() {
        const columns = [
            {
                title: '浏览器类型',
                dataIndex: 'name',
                align: 'center'
            }, {
                title: '访问数据量',
                dataIndex: 'num',
                align: 'center'
            }
        ];
        return (
            <Layout>
                <Layout style={{ background: '#fff', padding: 24, margin: 0 }}>
                    <h3>用户数据概览页面</h3>
                    <img src={MessageSvg} alt="图片"/>
                   {/*<h2> <Icon component={MessageSvg} /> Apple</h2>*/}
                </Layout>
                <Layout style={{ background: '#fff', padding: 24, paddingTop: 10, marginTop: 16, minHeight: 30 }}>
                    <Button type="primary" size="large" onClick={this.getData}>查询数据</Button>
                </Layout>
                <Layout style={{ background: '#fff', padding: 24, paddingTop: 10, marginTop: 16, minHeight: 100 }}>
                    <Table dataSource={this.state.dataList}
                           columns={columns}
                           rowKey={`name`}
                           size="middle"
                           bordered={true}
                    />
                </Layout>
            </Layout>
        );
    }
}

export default Overview;