import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author heeseonim
 *
 *         1. 주어진 항공권을 모두 이용하여 여행경로를 짬 2. 항상 ICN 공항에서 출발 3. 항공권 정보가 담긴 2차원 배열
 *         tickets 4. 방문하는 공향 경로를 배열에 담아 return
 * 
 *         1. 모든 공항은 알파벳 대문자 3글자 2. 주어진 공항수 3개 이상, 10,000개 이하 3. [a, b] a 공항에서 b
 *         공항으로 가는 항공권 4. 주어진 항공권 모두 사용해야 함 5. 가능한 경로 2개 이상일 경우, 알파벳 순서 앞서는 경로를
 *         return
 * 
 */
public class DFS_여행경로 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[][] { { "ICN", "SFO" }, { "ICN", "ATL" },
				{ "SFO", "ATL" }, { "ATL", "ICN" }, { "ATL", "SFO" } })));
	}

	public static String[] answer;

	public static String[] solution(String[][] tickets) {
		boolean[] visited = new boolean[tickets.length];
		List<String> temp = new ArrayList<>();
		answer = new String[tickets.length + 1];
		
		// 알파벳순으로 출력되기 위해 티켓을 먼저 정렬
		Arrays.sort(tickets, (o1, o2) ->  {
			if (o1[0].equals(o2[0])) {
				return o1[1].compareTo(o2[1]);
			} else {
				return o1[0].compareTo(o2[0]);
			}
		});
		
		dfs("ICN", tickets, visited, temp, 0);
		return answer;
	}

	// 경로가 이어지는지 확인
	public static boolean dfs(String from, String[][] tickets, boolean[] visited, List<String> temp, int cnt) {
		temp.add(from);

		if (cnt == tickets.length) {
			int index = 0;
			for (String t : temp) {
				answer[index++] = t;
			}

			return true;
		}

		for (int i = 0; i < tickets.length; i++) {
			if (tickets[i][0].equals(from) && !visited[i]) {
				visited[i] = true;

				if (dfs(tickets[i][1], tickets, visited, temp, cnt + 1))
					return true;

				visited[i] = false;
			}
		}
		
		temp.remove(temp.size()-1);
		return false;
	}
}
