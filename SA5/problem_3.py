# Take a string and integer as parameter. Return the left-to-right reading 
# of this string when it is diagonalized into n rows (see below for what 
# this means). Example: "TRACKISGREAT", 4 would return 
# "TSRIGTAKRACE"
# Diagonalization of "TRACKISGREAT" with 4 rows would be:
# T         S
# R     I   G     T
# A  K     R  A
# C         E

def diagnolization(string, row):
    map = {}
    r = 0
    c = 0
    down = True
    for char in string:
        if down:
            map[str(r) + ',' + str(c)] = char
            r += 1
            if r == row:
                down = False
                r -= 1
        else:
            r -= 1
            c += 1
            map[str(r) + ',' + str(c)] = char
            if r == 0:
                down = True
                r += 1
                
    output = ""
    for i in range (row):
        for j in range (c+1):
            if str(i) + ',' + str(j) in map:
                output += map[str(i) + ',' + str(j)]
    return output

string = "TRACKISGREAT"
print(diagnolization(string, 4))
        
        
            
        
    
    
    