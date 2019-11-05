package 연습;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2383 {
	private static int[][] map;
	private static int N, ans;
	private static ArrayList<pair> ppl;
	private static ArrayList<pair> stair;
	private static int[] set;
	private static PriorityQueue<pair2> pq;

	static class pair {
		int y;
		int x;

		public pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class pair2 implements Comparable<pair2> {
		int time;
		int stair;
		int status;

		public pair2(int time, int stair, int status) {
			this.time = time;
			this.stair = stair;
			this.status = status;
		}

		@Override
		public int compareTo(pair2 o) {
			if (this.time == o.time)
				return o.status - this.status;
			else
				return this.time - o.time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			ppl = new ArrayList<>();
			stair = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						ppl.add(new pair(i, j));
					if (map[i][j] > 1)
						stair.add(new pair(i, j));
				}
			}
			
			set = new int[ppl.size()];
			ans = Integer.MAX_VALUE;
			whichStair(0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}

	public static void whichStair(int len) {
		if (len == ppl.size()) { // 사람의 명수만큼 계단 고르기 --> 중복순열
			pq = new PriorityQueue<>();

			for (int i = 0; i < len; i++) {
				int py = ppl.get(i).y;
				int px = ppl.get(i).x;
				int sy = stair.get(set[i]).y;
				int sx = stair.get(set[i]).x;
				int t = Math.abs(py - sy) + Math.abs(px - sx);
				pq.add(new pair2(t, set[i], -1));
			}

			goStair();
			return;
		}

		for (int i = 0; i < stair.size(); i++) {
			set[len] = i; // 인덱스 저장
			whichStair(len + 1);
		}
	}

	public static void goStair() {
		// pair2의 status -1 : 계단에 도착 전, 0 : 계단에 도착은 했지만 대기중, 1 : 계단에 올라가 있음
		int min = 0;
		int[] inStair = new int[stair.size()]; // idx 번째 계단에 몇명의 사람이 있는지 확인
		while (!pq.isEmpty()) {
			min++;

			while (!pq.isEmpty()) {
				pair2 front = pq.peek();
				if (front.time != min)
					break;
				pq.poll();
				int mystair = front.stair;
				if (front.status != 1) { // 계단 아직 안감
					// 계단을 내려갈 수 있나?
					if (inStair[mystair] < 3) { // 갈 수 있으면
						int ntime = 0;
						if (front.status == -1) // 계단에 최초 도착이면 1분 대기한다.
							ntime = front.time + 1 + map[stair.get(mystair).y][stair.get(mystair).x];
						else if (front.status == 0) // 이전에 도착해 대기하고 있었으면 바로 계단에 간다.
							ntime = front.time + map[stair.get(mystair).y][stair.get(mystair).x];
						pq.add(new pair2(ntime, front.stair, 1));
						inStair[mystair]++;
					} else // 계단이 포화 상태라 갈 수 없다면
						pq.add(new pair2(front.time + 1, front.stair, 0));
				} else // front.status==1 계단에 있는 상태
					inStair[mystair]--;
			}
		}
		ans = ans > min ? min : ans;

	}

}
