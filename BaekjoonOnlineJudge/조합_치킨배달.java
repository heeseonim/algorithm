package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_백준_15686_치킨배달 {
	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static List<Point> house;
	public static List<Point> chicken;
	public static Point[] choice;
	public static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); // 치킨집 최대 개수
		
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					house.add(new Point(i, j));
				} else if (num == 2) {
					chicken.add(new Point(i, j));
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		
		// 선택된 치킨집
		choice = new Point[M];
		comb(chicken.size(), M);
		
		System.out.println(answer);
	}

	public static void comb(int n, int r) {
		if (r == 0) { // 종료 파트
			int sum = 0;
			for (int i = 0; i < house.size(); i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < choice.length; j++) {
					int result = Math.abs(house.get(i).x-choice[j].x) + Math.abs(house.get(i).y-choice[j].y);
					if (min > result)
						min = result;
				}
				sum+=min;
			}
			if (answer > sum)
				answer = sum;
		} else if (n < r) {
			return;
		} else { // 재귀 파트
			choice[r-1] = chicken.get(n-1);
			comb(n - 1, r - 1);
			comb(n - 1, r);
		}
	}
}
