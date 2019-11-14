package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 완전탐색, 그리디, 시뮬레이션
public class Solution_3501_순환소수짧게표현하기_t {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken()); // 분자
			int q = Integer.parseInt(st.nextToken()); // 분모

			sb.append('#').append(testCase).append(' ');
			int 몫 = p / q;
			int 나머지 = p % q;

			// 나누어 떨어지면 소수가 아니니까, 출력하고 다음 testCase로 넘어가기
			if (나머지 == 0) {
				sb.append(sb.append(몫).append('\n'));
				continue;
			}

			// 나눠=어 떨어지지 않으면 소수가 이쓴거니까 소수점 . 추가
			sb.append(몫).append('.');
			// 순환하는 소수 찾기
//			HashMap<Integer, Integer> hrm = new HashMap<>(); // 키 나머지값 : value, index
			int[] rest = new int[q]; // q로나눈 나머지의 개수
			
			for (int i = sb.length(); 나머지 > 0; i++) {
				if (rest[나머지] != 0) {
					sb.insert(rest[나머지], '('); // 시작괄호 삽입
					sb.append(')'); // 끝나는 괄호 붙임
					break;
				}
				rest[나머지] = i;
				나머지 *= 10;
				몫 = 나머지 / q;
				나머지 = 나머지 % q;
				sb.append(몫);
			}
			sb.append('\n');
		} // end of TC
		System.out.println(sb);

	}
}
