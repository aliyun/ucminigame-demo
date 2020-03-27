import React from 'react';
import cx from 'utils/classname';
import './index.scss';

export default (props) => {
  const { className, type, children, onClick } = props;

  const click = (e) => onClick && onClick(e);

  return (
    <span className={cx.join('button', className, type === 'primary' && 'button__primary')} onClick={click}>{children}</span>
  );
};
