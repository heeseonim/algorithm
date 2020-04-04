
/**
 * heeseonim
 * 
 * 1. n 명의 권투선수가 권투 대회에 참여
 * 2. 1대1 방식, 실력 좋으면 항상 이김
 * 3. 주어진 경기 결과를 가지고 선수들위 순위를 매김
 * 4. 하지만 몇몇 경기의 결과를 분실함
 * 
 * 1. 정확하게 순위를 매길 수 있는 선수의 수를 return
 * 2. 플로이드 와샬 알고리즘 사용 후 계속 M 으로 남아있는 노드의 개수를 빼줌
 */

public class 그래프_순위 {
	public static void main(String[] args) {
		System.out.println(solution(5, new int[][] { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } }));
	}

	public static final int M = Integer.MAX_VALUE;
	public static int[][] map;

	public static int solution(int n, int[][] results) {
		map = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (i == j) // 같을 때는 0
					continue;
				map[i][j] = M; // 나머지는 M 으로 초기화
			}

		for (int[] r : results) // 연결된 지점은 1로 표시 (한 방향으로만 표시)
			map[r[0] - 1][r[1] - 1] = 1;

		floydWarshall(n);

		boolean[] check = new boolean[n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) { // 연결된 지점 검사
				if (i == j)
					continue;
				if (map[i][j] == M && map[j][i] == M) { // 결과가 앖을 때
					check[i] = true; // 해당 선수의 순위를 매길 수 없음
					break; // 굳이 뒤쪽 볼 필요 없으므로 멈춤
				}
			}

		int answer = n;
		for (boolean i : check)
			if (i) // 순위를 매길 수 없는 선수를 발견했을 때
				answer--;
		return answer;
	}

	public static void floydWarshall(int n) {
		for (int k = 0; k < n; k++) // 경유
			for (int i = 0; i < n; i++) { // 출발
				if (k == i)
					continue;
				for (int j = 0; j < n; j++) { // 도착
					if (k == j || i == j)
						continue;

					if (map[i][k] != M && map[k][j] != M && map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
	}
}
