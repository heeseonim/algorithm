
public class N_Queen {
	public static void main(String[] args) {
		N_Queen N = new N_Queen();
		System.out.println(N.solution(4));
	}

	int[] arr;
	int N, answer;

	public int solution(int n) {
		arr = new int[n];
		this.N = n;

		answer = 0;
		nqueen(0);
		return answer;
	}

	public void nqueen(int level) {
		if (level == N) {
			answer++;
			return;
		} else {
			for (int i = 0; i < N; i++) {
				arr[level] = i;
				if (isPossible(level))
					nqueen(level + 1);
			}
		}
	}

	public boolean isPossible(int level) {
		for (int i = 0; i < level; i++)
			if (arr[i] == arr[level] || Math.abs(level - i) == Math.abs(arr[level] - arr[i]))
				return false;

		return true;
	}
}
