const signSort = (data) => {
    return Object.entries(data).sort((a, b) => {
        const [ aKey, aValue ] = a;
        const [ bKey, bValue ] = b;
        const keyA = aKey.toUpperCase();
        const keyB = bKey.toUpperCase();

        const valueA = String(aValue).toUpperCase();
        const valueB = String(bValue).toUpperCase();

        if (keyA < keyB) {
            return -1;
        }
        if (keyA > keyB) {
            return 1;
        }
        if (valueA < valueB) {
            return -1;
        }
        if (valueA > valueB) {
            return 1;
        }
        return 0;
    });
};

module.exports = signSort;
