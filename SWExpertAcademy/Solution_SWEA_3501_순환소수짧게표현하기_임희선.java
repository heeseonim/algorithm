package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_3501_순환소수짧게표현하기_임희선 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			int[] result = new int[100001]; // 몫을 저장할 배열, '.'을 함께 저장하기 위해 char 배열로 생성
			int[] nmg = new int[100001];

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ');

			int cur = p; // 나눌 값을 저장
			int r = 0;
			int start = 0;
			boolean flag = false;

			while (cur != 0) { // 나눌 값이 0이 될 때까지 반복
				if (r == 0) { // 첫 시작이라면
					sb.append(cur / q); // 소숫점 이전의 정수값 저장
					if (cur % q != 0) {// 나머지가 존재한다면
						sb.append('.');
						r++;
					}
				} else
					result[r++] = cur / q; // 몫을 저장

				cur %= q; // 나머지로 다시 연산
				if (nmg[cur] != 0) { // 해당 나머지로 연산을 했었다면
					flag = true;
					start = nmg[cur]; // 시작할 인덱스를 저장
				}

				if (flag) // 순환인 것을 발견했다면
					break;
				else
					nmg[cur] = r; // 현재의 인덱스 값 저장

				cur *= 10;
			}

			if (flag) {
				for (int i = 1; i < r; i++) { // 증가한 인덱스 값까지만 순회
					if (i == start) { // 순환소수 시작
						sb.append('(');
						for (int j = start; j < r; j++) {
							sb.append(result[j]);
						}
						sb.append(')');
						break;
					} else {
						sb.append(result[i]);
					}
				}
			} else {
				for (int i = 1; i < r; i++) {
					sb.append(result[i]);
				}
			}
			System.out.println(sb);

		}

	} // end of main

} // end of class
