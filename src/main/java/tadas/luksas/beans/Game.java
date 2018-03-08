package tadas.luksas.beans;

import java.util.List;

public class Game {
    String gameId;
    String status;
    List<Suvis> events;
    String nextTurn;

    public String getNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(String nextTurn) {
        this.nextTurn = nextTurn;
    }

    public List<Suvis> getEvents() {
        return events;
    }

    public void setEvents(List<Suvis> events) {
        this.events = events;
    }

    public String getgameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
