package be.kdg.event.converter;

import be.kdg.event.constants.RoomType;
import org.springframework.core.convert.converter.Converter;

import static be.kdg.event.constants.RoomType.*;

public class StringToRoomTypeConverter implements Converter<String, RoomType> {
    @Override
    public RoomType convert(String source) {
        return switch (source.substring(0,Math.min(source.length(),3)).toUpperCase()){
            case "CONFE" -> CONFERENCE;
            case "CLASS" -> CLASSROOM;
            case "AUDIT" -> AUDITORIUM;
            case "WORKS" -> WORKSHOP;
            default    -> REGULAR;
        };
    }
}
