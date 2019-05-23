import React, { Component } from 'react';
import { Layout, Menu, Icon } from 'antd';
import DetailContent from '../../routes/detailRoute';
import 'antd/dist/antd.css';
const { Sider } = Layout;
const SubMenu = Menu.SubMenu;


class LeftPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            collapsed: false,
            selectedKeys: ''
        }
    };

    menuKeys = [
        {key: '1', url: 'overviewPage'},
        {key: '2', url: 'detailDataPage'},
        {key: '3', url: 'managementPage'},
        {key: '4', url: 'userInfoPage'},
        {key: '5', url: 'loginLogPage'},

        {key: '6', url: 'timeMetricPage'},
        {key: '7', url: 'clusterLinkPage'},
        {key: '8', url: 'manageRulePage'},
        {key: '9', url: 'machineInfoPage'}
    ];

    componentDidMount() {
        console.log("enter LeftPage......");
    };

    onCollapse = (collapsed) => {
        console.log(collapsed);
        this.setState({ collapsed });
    };

    handleMenuClicked = (event) => {
        const menuKeys = this.menuKeys;
        this.setState({
            selectedKeys: event.key
        });
        for (let index in menuKeys) {
            let item = menuKeys[index];
            if (event.key === item.key) {
                //点击左侧菜单栏跳转至对应页面
                this.props.history.push(`${this.props.match.url}/${item.url}`);
            }
        }
    };

    render() {
        return (
            <Layout style={{minHeight: '100%'}}>
                <Sider collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
                    <Menu
                        mode="inline"
                        ref='menu'
                        theme="dark"
                        style={{borderRight: 0 }}
                        selectedKeys={[this.state.selectedKeys]}
                        onClick={this.handleMenuClicked}
                    >
                        <Menu.Item key="1">
                            <Icon type="pie-chart" />
                            <span>数据概览</span>
                        </Menu.Item>
                        <Menu.Item key="2">
                            <Icon type="desktop" />
                            <span>注册明细</span>
                        </Menu.Item>
                        <SubMenu
                            key="sub1" title={<span><Icon type="user" /><span>账号管理</span></span>}
                        >
                            <Menu.Item key="3">冻结/解冻</Menu.Item>
                        </SubMenu>
                        <Menu.Item key="4">
                            <Icon type="team" />
                            <span>基本信息</span>
                        </Menu.Item>
                        <Menu.Item key="5">
                            <Icon type="file" />
                            <span>登录日志</span>
                        </Menu.Item>

                        {/*Sentinel*/}
                        <Menu.Item key="6">
                            <Icon type="bars" />
                            <span>实时监控</span>
                        </Menu.Item>
                        <Menu.Item key="7">
                            <Icon type="bars" />
                            <span>簇点链路</span>
                        </Menu.Item>
                        <Menu.Item key="8">
                            <Icon type="bars" />
                            <span>治理规则</span>
                        </Menu.Item>
                        <Menu.Item key="9">
                            <Icon type="bars" />
                            <span>机器列表</span>
                        </Menu.Item>
                    </Menu>
                </Sider>
                <Layout>
                    {/*第二层的路由，需要match属性*/}
                    <DetailContent match={this.props.match} />
                </Layout>
            </Layout>
        );
    }
}

export default LeftPage;

// 3.