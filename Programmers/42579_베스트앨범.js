function solution(genres, plays) {
    var list = genres.reduce((hash, g, i) => { // 배열.reduce((누적값, 현재값, 인덱스))
        if (!hash[g]) {
            hash[g] = { plays: plays[i], music: [] };
        } else {
            hash[g].plays += plays[i];
        }
        hash[g].music.push([i, plays[i]]); // 이차원배열
        return hash; // 결과
    }, {}); // 초기값

    var answer = [];
    var sorted = Object.values(list).sort((l, r) => r.plays - l.plays); // 많이 재생된 장르를 먼저 수록
    sorted.forEach((g) => {
        if (g.music.length > 1) {
            g.music.sort((l, r) => {
                if (l[1] > r[1]) {
                    return -1;
                } else if (l[1] < r[1]) { // 장르 내 많이 재생된 노래를 먼저 수록
                    return 1;
                } else { // 재생 횟수가 같다면 고유 번호가 낮은 노래를 먼저 수록
                    return (l[0] > r[0]) ? 1 : -1;
                }
            });
            answer.push(g.music[0][0]);
            answer.push(g.music[1][0]);
        } else {
            answer.push(g.music[0][0]);
        }
    });

    return answer;

}