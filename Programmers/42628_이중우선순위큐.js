function solution(operations) {
   
    let queue = [];

    for (let item of operations) {
        let [command, val] = item.split(" ");
        if (command === "I") {
            queue.push(val * 1);
            queue.sort((l, r) => l - r);
        } else if (queue.length) {
            val * 1 > 0 ? queue.pop() : queue.shift();
        }
    }

    return queue.length ? [queue[queue.length - 1], queue[0]] : [0, 0];
}