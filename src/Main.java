import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        System.out.println("Введите путь к файлу");
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (!fileExists || isDirectory) {
                System.out.println("Указанный файл не существует или является путем к папке а не файлу");
                continue;
            }

            count++;
            System.out.println("Путь указан верно,");
            System.out.println("Это файл номер " + count);

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                int countLine = 0;
                int countGoogleBot = 0;
                int countYandexBot = 0;

                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length >= 1024) {
                        throw new IncorrectSizeException("Error. В строке более 1024 символа");
                    }
                    countLine++;

                    String[] parsLine = line.split(";");
                    int beginIndex = line.indexOf('(');
                    int endIndex = line.indexOf(')', beginIndex);
                    String subLine = line.substring(beginIndex + 1, endIndex).split(";")[1];
                    if (!subLine.contains("/")) {
                        continue;
                    }
                    String userAgent = subLine.substring(0, subLine.indexOf("/")).trim();
                    switch (userAgent) {
                        case "Googlebot":
                            countGoogleBot++;
                            break;
                        case "YandexBot":
                            countYandexBot++;
                            break;
                    }
                }
                System.out.println(String.format("В файле присутствует %d%% обращений Googlebot\n" +
                        "В файле присутствует %d%% обращений YandexBot", countGoogleBot*100 / countLine, countYandexBot*100 / countLine));
                System.out.printf("Количество строк = " + countLine);


            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
