import java.util.PriorityQueue;

public class 힙_더맵게 {
	public int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
		for(int aScoville : scoville) {
			heap.offer(aScoville); // 스코빌지수 삽입 (오름차순)
		}
		
		while(heap.peek() <= K) { // K 보다 작을 때 계산
			if (heap.size() == 1) { // 더 이상 만들 수 없는 상태라면 -1 반환
				return -1;
			}
			
			int a = heap.poll();
			int b = heap.poll();			
			int result = a + (b*2);
			
			heap.offer(result);
			answer++; // 횟수 증가
		}

		return answer;
	}
}
