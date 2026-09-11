package week04;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 159993 [미로 탈출]
 * @문제유형 연습문제
 * @문제설명
 * 1 x 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 합니다. 각 칸은 통로 또는 벽으로 구성되어 있으며,
 * 벽으로 된 칸은 지나갈 수 없고 통로로 된 칸으로만 이동할 수 있습니다. 통로들 중 한 칸에는 미로를 빠져나가는 문이 있는데,
 * 이 문은 레버를 당겨서만 열 수 있습니다. 레버 또한 통로들 중 한 칸에 있습니다. 따라서,
 * 출발 지점에서 먼저 레버가 있는 칸으로 이동하여 레버를 당긴 후 미로를 빠져나가는 문이 있는 칸으로 이동하면 됩니다.
 *
 * 이때 아직 레버를 당기지 않았더라도 출구가 있는 칸을 지나갈 수 있습니다.
 * 미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때, 최대한 빠르게 미로를 빠져나가는데 걸리는 시간을 구하려 합니다.
 *
 * 미로를 나타낸 문자열 배열 maps가 매개변수로 주어질 때, 미로를 탈출하는데 필요한 최소 시간을 return 하는 solution 함수를 완성해주세요.
 * 만약, 탈출할 수 없다면 -1을 return 해주세요.
 *
 * @제한사항
 * 5 ≤ maps의 길이 ≤ 100
 *  5 ≤ maps[i]의 길이 ≤ 100
 *  maps[i]는 다음 5개의 문자들로만 이루어져 있습니다.
 *         S : 시작 지점
 *         E : 출구
 *         L : 레버
 *         O : 통로
 *         X : 벽
 *  시작 지점과 출구, 레버는 항상 다른 곳에 존재하며 한 개씩만 존재합니다.
 *  출구는 레버가 당겨지지 않아도 지나갈 수 있으며, 모든 통로, 출구, 레버, 시작점은 여러 번 지나갈 수 있습니다.
 *
 * @TestCase(I/O)
 * | maps                                      | result |
 * | ["SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"] | 16     |
 * | ["LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"] | -1     |
 */

/**
 * 조건1. [길 탐험과 발자국 메모] 각 단계마다 탐험 대기줄과 밟았던 땅 표시판을 새로 만들고,
 *                          한 칸씩 움직일 때마다 걸음수(curr[2] + 1)를 누적한다.
 *
 * 조건2. [중간 점검 및 조기 탈락] 만약 레버나 출구로 가는 길이
 *                           벽('X')에 막혀서 목적지에 도달하지 못하면(-1), 즉시 게임 오버 코드를 던진다.
 *
 * 조건3. [최종 점수 합산] 1단계 걸음수와 2단계 걸음수를 모두 더한 최종 합계 도출하여 반환한다.
 */

import java.util.*;

public class week4_02 {
    public int solution(String[] maps) {
        int n = maps.length, m = maps[0].length(), totalDist = 0;
        int[][] positions = new int[3][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = maps[i].charAt(j);
                if (c == 'S') positions[0] = new int[]{i, j};
                else if (c == 'L') positions[1] = new int[]{i, j};
                else if (c == 'E') positions[2] = new int[]{i, j};
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int step = 0; step < 2; step++) {
            int[] start = positions[step];
            int[] target = positions[step + 1];

            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[n][m];

            q.add(new int[]{start[0], start[1], 0});
            visited[start[0]][start[1]] = true;

            int subDist = -1;
            while (!q.isEmpty()) {
                int[] curr = q.poll();

                if (curr[0] == target[0] && curr[1] == target[1]) {
                    subDist = curr[2];
                    break;
                }

                for (int[] d : dirs) {
                    int nx = curr[0] + d[0], ny = curr[1] + d[1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx].charAt(ny) != 'X' && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, curr[2] + 1});
                    }
                }
            }

            if (subDist == -1) return -1;
            totalDist += subDist;
        }

        return totalDist;
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
        week4_02 sol = new week4_02();
        /* DFS 방식 */
        profileTestCase("1", "16", new Runnable() {
            @Override
            public void run() {
                String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
                System.out.println("DFS - 실제 출력 결과: " + sol.solution(maps));
            }
        });

        profileTestCase("2", "-1", new Runnable() {
            @Override
            public void run() {
                String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
                System.out.println("DFS - 실제 출력 결과: " + sol.solution(maps));
            }
        });
    }
}
