package utils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * A classe DataHora oferece formatação de data e hora utilizando a classe DateTimeFormatter do Java.
 */
public class DataHora {

    /**
     * Formato padrão para data e hora no sistema ("DD/MM/yyyy HH:mm:ss").
     * Este formato é utilizado para representar datas e horas com informações detalhadas.
     */
    public static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("DD/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());

    /**
     * Formato para representação de data no sistema ("DD/MM/yyyy").
     * Este formato é utilizado para representar apenas a parte da data sem informações de hora.
     */
    public static final DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("DD/MM/yyyy");
}
