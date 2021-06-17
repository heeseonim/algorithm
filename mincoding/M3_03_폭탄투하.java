import java.io.*;
import java.util.*;

public class M3_03_폭탄투하 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static String[][] arr;
	static int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) throws Exception {
		arr = new String[4][5];
		for (int i = 0; i < arr.length; i++) {
			Arrays.fill(arr[i], "_");
		}

		int N = 2;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			bomb(r, c);
		}
		
		for(String[] a : arr) {
			for(String b : a) {
				sb.append(b).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

	public static void bomb(int r, int c) {
		for (int i = 0; i < dir.length; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			if (!isIn(nr, nc) || arr[nr][nc].equals("#"))
				continue;
			
			arr[nr][nc] = "#";
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < arr.length && c >= 0 && c < arr[0].length;
	}
}
