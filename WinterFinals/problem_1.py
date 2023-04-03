# Return a map/dictionary containing the prime factorization of an 
# integer n. The keys should represent the prime factors, the values 
# should represent the powers of each prime factor. For example, n = 
# 120 would return {2: 3, 3: 1, 5: 1} because 120 = 23*31*51. 

def factorization(num):
    map = {}
    count = 2
    # repeat the process of dividing the numbers until count is bigger than num 
    while (num >= count):
        if num % count == 0:
            num /= count 
            # add it into the map
            if count not in map:
                map[count] = 1
            else:
                map[count] = map[count] + 1      
        # if no longer is divisable, count plus 1 
        if num % count != 0:
            count += 1
    return map

num = 2
print(factorization(num))
            