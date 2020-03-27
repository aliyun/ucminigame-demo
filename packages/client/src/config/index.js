import defaultConfig from './default';
import dev from './dev';
import test from './test';
import pre from './pre';
import prod from './prod';

const env = process.env.REACT_APP_MY_ENV;

const envConfig = ({
  dev,
  test,
  pre,
  prod,
})[env] || prod;

export default {
  ...defaultConfig,
  ...envConfig,
};
