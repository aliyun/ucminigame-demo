import md5 from 'crypto-js/md5';

export default (value) => md5(value).toString();
