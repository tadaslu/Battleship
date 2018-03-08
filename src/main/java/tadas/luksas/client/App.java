package tadas.luksas.client;

import tadas.luksas.beans.Game;
import tadas.luksas.beans.Suvis;
import tadas.luksas.beans.User;
import tadas.luksas.services.GameServiceImpl;
import tadas.luksas.services.GameServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class App {
    //TODO perkelti i service
    //  public static final String READY_FOR_SECOND_PLAYER = "READY_FOR_SECOND_PLAYER";
    public static final String READY_FOR_SHIPS = "READY_FOR_SHIPS";
    public static final String READY_TO_PLAY = "READY_TO_PLAY";
    public static final String FINISHED = "FINISHED";
    public static final String KILOMETRAS = "KILOMETRAS";

//    public static void waitForStatus(GameServiceInterface gameService, Game game, String status) throws InterruptedException {
//        while (!game.getStatus().equals(status)) {
//            game = gameService.gameStatus(game.getgameId());
//            Thread.sleep(2000);
//        }
//    }

    public static void main(String[] args) throws InterruptedException {
        LaivuKurimas laivuKurimas = new LaivuKurimas();
        GameServiceInterface gameService = new GameServiceImpl();
        User user = gameService.createUser("Tadas", "tadasluksas@gmail.com");
        Game game = gameService.joinGame(user.getUserId());

        lauktiStatus(gameService, game, READY_FOR_SHIPS);
//        while (!game.getStatus().equals(READY_FOR_SHIPS)) {
//            game = gameService.gameStatus(game.getgameId());
//            Thread.sleep(2000);
//        }

        String raides = KILOMETRAS;
        Scanner scanner = new Scanner(System.in);
        Lentele lentele = new Lentele();
        String[][] mas = lentele.createTable(); // masyva,kuri grazino, kuriam kaip objekta
        String[][] masPrieso = lentele.createTable();
        //lentele.createTablePrieso();
        List<String> laivuSarasasWS = new ArrayList<>();
        lentele.printTable(mas, masPrieso);


        int a = 1;
        while (a <= 1) {
            print("Iveskite 1 - keturvieti, 2 - triviecius, 3 - dviviecius, 4 - vienviecius laivus. Ivedneti reikia laivo pradzios ir pabaigos koordinates");
            String raidePirma = toUpperCase(scanner.next());
            int raide1 = raides.indexOf(raidePirma);
            int skaicius1 = scanner.nextInt();
            String raideAntra = toUpperCase(scanner.next());
            int raide2 = raides.indexOf(raideAntra);
            int skaicius2 = scanner.nextInt();
            //TODO patikrinti ivedamus skaicius
            laivuKurimas.sukurtiLaiva(mas, raide1, skaicius1, raide2, skaicius2);
            laivuSarasasWS.add(laivuKurimas.sukurtiLaivoKordinates(raide1, skaicius1, raide2, skaicius2));
            lentele.printTable(mas, masPrieso);
            a++;
        }
        for (int i = 0; i < laivuSarasasWS.size(); i++) {
            print(laivuSarasasWS.get(i));
        }
        Game setupShips = gameService.setupShips(game.getgameId(), user.getUserId(), laivuSarasasWS);

        lauktiStatus(gameService, game, READY_TO_PLAY);
//        while (!game.getStatus().equals(READY_TO_PLAY)) {
//            game = gameService.gameStatus(game.getgameId());
//            Thread.sleep(2000);
//        }

        while (!game.getStatus().equals(FINISHED)) {
            game = gameService.gameStatus(game.getgameId());
            Thread.sleep(2000);

            while (game.getNextTurn().equals(user.getUserId())) {
                game = gameService.gameStatus(game.getgameId());


                for (Suvis suvis : game.getEvents()) {
                    int raide = raides.indexOf(suvis.getColumn());
                    int skaicius = (int) suvis.getRow();

                    if (suvis.getUserId().equals(user.getUserId())) {
                        if (suvis.getHit().equals(true)) {
                            masPrieso[skaicius][raide] = laivuKurimas.HIT;
                        } else {
                            masPrieso[skaicius][raide] = laivuKurimas.MISS;
                        }
                    } else {
                        laivuKurimas.pazymetiPriesoSuvi(mas, raide, skaicius);
                    }
                    lentele.printTable(mas, masPrieso);
                }
                print("saukite");
                String raideSauti = toUpperCase(scanner.next());
                int skaiciusSauti = scanner.nextInt();
                game = gameService.shot(game.getgameId(), user.getUserId(), raideSauti, skaiciusSauti);
            }

        }
        print("pabaiga");
    }

    private static void print(String str) {
        System.out.println(str);
    }
    public static void lauktiStatus(GameServiceInterface gameService, Game game, String status) throws InterruptedException {
        while (!game.getStatus().equals(status)) {
            game = gameService.gameStatus(game.getgameId());
            Thread.sleep(2000);
        }
    }

}

