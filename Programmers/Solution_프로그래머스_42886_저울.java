import java.util.*;

public class Solution_42886 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 3, 1, 6, 2, 7, 30, 1 }));
	}

	public static int solution(int[] weight) {
		Arrays.sort(weight); // 추의 무게 배열 정렬
		int answer = 1; // 추의 가장 작은 값
		for (int i = 0; i < weight.length; i++) {
			if (answer >= weight[i]) // 추가 최솟값보다 무겁지 않으면
				answer += weight[i]; // 추의 무게를 더해줌
			else
				break; // 현재 최솟값보다 큰 추를 발견하면 멈춤
		}
		return answer;
	}
}
