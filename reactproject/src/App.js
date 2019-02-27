import React, { Component } from 'react';
import { Layout } from 'antd';
import BaseContent from './routes/baseRoute';

class App extends Component {

  constructor() {
    super();
    this.state = {

    };
  }

  componentDidMount() {
    console.log("enter App.js......");
    //跳转至home页面加载图片
    this.props.history.push('/homePage');
  };

  enterPages = () => {
    this.props.history.push('/leftPage');     //表示在此页面基础上显示其他页面内容
    // window.location.href = "https://www.baidu.com";     //表示跳转至一个全新的页面
  };

  render() {
    return (
        <Layout>
            <Layout>
                <h4 style={{margin: '10px'}}>React工程DEMO</h4>
                <h4 onClick={this.enterPages} style={{margin: '-20px 0px 0px 1200px', cursor:'pointer'}}>
                    系统中心
                </h4>
            </Layout>
            <Layout>
                <BaseContent />
            </Layout>
        </Layout>
    )
  }
}

export default App;

// 2.
