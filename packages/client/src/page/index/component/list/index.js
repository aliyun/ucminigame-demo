import React, { useState } from 'react';
import { withRouter } from 'react-router-dom';
import listData from 'config/list-data';
import './index.scss';

export default withRouter((props) => {
  const { history } = props;

  const [list, setList] = useState(listData);

  const onClick = (e) => () => {
    const { link } = e;

    if (link) {
      history.push(link);
    } else {
      e.showSub = !e.showSub;
      setList([...list]);
    }
  };

  return (
    <ul className="index-list">
      {list.map(e => (
        <li key={e.title}>
          <div className="index-list__item" onClick={onClick(e)} style={{ opacity: e.showSub ? .5 : 1 }}>
            <span className="index-list__title">{e.title}</span>
            <img className="index-list__icon" src={e.icon} alt="list icon" />
          </div>
          {e.sub && e.showSub && (
            <ul>
              {e.sub.map(v => (
                <li key={v.title} className="index-list__sub-item" onClick={onClick(v)}>
                  <span className="index-list__sub-title">{v.title}</span>
                  <i className="iconfont iconjiantou_you" />
                </li>
              ))}
            </ul>
          )}
        </li>
      ))}
    </ul>
  );
});
