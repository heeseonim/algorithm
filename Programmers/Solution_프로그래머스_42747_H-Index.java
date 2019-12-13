import java.util.Arrays;

public class Solution_42747 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 3, 0, 6, 1, 5 }));
	}

	public static int solution(int[] citations) {
		Arrays.sort(citations);
		// [0, 1, 3, 5, 6]
		// 0회 이상 인용 논문 수 = 5개
		// 1회 이상 인용 논문 수 = 4개
		// 3회 이상 인용 논문 수 = 3개
		// 5회 이상 인용 논문 수 = 2개
		// 6회 이상 인용 논문 수 = 1개
		// a회 이상 인용 논문 수 = b개
		
		// h = a, b 중 작은 값
		// h-index는 h 중 최대값
		// 두 값의 최소값의 변화가 증가하다가 감소하는 지점
		
		int[] h = new int[citations.length];
		for (int i = 0; i < citations.length; i++) {
			h[i] = Math.min(citations[i], citations.length-i);
		}
		Arrays.sort(h);
		
		return h[h.length-1];
	}
}
