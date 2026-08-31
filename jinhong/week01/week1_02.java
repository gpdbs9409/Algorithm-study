package week01;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 178870
 * @문제설명
 * 비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
 * 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다.
 * 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다.
 * 부분 수열의 합은 k입니다.
 * 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다.
 * 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾습니다.
 * 수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때, 위 조건을 만족하는
 * 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return 하는 solution 함수를 완성해주세요. 이때 수열의 인덱스는 0부터 시작합니다.
 *
 * @제한사항
 * 5 ≤ sequence의 길이 ≤ 1,000,000
 * 1 ≤ sequence의 원소 ≤ 1,000
 * sequence는 비내림차순으로 정렬되어 있습니다.
 * 5 ≤ k ≤ 1,000,000,000
 * k는 항상 sequence의 부분 수열로 만들 수 있는 값입니다.
 *
 * @TestCase(I/O)
 * | sequence	           | k	| result
 * | [1, 2, 3, 4, 5]	   | 7	| [2, 3]
 * | [1, 1, 1, 2, 3, 4, 5] | 5	| [6, 6]
 * | [2, 2, 2, 2, 2]	   | 6	| [0, 2]
 */

import java.util.Arrays;

/**
 * 조건1. K 값에 해당 값을 배열에 담아 출력한다.
 *.     * 결국 answer의 값은 2개(시작점, 종료지점)로 처리된다.
 * 조건2. K 값이 동일 하다면 길이(Index)가 더 짧은 값을 정답으로 처리한다.
 */

class week1_02 {

    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        /*
         * int cost = 999999999;  <- 사용가능
         * int cost = 9999999999; <- 사용자의 실수로 인한 발생하는 error원인 : Integer Overflow
         * 위와 같은 방법을 쓰지 않는 이유 : 데이터의 범위가 어디까지 들어오지 모르는 상태에서 길이를 높게 잡으면,
         *                            시작점과 종료지점에 해당 하지 않아 비교가 되지 않기 때문에 사용을 지향한다.
         *                            따라서 아래와 같이 Integer Overflow 방지를 위해 선언하는 것이 좋다.
         */
        int cost = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for(int r = 0; r < sequence.length; r++) {
            sum += sequence[r];

            while(sum > k && left <= r ) {
                sum -= sequence[left];
                left++;
            }

            if(sum == k) {
                int currentCost = r - left;
                if(currentCost < cost) {
                    cost = currentCost;
                    answer[0] = left;
                    answer[1] = r;
                }
            }
        }
        return answer;
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
        week1_02 sol = new week1_02();

        profileTestCase("1", "[2, 3]", new Runnable() {
            @Override
            public void run() {
                int[] seq = {1, 2, 3, 4, 5};
                int k = 7;
                System.out.println("실제 출력 결과 : " + sol.solution(seq, k));
            }
        });

        profileTestCase("2", "[2, 3]", new Runnable() {
            @Override
            public void run() {
                int[] seq = {1, 1, 1, 2, 3, 4, 5};
                int k = 5;
                System.out.println("실제 출력 결과 : " + sol.solution(seq, k));
            }
        });

        profileTestCase("3", "[0, 2]", new Runnable() {
            @Override
            public void run() {
                int[] seq = {2, 2, 2, 2, 2};
                int k = 6;
                System.out.println("실제 출력 결과 : " + sol.solution(seq, k));
            }
        });
    }
}