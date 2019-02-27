import React from 'react'
import { Route } from 'react-router-dom'
import Loadable from 'react-loadable'
import { Layout } from 'antd'

const Loading = () => <div>Loading...</div>

const HomePage = Loadable({
    loader: () => import('../pages/homePage'),
    loading: Loading
})

const LeftPage = Loadable({
    loader:() => import('../pages/leftPage'),
    loading: Loading
})

const BaseContent = () => (
    <Layout>
        <Route path={`/homePage`} component={HomePage} />
        <Route path={`/leftPage`} component={LeftPage} />
    </Layout>
)

export default BaseContent;