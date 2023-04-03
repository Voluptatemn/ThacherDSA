# Given a string of letters and spaces, determine what sequence of 
# numbers would've had to be typed on an old flip phone (see here) to 
# get this string. For example, "celtics" would be "2358427"

def flip_Phone(string):
    map = {'a': 2,
           'b': 2,
           'c': 2, 
           'd': 3,
           'e': 3,
           'f': 3,
           'g': 4,
           'h': 4,
           'i': 4,
           'j': 5,
           'k': 5,
           'l': 5,
           'm': 6,
           'n': 6,
           'o': 6,
           'p': 7,
           'q': 7,
           'r': 7,
           's': 7,
           't': 8,
           'u': 8,
           'v': 8,
           'w': 9,
           'x': 9,
           'y': 9,
           'z': 9}
    
    soln = ""
    for i in string:
        soln += str(map[i])
    return soln

string = "celtics"
print(flip_Phone(string))
        