import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_SWEA_7675_통역사성경이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");
			int[] count = new int[N]; // 문장 수만큼 카운트 배열 생성
			int cnt = 0;

			// String 배열의 단어 하나씩 검사
			for (int i = 0; i < str.length; i++) {
				int len = str[i].length(); // 해당 단어의 길이
				char first = str[i].charAt(0);
				char end = str[i].charAt(len - 1); // 단어의 마지막 문자
				boolean flag = true;

				if (Character.isUpperCase(first)) { // 첫글자가 대문자라면
					if (len == 1) {
						// 첫글자가 대문자이고, 단어의 길이가 1이라면
						count[cnt]++; // 이름의 수 증가
						continue; // 다음 단어로 넘어감
					}

					// 마지막 문자 전까지 있는 문자들이 소문자인지 검색
					for (int j = 1; j < str[i].length() - 1; j++) {
						char alpha = str[i].charAt(j);
						if (!Character.isLowerCase(alpha)) { // 소문자가 아니라면
							flag = false;
							break;
						}
					}
					
					if (!flag) { // 사이에 소문자 아니면 마지막 문자 체크
						if (end == '.' || end == '?' || end == '!') {
							cnt++;
						}
						continue; // 다음 단어로 넘어감
					}

					// 마지막 문자 체크
					if (Character.isLowerCase(end)) { // 마지막 문자 소문자
						count[cnt]++; // 해당 문장의 이름의 수 증가
					} else if (end == '.' || end == '?' || end == '!') { // 마지막 문자 구두점
						count[cnt]++; // 해당 문장의 이름의 수 증가
						cnt++; // 문장의 끝이므로 다음 문장으로 넘어감
					}
				} else { // 첫글자가 대문자 아니라면 마지막 문자만 체크
					if (end == '.' || end == '?' || end == '!') {
						cnt++;
					}
				}
			}

			System.out.print("#" + tc + " ");
			for (int c : count) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
}
