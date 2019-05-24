import React, { Component } from 'react';
import server from '../../utils/server';
import { Layout, Select, Table, Button, Icon, Row, Col } from 'antd';
const Option = Select.Option;

class ClusterLink extends Component {
    constructor(props) {
        super(props);
        this.state = {
            appList: [],
            machineList: [],
            linkList: [],
            ip: '',
            port: -1
        }
    }

    componentDidMount() {
        console.log("enter ClusterLink...");
        this.getApps();
    };

    getApps = () => {
        server.get('/app/briefinfos.json').then(response => {
            this.setState({
                appList: response.data.data
            }, () => {
                console.log("appList = ", this.state.appList);
            })
        })
    };

    makeMachines = (value) => {
        let apps = this.state.appList;
        let list = [];
        apps.forEach(app => {
            if (app.app === value) {
                let machines = app.machines;
                for(var i in machines) {
                    list.push(machines[i]);
                }
            }
        });
        this.setState({
            machineList: list
        }, () => {
            console.log("machineList = ", this.state.machineList);
        })
    };

    getLinks = (value) => {
        var array = value.split(":");
        this.setState({
            ip: array[0],
            port: array[1]
        }, () => {
            server.get(`/resource/machineResource.json?ip=${this.state.ip}&port=${this.state.port}`).then(response => {
                this.setState({
                    linkList: response.data.data
                }, () => {
                    console.log("linkList = ", this.state.linkList);
                })
            })
        });

    };

    render () {
        const { appList, machineList, linkList } = this.state;
        const appMap = appList.map((temp, index) => (
            <Option key={temp.app}>{temp.app}</Option>
        ));
        const machineMap = machineList.map(temp => (
            <Option key={temp.ip +":"+temp.port}>{temp.ip}:{temp.port}</Option>
        ));
        const columns = [
            {
                title: '资源名',
                dataIndex: 'resource',
                key: 'resource'
            }, {
                title: '通过QPS',
                dataIndex: 'passQps',
                key: 'passQps'
            }, {
                title: '拒绝QPS',
                dataIndex: 'blockQps',
                key: 'blockQps'
            }, {
                title: '线程数',
                dataIndex: 'threadNum',
                key: 'threadNum'
            }, {
                title: '平均RT',
                dataIndex: 'averageRt',
                key: 'averageRt'
            }, {
                title: '分钟通过',
                dataIndex: 'oneMinutePass',
                key: 'oneMinutePass'
            }, {
                title: '分钟拒绝',
                dataIndex: 'oneMinuteBlock',
                key: 'oneMinuteBlock'
            }, {
                title: '操作',
                render: (text, record) =>
                    <div>
                        <Button size="small" type="primary"><Icon type="plus" />流控</Button>
                        &nbsp;&nbsp;
                        <Button size="small" type="primary"><Icon type="plus" />降级</Button>
                    </div>
            }
        ];
        return (
            <Layout style={{background: '#fff'}}>
                <Row style={{padding: 20}}>
                    <Col>
                        <h3>簇点链路列表</h3>
                    </Col>
                </Row>
                <Row style={{marginTop: 10, marginLeft: 20}}>
                    <Col span={8}>
                        <Select placeholder="请选择应用" onSelect={this.makeMachines} style={{width: '80%'}}>
                            {appMap}
                        </Select>
                    </Col>
                   <Col span={8}>
                       <Select placeholder="请选择ip和port" onSelect={this.getLinks} style={{width: '80%'}}>
                           {machineMap}
                       </Select>
                   </Col>
                </Row>
                <Row style={{marginTop: 10}}>
                    {/*没有使用折叠面板显示的形式*/}
                    <Table dataSource={linkList} columns={columns} rowKey={`port`}/>
                </Row>
            </Layout>
        );
    }
}

export default ClusterLink;