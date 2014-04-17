package chess.pieces;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitalii Siryi
 */
public enum Direction {

    FORWARD(1),
    FORWARD_RIGHT(2),
    RIGHT(3),
    BACK_RIGHT(4),
    BACK(5),
    BACK_LEFT(6),
    LEFT(7),
    FORWARD_LEFT(8);

    private static Map<Integer, Direction> map = new HashMap<>();

    static {
        for (Direction d : values()){
            map.put(d.getValue(), d);
        }
    }

    private int value;

    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Direction getDirection(int value){
        if(value > 8) value = value - 8;
        else if(value < 1) value = value + 8;
        return map.get(value);
    }
}
