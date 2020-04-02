import java.util.*;

public class Solution_42586 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 93, 30, 55 }, new int[] { 1, 30, 5 })));
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 0; i < progresses.length; i++) {
			int mok = (100 - progresses[i]) / speeds[i];
			int nmg = (100 - progresses[i]) % speeds[i];
			
			if (nmg > 0)
				mok++;
			
			queue.add(mok);
		}
		
		List<Integer> list = new ArrayList<>();
		int max = queue.poll();
		int count = 1;
		
		while (!queue.isEmpty()) {
			Integer q = queue.poll();
			if (max >= q)
				count++;
			else {
				list.add(count);
				max = q;
				count = 1;
			}
			
			if (queue.isEmpty())
				list.add(count);
		}
		
		int[] answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}		
		
		return answer;
	}
}
