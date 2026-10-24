# FloatToBinary

Program konsolowy w Javie, który zamienia liczbę zmiennoprzecinkową na 32-bitowy zapis binarny w standardzie IEEE 754 (typ `float`). Wynik jest rozbity na znak, wykładnik i mantysę oraz podany w zapisie szesnastkowym (hex).

## Opis działania

1. Program prosi o podanie liczby zmiennoprzecinkowej (kropka lub przecinek jako separator).
2. Zamienia przecinek na kropkę i sprawdza format wyrażeniem regularnym (odrzuca m.in. `NaN`, `Infinity`, `1f`).
3. Zamienia tekst na `float` i sprawdza, czy liczba mieści się w zakresie typu `float` (ok. ±3.4e38).
4. Pobiera surowe 32 bity za pomocą `Float.floatToIntBits()`.
5. Wydziela z nich trzy części operacjami bitowymi:
   - znak: bit 31 (1 bit),
   - wykładnik: bity 30-23 (8 bitów, bias 127),
   - mantysa: bity 22-0 (23 bity).
6. Wypisuje każdą część binarnie (uzupełnioną zerami), pełny zapis `1 + 8 + 23` bity oraz hex z prefiksem `0x`.

W przypadku błędnych danych program wyświetla komunikat o błędzie.

## Wartość liczby

```
(-1)^znak × 1.mantysa × 2^(wykładnik - 127)
```

## Wykorzystane elementy Javy

- klasa `Scanner`
- wyrażenia regularne: `matches()`
- `Float.parseFloat()`, `Float.isInfinite()`, `Float.floatToIntBits()`
- operatory bitowe `>>>`, `&` i maski `0xFF`, `0x7FFFFF`
- `Integer.toBinaryString()`
- `String.format()` (w tym `%08X` dla hex) oraz `replace()`

## Uruchomienie

```
javac FloatToBinary.java
java FloatToBinary
```

## Przykłady

| Wejście | Znak | Wykładnik  | Mantysa                   | Hex          |
|---------|------|------------|---------------------------|--------------|
| 5.75    | `0`  | `10000001` | `01110000000000000000000` | `0x40B80000` |
| 1       | `0`  | `01111111` | `00000000000000000000000` | `0x3F800000` |
| -0,1    | `1`  | `01111011` | `10011001100110011001101` | `0xBDCCCCCD` |
| -0      | `1`  | `00000000` | `00000000000000000000000` | `0x80000000` |
| abc     | komunikat o błędzie | | | |
| 1e50    | komunikat o błędzie (poza zakresem) | | | |

## Uwagi

- Liczba `0.1` nie ma dokładnego zapisu binarnego, dlatego mantysa jest zaokrąglona (`...1001101`).
- `-0` jest poprawną wartością w IEEE 754 (w przeciwieństwie do zapisu U2 dla liczb całkowitych).

## Autor

Szymon Rybikowski
