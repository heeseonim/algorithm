package practice_0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1932_정수삼각형 {
	public static int n;
	public static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int count = 1;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < count; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			count++;
		}
		
		triSum();
		int max = Integer.MIN_VALUE;
		for(int a : map[n-1]) {
			max = Math.max(max, a);
		}
		System.out.println(max);

	}

	public static void triSum() {
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if (j==0) {
					map[i][j] += map[i-1][j];
				} else if (j==i) {
					map[i][j] += map[i-1][j-1];
				} else {
					map[i][j] = Math.max(map[i][j] + map[i-1][j-1], map[i][j]+map[i-1][j]);
				}
			}
		}
	}
}
