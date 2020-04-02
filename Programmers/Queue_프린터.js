function solution(priorities, location) {
    let list = [];
    for (let i = 0; i < priorities.length; i++) {
        list.push(priorities[i]);
    }

    let sorted = list.sort();
    let reverseSorted = sorted.reverse();
    let max = sorted[0];

    let answer = 0;
    let index = 0;
    let flag = false;

    while(true) {
        if (max === priorities[index]) {
            answer++;
            priorities[index] = -1;
            flag = true;
        }

        if (priorities[location] === -1) {
            return answer;
        }

        if (index === priorities.length - 1) {
            index = 0;
        } else {
            index++;
        }

        if (flag) {
            reverseSorted.shift();
            max = reverseSorted[0];
            flag = false;
        }
    }
}