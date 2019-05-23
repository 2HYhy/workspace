import React, { Component } from 'react';
import { Layout, Select, Table, Row, Col } from 'antd';
import server from '../../utils/server';
import moment from 'moment';
import echarts from 'echarts';
import reactEcharts from 'echarts-for-react';
const Option = Select.Option;

class TimeMetric extends Component {
    constructor(props) {
        super(props);
        this.state = {
            appList: [],
            app: '',
            metricList: [],
            xAxis: [],  //图表横坐标
            barArray: [],  //图表纵坐标
            timeMetricList: []
        }
    }

    componentDidMount() {
        console.log("enter TimeMetric...");
        this.getApps();
        this.getTimeMetrics();
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

    getTimeMetrics = () => {
        let app = 'test-sentinel';
        server.get(`/metric/queryTopResourceMetric.json?app=${app}`).then(response => {
            this.setState({
                timeMetricList: response.data.data.metric
            }, () => {
                console.log("timeMetricList = ", this.state.timeMetricList);
                // this.state.timeMetricList.forEach(item => {
                //     console.log("item = ", item);
                // })
            })
        })
    };

    getMetrics = (value) => {
        this.setState({
            app: value
        }, () => {
            let app = this.state.app;
            server.get(`/metric/queryTopResourceMetric.json?app=${app}`).then(response => {
                let tempList = [];
                let tempMetric = response.data.data.metric;
                for (var key in tempMetric) {
                    //key=/test/one
                    let body = tempMetric[key];
                    //只取了前3条记录
                    for (var i=0; i<3; i++) {
                        tempList.push(body[i]);
                    }
                }
                this.setState({
                    metricList: tempList
                }, () => {
                    console.log("metricList = ", this.state.metricList);
                });
            });
        });
    };

    render() {
        const columns = [
            {
                title: '时间',
                dataIndex: 'timestamp',
                key: 'timestamp',
                render: ((text, record) => {return moment(text).format('YYYY-MM-DD HH:mm:ss') })
            }, {
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
                title: '响应时间(ms)',
                dataIndex: 'rt',
                key: 'rt'
            }
        ];
        const { appList, metricList } = this.state;
        const appMap = appList.map((temp, index) => (
            <Option key={temp.app}>{temp.app}</Option>
        ));

        return (
            <Layout style={{background: '#fff'}}>
                <Row style={{padding: 20}}>
                    <Col>
                        <h3>实时监控列表</h3>
                    </Col>
                </Row>
                <Row style={{marginTop: 10, marginLeft: 20}}>
                    <Col>
                        <Select placeholder="请选择应用" onSelect={this.getMetrics} style={{width:'30%'}}>
                            {appMap}
                        </Select>
                    </Col>
                </Row>
                <Row style={{marginTop: 15, marginLeft: 20}}>
                    <Col>
                        <Table dataSource={metricList} columns={columns} rowKey={`gmtCreate`}/>
                    </Col>
                </Row>
            </Layout>
        );
    }
}

export default TimeMetric;