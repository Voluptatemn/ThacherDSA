# (Math challenge) Calculate the nth Fibonacci number, where n will be a very large number.  

import sys


def Fibonacci(num):
    if num == 1:
        return 0
    if num == 2:
        return 1
    count = 2
    previous = 0
    soln = 1 
    while (count < num):
        count += 1
        temp = previous
        previous = soln
        soln = soln + temp
        print(soln)
    return soln 

sys.set_int_max_str_digits(100000000)
print(Fibonacci(500))


def Fibonacci_seq(num):
    if (num == 1):
        return 0
    if (num == 2):
        return 1
    return Fibonacci_seq(num-1) + Fibonacci_seq(num-2)

# print(Fibonacci_seq(10000))