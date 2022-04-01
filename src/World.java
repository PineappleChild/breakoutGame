import javafx.scene.layout.Region;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

public abstract class World extends javafx.scene.layout.Pane {
    private javafx.animation.AnimationTimer timer;
    HashSet<KeyCode> keyDownTracker = new HashSet<KeyCode>();

    public World() {
        timer = new Timer();
    }

    private class Timer extends AnimationTimer {
        @Override
        public void handle(long sec) {
            act(sec);
            for (int i = 0; i < getChildren().size(); i++) {
                if (getChildren().get(i).getClass() != Score.class) {
                    ((Actor) getChildren().get(i)).act(sec);
                }

            }
        }

    }

    public void addKeyToKeyDownTracker(KeyCode key) {
        keyDownTracker.add(key);
    }

    public void removeKeyFromKeyDownTracker(KeyCode key) {
        keyDownTracker.remove(key);
    }

    public boolean isInKeyDownTracker(KeyCode key) {
        if (keyDownTracker.contains(key)) return true;
        return false;
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void add(Actor actor) {
        getChildren().add(actor);
    }

    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
        ArrayList<A> newList = new ArrayList<A>();
        for (int i = 0; i < getChildren().size(); i++) {
            if (cls.isInstance(getChildren().get(i))) {
                newList.add((A) getChildren().get(i));
            }
        }
        return newList;
    }

    public abstract void act(long now);
}
