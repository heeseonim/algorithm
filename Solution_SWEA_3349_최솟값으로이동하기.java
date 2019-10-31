import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_3349_최솟값으로이동하기 {
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
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			Point[] order = new Point[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				order[i] = new Point(a - 1, b - 1);
			}
			
			int answer = 0;
			for (int i = 0; i < order.length - 1; i++) {
				int X = order[i].x - order[i + 1].x;
				int Y = order[i].y - order[i + 1].y;
				if (X * Y > 0) { // 모두 증가하거나 모두 감소할 때
					X = Math.abs(X);
					Y = Math.abs(Y);
					if (X > Y) {
						answer += Y + (X-Y);
					}
					else {
						answer += X + (Y-X);
					}
				} else {
					X = Math.abs(X);
					Y = Math.abs(Y);
					answer += X + Y;
				}
			}

			System.out.println("#" + tc + " " + answer);
		}
	}

}
