import React, { useState, useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import { getSystemInfoSync } from 'js/device/setting';
import './index.scss';

export default withRouter((props) => {
  const { children, info, history } = props;

  const [barHeight, setBarHeight] = useState(0);

  const onBack = () => history.push('/index.html');

  useEffect(() => {
    const { statusBarHeight = 0 } = getSystemInfoSync() || {};
    setBarHeight(statusBarHeight);
  }, []);

  return (
    <div className="header" style={{ marginTop: `${barHeight / 2 / 37.5}rem` }}>
      <div className="header__head">
        <i className="iconfont iconjiantou_zuo" onClick={onBack} />
        <div className="header__title">{children}</div>
        <div className="header__operate" />
      </div>
      {info && <div className="header__info">{info}</div>}
    </div>
  );
});
