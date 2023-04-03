# Take an integer as input. Return a string representing the number in 
# roman numerals. For example, 17 would output "XVII"

def IntToRoman(num):
    romanSymbols = {
        1: 'I', 
        4: 'IV', 
        9: 'IX',
        5: 'V',
        10: 'X',
        40: 'XL',
        50: 'L',
        90: 'XC', 
        100: 'C',
        400: 'CD',
        500: 'D',
        900: 'CM', 
        1000: 'M'
    }
    numbers = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
    soln = ""
    while (num > 0):
        for i in numbers:
            while (i <= num):
                soln += romanSymbols[i]
                num -= i
    return soln

print(IntToRoman(8888))