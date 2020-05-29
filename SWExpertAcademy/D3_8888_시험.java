import java.io.*;
import java.util.*;

public class 시험_8888 {
	public static int N, T, P;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			HashMap<Integer, Info> people = new HashMap<>(); // 각 참가자의 정보를 저장할 해쉬맵
			boolean[][] check = new boolean[N][T];
			int[] score = new int[T]; // T 개의 문제의 점수를 저장할 배열
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				people.put(i, new Info());
				for (int j = 0; j < T; j++) {
					int result = Integer.parseInt(st.nextToken());
					if (result == 1) {
						people.get(i).num++; // 문제 푼 수 증가
						check[i][j] = true;
					} else { // 0 이라면 해당 문제의 점수 증가
						score[j]++;
					}
				}
			}
			
			// 참가자마다 점수 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < T; j++) {
					if (check[i][j]) {
						people.get(i).total += score[j];
					}
				}
			}
			
			// 참가자들 순회하면서 지학이의 점수 계산
			int more_score = 0; // 자신보다 많은 점수 참가자 수
			int same_score_but_more_q = 0; // 자신과 같은 문제 수, but 푼 문제 수가 더 많음
			int same_score_same_q_but_less_num = 0;
			
			int jihak_total = people.get(P-1).total; // 지학이의 점수
			int jihak_q = people.get(P-1).num; // 지학이가 푼 문제 수
			
			for (int i = 0; i < N; i++) {
				if (N == P-1)
					continue;
				
				int other_total = people.get(i).total;
				int other_q = people.get(i).num;
				
				if (jihak_total < other_total) { // 지학이보다 점수가 큰 경우
					more_score++;
				} else if (jihak_total == other_total) { // 점수가 같은 경우
					if (jihak_q < other_q) {
						same_score_but_more_q++;
					} else if (jihak_q == other_q) { // 같은 수의 문제를 푼 경우
						if (P-1 > i) { // 번호가 더 작다면
							same_score_same_q_but_less_num++;
						}
					}
				}
				
			}
			
			int jihak_rank = more_score + same_score_but_more_q + same_score_same_q_but_less_num + 1;
		
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ').append(people.get(P-1).total).append(' ').append(jihak_rank);
			System.out.println(sb.toString());
		}
	}

	public static class Info {
		int total; // 총 점수
		int num; // 푼 문제 수

		public Info() {
			total = 0;
			num = 0;
		}
	}
}
