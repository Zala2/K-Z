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
        self.a = 0
        self.b = 0
        self.c = 0
        self.d = 0
        self.ind = 0
        self.ind1 = 0
        self.ind2 = 0
        self.števecKlikov = 0
        self.napis = None
        self.hrbet = PhotoImage(file = 'bela.gif')
        for i in range(1,11):
            self.sezObrazov.append(PhotoImage(file = 'obraz' + str(i) + '.gif'))
            self.sezImen.append(PhotoImage(file = 'ime' + str(i) + '.gif'))
        self.polja = self.matrika()
        self.narišiMatriko()
        self.platno.bind('<Button-1>',self.klikLeva)

        
    def matrika(self):
        sez = list(range(1,11))+list(range(-10,0))
        random.shuffle(sez)
        matrika = []
        for i in range(0,len(sez),5):
            v = sez[i:i+5]
            matrika.append(v)
        return matrika
                
    
    def narišiMatriko(self):
        self.matrikaId1 = [[0]*self.štStolpcev for i in range(self.štVrstic)]
        self.matrikaId2 = [[0]*self.štStolpcev for i in range(self.štVrstic)]
        for i in range(self.štVrstic):      
            for j in range(self.štStolpcev):
                vrednost = self.polja[i][j]
                if vrednost < 0:
                    slika = self.sezImen[-vrednost-1]
                else:
                    slika = self.sezObrazov[vrednost-1]
                x = j*250 + 125
                y = i*250 + 125
                self.matrikaId1[i][j] = self.platno.create_image(x,y,image = slika)
                self.matrikaId2[i][j] = self.platno.create_image(x,y,image = self.hrbet)
                
                

    def klikLeva(self,event):
        #find_overlapping(x1, y1, x2, y2)
        self.x = event.x
        self.y = event.y
        self.z = self.x//250
        self.k = self.y//250
        vrednost = self.polja[self.k][self.z]
        if vrednost < 0:
            slika = self.sezImen[-vrednost-1]
            self.ind = -vrednost-1
        else:
            slika = self.sezObrazov[vrednost-1]
            self.ind = vrednost-1
        self.platno.tag_raise(self.matrikaId1[self.k][self.z])
        self.števecKlikov += 1   
        if self.števecKlikov%2 != 0:
            self.ind1 = self.ind
            self.a = self.z
            self.b = self.k

        else:
            x1 = self.a*250
            y1 = self.b*250
            x2 = x1 + 250
            y2 = y1 + 250
            prekrivanje = self.platno.find_overlapping(x1, y1, x2, y2)
            if (self.z and self.k) in prekrivanje:
                self.platno.itemconfig(self.napis, text='Ta par je že odprt!')
                self.platno.tag_raise(self.matrikaId2[self.k][self.z])
            else:
                self.ind2 = self.ind
                self.c = self.z
                self.d = self.k
                self.platno.after(500,self.primerjava)

    def primerjava(self):
        if self.ind1 == self.ind2:
            pass
        else:
            self.platno.tag_raise(self.matrikaId2[self.b][self.a])
            self.platno.tag_raise(self.matrikaId2[self.d][self.c])

            
               


master = Tk()
aplikacija = Spomin(master)
master.mainloop()




    
