package week03;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 42861 [섬 연결하기]
 * @문제유형 탐욕법(Greedy)
 * @문제설명
 * n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 최소의 비용으로
 * 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.
 *
 * 다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다.
 * 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.
 *
 * @제한사항
 * 섬의 개수 n은 1 이상 100 이하입니다.
 * costs의 길이는 ((n-1) * n) / 2이하입니다.
 * 임의의 i에 대해, costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i] [2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용입니다.
 * 같은 연결은 두 번 주어지지 않습니다. 또한 순서가 바뀌더라도 같은 연결로 봅니다. 즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다.
 * 모든 섬 사이의 다리 건설 비용이 주어지지 않습니다. 이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
 * 연결할 수 없는 섬은 주어지지 않습니다.
 *
 * @TestCase(I/O)
 * | n | costs                                     | return |
 * | 4 | [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]] | 4      |
 */

/**
 * 조건1. n는 섬의 갯수 즉, 4개가 존재 개발자의 숫자 공식으로는 4개"[0, 1, 2, 3]"가 존재
 * 조건2. 최소 비용으로 모든 섬을 연결해야함 즉, 그림예시에 나와있는 모형으로 표현되는데
 *       가장 최소 길이로 모든섬을 연결할 수 있는 방법을 찾아야함.
 *   ex) 각 출발 노선에 따라 섬의 길이가 다름. (중복 노선은 생략 실제 코드에서는 중복노선을 파악 할수 없음)
 *       0번 출발 length - 8, 10, 11 ,14
 *       1번 출발 length - 11
 *       2번 출발 length - 4
 *       3번 출발 length - 11
 */

import java.util.*;
public class week3_04 {
    // 전역 변수
    int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;

        // [출발, 도착, 비용] 비용에 대한 오름차순 정렬
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] nodeA, int[] nodeB) {
                return Integer.compare(nodeA[2], nodeB[2]);
            }
        });

        // 섬의 연결하기 위한 데이터 셋팅
        parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }

        // 비용이 작은 다리부터 차례대로 출력하여 연결 검사 및 건설 비용 누적
        for(int j=0; j<costs.length; j++) {
            int start = costs[j][0], end = costs[j][1], cost = costs[j][2];

            if(union(start, end)) {
                answer += cost;
            }
        }
        return answer;
    }

    public boolean union(int start, int end) {
        int aRoot = find(start), bRoot = find(end);

        // 루트 값이 서로 다르다면 연결 완료
        if(aRoot != bRoot) {
            parent[bRoot] = aRoot;
            return true;
        }
        return false;
    }

    // 최종 루트의 섬을 찾기
    private int find(int island) {
        if (parent[island] == island) {
            return island;
        }
        return parent[island] = find(parent[island]);
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
        week3_04 sol = new week3_04();

        profileTestCase("1", "4", new Runnable() {
            @Override
            public void run() {
                int n = 4;
                int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
                System.out.println("실제 출력 결과: " + sol.solution(n, costs));
            }
        });

    }
}
