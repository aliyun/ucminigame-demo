import React from 'react';
import Header from 'component/header';
import Content from 'component/content';
import './index.scss';

// 游戏 CP 存储分包资源
const code1 = `
{
<p>"subpackages": {</p>
<p><span>"rootPath": "http://image.uc.cn/s/uae/g/72/minigame111/0.0.0.1"</span></p>
<p>}</p>
}`;

// UC 游戏 CDN 服务存储分包资源
const code2 = `
{
<p>"subpackages": {</p>
<p><span>"fileList": ["res", "libs", "cdn_assets"]</span></p>
<p>}</p>
}`;

// 跨域请求配置
const code3 = `
{
<p>"crossDomainList": ["a.bc.com", "x.test.cn"]</p>
}`;

export default () => {
  return (
    <div className="packages">
      <Header info="分包/跨域配置展示">分包/跨域</Header>
      <div className="packages__container">
        <Content title="游戏 CP 存储分包资源">
          <div dangerouslySetInnerHTML={{__html: code1}} />
        </Content>
        <Content title="UC 游戏 CDN 服务存储分包资源">
          <div dangerouslySetInnerHTML={{__html: code2}} />
        </Content>
        <Content title="跨域请求配置">
          <div dangerouslySetInnerHTML={{__html: code3}} />
        </Content>
      </div>
    </div>
  );
};
