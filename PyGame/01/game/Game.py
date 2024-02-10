import pygame, sys  # import modułów pythona
import game, entities         # import naszych modułówy

class Game:         # główna klasa gry
    
    def __init__( self ):   # konstruktor gry
        self.window = pygame.display.set_mode( ( game.Config.WIDTH, game.Config.HEIGHT ) )  # zainicjowanie okna gry o wymiarach pobranych z naszego modułu Config
        pygame.display.set_caption( game.Config.TITLE ) # zmiana domyślnego tytułu okna gry

        self.clock = pygame.time.Clock()    # utworzenie zegara
        self.run = True # flaga informująca o uruchomieniu gry
        self.loop() # uruchomienie petli gry

    def loop( self ):   # główna pętla gry
        while self.run: # pętla jest iterowana jeżeli gra jest uruchomiona

            for event in pygame.event.get():    # przeiterowanie po wszystkich wyłapanych zdarzeniach w danej iteracji pętli gry
                if event.type == pygame.QUIT:   # sprawdzenie czy nastapiło zdarzenia zamknięcia okna gry
                    self.run = False            # wyłączamy flakę uruchomienia gry, dzięki czemu pętla gry nie zostanie uruchomiona po raz kolejny

            self.update()   # wywołanie metody aktualizującej stan obiektów
            self.render()   # wywołanie metody renderującej klatkę animacji

            self.clock.tick( game.Config.FPS )  # wykonanie tiku zegara na podstawie FPSów z modułu Config
        
        # ten kod jest poza powyższą pętlą, wykona się po jej zakończeniu
        pygame.quit()   # zakmnięcie aplikacji pygame
        sys.exit()      # zakmnięcie aplikacji pythona

    def update( self ): # metoda aktualizacji stanów obiektów
        pass    # pass ponieważ metoda nic jeszcze nie robi

    def render( self ): # metoda renderująca klatkę animacji
        pygame.display.update() # aktualizacja stanu okna gry