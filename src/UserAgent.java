public class UserAgent {
    private final String browser;
    private final String system;

    public UserAgent(String line) {
        String[] inLine= line.trim().split("\"");
        String workLine= inLine[inLine.length-1];
        if (workLine.contains("Windows")) {
            system = "Windows";
        } else {
            if (workLine.contains("macOS")) {
                system = "macOS";
            } else if (workLine.contains("Linux")) {
                system = "Linux";
            } else {
                system = "other";
            }
        }
        if (workLine.contains("Edge")) {
            browser = "Edge";
        } else {
            if (workLine.contains("Firefox")) {
                browser = "Firefox";
            } else if (workLine.contains("Chrome")) {
                browser = workLine.contains("OPR/") ? "Opera":"Chrome";
            } else if (workLine.contains("Opera")) {
                browser = "Opera";
            } else {
                browser = "other";
            }
        }
    }

    public String getBrowser() {
        return browser;
    }

    public String getSystem() {
        return system;
    }
}
