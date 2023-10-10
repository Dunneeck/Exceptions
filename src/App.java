import ThirdHomeWork.ThirdHW;

public class App {

    public static void main(String[] args) throws Exception {
        // showHW2();
        ThirdHW hw3 = new ThirdHW();
        hw3.App();
        
    } 

    public static void showHW2(){
        // Реализуйте метод, который запрашивает у пользователя ввод дробного числа
        // (типа float),
        // и возвращает введенное значение. Ввод текста вместо числа не должно приводить
        // к падению приложения, вместо этого,
        // необходимо повторно запросить у пользователя ввод данных.
 
        System.out.println("Вот ваше число " + SecondHW.task1());

        SecondHW.task2();
        SecondHW.task3();

        // Разработайте программу, которая выбросит Exception, когда пользователь вводит
        // пустую строку.
        // Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
        System.out.println("Вот ваша строка " + SecondHW.task4());
    }
}
   
