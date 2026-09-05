# 문제 설명
n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.
송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

#문제 접근(풀이) 
1.그래프 형태이고 그래프 요소의 갯수를 세는 문제.
2.그래프의 엣지들을 하나씩 끊어보는 건 어렵지 않을 것 같다. 하나씩 끊고, left, right그룹의 갯수를 세서 배열로 저장한다.
3.|left-right|가 최소값일때의 인덱스를 반환한다.( 해당 인덱스가 곧 끊은 전선일 것이므로)
  
#sol 

def solution(n, wires):
    answer = -1
    #하나씩 끊어가면서 dfs 연결개수 세기 해야함. 
    
    candidate=[]

    
    for i in range(len(wires)):
        broken_wires = wires[:i] + wires[i+1:]
        visited=(n+1)*[False]
        def dfs(node):
            visited[node]=True
        
            for a,b in broken_wires:

                    if node==a and visited[b]==False:
                        dfs(b)
                    elif node==b and visited[a]==False:
                        dfs(a) 
                
        
        dfs(1)
        cnt_true=visited.count(True)
        cnt_false=n-cnt_true
        candidate.append(abs(cnt_true-cnt_false))
        
    answer=min(candidate)    
        
        
    return answer

# 
