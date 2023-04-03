# Given a string consisting only of parentheses, determine whether it is 
# a legal set of parentheses. For example, "(())()" is valid while "())()" is not.

def valid_parentheses(string):
    open_needed = 0
    for i in string:
        if i == '(':
            open_needed += 1
        if i == ')':
            open_needed -= 1
    if (open_needed == 0):
        return True
    else:
        return False
    
string = "())()"
print(valid_parentheses(string))


            
            