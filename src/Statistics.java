import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime = LocalDateTime.MAX;
    private LocalDateTime maxTime = LocalDateTime.MIN;

    public void addEntry(LogEntry le) {
        LocalDateTime leTime = le.getdateTime();
        totalTraffic += le.getDataSize();
        if (leTime.isBefore(minTime)) {
            minTime = leTime;
        }
        if (leTime.isAfter(maxTime)) {
            maxTime = leTime;
        }
    }

    public int getTrafficRate() {
        int calculateHour = (int) Duration.between(minTime, maxTime).toHours();
        if (calculateHour == 0) {
            return totalTraffic;
        }
        return totalTraffic / calculateHour;
    }

}

//создайте у класса свойство (поле) int totalTraffic, в которое в методе addEntry добавляйте объём данных, отданных сервером;
//создайте свойства (поля) minTime и maxTime класса LocalDateTime и заполняйте их в методе addEntry, если время в добавляемой
// записи из лога меньше minTime или больше maxTime соответственно;
//реализуйте в классе метод getTrafficRate, в котором вычисляйте разницу между maxTime и minTime в часах и делите общий объём
// трафика на эту разницу.
//●     Сделайте коммит в ветку master вашего репозитория access-log-parser.