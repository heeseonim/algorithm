import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_17136_색종이붙이기 {

	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static int[][] map;
	public static int[] p = { 0, 5, 5, 5, 5, 5 }; // 색종이 장수
	public static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		StringTokenizer st;
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				sum += map[i][j] = Integer.parseInt(st.nextToken()); // sum : 1의 개수
			}
		}

		min = Integer.MAX_VALUE;
		if (sum == 0) {
			min = 0;
		} else if (sum == 100) {
			min = 4;
		} else {
			dfs(sum, 0);
		}

		// min의 초기값이 그대로라면 채울 수 없으므로 -1 출력
		System.out.println((min == Integer.MAX_VALUE) ? -1 : min);

	} // end of main

	public static void dfs(int sum, int cnt) {
		if (min < cnt) { // 최소 개수보다 사용개수가 많아지면 중단
			return;
		} else if (sum == 0) { // 1이 다 사라졌을 때
			min = Math.min(min, cnt);
			return;
		} else { // 재귀파트
			// for문이 아닌 다른 곳에서도 쓰기 위해 for문 밖에 선언
			int r = -1;
			int c = -1;

			// 1인 위치 찾아서 좌표 저장하기
			ex: for (r = 0; r < 10; r++) {
				for (c = 0; c < 10; c++) {
					if (map[r][c] == 1) {
						break ex; // 1을 발견했을 경우 반복을 중단
					}
				}
			}

			int maxSize = 5; // 제일 큰 색종이부터 시작
			for (int i = maxSize; i > 0; i--) {
				if (check(r, c, i)) {
					maxSize = i;
					break; // 해당 사이즈를 붙일 수 있다면 반복을 중단
				}
			}

			// 색종이 덮어서 재귀호출 진행해보기
			// 다시 되돌려주기
			for (int i = maxSize; i > 0; i--) {
				if (p[i] > 0) { // 남은 색종이 있을 때 덮어주기
					paint(r, c, i); // 색종이 덮어주기
					p[i]--; // 색종이 사용
					dfs(sum - i * i, cnt + 1);
					paint(r, c, i); // 덮은 색종이 복원
					p[i]++; // 색종이 개수 복원
				}
			}

		}
	}

	// 정해진 사이즈만큼 색종이 붙일 수 있는 지 검사하는 함수
	public static boolean check(int r, int c, int size) {
		if (r + size > 10 || c + size > 10)
			return false;
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	// 색종이 복원하는 함수 : XOR 연산
	public static void paint(int r, int c, int size) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				map[i][j] = map[i][j] ^ 1;
			}
		}
	}

} // end of class
