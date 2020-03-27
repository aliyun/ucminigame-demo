import React from 'react';
import { CopyToClipboard } from 'react-copy-to-clipboard';
import logo from 'assets/image/logo.png';
import toast from 'utils/toast';
import List from './component/list';
import './index.scss';

const onCopy = () => toast.error('复制成功');

export default () => {
  return (
    <div className="index">
      <div className="index__header">
        <img className="index__logo" src={logo} alt="logo" />
        <p className="index__brief">
          以下将演示小游戏接口能力，具体属性参数详见PC端的小游戏开发文档，可
          <CopyToClipboard text="https://github.com/aliyun/ucminigame-demo" onCopy={onCopy}><span>点击此处</span></CopyToClipboard>
          复制github开源代码链接
        </p>
      </div>

      <List />
    </div>
  );
};
