package scene;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Rect;

import java.util.ArrayList;
import java.util.List;

public class Group extends Node {


    private Node focus;
    private final List<Node> children;



    public Group() {
        this(0, 0);
    }


    public Group(int x, int y) {
        super(x, y);
        children = new ArrayList<>();
    }


    public void removeNode(Node node) {
        if(node.getOwner() == this) {
            children.remove(node);
            node.setOwner(null);
        }
    }

    public void addNode(Node node) {
        Group pre = node.getOwner();
        if(pre != null) {
            pre.removeNode(node);
        }
        children.add(node);
        node.setOwner(this);
    }


    public int getSize() {
        return children.size();
    }


    @Override
    public boolean pointerReleased(int x, int y) {
        if(focus != null) {
            Node node = focus;
            this.focus = null;
            return node.pointerReleased(x, y);
        }
        return false;
    }


    @Override
    public boolean pointerDragged(int x, int y) {
        return false;
    }




    public boolean firePointerPressed(Node node, int local_x, int local_y) {
        if(node != null) {
            focus = node;
            return node.pointerPressed(local_x, local_y);
        }
        return false;
    }

    @Override
    public boolean pointerPressed(int x, int y) {
        int local_x = 0;
        int local_y = 0;
        Node target = null;
        for (Node node : children) {
            if(node.adjustClick(x, y)) {
                local_x = x - node.x;
                local_y = y - node.y;
                target = node;
                break;
            }
        }
        return firePointerPressed(target, local_x, local_y);
    }


    @Override
    public void update() {
        super.update();



        for (Node node : children) {
            if(node.isShowing()) {
                node.update();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(focus != null) {
            canvas.drawRect(Rect.makeXYWH(focus.getX(), focus.getY(), focus.getWidth(), focus.getHeight()), new Paint().setStroke(true).setColor(0xFF00FF00));
        }
        for (int i = 0; i < children.size(); i++) {
            Node node = children.get(i);
            drawChild(canvas, node, i);
        }
    }


    @Override
    protected void show() {
        super.show();
        for (Node node : children) {
            if(node.isVisible()) {
                node.show();
            }
        }
    }


    @Override
    protected void hide() {
        super.hide();
        for (Node node : children) {
            if(node.isVisible()) {
                node.hide();
            }
        }
    }

    private void drawChild(Canvas canvas, Node node, int i) {
        if(node.isVisible()) {
            int save = canvas.save();
            canvas.translate(node.getX(), node.getY());
            node.draw(canvas);
            canvas.restoreToCount(save);
        }

    }

    protected void clear() {
        children.clear();
    }
}
