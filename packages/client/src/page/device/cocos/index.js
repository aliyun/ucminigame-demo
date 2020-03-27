import React from 'react';
import { showToast } from 'js/common';
import enableWebGL from 'js/device/cocos';
import Header from 'component/header';
import Button from 'component/button';
import './index.scss';

export default () => {
  const click = () => {
    try {
      const data = enableWebGL() || {};
      const { success, msg } = data;
      const text = success ? '开启成功' : '开启失败';
      showToast({
        content: msg || text,
      });
    } catch (err) {
      showToast({
        content: '开启失败',
      });
    }
  };

  return (
    <div className="cocos">
      <Header info="uc.enableWebGL">Cocos 引擎开启 WebGL 兼容</Header>
      <div className="cocos__container">
        <Button type="primary" onClick={click}>开启 WebGL 兼容</Button>
      </div>
    </div>
  );
};
