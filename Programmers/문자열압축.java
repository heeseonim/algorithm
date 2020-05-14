
public class 문자열압축 {
	public static void main(String[] args) {
		문자열압축 문 = new 문자열압축();
		System.out.println(문.solution("abcabcdede"));
	}

	public int solution(String s) {
		int answer = s.length();

		for (int i = 1; i <= s.length() / 2; i++) {
			StringBuilder sb = new StringBuilder();
			String cur = s.substring(0, i);
			int cnt = 1;
			int index = -1;

			for (int j = i; j < s.length(); j += i) {

				if ((j + i) > s.length()) {// 단위가 범위를 넘어가면 찾기 종료
					index = j;
					break;
				}

				String next = s.substring(j, j + i);

				if (cur.equals(next))
					cnt++;
				else {
					if (cnt == 1)
						sb.append(cur);
					else
						sb.append(cnt).append(cur);

					cur = next;
					cnt = 1;
				}
			}

			// 뒤에 남은 것들 처리 작업
			if (cnt == 1)
				sb.append(cur);
			else
				sb.append(cnt).append(cur);
			
			if (index != -1) {
				sb.append(s.substring(index, s.length()));
			}

			System.out.println(sb.toString());
			answer = answer > sb.length() ? sb.length() : answer;
		}

		return answer;
	}
}
