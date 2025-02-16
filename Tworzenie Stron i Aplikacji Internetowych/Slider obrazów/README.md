# Slider obrazów

Przykład prostego slidera obrazów. Apliakacja jest wykonana w technologiach HTML, CSS oraz JavaScript.

## Pliki

Kod slidera oraz jego styl znadjują się w katalogu `slider`. Natomiast pliki z grafikami które mają być wyświetlane w sliderze znajdują się w katalogu `img/slider`.

## Działanie

Slider wyświetla obrazy zmieniana cyklicznie co określony czas.

Jeżeli do slidera przypisze się tlyko jeden obraz to będzie on wyświetlany bez kontrolek slidera oraz bez przejść.

## Składnia

W nagłówku `head` należy zaimportować styl slidera:

```html
<link rel="stylesheet" href="./slider/style.css">
```

Skrypt JS należy zaimporotwać w dowolnym miejscu jako moduł:

```html
<script src="./slider/slider.js" type="module"></script> 
```

Aby dodać slider do storny należy w dowolnym miescu ciałą `body` umieścić znacznik:

```html
<div
    id="slider"
    data-photos="rect.jpg;circle.jpg;triangle.jpg;hexagon.jpg;octagon.jpg"
    data-timer="3000"
>
</div>
```

gdzie:

`data-photo` - to lista obrazów z katalogu `img/slider` które będą wyświetlane w sldierze, kolejność wymieniana będzie kolejnością wyświetlania

`data-timer` - jest to czas zdefiniowany w milisekundach, slider będzie automatycznie co TEN CZAS zmieniał obraz na nastepny w kolejności

Jeżeli zostanie zdefiniowane tylko jedn o zdjęcie to slider nie będzie pokazywał swoicj kontrolek manualnego przewijania obrazów oraz nie będzie przewijał obrazu automatycznie.

## Przykład

W pliku `index.html` znajduje sięprzykład użycia slidera.
