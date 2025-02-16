import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private final String ip;
    private final LocalDateTime dateTime;
    private final HttpMethode httpMethode;
    private final String path;
    private final int requestCode;
    private final int dataSize;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String line) {
        String[] inline = line.split("\"");
        ip = line.trim().substring(0, line.indexOf(" "));

        String dateTimeString = line.substring(line.indexOf("[") + 1, line.indexOf("]") - 6);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
        dateTime = LocalDateTime.parse(dateTimeString, dt);

        String[] stringMethode = inline[1].split(" ");
        httpMethode = HttpMethode.valueOf(stringMethode[0]);
        if (stringMethode[1].contains("?")) {
            path = stringMethode[1].substring(0, stringMethode[1].indexOf("?"));
        } else {
            path = stringMethode[1];
        }

        String[] stringsCodeRequest = inline[2].trim().split(" ");
        requestCode = Integer.parseInt(stringsCodeRequest[0]);
        dataSize = Integer.parseInt(stringsCodeRequest[1]);
        referer = inline[3];
        userAgent = new UserAgent(inline[5]);
    }

    public String getIp() {
        return ip;
    }

    public LocalDateTime getdateTime() {
        return dateTime;
    }

    public HttpMethode getHttpMethode() {
        return httpMethode;
    }

    public String getPath() {
        return path;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public int getDataSize() {
        return dataSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}
//  Выполняйте задание в том же проекте AccessLogParser в ветке master.
//
//●     В предыдущем задании вы писали код, который “разбирает” строку из лог-файла на составляющие.
// В этом задании вам необходимо разработать класс LogEntry, объекты которого будут соответствовать строкам из лог-файла, а свойства (поля) —
// отдельным частям каждой такой строки.
//
//●     Создайте класс LogEntry со свойствами (полями), соответствующими компонентам строк лог-файла: IP-адресу, дате и времени запроса,
// методу запроса, пути запроса, коду ответа, размеру отданных сервером данных, referer, а также User-Agent. Возможные методы HTTP-запросов положите в enum.
// Типы остальных полей определите самостоятельно.