from tkinter import*
import random
from tkinter import font

class Spomin():
    def __init__(self, master):
        self.platno= Canvas(master, width=1450, height=1000, background = "orange")
        self.platno.pack()
        self.sezObrazov = []
        self.sezImen = []
        self.štVrstic = 4
        self.štStolpcev = 5
        self.vsiPari = 10
        self.fontNapisiŠtetje = font.Font(family='Helvetica', size=20, weight = 'bold')
        self.napis = self.platno.create_text(630,500,text='')
        self.hrbet = PhotoImage(file = 'bela.gif')
        for i in range(1,11): 
            self.sezObrazov.append(PhotoImage(file = 'obraz' + str(i) + '.gif'))
            self.sezImen.append(PhotoImage(file = 'ime' + str(i) + '.gif'))

        self.naslovnica = PhotoImage(file = 'naslovnica.gif')
        self.zacetnaSlika = self.platno.create_image(725, 500, image = self.naslovnica)
        self.fontGumb = font.Font(family='Helvetica', size=16, weight = 'bold')
        self.gumb = Button(self.platno, command = self.zacni)
        self.gumb.configure(width = 15, height = 5, text = 'START', font = self.fontGumb, bd = 10)
        self.gumbWindow = self.platno.create_window(750,700, window = self.gumb)
        self.platno.bind('<Button-1>',self.klikLeva)
        self.platno.bind('<Button-3>', self.ponastavi)
        
    def zacni(self):
        '''Funkcija nastavi platno na začetek igre.'''
        self.platno.delete(self.zacetnaSlika)
        self.platno.delete(self.gumbWindow)
        self.napis_štParov = self.platno.create_text(1350,300,text='')
        self.napis_štKlikov = self.platno.create_text(1350,500,text='')
        self.štParov = 0
        self.števecKlikov = 0 
        self.polja = self.matrika()
        self.narišiMatriko()
        self.stanje = 'IGRA'  
        
    def matrika(self):
        '''Funkcija ustvari matriko velikosti 5*4'''
        sez = list(range(1,11))+list(range(-10,0)) 
        random.shuffle(sez)
        matrika = []
        for i in range(0,len(sez),5): 
            v = sez[i:i+5] 
            matrika.append(v)
        return matrika
    

    def narišiMatriko(self):
        '''Funkcija, v prej pripravljeno matriko, vstavi pare.'''
        self.matrikaId1 = [[0]*self.štStolpcev for i in range(self.štVrstic)]
        self.matrikaId2 = [[0]*self.štStolpcev for i in range(self.štVrstic)]
        for i in range(self.štVrstic):      
            for j in range(self.štStolpcev):
                vrednost = self.polja[i][j]
                if vrednost < 0:
                    slika = self.sezImen[-vrednost-1] #Imena imajo negativen indeks
                else:
                    slika = self.sezObrazov[vrednost-1] #Obrazi pa pozitiven indeks
                x = j*250 + 125
                y = i*250 + 125
                self.matrikaId1[i][j] = self.platno.create_image(x,y,image = slika) #Matrika odprtih parov
                self.matrikaId2[i][j] = self.platno.create_image(x,y,image = self.hrbet) #Matrika zaprtih parov


    def klikLeva(self,event):
        '''Funkcija, ob levem kliku na miški, odpira pare. Za primerjavo dveh odprtih slik pokliče funkcijo primerjava'''
        self.z = event.x//250 #stolpec matrike (začnejo se z indeksom 0)
        self.k = event.y//250 #vrstica matrike (začnejo se z indeksom 0)
        sezID = self.platno.find_overlapping(event.x, event.y, event.x+1, event.y+1)
        if sezID != (): #Preverjamo, če je par že odprt
            Id = sezID[-1]
            if Id == self.matrikaId1[self.k][self.z]: 
                self.platno.itemconfig(self.napis, text='Ta par je že odprt!')
                self.platno.after(1000,lambda:self.platno.itemconfig(self.napis, text=''))  #lambda definira funkcijo brez imena, ki naredi tisto kar sledi za :                 
                return 
            else:
                pass
        vrednost = self.polja[self.k][self.z]
        if vrednost < 0:
            slika = self.sezImen[-vrednost-1]
            self.ind = -vrednost-1
        else:
            slika = self.sezObrazov[vrednost-1]
            self.ind = vrednost-1
        self.platno.tag_raise(self.matrikaId1[self.k][self.z])
        self.števecKlikov += 1   
        if self.števecKlikov%2 != 0: #Prva slika para
            self.ind1 = self.ind
            self.a = self.z 
            self.b = self.k
        else:
            self.ind2 = self.ind #Druga slika para
            self.c = self.z
            self.d = self.k
            self.platno.after(500,self.primerjava)
        self.platno.itemconfig(self.napis_štKlikov, text='Število  klikov:\n' + '         ' + str(self.števecKlikov), font = self.fontNapisiŠtetje)

    def primerjava(self):
        '''Funkcija primerja sliki, če sta par. Če ne, jih zapre.'''
        if self.ind1 == self.ind2:
            self.štParov += 1
        else:
            self.platno.tag_raise(self.matrikaId2[self.b][self.a])
            self.platno.tag_raise(self.matrikaId2[self.d][self.c])
        self.platno.itemconfig(self.napis_štParov, text='Število parov: \n'+ '       ' + str(self.štParov)+ '/10' ,font = self.fontNapisiŠtetje ) 
        if self.štParov == self.vsiPari:
                self.stanje = 'KONEC'
                self.platno.after(300,self.koncaj())
                
    def ponastavi(self,event):
        '''Funkcija ponastavi igro na začetek'''
        if self.stanje == 'ZAČETEK':
            self.platno.delete(self.napis_štParov) 
            self.platno.delete(self.napis_štKlikov)
            self.platno.delete(self.konec)
            self.platno.delete(self.fin)
            self.zacni()
        else:
            return
            
    def koncaj(self):
        '''Funkcija čestita uporabniku ob koncu. In ponudi možnost ponovitve igre.'''
        if self.stanje == 'KONEC':
            fnt = font.Font(family='Helvetica', size=30, weight='bold')
            tekst = 12*' '+'Čestitamo! Odkril si vse pare!\nZa novo igro pritisni desno tipko na miški.'
            self.slikaKonec = PhotoImage(file = 'baloni.gif')
            self.fin = self.platno.create_image(725, 500, image = self.slikaKonec)
            self.konec = self.platno.create_text(750, 680, text=tekst, fill='black', font=fnt)
            self.stanje = 'ZAČETEK'
        
    
        

                                

master = Tk()
aplikacija = Spomin(master)
master.mainloop()




    
