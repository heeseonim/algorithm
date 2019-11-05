import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정올_2543_타일채우기 {
	public static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		tile(0, 0, N - 1, N - 1, X, Y, 0);
		for (int[] a : map) {
			for (int b : a) {
				System.out.print(b + " ");
			}
			System.out.println();
		}
	}

	public static void tile(int sx, int sy, int ex, int ey, int hx, int hy, int hv) {
		int mx = (sx + ex) / 2;
		int my = (sy + ey) / 2;

		if (sx == ex) {
			map[sx][sy] = hv;
			return;
		}

		if (sx <= hx && hx <= mx && sy <= hy && hy <= my) {
			map[mx][my + 1] = 1;
			map[mx + 1][my] = 1;
			map[mx + 1][my + 1] = 1;

			tile(sx, sy, mx, my, hx, hy, hv);
			tile(sx, my + 1, mx, ey, mx, my + 1, 1);
			tile(mx + 1, sy, ex, my, mx + 1, my, 1);
			tile(mx + 1, my + 1, ex, ey, mx + 1, my + 1, 1);
		}

		else if (sx <= hx && hx <= mx && sy <= hy && hy >= my + 1) {
			map[mx][my] = 2;
			map[mx + 1][my] = 2;
			map[mx + 1][my + 1] = 2;
			tile(sx, sy, mx, my, mx, my, 2);
			tile(sx, my + 1, mx, ey, hx, hy, hv);
			tile(mx + 1, sy, ex, my, mx + 1, my, 2);
			tile(mx + 1, my + 1, ex, ey, mx + 1, my + 1, 2);
		}

		else if (sx <= hx && hx >= mx + 1 && sy <= hy && hy <= my) {
			map[mx][my] = 3;
			map[mx][my + 1] = 3;
			map[mx + 1][my + 1] = 3;
			tile(sx, sy, mx, my, mx, my, 3);
			tile(sx, my + 1, mx, ey, mx, my + 1, 3);
			tile(mx + 1, sy, ex, my, hx, hy, hv);
			tile(mx + 1, my + 1, ex, ey, mx + 1, my + 1, 3);
		}

		else if (sx <= hx && hx >= mx + 1 && sy <= hy && hy >= my + 1) {
			map[mx][my] = 4;
			map[mx][my + 1] = 4;
			map[mx + 1][my] = 4;
			tile(sx, sy, mx, my, mx, my, 4);
			tile(sx, my + 1, mx, ey, mx, my + 1, 4);
			tile(mx + 1, sy, ex, my, mx + 1, my, 4);
			tile(mx + 1, my + 1, ex, ey, hx, hy, hv);
		}
	}

}
