import React, { useState } from 'react';
import idx from 'idx';
import { showModal, exit } from 'js/common';
import getUserInfo from 'js/open/user';
import Header from 'component/header';
import Content from 'component/content';
import Button from 'component/button';
import './index.scss';

export default () => {
  const [userInfo, setUserInfo] = useState({});

  // 是否存在用户信息
  const isHasUserInfo = userInfo && Object.keys(userInfo).length > 0;

  const click = (force = false) => async () => {
    try {
      const result = await getUserInfo();
      if (result) setUserInfo(result);
    } catch (err) {
      const code = idx(err, _ => _.code);
      if (force && code && code === 300001) {
        showModal({
          title: '登录失败',
          content: '是否重新登录？',
          cancelText: '退出游戏',
          success: (data) => {
            if (data.confirm) {
              console.log('点击了确定');
              click(true)();
            } else if (data.cancel) {
              console.log('点击了取消');
              exit();
            }
          },
        });
      }
    }
  };

  const clear = () => setUserInfo({});

  return (
    <div className="user">
      <Header info="uc.getUserInfo">获取用户信息</Header>
      <div className="user__container">
        <Content title="用户信息">
          {isHasUserInfo ? (
            <>
              <img className="user__cover" src={userInfo.avatar_url} alt="cover" />
              <p className="user__nickname">{userInfo.nickname}</p>
            </>
          ) : (
            <div className="user__empty">
              <p>未获取</p>
              <p>点击蓝色按钮可以获取用户头像及昵称</p>
            </div>
          )}
        </Content>
        <Button className="user__btn" type="primary" onClick={click(false)}>获取用户信息</Button>
        <Button className="user__btn" type="primary" onClick={click(true)}>获取用户信息(强制登录)</Button>
        <Button className="user__btn user__clear" onClick={clear}>清空</Button>
      </div>
    </div>
  );
};
