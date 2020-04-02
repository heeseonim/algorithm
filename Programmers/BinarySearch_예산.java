/**
 * 
 * @author heeseonim
 * 
 *         1. 예산요청을 심사하여 예산을 분배 2. 예산의 총액은 정해져 있음 3. 가능한 한 최대의 총 예산을 배정
 * 
 *         1. 모든 요청 가능할 경우 요청 금액 그대로 배정 2. 가능하지 않을 경우 상한액 계산 후 이상에는 상한액, 이하에는
 *         요청금액 배정
 *
 */
public class BinarySearch_예산 {
	public int solution(int[] budgets, int M) {
		return binarySearch(budgets, M);
	}

	public int binarySearch(int[] budgets, int M) {
		long sum = 0;
		long max = Long.MIN_VALUE;

		for (int budget : budgets) {
			sum += budget;
			max = max < budget ? budget : max;
		}

		// 요청한 금액으로 예산 배정이 가능하다면 최댓값 return
		if (sum <= M)
			return (int) max;

		long ans = 0;
		long left = 0, right = max, mid = 0;
		
		while (left <= right) {
			sum = 0;
			mid = (left + right) / 2;

			for (int budget : budgets) {
				if (mid < budget) { // 예산이 상한액보다 크면 상한액을 더함
					sum += mid;
				} else {
					sum += budget;
				}
			}

			if (sum <= M) {
				ans = ans < mid ? mid : ans;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return (int) ans;
	}
}
