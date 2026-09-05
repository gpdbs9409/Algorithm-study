/**
 * @풀이
 *
 * DFS
 *
 * 전력망은 트리 구조로 이루어져 있기 때문에 전선 하나를 끊으면 반드시 두 개의 전력망으로 나누어짐
 *
 * 1. 모든 전선을 하나씩 끊어보고
 * 2. 전선을 끊은 상태에서 한쪽 전력망에 포함된 송전탑의 개수를 DFS로 확인
 * 3. 전체 송전탑의 개수가 N이므로 나머지 전력망의 송전탑 개수는 N - count
 * 4. 두 전력망의 송전탑 개수 차이를 구하고 그중 최솟값을 정답으로 반환
 *
 *
 * @처리순서
 *
 * 1. 전력망을 연결하는 모든 전선을 하나씩 확인
 *
 * 2. 현재 전선을 끊었다고 가정
 *
 * 3. 끊은 전선을 제외하고 DFS를 수행
 *
 * 4. DFS를 통해 한쪽 전력망에 포함된 송전탑의 개수를 확인
 *
 * 5. 다른 전력망의 송전탑 개수는 n - count로 계산
 *
 * 6. 두 전력망의 송전탑 개수 차이를 계산
 *
 * 7. 지금까지 구한 차이 중 최솟값을 정답에 저장
 *
 * 8. 모든 전선을 확인하면 최솟값을 반환
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

    // 송전탑의 개수
    int n;

    // 전력망을 끊었을 때
    // 한쪽 전력망의 송전탑 개수를 확인하기 위한 DFS
    public int dfs(int current, int[][] wires, boolean[] visited, int exceptWire) {

        // 현재 송전탑 방문 처리
        visited[current] = true;

        // 현재 전력망에 포함된 송전탑 개수
        int count = 1;

        // 모든 전선을 확인
        for (int i = 0; i < wires.length; i++) {

            // 현재 끊은 전선은 제외
            if (i == exceptWire) {
                continue;
            }

            int from = wires[i][0];
            int to = wires[i][1];

            // 현재 송전탑에서 연결된 송전탑 찾기
            int next = -1;

            if (from == current) {
                next = to;
            } else if (to == current) {
                next = from;
            }

            // 현재 송전탑과 연결되어 있고
            // 아직 방문하지 않은 송전탑이라면
            if (next != -1 && !visited[next]) {
                // 연결된 송전탑까지 DFS
                count += dfs(next, wires, visited, exceptWire);
            }
        }

        return count;
    }

    public int solution(int n, int[][] wires) {

        this.n = n;

        // 두 전력망의 송전탑 개수 차이 중 최솟값
        int answer = Integer.MAX_VALUE;

        // 모든 전선을 하나씩 끊어본다.
        for (int i = 0; i < wires.length; i++) {

            // 방문 여부를 저장하는 배열
            boolean[] visited = new boolean[n + 1];

            // i번째 전선을 끊었을 때
            // 한쪽 전력망의 송전탑 개수
            int count = dfs(1, wires, visited, i);

            // 나머지 전력망의 송전탑 개수
            int other = n - count;

            // 두 전력망의 송전탑 개수 차이
            int difference = Math.abs(count - other);

            // 최솟값 갱신
            answer = Math.min(answer, difference);
        }

        return answer;
    }
}
