import React, { useState, useEffect } from 'react';
import { onLoad, onClose, onError, create, show, destroy } from 'js/ad/excitation';
import toast from 'utils/toast';
import Header from 'component/header';
import Button from 'component/button';
import './index.scss';

export default () => {
  const [isOpened, setIsOpened] = useState(false);
  const [ad, setAd] = useState(false);

  const handleOnLoad = async () => {
    onLoad(() => {
      toast.success('广告加载成功');
    });
  };

  const handleShow = () => {
    if (!ad) {
      toast.error('广告组件初始化失败');
      return;
    }

    show();
  };

  const handleOnError = async () => {
    onError((err) => {
      console.log('show excitation ad error: ', err);
      toast.error('出错了');
    });
  };

  const handleCreate = () => {
    if (isOpened) return;

    if (!ad) {
      const adData = create();
      setAd(adData);

      handleClose();
      handleOnLoad();
      handleOnError();
    } else {
      handleShow();
    }

    setIsOpened(true);
  };

  const handleClose = async () => {
    onClose((res) => {
      setIsOpened(false);

      if (res && res.isEnded) {
        toast.success('正常播放结束');
      } else {
        toast.success('中途退出');
      }
    });
  };

  useEffect(() => destroy, []);

  return (
    <div className="excitation">
      <Header info="rewardVideoAd">激励广告</Header>
      <div className="banner__container">
        <Button type="primary" onClick={handleCreate}>展示广告</Button>
      </div>
    </div>
  );
};
