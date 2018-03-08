package tadas.luksas.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.input.KeyCode.R;

public class Lentele {
    public static final String WATER = "~";

    public String[][] createTable() {
        String[][] masyvas = new String[10][10];

        for (int b = 0; b < masyvas.length; b++) {
            for (int c = 0; c < masyvas[b].length; c++) {
                masyvas[b][c] = WATER;
            }
        }
        return masyvas;
    }

//    public String[][] createTablePrieso() {
//        String[][] masyvasPrieso = new String[10][10];
//
//        for (int b = 0; b < masyvasPrieso.length; b++) {
//            for (int c = 0; c < masyvasPrieso[b].length; c++) {
//                masyvasPrieso[b][c] = WATER;
//            }
//        }
//        return masyvasPrieso;
//    }

    public String printTableLine(String kaire, String desine) {
        char eilute[] = new char[64];
        for (int i = 0; i < eilute.length; i++) {
            eilute[i] = ' ';
        }
        for (int i = 0; i < kaire.length(); i++) {
            eilute[i * 2] = kaire.charAt(i);
        }
        for (int i = 0; i < desine.length(); i++) {
            eilute[27 + i * 2] = desine.charAt(i);
        }
        return new String(eilute) + "\n";
    }

    public void printTable(String[][] masyvas, String[][] masyvasPrieso) {
        printLine(printTableLine("Mano laivai", "Prieso laivai"));
        //printLine("Mano laivai               Prieso laivai"+"\n");
        printLine(printTableLine(" " + App.KILOMETRAS, " " + App.KILOMETRAS));

//        for (int a = 0; a < kilometras.length; a++) {
//            printLine(kilometras[a] + " ");
//        }
//        printLine("     ");
//        for (int a = 0; a < kilometras.length; a++) {
//            printLine(kilometras[a] + " ");
//        }
//
//        printLine("\n");

        for (int i = 0; i < 10; i++) {
   //         String mano = new String();
            //String prieso = new String();
            StringBuilder man = new StringBuilder();
            StringBuilder pries = new StringBuilder();
            man.append(i);
            pries.append(i);
            for (int j = 0; j < 10; j++) {
                man.append(masyvas[i][j]);
                pries.append(masyvasPrieso[i][j]);
            }
            printLine(printTableLine(man.toString(), pries.toString()));
        }

//        }
//        for (int b = 0; b < masyvas.length; b++) {
//            printLine(b + " ");
//            for (int c = 0; c < masyvas[b].length; c++) {
//                printLine(masyvas[b][c] + " ");
//            }
//            printLine("     ");
//            printLine(b + " ");
//            for (int c = 0; c < masyvasPrieso[b].length; c++) {
//                printLine(masyvasPrieso[b][c] + " ");
//            }
//
//            for (int d = 0; d < masyvasPrieso.length; d++) {
//                printLine(d + " ");
//                for (int e = 0; e < masyvasPrieso[d].length; e++) {
//                    printLine(masyvasPrieso[d][e] + " ");
//                }
//            }
//            printLine("\n");
//        }

    }

    private static void print(String str) {
        System.out.println(str);
    }

    private static void printLine(String str) {
        System.out.print(str);
    }


}

