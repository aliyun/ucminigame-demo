import React, { useState, useEffect } from 'react';
import { onLoad, onError, create, show, hide, destroy } from 'js/ad/banner';
import Header from 'component/header';
import Button from 'component/button';
import './index.scss';

export default () => {
  const [isLoaded, setIsLoaded] = useState(false);
  const [isHide, setIsHide] = useState(false);
  const [ad, setAd] = useState(false);

  const reset = () => {
    setAd(false);
    setIsLoaded(false);
    setIsHide(false);
  };

  const handleOnLoad = () => {
    onLoad(() => {
      console.log('load');
      setIsLoaded(true);
    });
  };

  const handleOnError = () => {
    onError(() => {
      console.log('error');
    });
  };

  const createAd = () => {
    if (ad) return;
    const adData = create();

    handleOnLoad();
    handleOnError();
    setAd(adData);
  };

  const toggle = () => {
    if (isHide) show();
    else hide();

    setIsHide(!isHide);
  };

  const handleDestroy = () => {
    reset();
    destroy();
  };

  useEffect(() => handleDestroy, []);

  return (
    <div className="banner">
      <Header info="uc.createBannerAd">banner 广告</Header>
      <div className="banner__container">
        {isLoaded ? (
          <>
            <Button className="banner__btn" type="primary" onClick={toggle}>{isHide ? '显示广告' : '隐藏广告'}</Button>
            <Button className="banner__btn" type="primary" onClick={handleDestroy}>销毁广告</Button>
          </>
        ) : (
          <Button type="primary" onClick={createAd}>展示广告</Button>
        )}
      </div>
    </div>
  );
};
