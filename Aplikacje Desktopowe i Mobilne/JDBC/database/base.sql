DROP TABLE IF EXISTS `zamowienia`;
DROP TABLE IF EXISTS `klient`;
DROP TABLE IF EXISTS `usluga`;

CREATE TABLE `klient` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `imie` VARCHAR( 20 ),
    `nazwisko` VARCHAR( 30 ),
    `rok_urodzenia` INT( 4 )
);

CREATE TABLE `usluga` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nazwa` VARCHAR( 100 ),
    `opis` TEXT,
    `koszt` REAL
);

CREATE TABLE `zamowienie` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `id_klienta` INT,
    `id_uslugi` INT,
    `data` DATETIME,
    CONSTRAINT `fk_klient` FOREIGN KEY ( `id_klienta` ) REFERENCES `klient`( `id` ),
    CONSTRAINT `fk_usluga` FOREIGN KEY ( `id_uslugi` ) REFERENCES `usluga`( `id` )
);

INSERT INTO `klient` VALUES
( NULL, "Jan", "Kowalski", 1980 ),
( NULL, "Adam", "Nowak", 1995 ),
( NULL, "Anna", "Wanna", 1990 ),
( NULL, "Katarzyna", "Margaryna", 2001 );

INSERT INTO `usluga` VALUES
( NULL, "Internet światłowodowy 1000 Mb/s", "Super szybki i super nowoczesny internet światłowodowy osiągający zawrtoną prędkość 1000 Mb / s.", 100 ),
( NULL, "Instalacja anteny", "Instalowanie anteny klienta na ścianie budynku do wysokości 5 metrów.", 49.99 ),
( NULL, "Instalacja okablowania", "Instalacja okablowania sieciowego.", 44.99 );

INSERT INTO `zamowienie` VALUES
( NULL, 1, 1, "2022-06-06 15:01:00" ),
( NULL, 2, 3, "2022-06-09 09:09:00" ),
( NULL, 1, 2, "2022-06-18 14:58:00" ),
( NULL, 3, 2, "2022-06-20 13:04:00" ),
( NULL, 4, 1, "2022-06-20 15:49:00" );