package week01;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 12909
 * @문제설명
 * 괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다. 예를 들어
 *
 * "()()" 또는 "(())()" 는 올바른 괄호입니다.
 * ")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.
 * '(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때, 문자열 s가 올바른 괄호이면 true를 return 하고, 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.
 *
 * 제한사항
 * 문자열 s의 길이 : 100,000 이하의 자연수
 * 문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.
 *
 * @TestCase(I/O)
 * | s         | answer  |
 * | ------------------- |
 * | "()()"	   | true    |
 * | "(())()"  | true    |
 * | ")()("	   | false   |
 * | "(()("	   | false   |
 */

/**
 * 조건1. "()"로 올바른 괄호 처리가 되어야함.
 *      * '(' 또는 ')' 로만 이루어진 문자열로만 주어짐
 *      1) 올바른 괄호 처리인 경우 true
 *      2) 잘못된 괄호 처리인 경우 false
 * 조건2. 괄호의 갯수가 맞더라도 순서가 잘못 되었을 경우에는 false 처리
 */

import java.util.Stack;
class week1_01 {

    boolean solution1(String s) {
        boolean answer = false;

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char dif = s.charAt(i);

            if ('(' == dif) {
                st.push(dif);
            } else if (')' == dif) {
                if (st.isEmpty()) return answer;
                st.pop();
            }
        }
        return st.isEmpty();
    }

    boolean solution2(String s) {
        int count = 0;

        for (char dif : s.toCharArray()) {
            if (dif == '(') {
                count++;
            } else if (dif == ')') {
                if (count == 0) return false;
                count--;
            }
        }

        return count == 0;
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
        week1_01 sol = new week1_01();

        profileTestCase("1-1", "true", new Runnable() {
            @Override
            public void run() {
                String testCase = "(())()";
                System.out.println("실제 출력 결과 : " + sol.solution1(testCase));
            }
        });

        profileTestCase("1-2", "false", new Runnable() {
            @Override
            public void run() {
                String testCase = "(()(";
                System.out.println("실제 출력 결과 : " + sol.solution1(testCase));
            }
        });

        profileTestCase("2-1", "true", new Runnable() {
            @Override
            public void run() {
                String testCase = "(())()";
                System.out.println("실제 출력 결과 : " + sol.solution2(testCase));
            }
        });

        profileTestCase("2-2", "false", new Runnable() {
            @Override
            public void run() {
                String testCase = "(()(";
                System.out.println("실제 출력 결과 : " + sol.solution2(testCase));
            }
        });

    }
}