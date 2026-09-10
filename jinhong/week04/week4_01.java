package week04;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 1844 [게임 맵 최단거리]
 * @문제유형 깊이/너비 우선 탐색(DFS/BFS)
 * @문제설명
 * ROR 게임은 두 팀으로 나누어서 진행하며, 상대 팀 진영을 먼저 파괴하면 이기는 게임입니다. 따라서, 각 팀은 상대 팀 진영에 최대한 빨리 도착하는 것이 유리합니다.
 *
 * 지금부터 당신은 한 팀의 팀원이 되어 게임을 진행하려고 합니다. 다음은 5 x 5 크기의 맵에,
 * 당신의 캐릭터가 (행: 1, 열: 1) 위치에 있고, 상대 팀 진영은 (행: 5, 열: 5) 위치에 있는 경우의 예시입니다.
 *
 * 위 그림에서 검은색 부분은 벽으로 막혀있어 갈 수 없는 길이며, 흰색 부분은 갈 수 있는 길입니다.
 * 캐릭터가 움직일 때는 동, 서, 남, 북 방향으로 한 칸씩 이동하며, 게임 맵을 벗어난 길은 갈 수 없습니다.
 * 아래 예시는 캐릭터가 상대 팀 진영으로 가는 두 가지 방법을 나타내고 있습니다.
 *
 * - 첫 번째 방법은 11개의 칸을 지나서 상대 팀 진영에 도착했습니다.
 * - 두 번째 방법은 15개의 칸을 지나서 상대팀 진영에 도착했습니다.
 *
 * 위 예시에서는 첫 번째 방법보다 더 빠르게 상대팀 진영에 도착하는 방법은 없으므로,
 * 이 방법이 상대 팀 진영으로 가는 가장 빠른 방법입니다.
 *
 * 만약, 상대 팀이 자신의 팀 진영 주위에 벽을 세워두었다면 상대 팀 진영에 도착하지 못할 수도 있습니다.
 * 예를 들어, 다음과 같은 경우에 당신의 캐릭터는 상대 팀 진영에 도착할 수 없습니다.
 *
 * 게임 맵의 상태 maps가 매개변수로 주어질 때, 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의
 * 최솟값을 return 하도록 solution 함수를 완성해주세요. 단, 상대 팀 진영에 도착할 수 없을 때는 -1을 return 해주세요.
 *
 * @제한사항
 * - maps는 n x m 크기의 게임 맵의 상태가 들어있는 2차원 배열로, n과 m은 각각 1 이상 100 이하의 자연수입니다.
 *  * n과 m은 서로 같을 수도, 다를 수도 있지만, n과 m이 모두 1인 경우는 입력으로 주어지지 않습니다.
 * - maps는 0과 1로만 이루어져 있으며, 0은 벽이 있는 자리, 1은 벽이 없는 자리를 나타냅니다.
 * - 처음에 캐릭터는 게임 맵의 좌측 상단인 (1, 1) 위치에 있으며, 상대방 진영은 게임 맵의 우측 하단인 (n, m) 위치에 있습니다.
 *
 * @TestCase(I/O)
 * | maps                                                          | answer  |
 * | [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]] | 11      |
 * | [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]] | -1      |
 */

import week03.week3_01;

/**
 * 조건1. [지도 파악 ] 맵의 크기(세로 n, 가로 m)를 확인하고,
 *                  주인공이 지나갈 수 있는 상하좌우 방향과 탐험 대기줄을 생성한다.
 *
 * 조건2. [길 확장하기] 대기줄에서 현재 위치를 꺼내 상하좌우로 한 칸씩 가보면서,
 *                  아직 안 가본 길(1)이 있으면 발걸음 수(기존 거리 + 1)를 적어 Queue에 넣어둔다.
 *
 * 조건3. [도착지 확인] 모든 길을 다 탐험했을 때,
 *                  미로의 맨 오른쪽 아래 끝 방(목적지)에 적힌 숫자가 1보다 큰지 확인하여 탈출 성공 여부를 체크
 *
 * 조건4. [결과 발표] 탈출에 성공했다면 누적된 최단 발걸음 수를 돌려주고,
 *                 벽에 가로막혀 갈 수 없다면 실패의 의미로 -1값을 반환하자
 */

import java.util.*;

public class week4_01 {
    public int solution(int[][] maps) {
        int n = maps.length, m = maps[0].length;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int[] d : dirs) {
                int nx = curr[0] + d[0];
                int ny = curr[1] + d[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1) {
                    maps[nx][ny] = maps[curr[0]][curr[1]] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return maps[n - 1][m - 1] > 1 ? maps[n - 1][m - 1] : -1;
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
        week4_01 sol = new week4_01();
        /* DFS 방식 */
        profileTestCase("1", "11", new Runnable() {
            @Override
            public void run() {
                int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
                System.out.println("DFS - 실제 출력 결과: " + sol.solution(maps));
            }
        });

        profileTestCase("2", "-1", new Runnable() {
            @Override
            public void run() {
                int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
                System.out.println("DFS - 실제 출력 결과: " + sol.solution(maps));
            }
        });
    }
}
