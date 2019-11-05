import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1247_최적경로 {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Point home;
	static Point company;
	static Point[] list;
	static int min, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			list = new Point[N];
			int[] check = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			company = new Point(x, y);
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Point(x, y);
			for (int i = 0; i < N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				list[i] = new Point(x, y);
				check[i] = i;
			}

			min = Integer.MAX_VALUE;
			shortPath(check, 0);
			System.out.println("#" + tc + " " + min);
		}

	}

	public static void shortPath(int[] check, int size) {
		int sum = 0;
		if (size == N) {
			// 회사와 첫 고객과의 거리
			sum += getDistance(company, list[check[0]]);
			for (int i = 0; i < N-1; i++) {
				sum += getDistance(list[check[i]], list[check[i+1]]);
			}
			// 집과 마지막 고객의 거리
			sum += getDistance(home, list[check[N-1]]);
			min = Math.min(min, sum);
			return;
		} else {
			for (int i = size; i < N; i++) {
				int temp = check[i];
				check[i] = check[size];
				check[size] = temp;
				shortPath(check, size + 1);
				temp = check[i];
				check[i] = check[size];
				check[size] = temp;
			}
		}
	}

	public static int getDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

}
