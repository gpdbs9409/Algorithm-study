/**
 * @풀이
 *
 * BFS
 *
 * 리코쳇 로봇은 한 번 이동을 시작하면
 * 장애물이나 게임판의 끝에 부딪힐 때까지
 * 한 방향으로 계속 이동함
 *
 * 따라서 한 칸씩 이동하는 것이 아니라
 * 한 번의 이동을 하나의 간선으로 보고 BFS를 수행
 *
 * 현재 위치에서 상, 하, 좌, 우로 이동하면서
 * 벽이나 게임판의 끝에 도달할 때까지 이동
 *
 * 이동을 멈춘 위치가 다음 탐색 위치가 됨
 *
 * 모든 이동의 횟수가 1이므로
 * BFS를 이용하면 목표 위치까지의 최단 이동 횟수를 구할 수 있음
 *
 *
 * @처리순서
 *
 * 1. 시작점(R)의 위치를 찾음
 *
 * 2. 시작점을 큐에 삽입하고 방문 처리
 *
 * 3. 큐에서 현재 위치를 꺼냄
 *
 * 4. 현재 위치에서 상, 하, 좌, 우로 이동
 *
 * 5. 벽(D)이나 게임판의 끝을 만날 때까지 계속 이동
 *
 * 6. 이동을 멈춘 위치가 아직 방문하지 않은 위치라면
 *    현재 위치까지의 이동 횟수 + 1을 저장하고 큐에 삽입
 *
 * 7. 큐에서 목표 위치(G)를 꺼내면
 *    해당 위치까지의 이동 횟수가 최단거리
 *
 * 8. BFS가 끝났는데 목표 위치에 도달하지 못했다면 -1 반환
 *
 *
 * @시간복잡도
 *
 * O(N × M × max(N, M))
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

    public int solution(String[] board) {

        n = board.length;
        m = board[0].length();

        // 시작점(R) 찾기
        int startR = 0;
        int startC = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {

                if (board[r].charAt(c) == 'R') {
                    startR = r;
                    startC = c;
                }
            }
        }

        // BFS
        return bfs(board, startR, startC);
    }

    // BFS
    public int bfs(String[] board, int startR, int startC) {

        Queue<int[]> queue = new LinkedList<>();

        // 각 위치까지의 이동 횟수를 저장
        int[][] distance = new int[n][m];

        // 시작점 삽입
        queue.offer(new int[]{startR, startC});

        // 시작점 방문 처리
        distance[startR][startC] = 1;

        while (!queue.isEmpty()) {

            // 현재 위치
            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            // 목표 지점(G)에 도착했다면
            // 실제 이동 횟수를 반환
            if (board[r].charAt(c) == 'G') {
                return distance[r][c] - 1;
            }

            // 상, 하, 좌, 우 확인
            for (int d = 0; d < 4; d++) {

                int nr = r;
                int nc = c;

                // 벽이나 끝을 만날 때까지 이동
                while (true) {

                    int nextR = nr + dr[d];
                    int nextC = nc + dc[d];

                    // 게임판의 끝에 도달한 경우
                    if (nextR < 0 || nextR >= n ||
                        nextC < 0 || nextC >= m) {
                        break;
                    }

                    // 장애물(D)을 만난 경우
                    if (board[nextR].charAt(nextC) == 'D') {
                        break;
                    }

                    // 한 칸 이동
                    nr = nextR;
                    nc = nextC;
                }

                // 움직였지만 같은 위치에서 멈춘 경우
                if (nr == r && nc == c) {
                    continue;
                }

                // 이미 방문한 위치인 경우
                if (distance[nr][nc] != 0) {
                    continue;
                }

                // 현재 위치까지의 이동 횟수 + 1
                distance[nr][nc] = distance[r][c] + 1;

                // 다음 탐색 대상으로 추가
                queue.offer(new int[]{nr, nc});
            }
        }

        // 목표 위치에 도달하지 못한 경우
        return -1;
    }
}
