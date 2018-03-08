package tadas.luksas.client;

public class LaivuKurimas {
    public static final String SHIP = "S";
    public static final String HIT = "X";
    public static final String MISS = "M";




    public void sukurtiLaiva(String[][] mas, int raide1, int skaicius1, int raide2, int skaicius2) {

        if (raide1 == raide2) {
            if (skaicius1 < skaicius2) {
                for (int a = skaicius1; a <= skaicius2; a++) {
                    mas[a][raide1] = SHIP;
                }
            }
            if (skaicius1 > skaicius2) {
                for (int a = skaicius1; a >= skaicius2; a--) {
                    mas[a][raide1] = SHIP;
                }
            }
        }
        if (skaicius1 == skaicius2) {
            if (raide1 < raide2) {
                for (int a = raide1; a <= raide2; a++) {
                    mas[skaicius1][a] = SHIP;
                }
            }
            if (raide1 > raide2) {
                for (int a = raide1; a >= raide2; a--) {
                    mas[skaicius1][a] = SHIP;
                }
            }
        }
        if (raide1 == raide2 && skaicius1 == skaicius2) {
            mas[skaicius1][raide1] = SHIP;
        }
    }

    public String sukurtiLaivoKordinates(int raide1, int skaicius1, int raide2, int skaicius2) {

        String raides = App.KILOMETRAS;
        char pakeistaRaide = raides.charAt(raide1);
        char pakeistaRaide2 = raides.charAt(raide2);
        print("" + pakeistaRaide + skaicius1 + "-" + pakeistaRaide2 + skaicius2);
        String laivoKoordinates = "" + pakeistaRaide + skaicius1 + "-" + pakeistaRaide2 + skaicius2;

        return laivoKoordinates;
    }

    public void pazymetiPriesoSuvi(String[][] mas, int raide, int skaicius) {
        if (mas[skaicius][raide] == SHIP|| mas[skaicius][raide] == HIT) {
            mas[skaicius][raide] = HIT;

        }
        if (mas[skaicius][raide] == Lentele.WATER|| mas[skaicius][raide] == MISS) {
            mas[skaicius][raide] = MISS;
        }
    }
    private static void print (String str){
        System.out.println(str);
    }
}



