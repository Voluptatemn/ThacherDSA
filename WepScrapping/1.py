from bs4 import BeautifulSoup
import requests

def webscappingFunction():
    topic = input("Topic: ")
    url = "https://en.wikipedia.org/wiki/" + topic
    numbOfParagraph = input("Num: ")

    r = requests.get(url)
    parser = BeautifulSoup(r.content, 'html.parser')

    mainSection = parser.find("div", {"id": "bodyContent"})

    account = 0
    for x in range (int(numbOfParagraph)):
        try:
            para = mainSection.find_all("p")[x+1]
            print(para.text)
            account += 1
        except IndexError:
            if (account > 1): 
                print("There is no more paragraph to print")
                break
            else:
                print("The topic you choose has no paragraphs, please enter a new topic.")
                webscappingFunction()
                break


webscappingFunction()








