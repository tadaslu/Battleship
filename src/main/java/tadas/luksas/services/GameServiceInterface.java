package tadas.luksas.services;

import tadas.luksas.beans.Game;
import tadas.luksas.beans.User;

import java.util.List;

public interface GameServiceInterface {

    User createUser(String name, String email);

    Game joinGame(String userId);

    Game setupShips(String gameId, String userId, List<String> laivai);

    Game gameStatus (String gameId);

    Game shot(String id, String userId, String gameId, int skaiciusSauti);




}
