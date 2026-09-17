/**
 * @풀이
 *
 * DFS + 백트래킹
 *
 * 현재 피로도에서 입장할 수 있는 던전을 하나씩 선택하면서
 * 최대한 많은 던전을 탐험하는 경우를 찾음
 *
 * 던전을 탐험하면 피로도가 감소하고,
 * 해당 던전을 다시 탐험할 수 없도록 방문 처리
 *
 * 이후 DFS가 끝나면 방문 처리를 다시 해제하여
 * 다른 던전 선택 경우를 탐색
 *
 * 모든 경우를 탐색하면서 가장 많이 탐험한 던전 수를 반환
 *
 *
 * @처리순서
 *
 * 1. 현재 피로도가 던전의 최소 필요 피로도 이상인지 확인
 *
 * 2. 아직 탐험하지 않은 던전이라면 선택
 *
 * 3. 해당 던전을 방문 처리
 *
 * 4. 현재 피로도에서 소모 피로도를 빼고 DFS 실행
 *
 * 5. DFS가 끝나면 방문 처리를 해제
 *    -> 다른 경우의 수에서 다시 사용할 수 있도록 백트래킹
 *
 * 6. 모든 던전을 확인하면서 최대 탐험 개수를 갱신
 *
 * 7. 최대 탐험 개수 반환
 *
 *
 * @시간복잡도
 *
 * O(N!)
 *
 *
 * @공간복잡도
 *
 * O(N)
 *
 */

class Solution {

    // 탐험할 수 있는 던전의 최대 개수
    int answer = 0;

    // 던전 방문 여부
    boolean[] visited;

    public int solution(int k, int[][] dungeons) {

        // 던전 개수만큼 방문 배열 생성
        visited = new boolean[dungeons.length];

        // DFS 시작
        dfs(k, 0, dungeons);

        return answer;
    }

    // DFS
    public void dfs(int fatigue, int count, int[][] dungeons) {

        // 현재까지 탐험한 던전 개수로 최대값 갱신
        answer = Math.max(answer, count);

        // 모든 던전을 확인
        for (int i = 0; i < dungeons.length; i++) {

            // 이미 탐험한 던전이면 제외
            if (visited[i]) {
                continue;
            }

            // 현재 피로도가 최소 필요 피로도보다 작은 경우
            // 해당 던전은 탐험할 수 없음
            if (fatigue < dungeons[i][0]) {
                continue;
            }

            // 던전 방문 처리
            visited[i] = true;

            // 던전 탐험
            // 현재 피로도에서 소모 피로도를 빼줌
            dfs(fatigue - dungeons[i][1], count + 1, dungeons);

            // 백트래킹
            // 다른 경우의 수에서 다시 사용할 수 있도록
            // 방문 상태를 원래대로 되돌림
            visited[i] = false;
        }
    }
}
