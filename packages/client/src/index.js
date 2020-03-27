import React from 'react';
import ReactDOM from 'react-dom';
import VConsole from 'vconsole';
import * as serviceWorker from './serviceWorker';
import router from './router';
import 'assets/scss/index.scss';

const env = process.env.REACT_APP_MY_ENV;
if (env && env !== 'prod') new VConsole();

ReactDOM.render(router, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
