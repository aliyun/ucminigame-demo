const isServer = typeof window === 'undefined';
let cssInserted = false;

/**
 * 用法：
 * const toast = require('silly-toast')
 * toast.info('hello')
 */
export default {
  success(msg, opt = { }) {
    toast('success', msg, opt);
  },
  info(msg, opt = { }) {
    toast('info', msg, opt);
  },
  warning(msg, opt = { }) {
    toast('warning', msg, opt);
  },
  error(msg, opt = { }) {
    toast('error', msg, opt);
  },
};

// private
function toast(type, msg, opt) {
  if (isServer) {
    return;
  }
  const className = opt.className || '';
  const outerClassName = opt.outerClassName || '';
  const delay = opt.delay || 3000;
  const str = `<div class="toast-outer ${outerClassName}"><div class="toast ${type} ${className}">${msg}</div></div>`;
  const $toast = createElement(str);

  document.body.appendChild($toast);

  insertCss();

  setTimeout(() => {
    document.body.removeChild($toast);
  }, delay);
}

function createElement(string) {
  const div = document.createElement('div');
  div.innerHTML = string;
  return div.firstElementChild;
}

function insertCss() {
  if (cssInserted) return;

  const $style = document.createElement('style');
  $style.setAttribute('type', 'text/css');
  const css = `
  .toast-outer {
    position: fixed;
    text-align: center;
    top: 50%;
    left: 50%;
    z-index: 10000005;
    -webkit-transform: translate(-50%, -50%);
    -moz-transform: translate(-50%, -50%);
     -ms-transform: translate(-50%, -50%);
      -o-transform: translate(-50%, -50%);
         transform: translate(-50%, -50%);
    width: 100%;
  }

  .toast {
    background-color: rgba(0, 0, 0, 0.88);
    padding: 0.2667rem 0.5333rem;
    display: inline-block;
    color: rgb(255, 255, 255);
    -webkit-border-radius: 0.21333rem;
    -moz-border-radius: 0.21333rem;
         border-radius: 0.21333rem;
    max-width: 15.46667rem;
    word-wrap: break-word;
    font-size: 0.3733rem;
    line-height: 1.5em;
    text-align: center;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
         box-sizing: border-box;
    opacity: 0.9;
  }
  `;
  if ($style.styleSheet) { // IE
    $style.styleSheet.cssText = css;
  } else { // else
    $style.appendChild(document.createTextNode(css));
  }
  document.head.appendChild($style);
  cssInserted = true;
}
