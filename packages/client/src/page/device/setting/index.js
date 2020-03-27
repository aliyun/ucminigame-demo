import React, { useState } from 'react';
import { getSystemInfo, getSystemInfoSync } from 'js/device/setting';
import toast from 'utils/toast';
import Header from 'component/header';
import Content from 'component/content';
import Button from 'component/button';
import './index.scss';

export default () => {
  const [data, setData] = useState({});

  const isHasData = data && Object.keys(data).length > 0;

  const handleGetSystemInfo = async () => {
    try {
      const data = await getSystemInfo();
      setData(data);
    } catch (err) {
      toast.error('获取失败');
    }
  };

  const handleGetSystemInfoSync = async () => {
    const data = await getSystemInfoSync();
    if (data) {
      setData(data);
    } else {
      toast.error('获取失败');
    }
  };

  const clear = () => setData({});

  return (
    <div className="setting">
      <Header info="uc.getSystemInfo">获取系统设备信息</Header>
      <div className="setting__container">
        <Content title="设备信息">
          {isHasData ? (
            <div className="setting__content">
              {Object.keys(data).map(e => (
                <p key={e}>
                  <span className="setting__label">{e}</span>
                  <span className="setting__value">{data[e]}</span>
                </p>
              ))}
            </div>
          ) : (
            <div className="setting__empty">
              <p>未获取</p>
              <p>点击蓝色按钮获取设备信息</p>
            </div>
          )}
        </Content>
        <Button className="setting__btn" type="primary" onClick={handleGetSystemInfo}>获取系统设备信息</Button>
        <Button className="setting__btn" type="primary" onClick={handleGetSystemInfoSync}>获取系统设备信息(同步)</Button>
        <Button className="setting__btn setting__clear" onClick={clear}>清空</Button>
      </div>
    </div>
  );
};
