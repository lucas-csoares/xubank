package utils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DataHora {
    public static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("DD/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());

    public static final DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("DD/MM/yyyy");
}
