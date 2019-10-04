import java.util.Comparator;
import java.util.PriorityQueue;

public class 힙_라면공장 {
	public static void main(String[] args) {
		int stock = 4;
		int[] dates = {4, 10, 15};
		int[] supplies = {20, 5, 10};
		int k = 30;
		solution(stock, dates, supplies, k);
	}
	public static int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        int index = 0;
        for (int i = 0; i < k; i++) { // 날짜 기준으로 순회
			if (index < dates.length) {
				if (i == dates[index]) { // 날짜와 해외배송가능날짜가 같으면 우선순위큐에 담음
					priorityQueue.offer(supplies[index]); // 많은 양부터 저장
					index++;
				}
			}
			
			if (i == stock) { // 날짜와 가지고 있는 밀가루가 같으면 (동날 예정)
				stock += priorityQueue.remove(); // 가장 많은 양의 밀가루 저장
				answer++; // 해외에서 가져온 횟수 더해줌
			}
		}
        return answer;
    }
}
