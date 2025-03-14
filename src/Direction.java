public enum Direction {
    UP, LEFT, DOWN, RIGHT;

    @Override
    public String toString() {

        switch (this) {

            case UP:
                return "UP";
            case LEFT:
                return "LEFT";
            case DOWN:
                return "DOWN";
            case RIGHT:
                return "RIGHT";
            default:
                throw new IllegalArgumentException("Unexpected direction: " + this);
        }
    }
}
