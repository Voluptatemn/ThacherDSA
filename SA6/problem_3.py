# You're given a square 2d array of integers. These integers represent 
# building heights in a square city. Return a 1d array holding how many 
# buildings you would be able to see when looking southwards from the 
# north of the city, from left to right. For example:
# 1 4 2
# 3 1 5      
# 2 7 7      
# would return [2, 2, 3]. The first 2 is because in the leftmost column we 
# would see the building of height 1 in front of the building of height 3, but 
# the building of height 2 would be obscured by height 3.

def numOfBuildings(array):
    map = {}
    
    for i in array:
        for j in range (len(i)):
            if j not in map:
                map[j] = [i[j], 1]
            else:
                if map[j][0] < i[j]:
                    map[j][0] = i[j]
                    map[j][1] = map[j][1] + 1
                    
    soln = []
    for i in map:
        soln.append(map[i][1])
        
    return soln 

array = [[1, 4, 2, 1],
         [3, 1, 5, 2],
         [2, 7, 7, 3],
         [4, 10, 10, 4],
         [1, 1, 1, 1]]
print(numOfBuildings(array))