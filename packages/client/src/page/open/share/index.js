import React, { useState } from 'react';
import idx from 'idx';
import { shareAppMessage, getLaunchOptionsSync } from 'js/open/share';
import toast from 'utils/toast';
import Header from 'component/header';
import Content from 'component/content';
import Button from 'component/button';
import './index.scss';

export default () => {
  const [refluxData, setRefluxData] = useState('');

  const share = async () => {
    try {
      await shareAppMessage();
      toast.success('分享成功');
    } catch (err) {
      toast.error('分享失败')
    }
  };

  const getRefluxData = async () => {
    try {
      const result = await getLaunchOptionsSync();
      const newResult = typeof result === 'object' ? JSON.stringify(result) : result;
      if (newResult) setRefluxData(newResult);
    } catch (err) {
      const msg = idx(err, _ => _.msg);
      toast.error(msg || '获取失败');
    }
  };

  const clear = () => setRefluxData('');

  return (
    <div className="share">
      <Header info="uc.shareAppMessage">分享回流</Header>
      <div className="share__container">
        <Content title="回流信息">
          {refluxData ? (
            <p className="share__text">{refluxData}</p>
          ) : (
            <div className="share__empty">
              <p>未获取</p>
              <p>点击「获取回流信息」按钮可以获取回流信息</p>
            </div>
          )}
        </Content>
        <Button className="share__btn" type="primary" onClick={share}>分享</Button>
        <Button className="share__btn" type="primary" onClick={getRefluxData}>获取回流信息</Button>
        <Button className="share__btn share__clear" onClick={clear}>清空</Button>
        <div className="share__tips">
          <p>注意：</p>
          <p>1. 「分享」与「获取分享回流信息」功能在这里并不关联，「获取分享回流信息」仅用于展示接口调用</p>
        </div>
      </div>
    </div>
  );
};
