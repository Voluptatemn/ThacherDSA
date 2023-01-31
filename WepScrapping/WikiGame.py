from bs4 import BeautifulSoup
import requests

def wikiGame(url, url1):
    
    global count 
    count += 1 # adding one step every time the function is called
    
    try: # there are some parts of the wiki page does not work, for example: the citation neseccary page
        r = requests.get(url) # standard way of getting the page content
        parser = BeautifulSoup(r.content, "html.parser")
        paragraph = parser.find_all("p") # want to get all the paragraph because the links we are trying to use are all in the paragraph
            
        linklist = [] # this is going to be populated by the links we fetch from all the paragraphs
        i = 0 # a counter which will be preesented to the player so they could choose based on the numbers, making selection simplier
            
        for p in paragraph: # getting all links
                name = p.find_all("a") # finding the names of the links
                for n in name:
                    if "/wiki/" in n["href"]: # filering the links for the only links that is going to take the player to another wiki page is a link with /wiki/
                        link = "https://en.wikipedia.org" + n["href"] # get the link
                        linklist.append(link) # put the link into the list 
                        i += 1 # add the i here because it is easier for the player, starting at 1 is common 
                        print(str(i) + ". " + n.text.strip()) # print out the number. name of the link 
                    else: 
                        next # excluding the links that are not valid 
                
        if len(linklist) == 0: # if there is no links in the page
                choice = input("There is no paragaphs in this page. If you want to continue the game with another topic, enter continue; if you want to end the game, enter end. ") # let the player choose what they want to do, end, or continue 
                if choice.lower() == "continue": 
                    count = 0 # reset the count 
                    startGame() # reference startGame
                elif choice.lower() == "end": # end
                    print("The game has ended.") # just print the end message
        
                
        else: 
            s = input("Choose the number corresponding to the link: ") # let the player choose the number cooresponding to the link 
            if s == "end": # let the player choose to end the game
                print("The game has ended.")
            else: 
                selection_url = linklist[int(s)-1] # s - 1 because i starts at 1 
                if selection_url.lower() == url1.lower(): # lower because there are some wierdness 
                    print("Congradulations! You win the game. You did it in " + str(count) + " steps.")
                else: 
                    wikiGame(selection_url, url1) # if the player does not hit the end, the function is recursive 
                    
    except:
        print("Sorry, there was an error. Try again")
        startGame() # restart the game
                 

def startGame():
    starting_topic = input("Input the stating topic: ") #the staring topic, what it to be outside the function because want to use recursions
    starting_url = "https://en.wikipedia.org/wiki/" + starting_topic # starting topic url
    ending_topic = input("Input the ending topic: ") # ending topic
    ending_url = "https://en.wikipedia.org/wiki/" + ending_topic.replace(" ", "_") # ending topic url, replacing the space to _ because wiki page use _ to concacotate words in the url
    wikiGame(starting_url, ending_url) # the game will start 
    
count = 0 # the score, or how many steps it takes the player to get to the link
startGame() # start the game


     