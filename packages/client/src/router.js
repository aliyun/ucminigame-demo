import React from 'react';
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom';
// import { createBrowserHistory } from "history";

// 主页
import Index from 'page/index';

// 开放能力
import User from 'page/open/user';
import Guest from 'page/open/guest';
import Share from 'page/open/share';
import Packages from 'page/open/packages';

// 设备
import Setting from 'page/device/setting';
import Screen from 'page/device/screen';
import Cocos from 'page/device/cocos';

//  广告
import Banner from 'page/ad/banner';
import Excitation from 'page/ad/excitation';

// 支付
import Pay from 'page/pay';
import PayCallback from 'page/pay/callback';

// const customHistory = createBrowserHistory();

export default (
  <Router>
    <Route path="/open/user" component={User}/>
    <Route path="/open/guest" component={Guest}/>
    <Route path="/open/share" component={Share}/>
    <Route path="/open/packages" component={Packages}/>

    <Route path="/device/setting" component={Setting}/>
    <Route path="/device/screen" component={Screen}/>
    <Route path="/device/cocos" component={Cocos}/>

    <Route path="/ad/banner" component={Banner}/>
    <Route path="/ad/excitation" component={Excitation}/>

    <Route path="/pay/callback" component={PayCallback}/>
    <Route path="/pay" exact component={Pay}/>

    <Route path="/index.html" exact component={Index}/>
    <Route path='/' exact>
        <Redirect to={{ pathname: '/index.html' }} />
    </Route>
  </Router>
)
