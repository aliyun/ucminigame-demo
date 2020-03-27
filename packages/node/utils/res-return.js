const success = (ctx, d = {}) => {
  const { status = 200, data = {}, msg = 'success', code = 2000000 } = d;
  ctx.body = {
    ...d,
    code,
    msg,
    data,
    tm: Date.now(),
  };
  ctx.status = status;
};

const fail = (ctx, d = {}) => {
  const { status = 500, data = {}, msg = 'fail', code = 500 } = d;
  ctx.body = {
    ...d,
    code,
    msg,
    data,
    tm: Date.now(),
  };
  ctx.status = status;
};

module.exports = {
  success,
  fail,
};
