import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author heeseonim
 * 
 *         1. 출발지점으로부터 distance 떨어진 곳에 도착점 2. 그 사이에는 바위가 놓여있음 3. 바위 중 몇 개 제거하려고
 *         함
 * 
 *         1. 0, 바위, distance 각 사이의 거리 2. 거리의 최솟값 중에 가장 큰 값을 return
 *
 */
public class BinarySearch_징검다리 {
	public int solution(int distance, int[] rocks, int n) {
		Arrays.sort(rocks); // 바위 위치 정렬
		return binarySearch(distance, rocks, n);
	}

	public int binarySearch(int distance, int[] rocks, int n) {
		long ans = 0;
		long left = 1, right = distance, mid = 0;

		while (left <= right) {
			int cnt = 0;
			int prev = 0;
			mid = (left + right) / 2;

			for (int i = 0; i < rocks.length; i++)
				if (rocks[i] - prev < mid)
					// mid 보다 작은 값이 존재하면 해당 바위 제거
					cnt++;
				else
					// mid 보다 크거나 같은 값이 존재하면 prev 를 현재 바위로 초기화
					prev = rocks[i];

			if (distance - prev < mid) // 마지막 바위와 도착점 사이의 거리 확인
				cnt++;

			// 제거할 바위 수 체크해서 범위 재설정
			if (cnt <= n) {
				ans = ans < mid ? mid : ans;
				left = mid + 1;
			} else
				right = mid - 1;
		}

		return (int) ans;
	}
}
