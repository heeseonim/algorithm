import java.util.LinkedList;
import java.util.Queue;

public class Solution_42583 {
	public static void main(String[] args) {
		System.out.println(solution(100, 100, new int[] { 10,10,10,10,10,10,10,10,10,10 }));
	}

	public static class truck {
		int weight;
		int distance;

		public truck(int weight, int distance) {
			this.weight = weight;
			this.distance = distance;
		}
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {

		Queue<truck> bridge = new LinkedList<>(); // 다리 큐
		int time = 0;
		int index = 0;

		while (true) {
			time++;

			if (!bridge.isEmpty() && bridge.peek().distance < 0) {
				bridge.poll();
			}


			if (index < truck_weights.length) {
				int w = 0;
				if (!bridge.isEmpty()) {
					for (truck t : bridge) {
						w += t.weight;
					}
				}
				
				if (w + truck_weights[index] <= weight) {
					bridge.add(new truck(truck_weights[index], bridge_length-1));
					index++;
				}
			}

			if (bridge.isEmpty())
				break;

			for (truck t : bridge) {
				t.distance--;
			}
		}

		return time;
	}
}
