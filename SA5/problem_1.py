# Take a list of integers as parameter, as well as an integer target. Return 
# a list of index pairs (i, j) such that list[i] + list[j] = target and i != j. 
# Challenge: complete this in linear time. Example: [4, -2, 0, 3, 2, 0] would return [[1, 4], [2, 5]]

def indexPairs(nums, target): 
    list = []
    map = {}
    
    for i in range (len(nums)):
        
        new_target = target - nums[i]
        if new_target in map:
            for j in map[new_target]:
                new = [i, j]
                list += [new]
            
        if nums[i] in map:
            map[nums[i]] += [i]
        else:
            map[nums[i]] = [i]

    return list
            
        
    
nums = [1, 0, 1, 0, 1, 0, 1, ]
target = 1
print(indexPairs(nums, target))

                
    