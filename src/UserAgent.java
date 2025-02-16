public class UserAgent {
    String[] line;
    private final String browser;
    private final String system;

    public UserAgent(String line) {
        String[] inLine= line.trim().split("\"");
        String workLine= inLine[inLine.length-1];
        if (workLine.contains("Windows")) {
            browser = "Windows";
        } else {
            if (workLine.contains("macOS")) {
                browser = "macOS";
            } else if (workLine.contains("Linux")) {
                browser = "Linux";
            } else {
                browser = "other";
            }
        }
        if (workLine.contains("Edge")) {
            system = "Edge";
        } else {
            if (workLine.contains("Firefox")) {
                system = "Firefox";
            } else if (workLine.contains("Chrome")) {
                system = workLine.contains("OPR/") ? "Opera":"Chrome";
            } else if (workLine.contains("Opera")) {
                system = "Opera";
            } else {
                system = "other";
            }
        }
    }
}
