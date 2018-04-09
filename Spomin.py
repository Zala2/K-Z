from tkinter import*
import random

class Spomin():
    def __init__(self, master):
        self.platno= Canvas(master, width=1250, height=1000, background = "orange")
        self.platno.pack()
        self.sezObrazov = []
        self.sezImen = []
        self.štVrstic = 4
        self.štStolpcev = 5
        self.hrbet = PhotoImage(file = 'bela.gif')
        for i in range(1,11):
            self.sezObrazov.append(PhotoImage(file = 'obraz' + str(i) + '.gif'))
            self.sezImen.append(PhotoImage(file = 'ime' + str(i) + '.gif'))
        self.polja = self.matrika()
        self.narišiMatriko()
        #self.narišiZaprtoMatriko()
        self.platno.bind('<Button-1>',self.klik)
        
    def matrika(self):
        sez = list(range(1,11))+list(range(-10,0))
        random.shuffle(sez)
        matrika = []
        for i in range(0,len(sez),5):
            v = sez[i:i+5]
            matrika.append(v)
        return matrika

    def narišiZaprtoMatriko(self):
        for i in range(self.štVrstic):      
            for j in range(self.štStolpcev):
                x = j*250 + 125
                y = i*250 + 125
                self.platno.create_image(x,y,image = self.hrbet)    
    
    def narišiMatriko(self):
        for i in range(self.štVrstic):      
            for j in range(self.štStolpcev):
                vrednost = self.polja[i][j]
                if vrednost < 0:
                    slika = self.sezImen[-vrednost-1]
                else:
                    slika = self.sezObrazov[vrednost-1]
                x = j*250 + 125
                y = i*250 + 125
                self.platno.create_image(x,y,image = slika)

    def klik(self,event):
        self.x = event.x
        self.y = event.y
        a = round((self.x - 125)/250, 0)
        b = round((self.y - 125)/250, 0)
        for i in range(self.štVrstic):      
            for j in range(self.štStolpcev):
                if a == j and b == i:
                    vrednost = self.polja[i][j]
                    if vrednost < 0:
                        slika = self.sezImen[-vrednost-1]
                    else:
                        slika = self.sezObrazov[vrednost-1]
                    x = j*250 + 125
                    y = i*250 + 125
                    self.platno.create_image(x,y,image = slika)
           
        
                
            

#def Odpiranje(primerja, in če ni zapre po par sekundah)
#def Konec(ko je vse odprto, baloni)
        
#self.canvas.bind("<Key>", self.tipka_pritisnjena)

master = Tk()
aplikacija = Spomin(master)
master.mainloop()




    
