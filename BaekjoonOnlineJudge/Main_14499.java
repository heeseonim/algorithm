import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main_백준_14499_주사위굴리기 {
	public static int[][] dir = { { 0, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	public static int[][] map;
	public static int N, M, K;
	public static int[] com;
	public static Vector<Integer> v;
	public static int[] dice;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도의 행 크기
		M = Integer.parseInt(st.nextToken()); // 지도의 열 크기
		int x = Integer.parseInt(st.nextToken()); // 주사위 초기 x 위치
		int y = Integer.parseInt(st.nextToken()); // 주사위 초기 y 위치
		K = Integer.parseInt(st.nextToken()); // 명령의 개수

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		com = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			com[i] = Integer.parseInt(st.nextToken());
		}

		v = new Vector<>();
		dice = new int[6];
		move(0, x, y);
		for (int i = 0; i < v.size(); i++) {
			System.out.println(v.get(i));
		}
	}

	public static void move(int comIndex, int r, int c) {
		// 종료 조건
		if (comIndex == com.length) {
			return;
		}
		
		int newR = r + dir[com[comIndex]][0];
		int newC = c + dir[com[comIndex]][1];

		if (isIn(newR, newC)) {
			switch (com[comIndex]) {
			case 1: // 동
				int temp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[5];
				dice[5] = dice[1];
				dice[1] = temp;
				break;
			case 2: // 서
				temp = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[3];
				dice[3] = temp;
				break;
			case 3: // 북
				temp = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[5];
				dice[5] = dice[4];
				dice[4] = temp;
				break;
			case 4: // 남
				temp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[5];
				dice[5] = dice[2];
				dice[2] = temp;
				break;
			}
			// 주사위 상단에 쓰여있는 값
			v.add(dice[0]);
			if (map[newR][newC] == 0) {
				map[newR][newC] = dice[5]; // 바닥면의 값 복사
			} else {
				dice[5] = map[newR][newC]; // 주사위의 바닥에 칸의 수 복사
				map[newR][newC] = 0; // 해당 칸은 0으로 만듦
			}
			move(comIndex + 1, newR, newC);
		} else { // 범위 밖이면
			move(comIndex + 1, r, c); // 다음 명령으로 넘어감
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
