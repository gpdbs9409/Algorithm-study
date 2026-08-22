def solution(sequence, k):
    answer = []
    left=0
    right=0
    part=[]
    total=sequence[0]
    partdiff=[]
    while right<len(sequence):
        
        if total==k:
            part.append([left,right])
            total -= sequence[left]
            left+=1
            
        elif total<k:
            if right < len(sequence)-1:
                right+=1
                total+=sequence[right]
            else:
                break
        elif total>k:
            
            total-=sequence[left]
            left+=1
    for i in range(len(part)):
        a,b=part[i]
        partdiff.append([b-a,a,b])
            
    mini=min(partdiff)
    answer=mini[1:3]
    
    return answer
