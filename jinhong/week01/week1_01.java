package week01;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @프로그래머스 12909
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
import java.util.Arrays;
class week1_01 {
    public static void main(String[] args) {
        week1_01 sol = new week1_01();

        // 테스트할 괄호 문자열 예제들
        String testCase1 = "(())()"; // 올바른 괄호 (true)
        String testCase2 = "(()(";   // 잘못된 괄호 (false)

        System.out.println("--- 1번 Stack 방식 결과 ---");
        System.out.println("테스트 1 결과: " + sol.solution1(testCase1));
        System.out.println("테스트 2 결과: " + sol.solution1(testCase2));

        System.out.println("\n--- 2번 toCharArray 방식 결과 ---");
        System.out.println("테스트 1 결과: " + sol.solution2(testCase1));
        System.out.println("테스트 2 결과: " + sol.solution2(testCase2));
    }

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
}