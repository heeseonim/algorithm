package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17779 {
	public static int N;
	public static int[][] map;
	public static int[][] num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		num = new int[N][N]; // 선거구를 표시할 배열
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 사각형을 그릴 수 있는지 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int w = 1; w < N; w++) {
					for (int h = 1; h < N; h++) {
						if (j - h < 0 || i + w + h >= N || j + w >= N)
							continue;
						System.out.println(i + " " + j + " " + w + " " + h);
						count(i, j, w, h);
					}
				}
			}
		}
	}

	public static void count(int r, int c, int w, int h) {
		int[] people = new int[5]; // 각 선거구별 인구수를 저장할 배열
		
		// 5 구역
		int width = w, height = h;
		while (width >= 0 && height >= 0) {
			people[4] += (width + height) * 2;
			width--;
			height--;
		}
		
		// 1 구역
		
		// 2 구역
		
		// 3 구역
		
		// 4 구역
	}
}
