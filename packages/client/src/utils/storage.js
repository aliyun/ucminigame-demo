export default {
  get: key => {
    let data;
    try {
      data = window.localStorage.getItem(key);
      data = JSON.parse(data);
    } catch (e) {
      // console.error(e);
    }

    return data;
  },
  set: (key, data) => {
    try {
      window.localStorage.setItem(key, JSON.stringify(data));
    } catch (e) {
      console.error(e);
    }
    return;
  }
}