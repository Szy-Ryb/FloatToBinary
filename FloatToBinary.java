import java.util.Scanner;

public class FloatToBinary { // główna klasa programu w Javie

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // utworzenie obiektu scanner do inputu z klawiatury

        System.out.println("Copyright Szymon Rybikowski"); // copyright

        System.out.print("Podaj liczbę zmiennoprzecinkową (np. 5.75 lub -0,1): "); // prośba o liczbę

        String wejscie = scanner.next().replace(',', '.'); // pobranie tekstu i zamiana przecinka na kropkę

        if (!wejscie.matches("[+-]?\\d+(\\.\\d+)?([eE][+-]?\\d+)?")) { // sprawdzenie formatu (odrzuca m.in. NaN, Infinity, 1f)

            System.out.println("Błąd!! To nie jest poprawna liczba zmiennoprzecinkowa!");

        } else {

            float liczba = Float.parseFloat(wejscie); // zamiana tekstu na float

            if (Float.isInfinite(liczba)) { // liczba za duża jak na float

                System.out.println("Błąd!! Liczba jest poza zakresem typu float!");

            } else {

                int bity = Float.floatToIntBits(liczba); // pobranie surowych 32 bitów zapisu ieee 754

                int znak = (bity >>> 31) & 1; // bit 31 to znak (0 = dodatnia, 1 = ujemna)
                int wykladnik = (bity >>> 23) & 0xFF; // bity 30-23 to wykładnik (8 bitów)
                int mantysa = bity & 0x7FFFFF; // bity 22-0 to mantysa (23 bity)

                String znakBin = String.valueOf(znak); // znak jako tekst (1 bit)
                String wykladnikBin = String.format("%8s", Integer.toBinaryString(wykladnik)).replace(' ', '0'); // wykładnik do 8 bitów
                String mantysaBin = String.format("%23s", Integer.toBinaryString(mantysa)).replace(' ', '0'); // mantysa do 23 bitów
                String hex = String.format("%08X", bity); // całość jako hex (8 cyfr, wielkie litery)

                System.out.println("Znak:      " + znakBin); // wyświetlenie znaku
                System.out.println("Wykładnik: " + wykladnikBin + " (" + wykladnik + ", po odjęciu biasu 127: " + (wykladnik - 127) + ")"); // wykładnik + wartość rzeczywista
                System.out.println("Mantysa:   " + mantysaBin); // wyświetlenie mantysy
                System.out.println("Całość:    " + znakBin + " " + wykladnikBin + " " + mantysaBin); // pełny zapis 1 + 8 + 23 bity
                System.out.println("Hex:       0x" + hex); // wyświetlenie hex
            }
        }

        scanner.close(); // zamknięcie skanera w celu zwolnienia zasobów systemowych
    }

} // Copyright Szymon Rybikowski
