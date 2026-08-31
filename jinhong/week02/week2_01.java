package week02;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 42862
 * @문제설명
 * 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다. 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
 * 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
 * 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.
 * 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
 *
 * 전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost,
 * 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
 * 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.
 *
 * @제한사항
 * 전체 학생의 수는 2명 이상 30명 이하입니다.
 * 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
 * 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
 *
 * @TestCase(I/O)
 * | n  | lost  | reserve   | return |
 * | ------------------------------- |
 * | 5	| [2, 4]| [1, 3, 5]	| 5      |
 * | 5	| [2, 4]| [3]       | 4      |
 * | 3	| [3]   | [1]       | 2      |
 */

/**
 * 조건1. 잃어버린 사람은 빌려주는 사람의 옆자리에 존재 하는 경우만 여분의 체육복을 받을 수 있음
 *       예제 2) n = 5 / lost = [2,4] / reserve = [3]
 *           5명 중 3명은 체육복을 가지고 있으나 여벌을 가지고 있는 3번은 다른 사람에게 빌려줄 수 있음
 *           3번은 양옆의 2번과 4번중 한명에게 빌려줄 수 있음
 *           따라서, 최대 입을 수 있는 사람은 4명이다.
 *       예제 3) n = 3 / lost = [3] / reserve = [1]
 *           1번 사람은 2번만 체육복을 빌려줄 수 있음 여벌이 있음에도 불구하고
 *           3번은 빌려줄 수 없음.
 *           따라서, 최대 입을 수 있는 사람은 1명
 */


import java.util.*;

class week2_01 {

    public int solution(int n, int[] lost, int[] reserve) {
        HashSet<Integer> lostSet = new HashSet<>();
        HashSet<Integer> reserveSet = new HashSet<>();

        for (int l : lost) lostSet.add(l);
        for (int r : reserve) reserveSet.add(r);

        HashSet<Integer> intersection = new HashSet<>(lostSet);
        intersection.retainAll(reserveSet);

        // 빌려야하는 사람과 빌려주는 사람을 제외한 나머지 인원 삭제
        // 해당 그래디를 진행하기 위해서 필요 없는 사람을 지워야 확인이 가능
        lostSet.removeAll(intersection);
        reserveSet.removeAll(intersection);

        int answer = n - lostSet.size();

        for (int r : reserveSet) {
            // 왼쪽 사람을 먼저 전달, 전달할 사람이 없다면 오른쪽 사람에게 전달
            if (lostSet.contains(r - 1)) {
                lostSet.remove(r - 1);
                answer++;
            } else if (lostSet.contains(r + 1)) {
                lostSet.remove(r + 1);
                answer++;
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
        week2_01 sol = new week2_01();

        profileTestCase("1", "5", new Runnable() {
            @Override
            public void run() {
                int n = 5;
                int[] lost = {2, 4};
                int[] reserve = {1, 3, 5};
                System.out.println("실제 출력 결과 : " + sol.solution(n, lost, reserve));
            }
        });

        profileTestCase("2", "4", new Runnable() {
            @Override
            public void run() {
                int n = 5;
                int[] lost = {2, 4};
                int[] reserve = {3};
                System.out.println("실제 출력 결과 : " + sol.solution(n, lost, reserve));
            }
        });

        profileTestCase("3", "2", new Runnable() {
            @Override
            public void run() {
                int n = 3;
                int[] lost = {3};
                int[] reserve = {1};
                System.out.println("실제 출력 결과 : " + sol.solution(n, lost, reserve));
            }
        });
    }
}