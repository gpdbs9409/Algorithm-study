```PYTHON 괄호 lv2```


def solution(s):
    answer = True
    stacks=[]


    for brackets in s:
        if brackets =='(':
            stacks.append(1)
        else:
            if not stacks:
                return False
            stacks.pop()
    if stacks:
        return False
        
    


    return True
