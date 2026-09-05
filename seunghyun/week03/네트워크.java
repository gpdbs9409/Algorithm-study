/**
 * @풀이
 *
 * DFS
 *
 * 컴퓨터가 서로 연결되어 있는 경우 하나의 네트워크로 볼 수 있음
 *
 * 즉, 아직 방문하지 않은 컴퓨터를 발견하면, 해당 컴퓨터와 연결된 모든 컴퓨터를 DFS로 탐색!
 *
 * DFS 탐색이 한 번 끝날 때마다 하나의 네트워크를 찾은 것이므로 정답을 1 증가
 *
 *
 * @처리순서
 *
 * 1. 모든 컴퓨터를 순서대로 확인
 *
 * 2. 아직 방문하지 않은 컴퓨터라면
 *    새로운 네트워크를 발견한 것이므로 정답을 1 증가
 *
 * 3. 해당 컴퓨터에서 DFS 시작
 *
 * 4. 현재 컴퓨터와 연결되어 있고
 *    아직 방문하지 않은 컴퓨터를 찾아 DFS 호출
 *
 * 5. 연결된 모든 컴퓨터를 방문 처리
 *
 * 6. 모든 컴퓨터를 확인하면 최종 네트워크 개수를 반환
 *
 *
 * @시간복잡도
 *
 * O(N^2)
 *
 *
 * @공간복잡도
 *
 * O(N)
 *
 */

class Solution {

    // 네트워크의 개수
    int answer = 0;

    // 컴퓨터의 개수
    int n;

    public int solution(int n, int[][] computers) {

        this.n = n;

        // 방문 여부를 저장하는 배열
        boolean[] visited = new boolean[n];

        // 모든 컴퓨터 확인
        for (int i = 0; i < n; i++) {

            // 아직 방문하지 않은 컴퓨터라면
            if (!visited[i]) {

                // 새로운 네트워크 발견
                answer++;

                // 해당 컴퓨터와 연결된 모든 컴퓨터 탐색
                dfs(computers, visited, i);
            }
        }

        return answer;
    }

    // DFS
    public void dfs(int[][] computers, boolean[] visited, int current) {

        // 현재 컴퓨터 방문 처리
        visited[current] = true;

        // 모든 컴퓨터 확인
        for (int next = 0; next < n; next++) {

            // 현재 컴퓨터와 연결되어 있고
            // 아직 방문하지 않은 컴퓨터라면
            if (computers[current][next] == 1 && !visited[next]) {
                // 연결된 컴퓨터로 DFS
                dfs(computers, visited, next);
            }
        }
    }
}
