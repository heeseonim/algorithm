import java.util.*;

public class 힙_더맵게_2 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 1, 2, 3, 9, 10, 12 }, 7));
	}

	public static int solution(int[] scoville, int K) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int s : scoville) {
			pq.add(s);
		}

		int answer = 0;
		while (true) {
			if (pq.peek() >= K) break; // 가장 낮은 스코빌 지수가 K 이상이라면
			if (pq.size() <= 1) return -1; // K 이상이 되지 못했는데 더이상 계산을 진행할 수 없으면
			
			int first = pq.poll();
			int second = pq.poll();
			pq.add(first + (second * 2));
			answer++;
		}

		return answer;
	}
}
