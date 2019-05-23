import React, { Component } from 'react';
import { Layout, Table, Select, Button, Tabs, Row, Col, Modal, Input, Icon, Popconfirm } from 'antd';
import server from '../../utils/server';
const Option = Select.Option;
const TabPane = Tabs.TabPane;

class ManageRule extends Component {

    constructor(props) {
        super(props);
        this.state = {
            flowList: [],
            degradeList: [],
            appList: [],
            serviceList: [],
            machineList: [],
            app: '',
            service: '',
            key: '1',
            addVisibleF: false,
            addVisibleD: false,
            editVisibleF: false,
            editVisibleD: false,
            resource: '',
            count: -1,
            timeWindow: -1,
            oldFlowRule: {},
            oldDegradeRule: {}
        }
    }

    componentDidMount() {
        console.log("enter ManageRule...");
        this.getApps();
    }

    getApps = () => {
        server.get('/app/briefinfos.json').then(response => {
            this.setState({
                appList: response.data.data
            }, () => {
                console.log("appList = ", this.state.appList);
            })
        })
    };

    getRules = (value) => {
        let app = this.state.app;
        var array = value.split(":");
        this.setState({
            ip: array[0],
            port: array[1]
        }, () => {
            let ip = this.state.ip;
            let port = this.state.port;
            if (this.state.key === '1') {
                server.get(`/v1/flow/rules?app=${app}&ip=${ip}&port=${port}`).then(response => {
                    this.setState({
                        flowList: response.data.data
                    }, () => {
                        console.log("flowList = ", this.state.flowList);
                    })
                })
            } else {
                server.get(`/degrade/rules.json?app=${app}&ip=${ip}&port=${port}`).then(response => {
                    this.setState({
                        degradeList: response.data.data
                    }, () => {
                        console.log("degradeList = ", this.state.degradeList);
                    })
                })
            }
        });
    };

    getMachines = (value) => {
    server.get(`/app/${value}/machines.json`).then(response => {
        this.setState({
            app: value,
            machineList: response.data.data
        }, () => {
            console.log("machineList = ", this.state.machineList);
        })
    })};

    changeType = (value) => {
        this.setState({
            key: value
        }, () => {
            if (this.state.flowList.length === 0 || this.state.degradeList.length === 0) {
                this.getRules(this.state.ip+":"+this.state.port)
            }
        })
    };

    showModalAdd = () => {
        if (this.state.key === '1') {
            this.setState({
                addVisibleF: true
            })
        } else {
            this.setState({
                addVisibleD: true
            })
        }
    };

    showModalEdit = (data) => {
        if (this.state.key === '1') {
            this.setState({
                editVisibleF: true,
                oldFlowRule: data
            })
        } else {
            this.setState({
                editVisibleD: true,
                oldDegradeRule: data
            })
        }
    };

    addRule = () => {
        if (this.state.key === '1') {
            const params = {
                app: this.state.app,
                ip: this.state.ip,
                port: this.state.port,
                resource: this.state.resource,
                count: this.state.count,
                limitApp: 'default',
                grade: 1,
                strategy: 0,
                controlBehavior: 0,
                clusterMode: false
            };
            server.post('/v1/flow/rule', params).then(response => {
                console.log("flow result : ", response.data.success);
                this.getRules(this.state.ip+":"+this.state.port)
            })
        } else {
            let app = this.state.app;
            let ip = this.state.ip;
            let port = this.state.port;
            let resource = this.state.resource;
            let count = this.state.count;
            let timeWindow = this.state.timeWindow;
            server.get(`/degrade/new.json?app=${app}&count=${count}&grade=0&ip=${ip}&limitApp=default
            &port=${port}&resource=${resource}&timeWindow=${timeWindow}`).then(response => {
                console.log("degrade result : ", response.data.success);
                this.getRules(this.state.ip+":"+this.state.port)
            })
        }
    };

    changeResource = (e) => {
        this.setState({
            resource: e.target.value
        })
    };

    changeCount = (e) => {
        this.setState({
            count: e.target.value
        })
    };

    changeTimeWindow = (e) => {
        this.setState({
            timeWindow: e.target.value
        })
    };

    handleOkAdd = () => {
        this.addRule();
        this.setState({
            addVisibleF: false,
            addVisibleD: false
        });
    };

    handleOkEdit = () => {
        this.editRule();
        this.setState({
            editVisibleF: false,
            editVisibleD: false
        });
    };

    handleCancel = () => {
        this.setState({
            addVisibleF: false,
            addVisibleD: false,
            editVisibleF: false,
            editVisibleD: false
        });
    };

    deleteRule = (data) => {
        if (this.state.key === '1') {
            server.delete(`/v1/flow/delete.json?app=${data.app}&id=${data.id}`).then(response => {
                console.log("flow result : ", response.data.success);
                this.getRules(this.state.ip+":"+this.state.port)
            })
        } else {
            server.get(`/degrade/delete.json?app=${data.app}&id=${data.id}`).then(response => {
                console.log("degrade result : ", response.data.success);
                this.getRules(this.state.ip+":"+this.state.port)
            })
        }
    };

    editRule = () => {
        if (this.state.key === '1') {
            let id = this.state.oldFlowRule.id;
            server.put(`/v1/flow/save.json?controlBehavior=0&count=${this.state.count}&grade=1&id=${id}&limitApp=default
            &maxQueueingTimeMs=500&resource=${this.state.resource}&strategy=0`).then(response => {
                console.log("flow result : ", response.data.success);
                this.getRules(this.state.ip+":"+this.state.port)
            })
        } else {
            let id = this.state.oldDegradeRule.id;
            server.get(`/degrade/save.json?count=${this.state.count}&grade=0&id=${id}&limitApp=default
            &resource=${this.state.resource}&timeWindow=${this.state.timeWindow}`).then(response => {
                console.log("degrade result : ", response.data.success);
                this.getRules(this.state.ip+":"+this.state.port)
            })
        }
    };

    render() {
        const { flowList, appList, machineList, degradeList, oldFlowRule, oldDegradeRule } = this.state;
        const appOptions = appList.map(temp => (
            <Option key={temp.app}>{temp.app}</Option>
        ));
        const ipPortOptions = machineList.map(temp => (
            <Option key={temp.ip +":"+temp.port}>{temp.ip}:{temp.port}</Option>
        ));
        const buttonText = this.state.key === '1' ? '新增限流规则' : '新增降级规则';
        const Fcolumns = [
            {
                title: '资源名',
                dataIndex: 'resource',
                key: 'resource'
            }, {
                title: '来源应用',
                dataIndex: 'limitApp',
                key: 'limitApp'
            },  {
                title: '流控模式',
                dataIndex: 'strategy',
                key: 'strategy'
            },  {
                title: '阈值类型',
                dataIndex: 'grade',
                key: 'grade'
            },  {
                title: '阈值',
                dataIndex: 'count',
                key: 'count'
            }, {
                title: '流控效果',
                dataIndex: 'controlBehavior',
                key: 'controlBehavior'
            }, {
                title: '操作',
                render: (text, record) => (
                    <div>
                        <Button type="primary" size="small" onClick={()=>this.showModalEdit(record)}>编辑</Button>
                        &nbsp;&nbsp;&nbsp;
                        <Popconfirm
                            title="确定要删除该条限流规则?"
                            onConfirm={()=>this.deleteRule(record)}
                        >
                            <Button type="primary" size="small">删除</Button>
                        </Popconfirm>
                    </div>
                )
            }
        ];
        const Dcolumns = [
            {
                title: '资源名',
                dataIndex: 'resource',
                key: 'resource'
            }, {
                title: '降级模式',
                dataIndex: 'grade',
                key: 'grade'
            }, {
                title: '阈值',
                dataIndex: 'count',
                key: 'count'
            }, {
                title: '时间间隔',
                dataIndex: 'timeWindow',
                key: 'timeWindow'
            }, {
                title: '操作',
                render: (text, record) => {
                    return (
                        <div>
                            <Button type="primary" size="small" onClick={()=>this.showModalEdit(record)}>编辑</Button>
                            &nbsp;&nbsp;&nbsp;
                            <Popconfirm
                                title="确定要删除该条降级规则?"
                                onConfirm={()=>this.deleteRule(record)}
                            >
                                <Button type="primary" size="small">删除</Button>
                            </Popconfirm>
                        </div>)
                }
            }
        ];
        return (
            <Layout style={{background: '#fff'}}>
                <Row style={{padding: 20}}>
                    <Col>
                        <h3>治理规则列表</h3>
                    </Col>
                </Row>
                <Row style={{marginTop: 10, marginLeft: 20}}>
                    <Col span={8}>
                        <Select placeholder="请选择应用名" onSelect={this.getMachines} style={{width: "80%"}}>
                            {appOptions}
                        </Select>
                    </Col>
                    <Col span={8}>
                        <Select placeholder="请选择ip和port" onSelect={this.getRules} style={{width: "80%"}}>
                            {ipPortOptions}
                        </Select>
                    </Col>
                    <Col span={8}>
                        <Button type="primary" onClick={this.showModalAdd}>
                            <Icon type="plus" />{buttonText}
                        </Button>
                    </Col>
                </Row>
                <Row style={{marginTop: 20, marginLeft: 20}}>
                    <Col>
                        <Tabs onChange={this.changeType} type="card">
                            <TabPane tab="限流规则" key="1">
                                <Table dataSource={flowList} columns={Fcolumns} rowKey={`resource`}/>
                                {/*<FlowRuleClass />   <!--或者新建一个限流的class-->*/}
                            </TabPane>
                            <TabPane tab="降级规则" key="2">
                                <Table dataSource={degradeList} columns={Dcolumns} rowKey={`resource`}/>
                                {/*<DegradeRuleClass />   <!--或者新建一个降级的class-->*/}
                            </TabPane>
                        </Tabs>
                    </Col>
                </Row>
                <Layout>
                    {/*新增限流模态框，其他字段暂时一律使用默认值*/}
                    <Modal visible={this.state.addVisibleF} onOk={this.handleOkAdd} onCancel={this.handleCancel}>
                        <Row>
                            <Col span={4} style={{marginTop: 6}}>资源名</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input resource" onChange={this.changeResource}/>
                            </Col>
                        </Row>
                        <Row style={{marginTop: 15}}>
                            <Col span={4} style={{marginTop: 6}}>单机阈值</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input count" onChange={this.changeCount}/>
                            </Col>
                        </Row>
                    </Modal>
                    {/*新增降级模态框，其他字段暂时一律使用默认值*/}
                    <Modal visible={this.state.addVisibleD} onOk={this.handleOkAdd} onCancel={this.handleCancel}>
                        <Row>
                            <Col span={4} style={{marginTop: 6}}>资源名</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input resource" onChange={this.changeResource}/>
                            </Col>
                        </Row>
                        <Row style={{marginTop: 15}}>
                            <Col span={4} style={{marginTop: 6}}>阈值RT</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input count" onChange={this.changeCount}/>
                            </Col>
                        </Row>
                        <Row style={{marginTop: 15}}>
                            <Col span={4} style={{marginTop: 6}}>时间窗口</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input timeWindow" onChange={this.changeTimeWindow}/>
                            </Col>
                        </Row>
                    </Modal>
                    {/*修改限流规则*/}
                    <Modal visible={this.state.editVisibleF} onOk={this.handleOkEdit} onCancel={this.handleCancel}>
                        <Row>
                            <Col span={4} style={{marginTop: 6}}>资源名</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input resource" onChange={this.changeResource}
                                       defaultValue={oldFlowRule.resource} />
                            </Col>
                        </Row>
                        <Row style={{marginTop: 15}}>
                            <Col span={4} style={{marginTop: 6}}>单机阈值</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input count" onChange={this.changeCount}
                                       defaultValue={oldFlowRule.count} />
                            </Col>
                        </Row>
                    </Modal>
                    {/*修改降级规则*/}
                    <Modal visible={this.state.editVisibleD} onOk={this.handleOkEdit} onCancel={this.handleCancel}>
                        <Row>
                            <Col span={4} style={{marginTop: 6}}>资源名</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input resource" onChange={this.changeResource}
                                       defaultValue={oldDegradeRule.resource} />
                            </Col>
                        </Row>
                        <Row style={{marginTop: 15}}>
                            <Col span={4} style={{marginTop: 6}}>阈值RT</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input count" onChange={this.changeCount}
                                       defaultValue={oldDegradeRule.count}/>
                            </Col>
                        </Row>
                        <Row style={{marginTop: 15}}>
                            <Col span={4} style={{marginTop: 6}}>时间窗口</Col>
                            <Col span={12}>
                                <Input type="text" placeholder="please input timeWindow" onChange={this.changeTimeWindow}
                                       defaultValue={oldDegradeRule.timeWindow}/>
                            </Col>
                        </Row>
                    </Modal>
                </Layout>
            </Layout>
        );
    }
}

export default ManageRule;

