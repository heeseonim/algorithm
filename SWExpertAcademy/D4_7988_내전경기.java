import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 내전경기 {
	static int K; // 시너지 효과의 갯수
	static HashMap<String, Integer> player; // 이름, 그 사람의 번호
	static boolean[][] map; // 두 정점 사이의 시너지가 있다 없다를 판단하는 용도의 인접행렬
	static int[] teamInfo; // 각 정점이 지금 어느 팀에 있는지를 기록할 배열
	static boolean ans; // 된다 안된다를 기록할 변수
	static int pcnt; // 총 인원이 문제에 입력으로 안들어옴. 세야됨.

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			K = sc.nextInt();

			map = new boolean[2 * K][2 * K];

			player = new HashMap<>();
			pcnt = 0; // 플레이어 번호 0부터 붙이자!
			for (int i = 0; i < K; i++) {
				String name1 = sc.next();
				String name2 = sc.next();

				if (!player.containsKey(name1)) {// 기존에 등장했던 애가 아니라면 ? 뉴페이스! 번호붙이자
					player.put(name1, pcnt++);
				}

				if (!player.containsKey(name2)) {// 기존에 등장했던 애가 아니라면 ? 뉴페이스! 번호붙이자
					player.put(name2, pcnt++);
				}

				map[player.get(name1)][player.get(name2)] = true;
				map[player.get(name2)][player.get(name1)] = true;
			}
			teamInfo = new int[pcnt]; // 각각이 임의의 팀에 소속될거임. 1번팀 또는 2번팀. 아직 안정해졌으면 0번인 상태

			ans = true;
			for (int i = 0; i < pcnt; i++) { // 모든 플레이어에 대해서 시너지가 상태편으로 갔는지 봅시다
				if (teamInfo[i] == 0) {// 아직 팀결정 안된 애는? 시너지 효과를 처음으로 따지게 되는 애네! 아무 팀이나 넣자~
					teamInfo[i] = 1;
					bfs(i);
				}

				if (!ans)
					break;
			}

			System.out.println("#" + tc + " " + (ans ? "Yes" : "No"));
		}
	}

	static void bfs(int idx) { // 출발하는 정점 시너지를 찢기 시작하자 !
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(idx);

		while (!queue.isEmpty()) {
			int me = queue.poll(); // 나를 기준으로
			for (int i = 0; i < pcnt; i++) { // 모든 애들을 검사해서 시너지 있는 애들 저쪽으로 보내자!
				if (map[me][i]) { // 나랑 시너지 있는 i다!
					if (teamInfo[i] != 0 && teamInfo[i] == teamInfo[me]) { // 팀이 결정된 애네? 근데 나랑 이미 같은 팀?
						ans = false;
						return;
					}

					if (teamInfo[i] == 0) { // 나랑 시너지 있는 앤데 팀이 미정이네? 절로 가~
						teamInfo[i] = teamInfo[me] == 1 ? 2 : 1;
						queue.add(i); // 자 그럼 쟤랑 시너지 있는 애를 또 반대편으로 밀자~
					}
				}
			}
		}
	}
}
