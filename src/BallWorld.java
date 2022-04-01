public class BallWorld extends World {

    private int score = 0;
    private Score sc;

    public BallWorld() {
        sc = new Score();
        sc.setX(100);
        sc.setY(100);
        getChildren().add(sc);
    }

    @Override
    public void act(long now) {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        sc.setScore(score);
        sc.updateDisplay();
    }
}
