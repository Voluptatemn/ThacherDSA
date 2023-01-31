from bs4 import BeautifulSoup
import requests

url = "https://www.nfl.com/stats/player-stats/category/receiving/2022/reg/all/receivingreceptions/desc"
choice = input("Choose a player: ")

r = requests.get(url)
parser = BeautifulSoup(r.content, "html.parser")

table = parser.find("tbody")
rows = table.find_all("tr")

count = 0

for r in rows:
    name = r.find("td").text.strip()

    if choice.lower() in name.lower() :
        count += 1
        stats = r.find_all("td")[2].text.strip()
        print(name + "'s receiving yards: " + stats)

if (count == 0):
    print("There is no player with name: " + choice)
    
        


        