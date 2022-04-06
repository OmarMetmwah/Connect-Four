package connectfour;

final class pair {
    private final char[][] child;
    private final int utility;

    public pair(char[][] child, int utility) {
        this.child = child;
        this.utility = utility;
    }

    public char[][] getChild() {
        return child;
    }

    public int getUtility() {
        return utility;
    }
}
