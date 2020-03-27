import React, { useState } from 'react';
import { showToast } from 'js/common';
import getCallback from 'js/pay/callback';
import Header from 'component/header';
import Content from 'component/content';
import Button from 'component/button';
import './index.scss';

export default () => {
  const [data, setData] = useState({});

  const isHasData = data && Object.keys(data).length > 0;

  const handleGetCallback = async () => {
    try {
      const data = await getCallback();
      setData(data);
    } catch (err) {
      showToast({
        content: '获取失败',
      });
    }
  };

  const { biz_content: bizContent = {} } = data;

  return (
    <div className="callback">
      <Header info="获取支付回调结果">获取支付回调结果</Header>
      <div className="callback__container">
        <Content title="支付信息">
          {isHasData ? (
            <div className="callback__content">
              <p>
                <span className="callback__label">订单id</span>
                <span className="callback__value">{bizContent.trade_id}</span>
              </p>
              <p>
                <span className="callback__label">订单标题</span>
                <span className="callback__value">{bizContent.title}</span>
              </p>
              <p>
                <span className="callback__label">订单状态</span>
                <span className="callback__value">{bizContent.trade_status}</span>
              </p>
              <p>
                <span className="callback__label">订单金额</span>
                <span className="callback__value">{bizContent.total_amount / 100}元</span>
              </p>
            </div>
          ) : (
            <div className="callback__empty">
              <p>未获取</p>
              <p>点击「获取」获取支付回调信息</p>
            </div>
          )}
        </Content>
        <Button type="primary" onClick={handleGetCallback}>获取</Button>
        <div className="callback__tips">
          <p>注意：</p>
          <p>这里的回调信息仅是 Mock 数据，用于展示数据解析和获取流程</p>
        </div>
      </div>
    </div>
  );
};
