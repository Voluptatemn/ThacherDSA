def findingTables(tableCount, path, maxPeople):
    #create the lists for all groups
    teacher = []
    freshman = []
    sophomore = []
    junior = []
    senior = []

    # read the file 
    path = 'RandomTable/Roster.txt'
    f = open(path)

    # seperate the file into different groups 
    number = 0
    for i in f.readlines():
        i = i.strip('\n') # process the words 
        if number == 0:
            if "---" in i:
                number += 1
            else:
                teacher.append(i)
        elif number == 1:
            if "---" in i:
                number += 1
            else:
                freshman.append(i)
        elif number == 2:
            if "---" in i:
                number += 1
            else:
                sophomore.append(i)
        elif number == 3:
            if "---" in i:
                number += 1
            else:
                junior.append(i)
        elif number == 4:
            if "---" in i:
                number += 1
            else:
                senior.append(i)
                
    # create a 38 table list
    l = []
    for i in range (tableCount):
        m = []
        l.append(m)

    # function for finding the minimum index 
    def mininmum(list):
        number = 999
        index = 0 
        for i in range(len(list)):
            if len(list[i]) < number:
                number = len(list[i])
                index = i 
        return index 

    # find the teacher to fit
    from numpy.random import default_rng
    def fit(list): # teacher as an example or placeholder
        rng = default_rng()
        teacher_numbers = rng.choice(len(list), size=tableCount, replace=False) # generate a random teacher assignment 

        tableNumber = 0 # table number iterate through the tables
        for n in teacher_numbers:
            if len(l[tableNumber]) >= maxPeople:
                l[mininmum(l)].append(list[n]) 
            else:
                l[tableNumber].append(list[n]) # put the teacher into the table
                tableNumber += 1
                
        # rest_table = rng.choice(38, size = len(list) - 38, replace = False)
        
        remainingTeacher = [i for j, i in enumerate(list) if j not in teacher_numbers] # finding the remaining teachers
        
        if len(remainingTeacher) >= tableCount: # if the remainder is greater, than do it again
            fit(remainingTeacher)
        else:
            return remainingTeacher

    # fit all groups, so that each table at least have one person from each grade 
    remainingTeacher = fit(teacher) 
    remainingSenior = fit(senior)
    remainingFreshman = fit(freshman)
    remainingSophomore = fit(sophomore)
    remainingJunior = fit(junior)

    def remainFit(list): # for a list of len < 38 
        rng = default_rng()
        tableNumber = rng.choice(tableCount, size = len(list), replace = False) # randomly generate the tables to fit into 
        tableIndex = 0 # the indicator of the tables 
        for element in list:
            if len(l[tableNumber[tableIndex]]) >= maxPeople: # finding the table that has the minimal people and place the person there
                l[mininmum(l)].append(element)
                
            else:
                l[tableNumber[tableIndex]].append(element) # if still place, fit it 
            tableIndex += 1
            
    # fit the remains 
    remainFit(remainingTeacher)
    remainFit(remainingSenior)
    remainFit(remainingFreshman)
    remainFit(remainingSophomore)
    remainFit(remainingJunior)

    return l
    

path = 'RandomTable/Roster.txt' # the path to the file
tableCount = 38 # the amount of table
maxPeople = 8 # the max people per table
l = findingTables(tableCount, path, maxPeople)
print(l)




        
    

 



