from bs4 import BeautifulSoup
import requests
import scipy.stats
import numpy as np

def countryGDPWorldBank(choice): #total average annual real growth in the service industry by country
    url = "http://wdi.worldbank.org/table/4.1"
    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")
    
    table = parser.find("tbody")
    rows = table.find_all("tr")
    
    for r in rows[:-12]:
        name = r.find_all("td")[0].text.strip()
        if choice.lower() in name.lower():
            stats = r.find_all("td")[10].text.strip()
            if stats == '..':
                return 
            else:
                print (float(stats))

def countryGDP(choice):
    # choice = input("Enter a country name: ")
    url = "https://www.worldometers.info/gdp/gdp-by-country/"
    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")
    table = parser.find("tbody")
    rows = table.find_all("tr")
    
    for r in rows:
        countryName = r.find_all("td")[1].text.strip()
        if choice.lower() in countryName.lower():
            stats = r.find_all("td")[4].text.strip() #webscapping the GDP growth of the country
            return stats[:-1] #.replace(",", "")
        
def coronavirusStats(choice):
    
    #need to rename the country so to match the keys in the table 

    if choice == "United States":
        choice = "USA"
    
    if choice == "United Kingdom":
        choice = "UK"

    if choice == "South Korea":
        choice = "S. Korea"

    if (choice == "United Arab Emirates"):
        choice = "UAE"

    if choice == "Czech Republic (Czechia)":
        choice = "Czechia"
    
    if choice == "Turkmenistan":
        choice = "Turks and Caicos"

    if choice == "Congo, Dem. Rep.":
        choice = "Congo"

    if choice == "Côte d'Ivoire":
        choice = "Ivory Coast"
    
    if choice == "State of Palestine":
        choice = "Palestine"
    
    if choice == "Central African Republic":
        choice = "CAR"
    
    if choice == "Saint Kitts & Nevis":
        choice = "Saint Kitts and Nevis"

    if choice == "St. Vincent & Grenadines":
        choice = "St. Vincent Grenadines"
    
    if choice == "American Samoa":
        choice = "Samoa"

    if choice == "Sao Tome & Principe":
        choice = "Sao Tome and Principe"
        
    if choice == "Bahamas, The":
        choice = "Bahamas"
        
    if choice == "Brunei Darussalam":
        choice = "Brunei"
        
    if choice == "Hong Kong SAR, China":
        choice = "Hong Kong"
        
    if choice == "Macao SAR, China":
        choice = "Macao"
    
    # choice = input("Enter a name: ")

    url = "https://www.worldometers.info/coronavirus/"
    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")
    table = parser.find("tbody")
    rows = table.find_all("tr")

    # count = 0 #checking if find anything

    for r in rows: #standard checking for each row
        name = r.find_all("td")[1].text.strip()
        if choice.lower() in name.lower():
            # count += 1
            stats = r.find_all("td")[2].text.strip() #webscraping the number of covid cases
            # print("The total cases of coronavirus in " + name + " is: " + stats)
            return stats.replace(",", "")
            

    # if (count == 0): #if found nothing
    #     print("There is no place called: " + choice + ". Please re-enter a valid name.")
        # coronavirusStats()

def countryName(): #Webscapping the country name so the numbers between GDP growth and covid number cases are matchable
    countryNameList = []
    url = "https://www.worldometers.info/gdp/gdp-by-country/"
    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")
    table = parser.find("tbody")
    rows = table.find_all("tr")
    
    for r in rows:
        countryName = r.find_all("td")[1].text.strip()
        countryNameList.append(countryName)
    
    return countryNameList
        

def countryNameWorldBank():
    
    countryNameList = []
    url = "http://wdi.worldbank.org/table/4.1"
    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")
    
    table = parser.find("tbody")
    rows = table.find_all("tr")
    
    for r in rows[:-12]:
        name = r.find_all("td")[0].text.strip()
        countryNameList.append(name)
        
    return countryNameList

def hypothesisTesting():
    
    # print("The null hypothesis: the number of covid cases does not affect the country GDP growth.")
    # print("The alternative hypothesis: the number of covid cases have a statistical significant impact on the country GDP growth.")
    # print("The experiment process: Webscapping the worldometer website for datas in number of covid cases and country GDP growth (in percentage).")
    # print("Splitting the top half of covid cases country into one group and the lower in another.")
    # print("Running a t-test comparing the average GDP growth in the two groups, the top half is going to have a lower GDP growth according to the alternative hypothesis.")
    
    
    countryGDPList_low = []
    countryGDPList_high = []
    countryCoronaVirusCases = []
    countryNameList = countryNameWorldBank()
    
    for i in range(len(countryNameList)):
        country = countryNameList[i]
        if (country == "Northern Mariana Islands" or country == "Guam"): #the two country was not found in the worldnometer website
            continue
        process = (i/len(countryNameList)) / 2 
        print(process)
        print(country)
        countryCoronaVirusCases.append(float(coronavirusStats(country)))
        
    median = np.median(countryCoronaVirusCases) # the median of the country corona virus cases because of the graph is skewed
    # print(mean)
    
    for i in range(len(countryNameList)):
        country = countryNameList[i]
        if (country == "Northern Mariana Islands" or country == "Guam"):
            continue
        process =  0.5 + (i/len(countryNameList)) / 2 
        print (process)
        print(country)
        numberOfCases = float(coronavirusStats(country))
        if (numberOfCases >= median): #if greater assign it into a list
            countryGDPList_high.append(float(countryGDPWorldBank(country)))
        else: 
            countryGDPList_low.append(float(countryGDPWorldBank(country))) 
            
    average_high = np.average(countryGDPList_high)
    average_low = np.average(countryGDPList_low)
    print(average_high, average_low)
    
    results = scipy.stats.ttest_ind(countryGDPList_high, countryGDPList_low, alternative = "less")
    if results[1] <= 0.05:
        print("There is a statistical significance of the alternative hypothesis.")
    else: 
        print("There is insufficient evidence to regect the null hypothesis.")
    
    
    
hypothesisTesting()





    


