import React, { Component } from 'react';
import { Layout, Select, Table, Row, Col } from 'antd';
import server from '../../utils/server';
import moment from 'moment';
import echarts from 'echarts';
import 'echarts/lib/chart/bar';
import 'echarts/lib/chart/line';
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';
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
        console.log("enter getTimeMetrics");
        let app = 'test-sentinel';
        //暂时没有动态展示数据
        server.get(`/metric/queryTopResourceMetric.json?app=${app}`).then(response => {
            this.setState({
                timeMetricList: response.data.data.metric
            }, () => {
                let listX = [];
                let listY = [];
                let listZ = [];
                let result = [];
                const arrayMap = {};
                for (var key in this.state.timeMetricList) {
                    let object = this.state.timeMetricList[key];
                    object.forEach(obj => {
                        listX.push(moment(obj.gmtCreate).format('HH:mm'));
                        listY.push(obj.passQps);
                        listZ.push(obj.blockQps);
                        result.push(obj);
                    });
                    break;  //暂时只监控其中一个接口，只展示一个折线统计图
                }
                arrayMap.pass = listY;
                arrayMap.block = listZ;
                this.setState({
                    xAxis: listX,
                    barArray: arrayMap,
                    metricList: result
                }, () => {
                    console.log("xAxis = ", this.state.xAxis);
                    console.log("barArray = ", this.state.barArray);
                })
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
        const { appList, metricList, xAxis, barArray } = this.state;
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
                        <Select placeholder="请选择应用" onSelect={this.getTimeMetrics} style={{width:'30%'}}>
                            {appMap}
                        </Select>
                    </Col>
                </Row>
                <Row style={{marginTop: 15, marginLeft: 20}}>
                    <Col>
                        <Table dataSource={metricList} columns={columns} rowKey={`gmtCreate`}/>
                    </Col>
                </Row>

                <BarChart xAxis={xAxis} barArray={barArray}/>

            </Layout>
        );
    }
}

export default TimeMetric;

class BarChart extends Component {

    constructor(props) {
        super(props);
        this.state = {
            xAxis: this.props.xAxis,
            barArray: this.props.barArray
        }
    }

    componentDidMount() {};

    componentWillReceiveProps(nextProps) {
        console.log("enter BarChart...");
        this.setState({
            xAxis: nextProps.xAxis,
            barArray: nextProps.barArray
        },()=>{
            // console.log("this.state.xAxis = ", this.state.xAxis);
            // console.log("this.state.barArray = ", this.state.barArray);
           this.createChart();
        });
    }

    createChart = () => {
        var barChart = echarts.init(document.getElementById('main'));
        barChart.setOption({
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                type: 'category',
                axisLabel : {
                    interval: 0,
                    rotate: "0"
                },
                data: this.state.xAxis
            },
            yAxis: [
                {
                    type: 'value',
                    scale: true,
                    name: '通过/拒绝QPS'
                }
            ],
            legend: {
                left: 'center',
                top: 'bottom',
                data: ['通过QPS','拒绝QPS']
            },
            series: [
                {
                    name: '通过QPS',
                    type: 'line',
                    data: this.state.barArray.pass,
                    label: {
                        show: true,
                        position: 'top',
                        color: '#000000'
                    },
                    itemStyle: {
                        normal: {
                            color: '#46A3FF'
                        }
                    }
                }, {
                    name: '拒绝QPS',
                    type: 'line',
                    data: this.state.barArray.block,
                    label: {
                        show: true,
                        position: 'top',
                        color: '#000000'
                    }
                }
            ]
        });
    };

    render() {
        return (
            <div id="main" style={{ width: 700, height: 350 }}></div>
        );
    }
}