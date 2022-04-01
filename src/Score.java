import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text {

    private int score;
    private Font font;


    public Score() {
        score = 0;
        font = new Font(30);
        super.setFont(font);
        updateDisplay();


    }

    public void updateDisplay() {
        super.setText("" + score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
