import requests
from bs4 import BeautifulSoup

def coronavirusStats():

    choice = input("Put in a continent or a country: ")

    url = "https://www.worldometers.info/coronavirus/"
    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")
    table = parser.find("tbody")
    rows = table.find_all("tr")

    count = 0 #checking if find anything

    for r in rows: #standard checking for each row
        name = r.find_all("td")[1].text.strip()
        if choice.lower() in name.lower():
            count += 1
            stats = r.find_all("td")[2].text.strip()
            print("The total cases of coronavirus in " + name + " is: " + stats)
            

    if (count == 0): #if found nothing
        print("There is no place called: " + choice + ". Please re-enter a valid name.")
        coronavirusStats()
       

coronavirusStats()