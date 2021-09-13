import java.io.*;
import java.util.*;

public class PriorityQueue_1655_가운데를말해요 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 1. 2개의 PQ 선언
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			// 2. 크기가 같은 경우 maxHeap 에 값을 추가
			// 3. 크기가 다른 경우 minHeap 에 값을 추가
			if (maxHeap.size() == minHeap.size())
				maxHeap.offer(num);
			else
				minHeap.offer(num);
			// 4. maxHeap 의 top(최댓값)이 minHeap 의 top(최솟값)보다 크다면 swap
			if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				if (maxHeap.peek() > minHeap.peek()) {
					int temp = maxHeap.poll();
					maxHeap.offer(minHeap.poll());
					minHeap.offer(temp);
				}
			}
			// 5. maxHeap 의 top 이 중간값
			sb.append(maxHeap.peek()).append('\n');
		}
		System.out.println(sb);
	}
}
