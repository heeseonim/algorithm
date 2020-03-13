function solution(clothes) {
    var closet = clothes.reduce((a, c) => {
        a[c[1]] = (a[c[1]]) ? a[c[1]] + 1 : 1;
        return a;
    }, {});

    var item = Object.values(closet);
    if (item.length === 1) {
        return item[0];
    }

    var result = 1;
    item.forEach((i) => result *= (i + 1));

    return result - 1;
}