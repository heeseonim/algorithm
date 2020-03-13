function solution(nums) {
    var length = nums.length;
    var max = length / 2;

    var set = new Set();
    nums.forEach(num => {
        set.add(num);
    });

    var size = set.size;
    if (size > max) {
        return max;
    } else {
        return size;
    }
}