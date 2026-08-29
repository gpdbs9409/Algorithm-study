# 문제 설명
    무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다. 구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게 제한도 있습니다.
    
    예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 구명보트의 무게 제한이 100kg이라면 2번째 사람과 4번째 사람은 같이 탈 수 있지만 1번째 사람과 3번째 사람의 무게의 합은 150kg이므로 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.
    
    구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.
    
    사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때, 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.
    
    제한사항
    무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
    각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
    구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
    구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.


#sol 1-답은 맞았지만 시간 초과로 에러(테스트케이스 전부 시간초과)

      def solution(people, limit):
        answer = 0
        #정렬해서 가장 무거운 사람부터 뽑는다. 
        people.sort()
        
        while people:
            save=people.pop()
            #만약 남은 사람들 중에 합이 100이 되지 않는다면 같이 보트에 태우고 아니라면 넘어감
            for person in reversed(people):
                if save+person<=limit:
                    people.remove(person)
                    break
                
            answer+=1
        return answer
    
    시간복잡도 : people*people =>O(n^2) 


# sol 2 답은 맞았지만 시간 초과로 에러(테스트케이스 일부 시간초과)
      def solution(people, limit):
          answer = 0
          #정렬해서 가장 무거운 사람부터 뽑는다. 
          people.sort()
          
          while people:
              save=people.pop()
              #만약 남은 사람들 중에 합이 100이 되지 않는다면 같이 보트에 태우고 아니라면 넘어감   
              if people:
                  lightest=people[0]
                  if save+lightest<=limit:
                      people.remove(lightest)
                #for문으로 보트에 탈 나머지 사람을 찾기 위해서 전체를 훑는게 아닌 가장 가벼운사람만 pop하는 방식으로 바꿈
                  
              answer+=1
          return answer
      
      시간복잡도가 O(n)인줄 알았지만 O(n^2)이었음 
      people.remove(lightest)< 이렇게 stack에서 pop하는 방식이 아니라 가장 앞의 요소를 삭제하면 O(n)이 또다시 발생함. ->O(n^2) 
      peole.sort() 도 O(n log n)인데, remove() 때문에 전체는 결국 O(n²)이 지배


#sol3  > stack을 직접 추가/삭제 하지 않고도 two pointer를 써서 인덱스를 이용해서 실제 스택을 가리키기만 한다면 시간복잡도가 훨씬 줄어드므로 투포인터를 써서 구현. 
    def solution(people, limit):
        answer = 0
        #정렬해서 가장 무거운 사람부터 뽑는다. 
        people.sort()
        right=len(people)-1
        left=0
        
        while left<=right:
    
            if people[right]+people[left]<=limit:
                left+=1
                
            right-=1   
            answer+=1
        return answer

      시간복잡도는 O(n log n)


#어려웠던점(배운점):
    1. 시간복잡도
        문제 아이디어 자체는 직관적이어서 어렵지 않았지만, 시간복잡도를 고려해서 풀어야 한다는 것을 분명하게 깨달은 문제였다.
        또한 투포인터를 쓰는 이유를 확실하게 깨달을 수 있었다. 리스트의 앞/중간 요소를 삭제하면 뒤 원소들을 당겨야 해서 O(n)이 발생하기 때문. 
        people.pop()      # 맨 뒤 삭제 → O(1)
        people.append(x)  # 맨 뒤 추가 → O(1)
        
        people.pop(0)     # 맨 앞 삭제 → O(n)
        people.remove(x)  # 값 탐색 + 삭제 → O(n)

    2.list 문법
        list에는 delete() 문법이 없으므로 특정값을 삭제하고 싶다면 remove를 쓸것 
        reversed(people) 문법 :원본 리스트 자체를 역정렬하는 게 아니라 역순으로 순회할 수 있는 iterator를 반환



