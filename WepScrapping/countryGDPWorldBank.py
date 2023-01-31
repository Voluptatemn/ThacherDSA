import numpy as np
from bs4 import BeautifulSoup
import requests
import scipy.stats

# hypothesis: higher industry, higher gross domestic product

def countryNameWorldBank(): 
    
    # finding the name of the countries with filters
    
    countryName = []
    
    url = "http://wdi.worldbank.org/table/4.1" # standard way of performing web scrapping
    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")
    
    table = parser.find("tbody") #find the table on the website
    rows = table.find_all("tr")
    
    for r in rows[:-12]:
        name = r.find_all("td")[0].text.strip()
        if name == "Samoa": # after several tests of the code, I can not resolve the problem caused by this row. So, idea is to just skip over it
            next
        else:
            Industry = r.find_all("td")[6].text.strip() # finding the amount of industry for this country
            DomesticProduct = r.find_all("td")[2].text.strip() # finding the amount of gross product for this country
            if DomesticProduct == ".." or Industry == "..": # filter the country so to deselect the country without the appropriate information
                next
            else: 
                countryName.append(name) # create the list of usable country
            
    return countryName

def countryDomestic(choice):
    
    url = "http://wdi.worldbank.org/table/4.1" # standard
    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")
    
    table = parser.find("tbody") # standard 
    rows = table.find_all("tr")
    
    for r in rows[:-12]: 
         name = r.find_all("td")[0].text.strip()
         if choice in name:
             DomesticProduct = r.find_all("td")[2].text.strip() # finding the gross domestic product of the specific country
             return float(DomesticProduct)
    
def countryIndustry(choice):
    
    url = "http://wdi.worldbank.org/table/4.1" # standard 
    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")
    
    table = parser.find("tbody") # standard
    rows = table.find_all("tr")
    
    for r in rows[:-12]:
        name = r.find_all("td")[0].text.strip()
        if choice in name:
              Industry = r.find_all("td")[6].text.strip() # finding the industry of the specific country
              return float(Industry)
    
def hypothesisTesting():
    
    # hypothesis: higher industry, higher gross domestic product
    
    countryDomesticList_HighIndustry = [] # a list of the domestic product numbers of countries which have high industry numbers
    countryDomesticList_LowIndustry = [] # vice versa
    
    countryName = countryNameWorldBank() # creating a list of usable country
    countryIndustryList = [] # an empty list that will later be populated by country industry stats
    
    for country in countryName: # populating the industry stats
        print(country) # show the process 
        Industry = countryIndustry(country)
        countryIndustryList.append(Industry)
    
    median = np.average(countryIndustryList) # finding the median of the country industry stats, ensuring an exact top half and a lower half in a mostly skewed data set
    
    for country in countryName: # populating the domestic product number lists 
        print(country) # show the process 
        Industry = countryIndustry(country) # finding the industry of the specific country
        Domestic = countryDomestic(country) # finding the domestic of the specific country
        if Industry >= median: # if the industry is higher than the median
            countryDomesticList_HighIndustry.append(Domestic) # put it into the top half
        else:
            countryDomesticList_LowIndustry.append(Domestic) # vice versa
            
    # generate result using a T-Test, imported scipy.stats, testing the alternative hypothesis
        
    results = scipy.stats.ttest_ind(countryDomesticList_HighIndustry, countryDomesticList_LowIndustry, alternative = "greater") 
    
    # the two averages for the user
    average_high = np.average(countryDomesticList_HighIndustry) 
    average_low = np.average(countryDomesticList_LowIndustry)
    
    print(results, average_high, average_low)
    
    # final output result 
    if results[1] <= 0.05: # statistical significance test, p-value 
        print("There is a statistical significance of the alternative hypothesis.") 
    else: 
        print("There is insufficient evidence to regect the null hypothesis.")
        
hypothesisTesting()
            
        
    
            



    
 
        
    