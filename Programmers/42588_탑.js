function solution(heights) {
    var answer = [];
    for (const index in heights) {
        answer[index] = 0;
    }

    for (let i = heights.length - 1; i > 0; i--) {
        var cur = heights[i];
        for(let j = i - 1; j >= 0; j--) {
            if (heights[j] > cur) {
                answer[i] = j + 1;
                break;
            }
        }
    }

    return answer;
}