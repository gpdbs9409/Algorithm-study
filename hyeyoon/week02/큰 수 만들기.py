#문제 설명
  어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
  예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.
  문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다. number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.



#sol1  >답도 틀림 (tc 2/3커버) 
    def solution(number, k):
        answer = ''
        
        #시간복잡도를 위해서 탐색에 투포인터를 도입? > 단순 탐색이아니라 실제로 list변경이 필요할듯하여 투포인터가 아닌 실제 리스트를 이용 
    
        pointer=0
        numbers=list(number)
        
        #k>=1이여서 제거의 기회가 남아있다. 
        while k>=1:
            
                if pointer==len(numbers)-1:
                    numbers.pop()
                    k -=1
                    pointer=0
                #만약 앞자리수가 더 작다면 , 작은 숫자를 제거하고 더 큰숫자가 높은 자리수로 올 수 있게 함.
                elif numbers[pointer]< numbers[pointer+1]:
                    numbers.pop(pointer)
                    k-=1
                    
                #만약에 앞자리수가 더 크다면, 포인터를 다음으로 넘김. 
                else:
                    pointer+=1
                
                     
        
        return ''.join(numbers)

#sol2 > 원본리스트를 삭제하지않고 새로운 stack을 만들어서 반환하는 방법 -선택한 숫자를 stack에 저장 TC일부만 통과 
  def solution(number, k):
      answer = ''
      number=list(number)
      stack=[]
      for num in number:
          #삭제횟수가 남아있고 stack[-1]이 여전히 num보다 작다면 계속 삭제를 해야함 
        
          while k>=1 and stack and stack[-1]<num:
              stack.pop()
              k-=1
          
          stack.append(num)
      
      
      answer=''.join(stack)
      
      return answer

#sol3 > 원본리스트를 삭제하지않고 새로운 stack을 만들어서 반환하는 방법 -선택한 숫자를 stack에 저장 TC일부만 통과 
    def solution(number, k):
        number = list(number)
        stack = []
    
        for num in number:
            # 삭제 횟수가 남아있고
            # 현재 숫자가 stack의 마지막 숫자보다 크다면 계속 삭제
            while k >= 1 and stack and stack[-1] < num:
                stack.pop()
                k -= 1
    
            stack.append(num)
    
        # 끝까지 돌았는데도 삭제 횟수가 남았다면
        # 뒤에서부터 제거
        if k > 0:
            stack = stack[:-k]
    
        answer = ''.join(stack)
    
        return answer

어려웠던 점(배운 점)

1. 처음에는 시간복잡도를 줄이기 위해 투포인터를 사용할 수 있을지 고민했다.
하지만 이 문제는 단순히 두 위치를 탐색하는 것보다, 최근에 선택한 숫자를 다시 제거하면서 더 큰 숫자로 갱신해야 하는 구조이기 때문에 stack이 더 적합했다.

원본 리스트의 중간 요소를 계속 삭제하는 대신, 선택한 숫자를 새로운 stack에 저장하고 필요할 때 마지막 요소를 pop()하는 방식으로 구현했다.


2. 모든 숫자가 내림차순인 경우를 따로 생각해야 했다.

number = "98765", k = 2

모든 숫자가 내림차순이면 현재 숫자가 stack의 마지막 숫자보다 큰 경우가 한 번도 없기 때문에 pop()이 발생하지 않고 k=2가 그대로 남는다.

하지만 반드시 k개의 숫자를 제거해야 하므로, 탐색이 끝난 뒤에도 k가 남아 있다면 뒤에서부터 남은 개수만큼 제거해야 한다.

내림차순에서는 앞의 숫자를 제거하면 더 작은 숫자가 높은 자릿수로 올라오기 때문에 뒤의 숫자를 제거하는 것이 가장 큰 수를 만드는 방법이다.


3. for문 안에 while문이 있다고 해서 무조건 O(n²)은 아니다.

이 풀이에서는 각 숫자가 stack에 최대 한 번 들어가고, 최대 한 번 pop()되기 때문에 전체 순회는 O(n)이다.
