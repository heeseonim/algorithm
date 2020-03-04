import java.util.Scanner;

public class 햄스터 {
	static int[] cage; // 가능한 모든 햄스터 배치를 해볼 (중복순열)
	static int N, X, M;
	static int[][] input;
	static int max; // 햄스터 배치 여러가지 종류 가능하다면? 합이 최대가 되는 경우를 선택
	static String ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			max = -1;
			ans = "";

			N = sc.nextInt(); // 총 우리 갯수
			X = sc.nextInt(); // 각 우리 가능 마릿수
			M = sc.nextInt(); // 체크 횟수

			cage = new int[N + 1]; // 케이지 번호를 1 부터 N까지 인덱스로 사용 예정
			input = new int[M][3]; // left, right, sum

			for (int i = 0; i < M; i++) {
				input[i][0] = sc.nextInt(); // left
				input[i][1] = sc.nextInt(); // right
				input[i][2] = sc.nextInt(); // sum
			}
			perm(1, 0); // 1번 케이지부터 가능한 모든 마릿수 채워서
			System.out.println("#" + tc + " " + ((max == -1) ? max : ans));
		}
	}

	static void perm(int idx, int sum) { // idx 는 햄스터 케이지 번호
		if (idx == cage.length) { // 경근이의 체크와 부합하는지?
			if (check()) { // 만족한다?
				if (max < sum) { // 등호 없는 조건은 ? 똑같은 합계가 나온 사전 순 나중에 나온 애는 무시~
					max = sum; // 오 합계가 크다? 그러면 지금 케이지 순서 일단 저장하자~
					ans = makeAns();
				}
			}
			return;
		}

		for (int i = 0; i <= X; i++) { // 알아서 0부터 채우기 때문에 다음 경우의 수는 점점 커짐!
			cage[idx] = i;
			perm(idx + 1, sum + i);
		}
	}

	static String makeAns() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) { // 1번 케이지부터 ~ N번 케이지까지
			sb.append(cage[i]).append(' ');
		}
		return sb.toString();
	}

	static boolean check() {
		for (int i = 0; i < M; i++) { // M 번의 체크 다 되나?
			int tmp = 0;
			for (int j = input[i][0]; j <= input[i][1]; j++) { // j => left ~ right 까지 돌면서 누적
				tmp += cage[j];
			}
			if (tmp != input[i][2]) // 경근이가 센 거랑 달라?
				return false;
		}
		return true;
	}
}
