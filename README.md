# bif-randomness-test
Several tests of LCG and Mersenne Twister pseudo-random number generators for purpose of "Computer security" lessons.

# Useful links

http://qrng.b-phot.org/static/media/NistTestsLongDescription.pdf
https://www.fi.muni.cz/~xkrhovj/lectures/2005_PA168_Statistical_Testing_slides.pdf
https://en.wikipedia.org/wiki/Diehard_tests

## Full overview
Aby znaleźć rożnicę w zaimplementowaych algorytmach generowania liczb losowych można posłużyć się narzędziem dieharder. Poniżej przedstawiam sposób jak użyć go w środowisku Ubuntu. Potrzebne ok. 10GB przestrzeni dyskowej i trochę czasu.
```bash
./src/main/resources/opso.sh
```
W ten sposób znalazłem test, któty dla tej samej ilości prób daje różne wyniki testów pomiędzy próbkami z LCG oraz MT

# Wstęp
Implementacje testów i algorytmów generowania liczb losowych. Można znaleźć w moim repozyrium https://github.com/dagi12/bif-randomness-test.git.

# Tests
## 1. FrequencyBinaryTest
### Opis 
Test polegający na sprawdzeniu liczby zer i jedynek w ciągu binarnym ciąg przechodzi test, jeśli liczba zer i jedynek jest mniej więcej taka sama.
 
### Wnioski
 Dla tego testu oba algorytmy dają mniej więcej takie same wyniki nie zauważyłem, aby jeden lub drugi dawały w ostatecznym rozrachunku mniejszą lub większą liczbę jedynek. Dla 1000 wejściowych liczb pseudolosowych różnicy różnica pomiędzy jedynkami i zerami nie jest większa niż 12000.

## 2.  RunsTest
### Opis 
Test, który sprawdza ile w danym ciągu jest podciągów złożonych z samych jedynek (11...1) i ile jest podciągów złożonych z samych zer (00...0). 

### Wnioski 
Wyniki testu dla obu algorytmu wychodzą bardzo podobnie.
- Dla MT: Ilość jedynek 7749947, ilość zer 7749948
- Dla LCG: ilość jedynek 7750425, Ilość zer 7750425
Dla każdego wywołania testu choć ilośc jedynek różni się znacząco między wywołaniami testu. To różnica pomiędzy ilością ciągów zer a jedynek nigdy nie jest większa od 0.

## 3. Frequency Binary test (within block)
### Uzasadnie
Test ten zaimplementowałem zamiast opisanego w mailu "Cumulative sums test" ponieważ w implementacji nie różni się on niczym od "FrequencyBinaryTest".

### Opis 
Test podobny do FrequencyBinaryTest z tym, że tutaj porównujemy stosunek zer i jedynek w blokach długości M. Z uzyskanych wyników wyciagamy średnią arytmetyczną. Dla M = 1, test ten odpowiada "Frequency Binary test".

### Wnioski
Podobnie jak w przypadku FBT oba algorytmy nie różnią się wiele od siebie. Testy przechodzą bez problemu dla odchyleń rzędu 0.17 dla miliona liczb wejściowych. Co ciekawe zmiana wielkości bloku nie wpływa na odchylenie za to znacząco wpływa na wydajność testu.

## 4. OPSO: overlapping - pairs - sparse – test
### Uzasadnie
Test ten zaimplementowałem zamiast opisanego w poprzednim mailu "Overlapping permutations (operm5)". Ponieważ zgodnie z poleceniem.
 > Użyj co najmniej czterech testów losowości. Dobierz je tak, żeby przynajmniej jeden z nich wykazał jakieś różnice w testowanych algorytmach.

A operm5 nie wykazywał znaczących różnić pomiędzy algorytmami.


# Opis
 Test OPSO uwzględnia 2-literowe słowa z alfabetu składającego się z 1024 liter. Każda litera jest określona przez dziesięć bitów z 32-bitowej liczby całkowitej w testowanej sekwencji. OPSO generuje 2<sup>21</sup> (nakładających się) 2-literowych słów (od 2<sup>21</sup> + 1 "naciśnięcia klawisza") i zlicza liczbę brakujących słów - czyli dwuliterowe słowa, które nie pojawiają się w całej sekwencji. Liczba ta powinna być bardzo zbliżona do normalnie rozłożonej ze średnią 141909, sigma 290. Tak więc 
 > (brakujące słowa - 141909) ÷ 290 
 
 Powinny być standardową zmienną normalną. Test OPSO pobiera 32 bity na raz z pliku testowego i wykorzystuje wyznaczony zestaw dziesięciu kolejnych bitów. Następnie uruchamia ponownie plik dla kolejnych wyznaczonych 10 bitów i tak dalej. 

# Wnioski
Jest to jedyny test z wymienionych w znalezionych publikacjach, w którym algorytm Mersenne Twister i java'owy LCG różnią się od siebie dla zadanych 100000000 losowych liczb.
- LCG oblewa test z prawdopodobieństwem (p-value) 0
- MT przechodzi test z prawdopodobieństwem (p-value) w zakresie od 0.19 do 0.89
Co wskazuje na to, że Mersenne Twister jest lepszym (jakościowo) generatorem liczb losowych.
