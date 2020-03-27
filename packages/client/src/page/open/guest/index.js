import React, { useState } from 'react';
import getGuestInfo from 'js/open/guest';
import Header from 'component/header';
import Content from 'component/content';
import Button from 'component/button';
import './index.scss';

export default () => {
  const [guestInfo, setGuestInfo] = useState({});

  // 是否存在用户信息
  const isHasGuestInfo = guestInfo && Object.keys(guestInfo).length > 0;

  const click = async () => {
    const result = await getGuestInfo();
    if (result) setGuestInfo(result);
  };

  const clear = () => setGuestInfo({});

  return (
    <div className="user">
      <Header info="uc.getGuestInfo">获取游客信息</Header>
      <div className="guest__container">
        <Content title="游客信息">
          {isHasGuestInfo ? (
            <>
              <img className="guest__cover" src={guestInfo.avatar_url} alt="cover" />
              <p className="guest__nickname">{guestInfo.nickname}</p>
            </>
          ) : (
            <div className="guest__empty">
              <p>未获取</p>
              <p>点击蓝色按钮可以获取游客头像及昵称</p>
            </div>
          )}
        </Content>
        <Button type="primary" onClick={click}>获取游客信息</Button>
        <Button className="guest__clear" onClick={clear}>清空</Button>
      </div>
    </div>
  );
};
