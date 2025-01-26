import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;

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
                int min = 0;
                int max = 0;
                int countLine = 0;

                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length >= 1024) {
                        throw new IncorrectSizeException("Error. В строке более 1024 символа");
                    }
                    if(min==0){min = length;}
                    min = Math.min(length, min);
                    max = Math.max(length, max);
                    countLine++;
                }
                System.out.printf("Самая коротка строка %d символов, самая длинная строка %d символов", min, max);
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
