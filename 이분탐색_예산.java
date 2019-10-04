public class 이분탐색_예산 {
	public static void main(String[] args) {
		int[] budgets = { 120, 110, 140, 150 };
		int M = 485;
		System.out.println(solution(budgets, M));
	}

	public static int solution(int[] budgets, int M) {
		int answer = 0;
		int left = 0;
		int right = 0;
		for (int i = 0; i < budgets.length; i++) {
			right = Math.max(right, budgets[i]);
		}
		while (right >= left) {
			int mid = (left + right) / 2; // 중간값을 상한액으로 지정
			int result = 0;
			for (int i = 0; i < budgets.length; i++) {
				if (mid > budgets[i]) { // 예산이 상한액보다 적으면
					result += budgets[i]; // 예산 그대로 더함
				} else { // 예산이 상한액보다 크면
					result += mid; // 상한액을 더함
				}
			}
			if (result > M) { // 총 합이 M보다 클 때
				right = mid - 1; // 작은 쪽에서 다시 탐색
			} else { // 총 합이 M보다 작을 때
				answer = mid; // 상한액을 정답으로 저장
				left = mid + 1; // 큰 쪽에서 다시 탐색
			}
		}
		return answer;
	}
}
