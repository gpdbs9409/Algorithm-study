package week03;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 86971 [전력망을 둘로 나누기]
 * @문제유형 완전탐색
 * @문제설명
 * n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다.
 * 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다.
 * 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.
 *
 * 송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다.
 * 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때,
 * 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.
 *
 * @제한사항
 * n은 2 이상 100 이하인 자연수입니다.
 * wires는 길이가 n-1인 정수형 2차원 배열입니다.
 * wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
 * 1 ≤ v1 < v2 ≤ n 입니다.
 * 전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.
 *
 * @TestCase(I/O)
 * | n | wires                                             | result |
 * | 9 | [[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]] | 3      |
 * | 4 | [[1,2],[2,3],[3,4]]                               | 0      |
 * | 7 | [[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]             | 1      |
 */

/**
 * [예제2 - 설명 내용이 짧기 때문]
 * 조건1. 최소값을 확인하기 위해서 처음 최소값을 Integer의 최대값으로 담기.
 * 조건2. 이차원배열이지만 사실상 1 - 2 - 3 - 4 형태로 나열되어있음.
 * 조건3. 1씩 움직일때 마다 전선을 끊어 전기를 공급을 균등하게 배열해야한다.
 * 조건4. 마지막 배열의 숫자는 앞에 전선을 끊어 전기 공급을 확인한다.
 *
 * 주의  '두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)'말이 없는 경우
 *      {-2, 0, 2}로 표출되지만, 저내용으로 인해서 음수를 정수로 변경하기 때문에 [2,0,2]로 표현된다.
 */

public class week3_03 {
    public int solution(int n, int[][] wires) {
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) {
            boolean[] visited = new boolean[n + 1];

            int team1Count = dfs(n, 1, visited, wires, i);
            int team2Count = n - team1Count;

            minDiff = Math.min(minDiff, Math.abs(team1Count - team2Count));
        }
        return minDiff;
    }

    public int dfs(int n, int node, boolean[] visited, int[][] wires, int skipIndex) {
        visited[node] = true;
        int count = 1;

        for (int i = 0; i < wires.length; i++) {
            if (i == skipIndex) continue;

            int v1 = wires[i][0];
            int v2 = wires[i][1];

            if (v1 == node && !visited[v2]) {
                count += dfs(n, v2, visited, wires, skipIndex);
            } else if (v2 == node && !visited[v1]) {
                count += dfs(n, v1, visited, wires, skipIndex);
            }
        }

        return count;
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
        week3_03 sol = new week3_03();

        profileTestCase("1", "3", new Runnable() {
            @Override
            public void run() {
                int n = 9;
                int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
                System.out.println("실제 출력 결과: " + sol.solution(n, wires));
            }
        });

        profileTestCase("2", "0", new Runnable() {
            @Override
            public void run() {
                int n = 4;
                int[][] wires = {{1,2},{2,3},{3,4}};
                System.out.println("실제 출력 결과: " + sol.solution(n, wires));
            }
        });

        profileTestCase("3", "1", new Runnable() {
            @Override
            public void run() {
                int n = 7;
                int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};
                System.out.println("실제 출력 결과: " + sol.solution(n, wires));
            }
        });

    }
}
