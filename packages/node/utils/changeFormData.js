const FormData = require('form-data');

const changeFormData = (data) => {
    return Object.keys(data).reduce((result, key) => {
        result.append(key, data[key]);
        return result;
    }, new FormData());
};

module.exports = changeFormData;
