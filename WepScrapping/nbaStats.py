from bs4 import BeautifulSoup
import requests

def nbaStats():
    url = "https://www.basketball-reference.com/leaders/ws_season.html"
    

    r = requests.get(url)
    parser = BeautifulSoup(r.content, "html.parser")

    table = parser.find("tbody")
    
    for rows in table.find_all("tr"):
        player = rows.find("a")

        # if choice.lower in player.text.lower:
        link = "https://www.basketball-reference.com/" + player["href"]
            
        r = requests.get(link)
        parser = BeautifulSoup(r.content, "html.parser")
        infoSection = parser.find("div", {"class": "p1"})

            

        # for infoPara in infoSection.find_all("p"):
        #     info = infoPara.find("span").text
        #     print(info)


        # for i in range(len(infoPara)):
        #     if (infoPara[i].text == "Shoots:"):
        #         print(infoPara[i+1])

          
        # height = infoPara.find("span").text
        # print(height)

        
        
    

nbaStats()


        
