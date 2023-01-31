from bs4 import BeautifulSoup
import requests

url = "https://www.pro-football-reference.com/leaders/av_single_season.htm"

r = requests.get(url)
parser = BeautifulSoup(r.content, "html.parser")

table = parser.find("tbody")

for row in table.find_all("tr"):
    player = row.find("a")
    
    link = "https://www.pro-football-reference.com/" + player["href"]

    r = requests.get(link)
    parser = BeautifulSoup(r.content, "html.parser")
    infoSection = parser.find("div", {"id": "info"})
    infoPara = infoSection.find_all("p")[2]
    height = infoPara.find("span").text
    print(height)


