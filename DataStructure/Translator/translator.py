translator = {}

path = 'EnglishToArabicDictionary.txt'
f = open(path)

key = ''
count = 0
firstRow = True

for i in f.readlines():
    if firstRow:
        firstRow = False
        i = i[1:]
    count += 1
    if (count == 1):
        key = i.strip('\n')
    elif (count == 2):
        translator[key] = i.strip('\n')
        count = 0

user_key = input("Input the english word to translate into arabic: ")
try:
    print('The word ' + user_key + ' in arabic is ' + translator[user_key.lower()])
except:
    print("There is a key error, the dictionary does not have " + user_key)
    