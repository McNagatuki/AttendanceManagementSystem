package net.kunmc.lab.attendancemanagementsystem;

public enum CardinalDirections {
    EAST("east"),
    WEST("west"),
    NORTH("north"),
    SOUTH("south");

    private final String str;

    private CardinalDirections(String str) {
        this.str = str;
    }

    public static boolean isValid(String str) {
        if (str.equals("east")) return true;
        if (str.equals("west")) return true;
        if (str.equals("SOUTH")) return true;
        if (str.equals("NORTH")) return true;
        return false;
    }
}
