package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_3190_뱀 {
	public static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	public static int N, d, index;
	public static Node[] bam;
	public static int answer, L;
	public static boolean[][] appleMap, snakeMap;
	public static Queue<Point> queue;

	public static class Node {
		int time;
		String direction;

		public Node(int time, String direction) {
			this.time = time;
			this.direction = direction;
		}
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int K = Integer.parseInt(br.readLine());
		appleMap = new boolean[N][N];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			appleMap[x][y] = true;
		}
		
		L = Integer.parseInt(br.readLine());
		bam = new Node[L];
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();
			bam[i] = new Node(time, direction);
		}

		d = index = answer = 0;
		queue = new LinkedList<>();
		snakeMap = new boolean[N][N];
		move(0, 0, 0);
		System.out.println(answer);
	}

	public static void move(int time, int r, int c) {
		queue.add(new Point(r, c));

		if (index < L && time == bam[index].time) {
			if (bam[index].direction.equals("D")) {
				d += 1;
				if (d > 3)
					d -= 4;
			} else if (bam[index].direction.equals("L")) {
				d -= 1;
				if (d < 0)
					d += 4;
			}
			index++;
		}

		int newR = r + dir[d][0];
		int newC = c + dir[d][1];
		if (!isIn(newR, newC) || snakeMap[newR][newC]) { // 벽에 부딪히거나 자기 자신을 만나면 return
			answer = time + 1;
			return;
		}

		snakeMap[newR][newC] = true;

		if (!appleMap[newR][newC]) {
			Point p = queue.poll();
			snakeMap[p.x][p.y] = false;
		} else
			appleMap[newR][newC] = false;

		move(time + 1, newR, newC);
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
