class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
		for (int i = 0; i < skill_trees.length; i++) {
			int count = 0;
			boolean flag = true;
			char[] s = skill_trees[i].toCharArray();
			for (int j = 0; j < s.length; j++) {
				if(count < skill.indexOf(s[j])) {
					flag = false;
					break;
				} else if (count == skill.indexOf(s[j])) {
					count++;
				}
			}
			
			if (flag) {
				answer++;
			}
		}
		return answer;
    }
}
