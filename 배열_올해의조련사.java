import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_5672 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			char[] bird = new char[N];
			for (int i = 0; i < N; i++) {
				bird[i] = br.readLine().charAt(0);
			}

			sb.append('#').append(tc).append(' ');

			int fidx = 0;
			int ridx = bird.length - 1;

			while (fidx <= ridx) {
				// 배열 조회는 처음에만 수행
				char front = bird[fidx];
				char rear = bird[ridx];

				if (fidx == ridx) { // 하나만 남은 경우
					sb.append(front);
					break;
				}

				if (front < rear) {
					sb.append(front);
					fidx++;
				} else if (front > rear) {
					sb.append(rear);
					ridx--;
				} else if (front == rear) { // 같을 경우 그 다음 깊이의 경우 탐색
					// 인덱스 초기값 저장
					int first = fidx;
					int end = ridx;
					
					while (true) {
						if (first >= end) {
							sb.append(bird[first]);
							fidx++;
							break;
						}
						// 알파벳이 같지 않을 경우
						if (bird[first] != bird[end]) {
							if (bird[first] < bird[end]) {
								sb.append(bird[fidx++]);
							} else {
								sb.append(bird[ridx--]);
							}
							break;
						}
						// break 되지 않았다면 다음 경우 탐색 위해 값 변경
						first++;
						end--;
					}
				}
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
