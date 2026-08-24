package week02;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @프로그래머스 42885
 * @문제설명
 * 무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다. 구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게 제한도 있습니다.
 * 예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 구명보트의 무게 제한이 100kg이라면 2번째 사람과 4번째 사람은 같이 탈 수 있지만
 * 1번째 사람과 3번째 사람의 무게의 합은 150kg이므로 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.
 *
 * 구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.
 * 사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때,
 * 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.
 *
 * @제한사항
 * 무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
 * 각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
 * 구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
 * 구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.
 *
 * @TestCase(I/O)
 * | people	          | limit   | return |
 * | ----------------------------------- |
 * | [70, 50, 80, 50] | 100     | 3      |
 * | [70, 80, 50]     | 100	    | 3      |
 */

/**
 * 조건1. 100kg이 넘지 않는 선에서 필요한 최소값의 구명보트 개수를 구하기
 * 조건2. 총합이 100kg이 넘지 않아야하며, 혼자 탑승 혹은 2명만 탑승을 할 수 있음
 *       보트를 타고 간 인원은 다시한번 재 탑승 불가
 *       예제1) 70, 50, 80, 50
 *            1번 보트 : 80 + 70 = 150 [X] / 80 + 50 = 130 [X] / 80 = 80 [0]
 *            2번 보트 : 70 + 50 = 120 [X] / 70 = 70 [0]
 *            3번 보트 : 50 + 50 = 100 [0]
 *         따라서, 최소값의 보트 탑승 가능 횟수는 3개
 */

import java.util.*;

class week2_02 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int answer = 0, lightIdx = 0;
        for(int i = people.length - 1; i >= lightIdx ; i--) {
            if(people[i] + people[lightIdx] <= limit) {
                lightIdx++;
            }
            // 조건에 부합하더라도 1인승도 1보트로 취급
            answer++;
        }

        return answer;
    }

    public static void profileTestCase(String testNumber, String expected, Runnable testAction) {
        long startTime = System.nanoTime();
        testAction.run(); // 넘겨받은 익명 클래스의 run() 메서드를 여기서 실행!
        long endTime = System.nanoTime();

        long durationNano = endTime - startTime;
        double durationMilli = durationNano / 1000000.0;

        System.out.println("🎯 [테스트 " + testNumber + " 예상 결과]: " + expected);
        System.out.println("⏳ [처리 시간]: " + durationMilli + " ms");
        System.out.println("--------------------------------------------------\n");
    }
    public static void main(String[] args) {
        week2_02 sol = new week2_02();
        profileTestCase("1", "3", new Runnable() {
            @Override
            public void run() {
                int[] people = {70, 50, 80, 50};
                int limit = 100;
                System.out.println("실제 출력 결과 : " + sol.solution(people, limit));
            }
        });

        profileTestCase("1", "3", new Runnable() {
            @Override
            public void run() {
                int[] people = {70, 80, 50};
                int limit = 100;
                System.out.println("실제 출력 결과 : " + sol.solution(people, limit));
            }
        });
    }
}