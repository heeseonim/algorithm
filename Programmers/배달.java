
public class 배달 {
	public static void main(String[] args) {
		배달 배 = new 배달();
		System.out.println(배.solution(6, new int[][] { { 1, 2, 1 }, { 1, 3, 2 }, { 2, 3, 2 }, { 3, 4, 3 }, { 3, 5, 2 },
				{ 3, 5, 3 }, { 5, 6, 1 } }, 4));
	}

	public int solution(int N, int[][] road, int K) {
		int[][] map = new int[N][N];
		int M = Integer.MAX_VALUE;

		for (int i = 0; i < road.length; i++) {
			if (map[road[i][0] - 1][road[i][1] - 1] != 0 && map[road[i][0] - 1][road[i][1] - 1] < road[i][2]) {
				continue;
			}
			map[road[i][0] - 1][road[i][1] - 1] = road[i][2];
			map[road[i][1] - 1][road[i][0] - 1] = road[i][2];
		}

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				if (i != j && map[i][j] == 0)
					map[i][j] = M;

		int start = 0;
		int[] arr = map[start];

		boolean[] visit = new boolean[map.length];
		for (int i = 0; i < map.length; i++) {
			int minIndex = -1;
			int min = M;
			for (int j = 0; j < arr.length; j++) {
				if (!visit[j] && min > arr[j]) { // 해당 정점을 방문하지 않았고 작은값을 발견헀다면
					minIndex = j;
					min = arr[j];
				}
			}
			visit[minIndex] = true;

			for (int j = 0; j < arr.length; j++) {
				if (!visit[j] && map[minIndex][j] != M && arr[j] > arr[minIndex] + map[minIndex][j]) {
					arr[j] = arr[minIndex] + map[minIndex][j];
				}
			}
		}

		for (int[] m : map) {
			for (int mm : m) {
				System.out.print(mm == M ? 'M' + " " : mm + " ");
			}
			System.out.println();
		}
		System.out.println();

		int answer = 0;
		for (int m : map[0])
			if (m <= K) {
				answer++;
			}

		return answer;
	}
}
