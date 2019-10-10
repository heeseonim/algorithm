import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private class node {
		String str;
		int level;

		public node(String str, int level) {
			this.str = str;
			this.level = level;
		}
	}
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
		Queue<node> q = new LinkedList<node>();
		boolean[] visited = new boolean[words.length];
		q.add(new node(begin, 0));

		while (!q.isEmpty()) {
			node n = q.poll();
			
			if (n.str.equals(target)) {
				answer = n.level;
				break;
			}
			
			for (int i = 0; i < words.length; i++) {
				if (!visited[i] && checkOne(n.str, words[i])) {
					visited[i] = true;
					q.add(new node(words[i], n.level + 1));
				}
			}
		}

		return answer;
    }
    
    private boolean checkOne(String s1, String s2) {
		int len = s1.length();
		int cnt = 0;
		for (int i = 0; i < len; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (++cnt > 1) {
					return false;
				}
			}
		}
		return true;
	}
}
