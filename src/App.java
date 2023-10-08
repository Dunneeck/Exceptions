import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Реализуйте метод, который запрашивает у пользователя ввод дробного числа
        // (типа float),
        // и возвращает введенное значение. Ввод текста вместо числа не должно приводить
        // к падению приложения, вместо этого,
        // необходимо повторно запросить у пользователя ввод данных.
 
        System.out.println("Вот ваше число " + task1());

        task2();
        task3();

        // Разработайте программу, которая выбросит Exception, когда пользователь вводит
        // пустую строку.
        // Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
        System.out.println("Вот ваша строка " + task4());

        
    } 
    public static float task1(){
        float res = 0;
        boolean flag = true;
        while (flag) {
            try {
                res = Float.parseFloat(getValue("Введите дробное число \n(Пример: 1.2 )"));
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Не правильно введино значение! " + e.getMessage());
            }
        }
        return res;  
    }
    public static Scanner scan = new Scanner(System.in);
    public static String getValue(String message){
        System.out.println(message);
        String value = scan.nextLine();
        return value;
    }

    public static void task2(){
        System.out.println("\n задача 2");
            try {
            int[] intArray = new int[1];
            int d = 0;
            double catchedRes1 = intArray[8] / d; // я не понял что тут надо делать и тупо инициализировал массив выше
            System.out.println("catchedRes1 = " + catchedRes1);
                                                 // а также добавил проверку на ошибку 
        } catch (ArithmeticException  | IndexOutOfBoundsException e) {
            System.out.println("Catching exception: " + e.getMessage());
        }
    }
    public static void task3(){
        System.out.println("\n задача 3");
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = { 1, 2 };
            abc[3] = 9;
        } 
        // catch (NullPointerException ex) {
        // System.out.println("Указатель не может указывать на null!");
        // }  // не нужная проверка
        catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (Throwable ex) { // перетянул сюда Trowable, т.к. он не даст сработать другим ибо они ниже по иерархии
            System.out.println("Что-то пошло не так...");
        }
    }
                                                          // была не правильная проверка на ошибку
    public static void printSum(Integer a, Integer b) throws NullPointerException {
        System.out.println(a + b);
    }

    public static String task4(){
        boolean flag = true;
        String res = "";
        while (flag) {
            res = getValue("Введите строку ");
            if (res.isEmpty()) {
                try {
                    throw new EmptyStringException("Строка не должна быть пустой! ");
                } catch (EmptyStringException e) {
                    System.out.println(e.getMessage()); 
                }
            }
            else flag = false;
        }
        scan.close();
        return res;
    }
}
   
