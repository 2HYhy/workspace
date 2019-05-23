import React, { Component } from 'react';
import { Layout, Table, Select, Row, Col } from 'antd';
import server from '../../utils/server';
import moment from 'moment';
const Option = Select.Option;

class MachineInfo extends Component {

    constructor(props) {
        super(props);
        this.state = {
            machineList: [],
            app: '',
            appList: [],
            env: '',
            serviceList: [],
            service: '',
            userId: '72abadcb-4ce6-44e7-acd4-77ca17893859'
        }
    };

    componentDidMount() {
        console.log("enter MachineInfo......");
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

    getMachines = (value) => {
        server.get(`/app/${value}/machines.json`).then(response => {
            this.setState({
                machineList: response.data.data
            },() => {
                console.log("this.state.machineList = ", this.state.machineList);
            });
        })
    };

    render() {
        const { machineList, appList } = this.state;
        const appOptions = appList.map(app => (
           <Option key={app.app}>{app.app}</Option>
        ));
        const columns = [
            {
                title: '机器名',
                dataIndex: 'hostname',
                key: 'hostname'
            }, {
                title: 'IP地址',
                dataIndex: 'ip',
                key: 'ip'
            }, {
                title: '端口号',
                dataIndex: 'port',
                key: 'port'
            }, {
                title: 'Sentinel客户端版本',
                dataIndex: 'version',
                key: 'version'
            }, {
                title: '健康状态',
                dataIndex: 'healthy',
                key: 'healthy',
                render: text => {
                    if (text === true) {
                        return '健康'
                    } else {
                        return '失联'
                    }
                }
            }, {
                title: '心跳时间',
                dataIndex: 'lastHeartbeat',
                key: 'lastHeartbeat',
                render: lastHeartbeat => {
                    return moment(lastHeartbeat).format('YYYY-MM-DD HH:mm:ss');
                }
            }
        ];

        return(
            <Layout style={{background: '#fff'}}>
                <Row style={{padding: 20}}>
                    <Col>
                        <h3>机器信息列表</h3>
                    </Col>
                </Row>
                <Row style={{marginTop: 15, marginLeft: 20}}>
                    <Col>
                        <Select placeholder="请选择应用" onSelect={this.getMachines} style={{width: "30%"}}>
                            {appOptions}
                        </Select>
                    </Col>
                </Row>
                <Row style={{marginTop: 15, marginLeft: 20}}>
                    <Col>
                        <Table dataSource={machineList} columns={columns} rowKey={`lastHeartbeat`}/>
                    </Col>
                </Row>
            </Layout>
        );
    }
}

export default MachineInfo;