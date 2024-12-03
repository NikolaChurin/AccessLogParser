import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число 1:");
        int number1 = scanner.nextInt();
        System.out.println("Введите число 2:");
        int number2 = scanner.nextInt();
        System.out.println("Сумма: " + (number1+number2));
        System.out.println("Разность: " +(number1-number2));
        System.out.println("Произведение: "+ number1*number2);
        System.out.println("Частное: " + (double)(number1/number2));
    }
}
