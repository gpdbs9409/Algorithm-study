#문제 설명
  어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
  예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.
  문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다. number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.


# 아이디어
 무작정 투포인터를 사용하는 것이 아니라,list 그 자체를 변형하면서도  O(n^2)이 걸리지 않는다면 그대로가기 

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

#sol2 > 원본리스트를 삭제하지않고 새로운 stack을 만들어서 반환하는 방법 
def solution(number, k):
    answer = ''
    number=list(number)
    stack=[]
    for num in number:
        
        while k>=1 and stack and stack[-1]<num:
            stack.pop()
            k-=1
        
        stack.append(num)
    
    
    answer=''.join(stack)
    
    return answer
