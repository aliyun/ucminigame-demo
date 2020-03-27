import config from 'config';

const env = process.env.REACT_APP_MY_ENV;

/**
 * 生成接口地址
 * @param   {String}  url 接口路径
 * @return  {String}      完整接口地址
 * */
export const getUrl = (url) => {
  const path = env !== 'dev' ? '/api/demo/v1' : '';
  return `${config.host}${path}${url}`;
};

export default async (url, opts) => {
  const res = await fetch(getUrl(url), {
    mode: 'cors',
    ...opts,
  }).then(e => e.json());

  const { code, data } = res;

  return code === 2000000 ? data : Promise.reject(res);
};
