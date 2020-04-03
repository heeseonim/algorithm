function solution(answers) {
    let supoja = [[1,2,3,4,5], [2,1,2,3,2,4,2,5], [3,3,1,1,2,2,4,4,5,5]];
    let score = [0,0,0];

    for(var i in answers) {
        if (answers[i] === supoja[0][i%5]) {
            score[0]++;
        }
        if (answers[i] === supoja[1][i%8]) {
            score[1]++;
        }
        if (answers[i] === supoja[2][i%10]) {
            score[2]++;
        }
    }

    var max = score[0];
    for(var i in score) {
        if (max < score[i]) {
            max = score[i];
        }
    }

    let list = [];
    var index = 0;
    for(var i in score) {
        if (max === score[i]) {
            list[index++] = ++i;
        }
    }

    return list;
}