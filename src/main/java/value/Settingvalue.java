package value;
public class Settingvalue {

    public int playerCount = 3;
    public int cardCount = 36;
    public String direction = "По часовой стрелке";

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}