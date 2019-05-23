import React from 'react'
import { Route } from 'react-router-dom'
import Loadable from 'react-loadable'
import { Layout } from 'antd'

const Loading = () => <div>Loading...</div>

const OverviewPage = Loadable({
    loader: () => import('../pages/overview'),
    loading: Loading
})

const DetailDataPage = Loadable({
    loader: () => import('../pages/detaildata'),
    loading: Loading
})

const LoginLogPage = Loadable({
    loader: () => import('../pages/loginlog'),
    loading: Loading
})

const ManagementPage = Loadable({
    loader: () => import('../pages/management'),
    loading: Loading
})

const UserInfoPage = Loadable({
    loader: () => import('../pages/userInfo'),
    loading: Loading
})

const MachineInfo = Loadable({
    loader: () => import('../pages/machineInfo'),
    loading: Loading
})

const ManageRule = Loadable({
        loader: () => import('../pages/managerule'),
    loading: Loading
})

const timeMetric = Loadable({
        loader: () => import('../pages/timemetric'),
    loading: Loading
})

const clusterLink = Loadable({
        loader: () => import('../pages/clusterlink'),
    loading: Loading
})

const DetailContent = (props) => (
    <Layout>
        <Route path={`${props.match.url}/overviewPage`} component={OverviewPage} />
        <Route path={`${props.match.url}/detailDataPage`} component={DetailDataPage} />
        <Route path={`${props.match.url}/loginLogPage`} component={LoginLogPage} />
        <Route path={`${props.match.url}/managementPage`} component={ManagementPage} />
        <Route path={`${props.match.url}/userInfoPage`} component={UserInfoPage} />

        <Route path={`${props.match.url}/machineInfoPage`} component={MachineInfo} />
        <Route path={`${props.match.url}/manageRulePage`} component={ManageRule} />
        <Route path={`${props.match.url}/timeMetricPage`} component={timeMetric} />
        <Route path={`${props.match.url}/clusterLinkPage`} component={clusterLink} />
    </Layout>
)

export default DetailContent;


