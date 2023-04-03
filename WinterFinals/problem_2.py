# There is a natural pecking order in nature. A wolf will eat a sheep, a 
# sheep will eat grass, etc. You'll be given a map in which a key is a 
# predator, and the value is a list of things it eats. You'll also be given a 
# list representing an environment. Your job is to determine how many 
# organisms will be alive at the end of the simulation. Animals will only 
# eat things directly adjacent to them. Animals have manners, so they 
# eat in turns (an animal will only eat once per turn). They will eat from 
# left to right, so the first thing in the list has a chance to eat before the 
# second thing in the list (that might mean the second thing gets eaten 
# before it ever has a chance to eat). 
# Example: map: {"wolf" : ["sheep", "mouse"], "sheep" : ["grass"]}, environment: 
# ["wolf", "sheep", "grass", "grass", "sheep", "mouse", "wolf"]
# After round 1, the leftmost wolf has eaten a sheep, the middle sheep has 
# eaten grass, and the rightmost wolf has eaten a mouse. Our environment 
# would be: ["wolf", "grass", "sheep", "wolf"]
# After the second round, it would be ["wolf", "wolf"]. The sheep ate the grass, 
# then the wolf ate the sheep. No more predator-prey pairs are left, so the 
# solution is 2.

def pecking(map, enviroment):
    
    del_list = []
    for i in range(len(enviroment)):
        if i == 0:
            if enviroment[i] in map:
                if enviroment[i+1] in map[enviroment[i]]:
                    del_list.append(i+1)
        elif i not in del_list:
            if i == len(enviroment) - 1:
                if enviroment[i] in map:
                    if enviroment[i-1] in map[enviroment[i]]:
                        del_list.append(i-1)    
            elif enviroment[i] in map:
                if enviroment[i-1] in map[enviroment[i]]:
                    del_list.append(i-1)
                elif enviroment[i+1] in map[enviroment[i]]:
                    del_list.append(i+1)
        if i == len(enviroment) - 1:
            list = []
            for i in sorted(del_list, reverse=True):
                if i not in list:
                    list.append(i)
                    del enviroment[i]
    if len(del_list) != 0:
        pecking(map, enviroment)
    


    

map = {"wolf" : ["sheep", "mouse"], "sheep" : ["grass"]}
enviroment = ["wolf", "sheep", "grass", "grass", "sheep", "mouse", "wolf"]
pecking(map, enviroment)
print(enviroment)
        
                
                
                
        
    
            
            
            
        

                            
                
                            
                        