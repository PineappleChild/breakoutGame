
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.Node;

import java.util.ArrayList;

public abstract class Actor extends javafx.scene.image.ImageView {

    public Actor() {

    }

    public void move(double dx, double dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }

    public World getWorld() {
        if (getParent() != null) {
            return (World) getParent();
        }
        return null;
    }

    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }

    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
        ArrayList<A> arr = (ArrayList<A>) getWorld().getObjects(cls);
        ArrayList<A> returnArr = new ArrayList<A>();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getClass() != this.getClass()
                    && arr.get(i).getClass() == cls
                    && intersects(arr.get(i).getBoundsInLocal())) {
                returnArr.add(arr.get(i));
            }
        }

        return returnArr;
//		for(int i = 0; i<getWorld().getObjects(cls).size(); i++){
//			if(getWorld().getObjects(cls).get(i) != this && getWorld().getObjects(cls).get(i).getClass() == cls &&
//					getWorld().getObjects(cls).get(i).intersects(getX(),getY(),getWidth(),getHeight())){
//
//				arr.add(getWorld().getObjects(cls).get(i));
//			}
//		}
//		return arr;

    }

    public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {

        ArrayList<A> arr = (ArrayList<A>) getWorld().getObjects(cls);

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getClass() != this.getClass()
                    && arr.get(i).getClass() == cls
                    && intersects(arr.get(i).getBoundsInLocal())) {
                return arr.get(i);
            }
        }

        return null;
//		for(int i = 0; i<getWorld().getObjects(cls).size(); i++){
//			if(getWorld().getObjects(cls).get(i) != this && getWorld().getObjects(cls).get(i).getClass() == cls &&
//					getWorld().getObjects(cls).get(i).intersects(getX(),getY(),getWidth(),getHeight())){
//
//				return getWorld().getObjects(cls).get(i);
//			}
//		}
//		return null;
    }

    public abstract void act(long now);
}
