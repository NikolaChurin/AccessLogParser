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
    private Set<String> missingAdressBook = new HashSet<>();

    private Map<String, Integer> systemBook = new HashMap<>();
    private Map<String, Integer> browserBook = new HashMap<>();

    private int countUser;
    private int countBadRequest;
    private Set<String> ipBook = new HashSet<>();

    public void addEntry(LogEntry le) {
        LocalDateTime leTime = le.getdateTime();
        totalTraffic += le.getDataSize();
        int requestCode = le.getRequestCode();
        if (leTime.isBefore(minTime)) {
            minTime = leTime;
        }
        if (leTime.isAfter(maxTime)) {
            maxTime = leTime;
        }

        if (requestCode == 200) {
            adressBook.add(le.getPath());
        }
        if (requestCode == 404) {
            missingAdressBook.add(le.getPath());
        }
        if (requestCode > 399 && requestCode < 600) {
            countBadRequest++;
        }
        String leAgentSystem = le.getUserAgent().getSystem();
        String leAgentBrowser = le.getUserAgent().getBrowser();
        systemBook.put(leAgentSystem, systemBook.getOrDefault(leAgentSystem, 0) + 1);
        browserBook.put(leAgentBrowser, browserBook.getOrDefault(leAgentBrowser, 0) + 1);

        if (!le.getUserAgent().isBot()) {
            countUser++;
            ipBook.add(le.getIp());
        }
    }


    public int averageVisitPerHour() {
        return countUser / calculateHour();
    }

    public int averageBadRequest() {
        return countBadRequest / calculateHour();
    }

    public int averageVisitOnePerson() {
        return countUser / ipBook.size();
    }

    private int calculateHour() {
        return (int) Duration.between(minTime, maxTime).toHours();
    }


    public int getTrafficRate() {
        int calculateHour = calculateHour();
        if (calculateHour == 0) {
            return totalTraffic;
        }
        return totalTraffic / calculateHour;
    }

    public Set<String> getAdressBook() {
        return adressBook;
    }

    public Set<String> getMissingAdressBook() {
        return missingAdressBook;
    }

    public Map<String, Double> getSystemRate() {
        if (systemBook.size() == 0) {
            return new HashMap<>();
        }
        Map<String, Double> systemStatistic = new HashMap<>();

        for (Map.Entry<String, Integer> oneSystem : systemBook.entrySet()) {
            systemStatistic.put(oneSystem.getKey(), (double) oneSystem.getValue() / systemBook.size());
        }
        return systemStatistic;
    }

    public Map<String, Double> getBrowserRate() {
        if (browserBook.size() == 0) {
            return new HashMap<>();
        }
        Map<String, Double> browserStatistic = new HashMap<>();

        for (Map.Entry<String, Integer> oneBrowser : browserBook.entrySet()) {
            browserStatistic.put(oneBrowser.getKey(), (double) oneBrowser.getValue() / browserBook.size());
        }
        return browserStatistic;
    }
}
