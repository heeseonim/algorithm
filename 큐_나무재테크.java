import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.DelayQueue;

public class Main_백준_16235_나무재테크_임희선 {
	public static int[][] map;
	public static int[][] plus;
	public static int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
			{ 1, 1 } };
	public static int N, M;
	public static PriorityQueue<tree> t = new PriorityQueue<>();
	public static Deque<tree> dq = new LinkedList<>();
	public static Deque<tree> nextDq = new LinkedList<>();
	public static Queue<tree> deleteT = new LinkedList<>();
	public static Queue<tree> changeT = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 맵 크기
		M = Integer.parseInt(st.nextToken()); // 심는 나무의 개수
		int K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		plus = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = 5; // 초기 양분은 5
				plus[i][j] = Integer.parseInt(st.nextToken()); // 추가되는 양분의 정보
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			t.add(new tree(x, y, z));
		}
		
		while(!t.isEmpty()) {
			dq.add(t.poll());
		}

		year(K);
		System.out.println(dq.size());
	}

	public static class tree implements Comparable<tree> {
		int x;
		int y;
		int age;

		public tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(tree o) {
			return this.age - o.age;
		}
	}

	public static void year(int k) {
		int n = k;

		while (n > 0) {

			// spring
			while (!dq.isEmpty()) {
				tree temp = dq.poll();
				int x = temp.x;
				int y = temp.y;
				int age = temp.age;

				if (map[x][y] >= age) {
					map[x][y] -= age;
					changeT.add(new tree(x, y, age + 1)); // 나이 변경된 큐
					nextDq.add(new tree(x, y, age + 1)); // 다음 턴에 사용될 큐
				} else {
					deleteT.add(new tree(x, y, age));
				}
			}

			// summer
			while (!deleteT.isEmpty()) {
				tree temp = deleteT.poll();
				int x = temp.x;
				int y = temp.y;
				int age = temp.age;
				map[x][y] += age / 2;
			}

			// fall
			while (!changeT.isEmpty()) {
				tree temp = changeT.poll();
				int x = temp.x;
				int y = temp.y;
				int age = temp.age;
				if (age % 5 == 0) {
					for (int j = 0; j < dir.length; j++) {
						int newX = x + dir[j][0];
						int newY = y + dir[j][1];
						if (isIn(newX, newY)) {
							nextDq.addFirst(new tree(newX, newY, 1));
						}
					}
				}
			}

			// winter
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += plus[i][j];
				}
			}

			n--;

			while (!nextDq.isEmpty()) {
				dq.add(nextDq.poll());
			}
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
