import React from 'react';
import requestScreenOrientation from 'js/device/screen';
import Header from 'component/header';
import Button from 'component/button';
import './index.scss';

export default () => {
  const setScreenVertical = () => requestScreenOrientation(1);
  const setScreenHorizontal = () => requestScreenOrientation(2);

  return (
    <div className="screen">
      <Header info="uc.requestScreenOrientation">强制横屏或竖屏</Header>
      <div className="screen__container">
        <Button className="screen__btn" type="primary" onClick={setScreenVertical}>设置竖屏</Button>
        <Button className="screen__btn" type="primary" onClick={setScreenHorizontal}>设置横屏</Button>
      </div>
    </div>
  );
};
