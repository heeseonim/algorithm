package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_4261_빠른휴대전화키패드 {
	public static String[] alpha = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
	public static int[] p = new int[26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < alpha.length; i++) {
			for (int j = 0; j < alpha[i].length(); j++) {
				p[alpha[i].charAt(j) - 97] = i + 2;
			}
		}

		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			String com = st.nextToken(); // 주어지는 문자열
			int num = Integer.parseInt(st.nextToken()); // 사전의 길이
			String[] dictionary = new String[num]; // 사전 배열
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < num; i++) {
				dictionary[i] = st.nextToken();
			}

			boolean[] visited = new boolean[num];

			for (int i = 0; i < com.length(); i++) { // 문자열
				int cur = com.charAt(i) - '0';
				for (int j = 0; j < num; j++) {
					if (visited[j] || i >= dictionary[j].length())
						continue;
					if (p[dictionary[j].charAt(i) - 97] != cur)
						visited[j] = true;

				}
			}

			int answer = 0;
			for (int i = 0; i < num; i++)
				if (!visited[i])
					answer++;

			sb.append('#').append(tc).append(' ').append(answer);
			System.out.println(sb);

		}

	}

}
