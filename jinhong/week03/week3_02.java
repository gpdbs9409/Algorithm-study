package week03;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 43162 [네트워크]
 * @문제유형 깊이/너비 우선 탐색(DFS/BFS)
 * @문제설명
 * 네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다.
 * 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때
 * 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다.
 * 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.
 *
 * 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때,
 * 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.
 *
 * @제한사항
 * 컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
 * 각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
 * i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
 * computer[i][i]는 항상 1입니다.
 *
 * @TestCase(I/O)
 * | n | computers                         | return |
 * | 3 | [[1, 1, 0], [1, 1, 0], [0, 0, 1]] | 2      |
 * | 3 | [[1, 1, 0], [1, 1, 1], [0, 1, 1]] | 1      |
 */

/**
 * 조건1. 연결된 컴퓨터를 확인하여
 * 조건2. 기존에 연결된 컴퓨터가 있다면 중복으로 체크하지 않고 스킵한다.
 */

public class week3_02 {
    public int solution(int n, int[][] computers) {
        int count = 0;
        boolean[] visited = new boolean[n];

        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                dfs(computers, visited, n, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int[][] computers, boolean[] visited, int n, int node) {
        visited[node] = true;

        for (int j=0; j<n; j++) {
            if (computers[node][j] == 1 && !visited[j]) {
                dfs(computers, visited, n, j);
            }
        }
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
        week3_02 sol = new week3_02();

        profileTestCase("1", "2", new Runnable() {
            @Override
            public void run() {
                int n = 3;
                int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
                System.out.println("실제 출력 결과: " + sol.solution(n, computers));
            }
        });

        profileTestCase("2", "1", new Runnable() {
            @Override
            public void run() {
                int n = 3;
                int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
                System.out.println("실제 출력 결과: " + sol.solution(n, computers));
            }
        });

    }
}
