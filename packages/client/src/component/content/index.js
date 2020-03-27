import React from 'react';
import cx from 'utils/classname';
import './index.scss';

export default (props) => {
  const { title, className, children } = props;

  return (
    <div className={cx.join('content', className)}>
      <p className="content__title">{title}</p>
      <div className="content__wrap">{children}</div>
    </div>
  );
};
