import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_프로그래머스_42628_이중우선순위큐 {
	public static void main(String[] args) {
		String[] operations = { "I 7", "I 5", "I -5", "D -1" };
		System.out.println(Arrays.toString(solution(operations)));
	}

	public static int[] solution(String[] operations) {
		int[] answer = {0, 0};
		// 오름차순 우선순위 큐
		PriorityQueue<Integer> q = new PriorityQueue<>();
		// 내림차순 우선순위 큐
		PriorityQueue<Integer> qReverse = new PriorityQueue<>(Comparator.reverseOrder());
		int max = 0, min = 0;
		for (int i = 0; i < operations.length; i++) {
			String[] token = operations[i].split(" ");
			if (token[0].equals("I")) {
				q.add(Integer.parseInt(token[1]));
				qReverse.add(Integer.parseInt(token[1]));
			} else { // D
				if (!q.isEmpty()) {
					if (token[1].equals("1")) { // 최댓값 제거
						max = qReverse.peek();
						q.remove(max);
						qReverse.remove(max);
					} else { // -1, 최솟값 제거
						min = q.peek();
						q.remove(min);
						qReverse.remove(min);
					}
				}
			}
		}
		
		if(!q.isEmpty()) {
			answer[0] = qReverse.peek();
			answer[1] = q.peek();
		}

		return answer;
	}
}
