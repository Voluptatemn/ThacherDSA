import requests
from bs4 import BeautifulSoup

def temperaturescapping():
    state = input("Pick a state in US in abbreviation: ")
    city = input("Choose a city in " + state + ": ")
    url = "https://www.wunderground.com/forecast/us/" + state.lower() + "/" + city.lower()
    
    r = requests.get(url)
    parser = BeautifulSoup(r.content, 'html.parser')
    temperature = parser.find("lib-display-unit", {"type": "temperature"})
    sunrise = parser.find("span", {"class": "data-point"})
    pressure = parser.find("lib-display-unit", {"type": "pressure"})
    rainfall = parser.find("lib-display-unit", {"type": "rain"})
    
    

    # additionalconditions = parser.find("div", {"class": "ng-star-inserted"})
    # for x in additionalconditions:
    #     rows = additionalconditions.find_all("div")
    #     label = rows.find("div", {"class": "small-4columns"})
    #     print(label.text)
    #     number = rows.find("lib-display-unit", {"type": "pressure"})
    #     print(number.text)
    # astronomy = parser.find("div", {"class": "content ng-star-inserted"})
    # for x in range (len(astronomy)):
    #     section = astronomy.find_all("row")[x]
    #     for y in range(len(section)):
    #         column = section.find_all("column")[y]
    #         print(column.text +"\n\n")

    print("The temperature at " + city + " is " + str(temperature.text) + ". \n" 
         "The sun is going to rise at " + str(sunrise.text) + " a.m. \n"
        "The pressure is " + str(pressure.text) + ". \n"
        "And the rainfall is " + str(rainfall.text) + ". ")


temperaturescapping()


