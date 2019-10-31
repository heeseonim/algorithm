import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_6109 {
	// swea 6109 추억의2048게임
	public static int N;
	public static int[][] map;
	public static int[][] newMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			String com = st.nextToken();

			map = new int[N][N];
			newMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			if (com.equals("up"))
				up();
			else if (com.equals("down"))
				down();
			else if (com.equals("left"))
				left();
			else if (com.equals("right"))
				right();

			System.out.println("#" + tc);
			for (int[] a : newMap) {
				for (int b : a) {
					System.out.print(b + " ");
				}
				System.out.println();
			}
		} // end of tc
	} // end of main

	public static void up() {
		Deque<Integer> deque = new ArrayDeque<>();

		for (int j = 0; j < N; j++) {
			int cur = -1;
			for (int i = 0; i < N; i++) { // 위에서부터 탐색
				if (map[i][j] == 0)
					continue;
				if (map[i][j] == cur) {
					deque.pollLast();
					deque.add(cur * 2);
					cur = -1; // 다시 합쳐지면 안되므로 값 바꿔줌
				} else {
					cur = map[i][j];
					deque.add(cur);
				}
			}
			int index = 0;
			while (!deque.isEmpty())
				newMap[index++][j] = deque.poll(); // 앞부터 채워줌
		}
	}

	public static void down() {
		Deque<Integer> deque = new ArrayDeque<>();

		for (int j = 0; j < N; j++) {
			int cur = -1;
			for (int i = N - 1; i >= 0; i--) { // 밑에서부터 탐색
				if (map[i][j] == 0)
					continue;
				if (map[i][j] == cur) {
					deque.pollLast();
					deque.add(cur * 2);
					cur = -1; // 다시 합쳐지면 안되므로 값 바꿔줌
				} else {
					cur = map[i][j];
					deque.add(cur);
				}
			}
			int index = N - 1;
			while (!deque.isEmpty())
				newMap[index--][j] = deque.poll(); // 밑에서부터 채워줌
		}
	}

	public static void left() {
		Deque<Integer> deque = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			int cur = -1;
			for (int j = 0; j < N; j++) { // 앞에서부터 탐색
				if (map[i][j] == 0)
					continue;
				if (map[i][j] == cur) {
					deque.pollLast();
					deque.add(cur * 2);
					cur = -1; // 다시 합쳐지면 안되므로 값 바꿔줌
				} else {
					cur = map[i][j];
					deque.add(cur);
				}
			}
			int index = 0;
			while (!deque.isEmpty())
				newMap[i][index++] = deque.poll(); // 앞부터 채워줌
		}
	}

	public static void right() {
		Deque<Integer> deque = new ArrayDeque<>(); // 확인할 땐 뒤에서, 넣어줄 땐 앞에서

		for (int i = 0; i < N; i++) {
			int cur = -1; // 비교할 숫자
			for (int j = N - 1; j >= 0; j--) { // 뒤에서부터 탐색
				if (map[i][j] == 0)
					continue;
				if (map[i][j] == cur) {
					deque.pollLast();
					deque.add(cur * 2);
					cur = -1;
				} else {
					cur = map[i][j];
					deque.add(cur);
				}
			}
			int index = N - 1;
			while (!deque.isEmpty())
				newMap[i][index--] = deque.poll(); // 뒤부터 채워줌
		}
	}

}
