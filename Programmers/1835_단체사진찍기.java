import java.util.HashMap;

public class Solution_1835 {
	public void main(String[] args) {
		System.out.println(solution(2, new String[] { "N~F=0", "R~T>2" }));
	}

	public boolean[] selected;
	public int[] perm;
	public int count;
	public HashMap<Character, Integer> hm;

	public int solution(int n, String[] data) {
		hm = new HashMap<Character, Integer>();
		hm.put('A', 0);
		hm.put('C', 1);
		hm.put('F', 2);
		hm.put('J', 3);
		hm.put('M', 4);
		hm.put('N', 5);
		hm.put('R', 6);
		hm.put('T', 7);

		selected = new boolean[8];
		perm = new int[8];
		count = 0;
		dfs(0, data);

		return count;
	}

	public void dfs(int pos, String[] data) {
		if (pos == 8) {
			for (int i = 0; i < data.length; i++) {
				String s = data[i];
				int a = perm[hm.get(s.charAt(0))];
				int b = perm[hm.get(s.charAt(2))];
				char com = s.charAt(3);
				int num = s.charAt(4) - '0';
				switch (com) {
				case '>':
					if (Math.abs(a - b) - 1 <= num)
						return;
					break;
				case '<':
					if (Math.abs(a - b) - 1 >= num)
						return;
					break;
				case '=':
					if (Math.abs(a - b) - 1 != num)
						return;
					break;
				}
			}
			count++;
			return;
		}

		for (int i = 0; i < 8; i++) {
			if (!selected[i]) {
				selected[i] = true;
				perm[pos] = i;
				dfs(pos + 1, data);
				selected[i] = false;
			}
		}
	}
}
