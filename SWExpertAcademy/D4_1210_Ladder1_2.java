import java.io.*;
import java.util.*;

public class D4_1210_Ladder1_2 {
	public static int[][] map;
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		while (T-- > 0) {
			int tc = Integer.parseInt(br.readLine());
			map = new int[100][100];

			StringTokenizer st;
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}

			// 정답의 위치
			int r = 99;
			int c = 99;
			for (int i = 0; i < 100; i++)
				if (map[99][i] == 2)
					c = i;

			// 0 : 상 , 1 : 우, 2 : 좌
			int dir = 0;
			// 정답의 위치에서 위로 올라가기
			while (r > 0)
				if (c > 0 && dir != 1 && map[r][c - 1] == 1) { // 좌
					dir = 2;
					c--;
				} else if (c < 99 && dir != 2 && map[r][c + 1] == 1) { // 우
					dir = 1;
					c++;
				} else { // 상
					dir = 0;
					r--;
				}

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(c);
			System.out.println(sb.toString());
		}
	}
}
