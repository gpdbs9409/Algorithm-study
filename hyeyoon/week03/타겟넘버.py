# 문제 타겟 넘버


n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.

제한사항
주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
각 숫자는 1 이상 50 이하인 자연수입니다.
타겟 넘버는 1 이상 1000 이하인 자연수입니다.
  
입출력 예
  
numbers	target	return
[1, 1, 1, 1, 1]	3	5
[4, 1, 2, 1]	4	2



#sol1 (문법오류) 

def solution(numbers, target):
    answer = 0
    now=numbers[0]
    
    def dfs(numbers[i],now):
        
        if now==target:
            answer+=1
        i+=1
        
        dfs(numbers[i+1],now+numbers[i+1])          
        dfs(numbers[i+1],now-numbers[i+1])   
        

    return answer

틀린이유: 
1. 함수의 매개변수에 변수가 아닌 특정값을 넣어버렸음( 변수를 넣어야함) 
2. dfs 함수에 리턴값이 정의되어있지 않으며, 실행조차 하지않았음 (프로그래머스는 solution함수를 실행하는 구조 
3. target이 되었다고 정답이 아니라 인덱스가 끝까지 돌았을때 + 타깃이어야함(if문이 중첩되어야함) 


sol2
def solution(numbers, target):
    answer = 0
    
    def dfs(i,now):
        
        
        if i ==len(numbers):
            if now==target: #탐색을 멈춰야하는 시점 
                answer+=1
            return
        
        dfs(i+1,now+numbers[i])          
        dfs(i+1,now-numbers[i])   
        return answer
    
    return dfs(0,0)


틀린이유 1: answer은 dfs바깥에 있는변수인데 dfs내부에서 수정하려고 함 
틀린이유 2: 탐색을 멈춰야하는 시점에 탐색을 멈추지않고 계속 dfs를 호출? i==len(numbers)라는거면 끝까지 탐색을 했다는것이니까 끝내야하는데 계속 dfs호출해버림->무조건 리턴을 해서 함수를 끝내야함.
틀린이유 3: i+=1을 미리 해버리면 i=0인 경우를 dfs호출에서 빼먹음(numbers[0])을 스킵하게됨 


sol3 최종정답

def solution(numbers, target):

    def dfs(i, now):
        if i == len(numbers):
            if now == target:
                return 1
            return 0

        return dfs(i + 1, now + numbers[i]) + dfs(i + 1, now - numbers[i])

    return dfs(0, 0)
