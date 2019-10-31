public class Solution_3074_입국심사 {
	public static void main(String[] args) {
		int n = 1;
		int[] times = {2, 2};
		System.out.println(solution(n, times));
	}
	public static long solution(int n, int[] times) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < times.length; i++) {
			max = Math.max(max, times[i]);
		}
        long left = 0;
		long right = (long) max * (long) n; // 최악의 시간 저장
		long answer = Long.MAX_VALUE;
			while (left <= right) { // 이분탐색
				long mid = (left + right) / 2;
				long result = 0; // 처리한 사람 수
				for (int i = 0; i < times.length; i++) {
					result += mid / times[i];
				}
				if (result < n) { // 처리된 사람의 수가 적으면 - 시간이 더 필요함
					left = mid + 1; // 시간 늘려서 탐색
				} else { // 처리된 사람의 수가 같거나 많으면 - 시간을 줄여도 됨, 최소시간 찾기
					answer = Math.min(answer, mid); // 최소 시간 저장
					right = mid - 1; // 시간 줄여서 탐색
				}
			}
        return answer;
    }
}
