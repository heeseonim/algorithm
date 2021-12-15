import java.io.*;
import java.util.*;

public class Main {
	public static int N, M, answer;
	public static List<Point> list; // 리스트 하나로 합치기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<Main.Point>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				list.add(new Point(num, i)); // 학급 표시
			}
		}

		Collections.sort(list); // 능력치 오름차순 정렬
		answer = Integer.MAX_VALUE;
		
		twoPointer();

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	public static void twoPointer() {
		int[] arr = new int[N]; // 현재 범위 안의 학급 학생 수
		int num, start, end;
		start = end = 0;
		// 사전처리
		num = 1;
		arr[list.get(start).hakgub]++;
	
		while (end < N * M) {
			if (num == N) { // 전체 학급이 선택되었을 때 최솟값 갱신
				answer = Math.min(answer, list.get(end).num - list.get(start).num);
				int index = list.get(start).hakgub; // 이전 start의 index값 기록
				arr[index]--;
				if (arr[index] == 0) // 선택된 학급이 없다면
					num--; // 학급 수 줄이기
				start++;
			} else {
				end++;
				if (end == N * M) break;
				int index = list.get(end).hakgub;
				if (arr[index] == 0) // 선택되지 않은 학급이라면
					num++;
				arr[index]++;
			}
		}
	}

	public static class Point implements Comparable<Point> {
		int num, hakgub;

		public Point(int num, int hakgub) {
			this.num = num;
			this.hakgub = hakgub;
		}

		@Override
		public int compareTo(Point o) {
			return this.num - o.num;
		}
	}
}
