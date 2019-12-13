import java.util.Arrays;

public class Solution_42746 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 6, 10, 2 }));
	}

	public static String solution(int[] numbers) {
		// 스트링 배열 생성
		String[] s = new String[numbers.length];
		
		// 숫자를 스트링으로 바꿔 저장
		for (int i = 0; i < numbers.length; i++) {
			s[i] = String.valueOf(numbers[i]);
		}

		// 두개씩 붙여 보면서 내림차순으로 정렬
		Arrays.sort(s, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
		
		// 앞자리가 0일 경우 0을 반환
		if (s[0].charAt(0) == '0') return "0";
		
		// 정렬된 스트링을 붙여서 반환
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length; i++) {
			sb.append(s[i]);
		}
		
		return sb.toString();
	}
}
