package net.bonestherabbit.infoaccessories;

//TODO move this class to bookshelf
public enum IntercardinalDirection {

    SOUTH("S", 10, 350),
    SOUTH_WEST("SW", 10, 80, true),
    WEST("W", 80, 100),
    NORTH_WEST("NW", 100, 170, true),
    NORTH("N", 170, 190),
    NORTH_EAST("NE", 190, 260, true),
    EAST("E", 260, 280),
    SOUTH_EAST("SE", 280, 350, true);

    private final String shorthand;
    private final int min;
    private final int max;
    private final boolean inter;

    IntercardinalDirection (String abrv, int min, int max) {

        this(abrv, min, max, false);
    }

    IntercardinalDirection (String shorthand, int min, int max, boolean inter) {

        this.shorthand = shorthand;
        this.min = min;
        this.max = max;
        this.inter = inter;
    }

    public String getShorthand () {

        return this.shorthand;
    }

    public boolean isDirection (int yaw) {

        if (this == SOUTH) {

            return yaw <= 10 || yaw >= 350;
        }

        else if (this.inter) {

            return yaw > this.min && yaw < this.max;
        }

        return yaw >= this.min && yaw <= this.max;
    }
}