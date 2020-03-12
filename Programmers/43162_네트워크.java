class Solution_프로그래머스_43162_네트워크 {
    private boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
			if(!visited[i]) { // 방문처리가 되어있지 않으면
				answer++; // 네트워크 수에 더해줌
				dfs(i, computers); // 연결되어있는 지점을 방문처리 해주기 위해 dfs 탐색
			}
		}        
        return answer;
    }
    
    private void dfs(int index, int[][] computers) {
        visited[index] = true; // 방문처리
		for (int i = 0; i < computers.length; i++) { //해당 지점과 연결되어있는 곳 체크
			if(!visited[i] && computers[index][i] == 1) { // 해당 지점을 방문하지 않았고, 연결이 되어 있다면
				dfs(i, computers); // 방문처리해주고, 연결되어있는 곳을 또 탐색하기 위해 재귀호출
			}
		}
    }
}
