public class SUpdate {
    private Player p1,p2,p3;
    private boolean bosswin, endGame;

    public SUpdate(Player p1, Player p2, Player p3, boolean bosswin, boolean endGame) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.bosswin = bosswin;
        this.endGame = endGame;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public Player getP3() {
        return p3;
    }

    public void setP3(Player p3) {
        this.p3 = p3;
    }

    public boolean isBosswin() {
        return bosswin;
    }

    public void setBosswin(boolean bosswin) {
        this.bosswin = bosswin;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}
