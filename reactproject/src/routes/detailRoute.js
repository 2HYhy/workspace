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
    loader: () => import('../pages/detailData'),
    loading: Loading
})

const LoginLogPage = Loadable({
    loader: () => import('../pages/loginLog'),
    loading: Loading
})

const ManagementPage = Loadable({
    loader: () => import('../pages/management'),
    loading: Loading
})

const UserInfoPage = Loadable({
    loader:() => import('../pages/userInfo'),
    loading: Loading
})

const DetailContent = (props) => (
    <Layout>
        <Route path={`${props.match.url}/overviewPage`} component={OverviewPage} />
        <Route path={`${props.match.url}/detailDataPage`} component={DetailDataPage} />
        <Route path={`${props.match.url}/loginLogPage`} component={LoginLogPage} />
        <Route path={`${props.match.url}/managementPage`} component={ManagementPage} />
        <Route path={`${props.match.url}/userInfoPage`} component={UserInfoPage} />
    </Layout>
)

export default DetailContent;


