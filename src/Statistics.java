import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime = LocalDateTime.MAX;
    private LocalDateTime maxTime = LocalDateTime.MIN;
    private Set<String> adressBook = new HashSet<>();
    private Map<String, Integer> systemBook = new HashMap<>();

    public void addEntry(LogEntry le) {
        LocalDateTime leTime = le.getdateTime();
        totalTraffic += le.getDataSize();
        if (leTime.isBefore(minTime)) {
            minTime = leTime;
        }
        if (leTime.isAfter(maxTime)) {
            maxTime = leTime;
        }

        if (le.getRequestCode() == 200) {
            adressBook.add(le.getPath());
        }
        String leAgentSystem = le.getUserAgent().getSystem();
        systemBook.put(leAgentSystem, systemBook.getOrDefault(leAgentSystem, 0) + 1);
    }

    public int getTrafficRate() {
        int calculateHour = (int) Duration.between(minTime, maxTime).toHours();
        if (calculateHour == 0) {
            return totalTraffic;
        }
        return totalTraffic / calculateHour;
    }

    public Set<String> getAdressBook() {
        return adressBook;
    }

    public Map<String, Double> getSystemRate() {
        Map<String, Double> systemStatistic = new HashMap<>();
        for (Map.Entry<String, Integer> oneSystem : systemBook.entrySet()) {
            systemStatistic.put(oneSystem.getKey(), (double) oneSystem.getValue() / systemBook.size() * 100);
        }
        return systemStatistic;
    }

}

//создайте у класса свойство (поле) int totalTraffic, в которое в методе addEntry добавляйте объём данных, отданных сервером;
//создайте свойства (поля) minTime и maxTime класса LocalDateTime и заполняйте их в методе addEntry, если время в добавляемой
// записи из лога меньше minTime или больше maxTime соответственно;
//реализуйте в классе метод getTrafficRate, в котором вычисляйте разницу между maxTime и minTime в часах и делите общий объём
// трафика на эту разницу.
//●     Сделайте коммит в ветку master вашего репозитория access-log-parser.