/**
 * @풀이
 *
 * BFS
 *
 * 미로에서 한 칸 이동할 때마다 거리가 1씩 증가하므로
 * BFS를 이용하면 두 위치 사이의 최단거리를 구할 수 있음
 *
 * 이 문제에서는 시작점(S)에서 레버(L)를 먼저 찾아야 하고,
 * 레버를 당긴 후 출구(E)로 이동해야 함
 *
 * 따라서 BFS를 2번 수행
 *
 * 1. S → L 최단거리
 * 2. L → E 최단거리
 *
 * 두 거리를 더하면 S → L → E의 최단거리가 됨
 *
 * 만약 S에서 L에 도달할 수 없거나
 * L에서 E에 도달할 수 없다면
 * 탈출할 수 없는 경우이므로 -1 반환
 *
 *
 * @처리순서
 *
 * 1. 미로에서 시작점(S), 레버(L)의 위치를 찾음
 *
 * 2. 시작점(S)에서 레버(L)까지 BFS 수행
 *
 * 3. 레버(L)에 도달하지 못했다면 -1 반환
 *
 * 4. 레버(L)에서 출구(E)까지 BFS 수행
 *
 * 5. 출구(E)에 도달하지 못했다면 -1 반환
 *
 * 6. S → L 거리와 L → E 거리를 더해서 반환
 *
 *
 * @시간복잡도
 *
 * O(N × M)
 *
 *
 * @공간복잡도
 *
 * O(N × M)
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

    public int solution(String[] maps) {

        n = maps.length;
        m = maps[0].length();

        // 시작점(S)에서 레버(L)까지 최단거리
        int startToLever = bfs(maps, 'S', 'L');

        // 레버까지 도달하지 못했다면 탈출 불가능
        if (startToLever == -1) {
            return -1;
        }

        // 레버(L)에서 출구(E)까지 최단거리
        int leverToExit = bfs(maps, 'L', 'E');

        // 출구까지 도달하지 못했다면 탈출 불가능
        if (leverToExit == -1) {
            return -1;
        }

        // S → L → E 최단거리
        return startToLever + leverToExit;
    }

    // 시작점에서 목적지까지의 최단거리를 구하는 BFS
    public int bfs(String[] maps, char start, char target) {

        Queue<int[]> queue = new LinkedList<>();

        // 각 위치까지의 최단 거리를 저장
        int[][] distance = new int[n][m];

        // 시작점 찾기
        int startR = 0;
        int startC = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {

                if (maps[r].charAt(c) == start) {
                    startR = r;
                    startC = c;
                }
            }
        }

        // 시작점 삽입
        queue.offer(new int[]{startR, startC});

        // 시작점 방문 처리
        distance[startR][startC] = 1;

        while (!queue.isEmpty()) {

            // 현재 위치
            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            // 현재 위치가 목적지라면
            // 해당 위치까지의 거리를 반환
            if (maps[r].charAt(c) == target) {
                return distance[r][c] - 1;
            }

            // 상, 하, 좌, 우 확인
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // 맵의 범위를 벗어난 경우
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                // 벽인 경우
                if (maps[nr].charAt(nc) == 'X') {
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

        // 목적지에 도달하지 못한 경우
        return -1;
    }
}
