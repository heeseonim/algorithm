package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_백준_14891_톱니바퀴 {

	public static class q {
		int front;
		int rear;

		public q(int front, int rear) {
			this.front = front;
			this.rear = rear;
		}
	}

	public static class c {
		int n;
		int d;

		public c(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}

	public static int[][] map;
	public static q[] wheel;
	public static List<c> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		wheel = new q[4]; // 각 바퀴의 front, rear 정보
		for (int i = 0; i < 4; i++) {
			wheel[i] = new q(0, 7);
		}

		list = new ArrayList<>();
		
		int K = Integer.parseInt(br.readLine()); // 회전 횟수
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			rotate(num, dir);
			change();
		}

		int sum = 0;
		int plus = 1;
		for (int i = 0; i < 4; i++) {
			if (map[i][wheel[i].front] == 1) {
				sum += plus;
			}
			plus *= 2;
		}
		System.out.println(sum);
	}

	public static void rotate(int num, int dir) {
		if (num < 0 || num > 3)
			return;
		list.add(new c(num, dir));
		int ld = dir, rd = dir;

		// left 확인
		for (int i = num; i >= 1; i--) {
			int f = wheel[i - 1].front + 2;
			if (f >= 8)
				f -= 8;
			int r = wheel[i].rear - 1;
			if (r < 0)
				r += 8;
			if (map[i - 1][f] != map[i][r]) {
				list.add(new c(i - 1, -ld));
				ld = -ld;
			} else {
				break;
			}
		}
		// right 확인
		for (int i = num; i < 3; i++) {
			int f = wheel[i].front + 2;
			if (f >= 8)
				f -= 8;
			int r = wheel[i + 1].rear - 1;
			if (r < 0)
				r += 8;
			if (map[i][f] != map[i + 1][r]) {
				list.add(new c(i + 1, -rd));
				rd = -rd;
			} else {
				break;
			}
		}
	}

	public static void change() {
		for (int i = 0; i < list.size(); i++) {
			int n = list.get(i).n;
			int d = list.get(i).d;
			if (d == 1) { // 시계방향
				wheel[n].front--;
				if (wheel[n].front < 0)
					wheel[n].front += 8;
				wheel[n].rear--;
				if (wheel[n].rear < 0)
					wheel[n].rear += 8;
			} else { // 반시계방향
				wheel[n].front++;
				if (wheel[n].front >= 8)
					wheel[n].front -= 8;
				wheel[n].rear++;
				if (wheel[n].rear >= 8)
					wheel[n].rear -= 8;
			}
		}

		list.clear();
	}
}
