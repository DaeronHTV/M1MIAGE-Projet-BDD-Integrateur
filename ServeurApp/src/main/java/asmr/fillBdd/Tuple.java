package asmr.fillBdd;

public class Tuple<L,R> {
    private L left;
    private R right;

    public Tuple(L left, R right){
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }
}
