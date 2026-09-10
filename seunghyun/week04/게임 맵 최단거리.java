/**
 * @풀이
 *
 * BFS
 *
 * 게임 맵에서 한 칸 이동할 때마다 거리가 1씩 증가하므로
 * BFS를 이용하면 목적지까지의 최단거리를 구할 수 있음
 *
 * 시작점에서 가까운 칸부터 순서대로 탐색하고,
 * 아직 방문하지 않은 칸에 현재 칸까지의 거리 + 1을 저장
 *
 * BFS 탐색 중 목적지에 도달하면 해당 거리가 최단거리
 *
 * 만약 BFS가 끝났는데 목적지에 도달하지 못했다면
 * 이동할 수 없는 경우이므로 -1 반환
 *
 *
 * @처리순서
 *
 * 1. 시작점 (0, 0)을 큐에 삽입하고 방문 처리
 *
 * 2. 큐에서 현재 위치를 꺼냄
 *
 * 3. 현재 위치에서 이동할 수 있는 상, 하, 좌, 우 4방향을 확인
 *
 * 4. 맵의 범위를 벗어나거나 벽(0)이거나 이미 방문한 위치라면 제외
 *
 * 5. 이동할 수 있는 위치라면 현재 위치의 거리 + 1을 저장하고 큐에 삽입
 *
 * 6. 큐가 빌 때까지 반복
 *
 * 7. 목적지에 저장된 거리를 반환
 *
 * 8. 목적지에 도달하지 못했다면 -1 반환
 *
 *
 * @시간복잡도
 *
 * O(N × M)
 *
 * 모든 칸을 최대 한 번씩 방문
 *
 *
 * @공간복잡도
 *
 * O(N × M)
 *
 * 방문 거리 배열과 BFS 큐에 최대 모든 칸이 들어갈 수 있음
 *
 */

import java.util.*;

class Solution {

    // 맵의 행, 열
    int n;
    int m;

    // 상, 하, 좌, 우
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maps) {

        n = maps.length;
        m = maps[0].length;

        // 각 위치까지의 최단 거리를 저장
        int[][] distance = new int[n][m];

        // BFS 시작
        bfs(maps, distance);

        // 목적지까지 도달하지 못했다면 -1
        if (distance[n - 1][m - 1] == 0) {
            return -1;
        }

        return distance[n - 1][m - 1];
    }

    // BFS
    public void bfs(int[][] maps, int[][] distance) {

        Queue<int[]> queue = new LinkedList<>();

        // 시작점 삽입
        queue.offer(new int[]{0, 0});

        // 시작점 방문 처리
        distance[0][0] = 1;

        while (!queue.isEmpty()) {

            // 현재 위치
            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            // 상, 하, 좌, 우 확인
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // 맵의 범위를 벗어난 경우
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                // 벽인 경우
                if (maps[nr][nc] == 0) {
                    continue;
                }

                // 이미 방문한 경우
                if (distance[nr][nc] != 0) {
                    continue;
                }

                // 현재 위치까지의 거리 + 1
                distance[nr][nc] = distance[r][c] + 1;

                // 다음 탐색 대상으로 추가
                queue.offer(new int[]{nr, nc});
            }
        }
    }
}
