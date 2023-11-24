package action;

public class FuncWithCard {
    private String suit;
    private String dignity;
    private String[][][] cardOnTable;
    private int player;
    private int[] cardLeft;
    public FuncWithCard(String suit, String dignity, String[][][] cardOnTable, int player, int[] cardLeft) {
        this.suit = suit;
        this.dignity = dignity;
        this.cardOnTable = cardOnTable;
        this.player = player;
        this.cardLeft = cardLeft;
    }

    public boolean isPossiblePutCard(String[][][] cardOnTable, String suit, String dignity) {
        int dig = Integer.parseInt(dignity);
        if (dig == 9) {
            return true;
        } else if (dig < 9) {
            if (suit.equals("K")) {
                return cardOnTable[0][dig - 1][0] != null;
            } else if (suit.equals("C")) {
                return cardOnTable[1][dig - 1][0] != null;
            } else if (suit.equals("P")) {
                return cardOnTable[2][dig - 1][0] != null;
            } else if (suit.equals("B")) {
                return cardOnTable[3][dig - 1][0] != null;
            }
        } else if (dig > 9) {
            if (suit.equals("K")) {
                return cardOnTable[0][dig - 3][0] != null;
            } else if (suit.equals("C")) {
                return cardOnTable[1][dig - 3][0] != null;
            } else if (suit.equals("P")) {
                return cardOnTable[2][dig - 3][0] != null;
            } else if (suit.equals("B")) {
                return cardOnTable[3][dig - 3][0] != null;
            }
        }
        return false;
    }

    public String setIconForCard(String suit, String dignity) {
        if (suit.equals("C")) {
            if (dignity.equals("2")) {
                return "data/13c.jpg";
            } else if (dignity.equals("3")) {
                return "data/12c.jpg";
            } else if (dignity.equals("4")) {
                return "data/11c.jpg";
            } else if (dignity.equals("5")) {
                return "data/10c.jpg";
            } else if (dignity.equals("6")) {
                return "data/1c.jpg";
            } else if (dignity.equals("7")) {
                return "data/2c.jpg";
            } else if (dignity.equals("8")) {
                return "data/3c.jpg";
            } else if (dignity.equals("9")) {
                return "data/4c.jpg";
            } else if (dignity.equals("10")) {
                return "data/5c.jpg";
            } else if (dignity.equals("11")) {
                return "data/6c.jpg";
            } else if (dignity.equals("12")) {
                return "data/7c.jpg";
            } else if (dignity.equals("13")) {
                return "data/8c.jpg";
            } else if (dignity.equals("14")) {
                return "data/9c.jpg";
            }
        } else if (suit.equals("K")) {
            if (dignity.equals("2")) {
                return "data/13k.jpg";
            } else if (dignity.equals("3")) {
                return "data/12k.jpg";
            } else if (dignity.equals("4")) {
                return "data/11k.jpg";
            } else if (dignity.equals("5")) {
                return "data/10k.jpg";
            } else if (dignity.equals("6")) {
                return "data/1k.jpg";
            } else if (dignity.equals("7")) {
                return "data/2k.jpg";
            } else if (dignity.equals("8")) {
                return "data/3k.jpg";
            } else if (dignity.equals("9")) {
                return "data/4k.jpg";
            } else if (dignity.equals("10")) {
                return "data/5k.jpg";
            } else if (dignity.equals("11")) {
                return "data/6k.jpg";
            } else if (dignity.equals("12")) {
                return "data/7k.jpg";
            } else if (dignity.equals("13")) {
                return "data/8k.jpg";
            } else if (dignity.equals("14")) {
                return "data/9k.jpg";
            }
        } else if (suit.equals("P")) {
            if (dignity.equals("2")) {
                return "data/13p.jpg";
            } else if (dignity.equals("3")) {
                return "data/12p.jpg";
            } else if (dignity.equals("4")) {
                return "data/11p.jpg";
            } else if (dignity.equals("5")) {
                return "data/10p.jpg";
            } else if (dignity.equals("6")) {
                return "data/1p.jpg";
            } else if (dignity.equals("7")) {
                return "data/2p.jpg";
            } else if (dignity.equals("8")) {
                return "data/3p.jpg";
            } else if (dignity.equals("9")) {
                return "data/4p.jpg";
            } else if (dignity.equals("10")) {
                return "data/5p.jpg";
            } else if (dignity.equals("11")) {
                return "data/6p.jpg";
            } else if (dignity.equals("12")) {
                return "data/7p.jpg";
            } else if (dignity.equals("13")) {
                return "data/8p.jpg";
            } else if (dignity.equals("14")) {
                return "data/9p.jpg";
            }
        } else if (suit.equals("B")) {
            if (dignity.equals("2")) {
                return "data/13b.jpg";
            } else if (dignity.equals("3")) {
                return "data/12b.jpg";
            } else if (dignity.equals("4")) {
                return "data/11b.jpg";
            } else if (dignity.equals("5")) {
                return "data/10b.jpg";
            } else if (dignity.equals("6")) {
                return "data/1b.jpg";
            } else if (dignity.equals("7")) {
                return "data/2b.jpg";
            } else if (dignity.equals("8")) {
                return "data/3b.jpg";
            } else if (dignity.equals("9")) {
                return "data/4b.jpg";
            } else if (dignity.equals("10")) {
                return "data/5b.jpg";
            } else if (dignity.equals("11")) {
                return "data/6b.jpg";
            } else if (dignity.equals("12")) {
                return "data/7b.jpg";
            } else if (dignity.equals("13")) {
                return "data/8b.jpg";
            } else if (dignity.equals("14")) {
                return "data/9b.jpg";
            }
        }
        return "";
    }
}