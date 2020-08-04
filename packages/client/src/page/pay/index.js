import React, { useState } from 'react';
import idx from 'idx';
import { pay, repay, getTradeData } from 'js/pay';
import { showToast } from 'js/common';
import { checkAndLogin } from 'js/open/user';
import getGuestInfo from 'js/open/guest';
import Header from 'component/header';
import Content from 'component/content';
import Button from 'component/button';
import './index.scss';

export default () => {
  const [preCreateData, setPreCreateData] = useState({});
  const [orderData, setOrderData] = useState({});

  const isHasTradeData = orderData && Object.keys(orderData).length > 0;

  const handleError = (error, defaultMsg = '出错了') => {
    const msg = idx(error, _ => _.msg);
    showToast({
      content: msg || defaultMsg,
    });
  };

  const handlePay = async () => {
    try {
      const userInfo = await checkAndLogin();
      const guestInfo = await getGuestInfo();

      const result = await pay({
        open_id: userInfo.open_id,
        guest_id: guestInfo.guestid,
      });
      const { token, order_id: orderId, biz_order_id: bizOrderId } = result;

      setPreCreateData({
        token,
        order_id: orderId,
        biz_order_id: bizOrderId,
      });

      const data = await getTradeData({
        orderId,
        bizOrderId,
        openId: userInfo.open_id,
        guest_id: guestInfo.guestid,
      });
      setOrderData(data);
    } catch (err) {
      console.error(err);
      handleError(err, '发起支付失败');
    }
  };

  const handleRepay = async () => {
    const { token, order_id: orderId, biz_order_id: bizOrderId } = preCreateData || {};

    try {
      const userInfo = await checkAndLogin();
      const guestInfo = await getGuestInfo();

      await repay(token, orderId);

      const data = await getTradeData({
        orderId,
        bizOrderId,
        openId: userInfo.open_id,
        guest_id: guestInfo.guestid,
      });
      setOrderData(data);
    } catch (err) {
      handleError(err, '重新支付失败');
    }
  };

  return (
    <div className="pay">
      <Header info="uc.requestPayment">支付/重新支付</Header>
      <div className="pay__container">
        <Content title="支付信息">
          {isHasTradeData ? (
            <div className="pay__content">
              <p>
                <span className="pay__label">订单id</span>
                <span className="pay__value">{orderData.order_id}</span>
              </p>
              <p>
                <span className="pay__label">订单标题</span>
                <span className="pay__value">{orderData.title}</span>
              </p>
              <p>
                <span className="pay__label">订单状态</span>
                <span className="pay__value">{orderData.trade_status}</span>
              </p>
              <p>
                <span className="pay__label">订单金额</span>
                <span className="pay__value">{orderData.total_amount / 100}元</span>
              </p>
            </div>
          ) : (
            <div className="pay__empty">
              <p>未发起订单</p>
              <p>点击支付按钮发起订单</p>
            </div>
          )}
        </Content>
        <Button className="pay__btn" type="primary" onClick={handlePay}>发起支付</Button>
        <Button className="pay__btn" type="primary" onClick={handleRepay}>重新支付</Button>
        <div className="pay__tips">
          <p>注意：</p>
          <p>1. 支付功能需要在登录状态下使用</p>
          <p>2. 重新支付只能在订单「未确定」的情况下才能进行，当页面跳转到「支付宝」页面则订单为「已确认」状态</p>
        </div>
      </div>
    </div>
  );
};
