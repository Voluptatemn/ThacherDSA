# Take an integer n as parameter. Return a list containing the unique 
# prime factors of n. For example, 120 would return [2, 3, 5] 

def uniquePrime(num):
    list = []
    count = 2
    while (num >= count):
        if num % count == 0:
            num /= count 
            if count not in list:
                list.append(count)
        if num % count != 0:
            count += 1
    return list 

print(uniquePrime(12))
            
