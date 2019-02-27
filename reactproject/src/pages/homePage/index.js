import React, { Component } from 'react';
import imgLogo from '../../logo.svg';
import { Layout } from 'antd';
import './index.css';

class HomePage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    };

    componentDidMount() {
        console.log("enter HomePage......");
    };

    render() {
        return (
            <Layout style={{height: '100%', width: '100%'}}>
                <div className="Home-header">
                    <img src={imgLogo} className="Home-logo" alt="logo" />
                    <p>Edit <code>src/App.js</code> and save to reload.</p>
                    <a
                        className="Home-link"
                        href="https://reactjs.org"
                        target="_blank"
                        rel="noopener noreferrer">
                        Learn React
                    </a>
                </div>
            </Layout>

        );
    }
}

export default HomePage;