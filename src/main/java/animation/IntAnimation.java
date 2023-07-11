package animation;

public class IntAnimation extends Animation {

    private int form;
    private int to;


    public IntAnimation(int form, int to) {
        this.form = form;
        this.to = to;
    }

    public IntAnimation() {

    }



    public void reset(int form, int to) {
        super.reset();
        this.form = form;
        this.to = to;
    }

    @Override
    public void apply(float factor) {
        int value = form + Math.round((to - form) * factor);
        updateValue(value);
    }

    public void updateValue(int value) {

    }
}
