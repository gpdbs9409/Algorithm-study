package week04;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 169199 [리코쳇 로봇]
 * @문제유형 연습문제
 * @문제설명
 * 리코쳇 로봇이라는 보드게임이 있습니다.
 *
 * 이 보드게임은 격자모양 게임판 위에서 말을 움직이는 게임으로,
 * 시작 위치에서 출발한 뒤 목표 위치에 정확하게 멈추기 위해 최소 몇 번의 이동이 필요한지 말하는 게임입니다.
 *
 * 이 게임에서 말의 이동은 현재 위치에서 상, 하, 좌, 우 중 한 방향으로
 * 게임판 위의 장애물이나 게임판 가장자리까지 부딪힐 때까지 미끄러져 움직이는 것을 한 번의 이동으로 정의합니다.
 *
 * 다음은 보드게임판을 나타낸 예시입니다. ("."은 빈 공간을, "R"은 로봇의 처음 위치를, "D"는 장애물의 위치를, "G"는 목표지점을 나타냅니다.)
 *
 * ```
 *   ...D..R
 *   .D.G...
 *   ....D.D
 *   D....D.
 *   ..D....
 * ```
 *
 * 이때 최소 움직임은 7번이며 "R" 위치에서 아래, 왼쪽, 위, 왼쪽, 아래, 오른쪽, 위 순서로 움직이면 "G" 위치에 멈춰 설 수 있습니다.
 * 게임판의 상태를 나타내는 문자열 배열 board가 주어졌을 때, 말이 목표위치에 도달하는데 최소 몇 번 이동해야 하는지
 * return 하는 solution함수를 완성해주세요. 만약 목표위치에 도달할 수 없다면 -1을 return 해주세요.
 *
 * @제한사항
 * 3 ≤ board의 길이 ≤ 100
 *  3 ≤ board의 원소의 길이 ≤ 100
 *  board의 원소의 길이는 모두 동일합니다.
 *  문자열은 ".", "D", "R", "G"로만 구성되어 있으며 각각 빈 공간, 장애물, 로봇의 처음 위치, 목표 지점을 나타냅니다.
 *  "R"과 "G"는 한 번씩 등장합니다.
 *
 * @TestCase(I/O)
 * | board                                                   | result |
 * | ["...D..R", ".D.G...", "....D.D", "D....D.", "..D...."] | 7      |
 * | [".D.R", "....", ".G..", "...D"]                        | -1     |
 */

/**
 * 조건1. [미끄럼틀 타기] 로봇은 한 칸씩 가는 게 아니라,
 *                    장애물('D')이나 게임판 끝에 부딪힐 때까지 선택한 방향으로 계속 전진한다.
 *
 * 조건2. [도착지 검사] 미끄러져서 멈춘 그 자리가 목표 지점('G')인지 확인하고,
 *                  맞다면 지금까지 미끄러진 횟수(curr[2])를 즉시 반환한다.
 *
 * 조건3. [새로운 정거장 등록] 멈춘 자리가 처음 와본 곳이라면 방문 도장을 찍고,
 *                        다음번에 또 미끄러져 출발할 정거장으로 대기줄에 추가한다.
 */

import java.util.*;
public class week4_03 {
    public int solution(String[] board) {
        int n = board.length, m = board[0].length();
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (board[i].charAt(j) == 'R') {
                    q.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (board[curr[0]].charAt(curr[1]) == 'G') return curr[2];

            for (int[] d : dirs) {
                int nx = curr[0], ny = curr[1];

                while (nx + d[0] >= 0 && nx + d[0] < n && ny + d[1] >= 0 && ny + d[1] < m
                        && board[nx + d[0]].charAt(ny + d[1]) != 'D') {
                    nx += d[0]; ny += d[1];
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, curr[2] + 1});
                }
            }
        }
        return -1;
    }

    public static void profileTestCase(String testNumber, String expected, Runnable testAction) {
        long startTime = System.nanoTime();
        testAction.run();
        long endTime = System.nanoTime();

        long durationNano = endTime - startTime;
        double durationMilli = durationNano / 1000000.0;

        System.out.println("🎯 [테스트 " + testNumber + " 예상 결과]: " + expected);
        System.out.println("⏳ [처리 시간]: " + durationMilli + " ms");
        System.out.println("--------------------------------------------------\n");
    }

    public static void main(String[] args) {
        week4_03 sol = new week4_03();
        /* DFS 방식 */
        profileTestCase("1", "7", new Runnable() {
            @Override
            public void run() {
                String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
                System.out.println("DFS - 실제 출력 결과: " + sol.solution(board));
            }
        });

        profileTestCase("2", "-1", new Runnable() {
            @Override
            public void run() {
                String[] board = {".D.R", "....", ".G..", "...D"};
                System.out.println("DFS - 실제 출력 결과: " + sol.solution(board));
            }
        });
    }
}
