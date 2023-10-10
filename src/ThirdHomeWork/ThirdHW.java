package ThirdHomeWork;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ThirdHW {
    /**
     * запрос данных, разбика в массив, сортировка, и запись в файлы
     */
    public void App() {
        String personData = getPersonData("Введите данные человека! (Ф И О датарождения тел.номер пол)");
        System.out.println(personData);
        String[] splitPersonData = splitPersonData(personData);
        Object[] parsePersonData = parsePersonData(splitPersonData);
        Object[] sortedPersonData = sortPersonData(parsePersonData);
        saveArrayToFile(sortedPersonData);
        scanner.close();
    }

    /**
     * сохранение данных человека в файл с названием по фамилии
     * @param personData данные чела
     */
    private static void saveArrayToFile(Object[] personData) {
        String fileName = personData[0].toString() + ".txt";
        
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));){
            
            for (Object obj : personData) {
                out.print("<" + obj + ">");
            }
            out.print("\n");
            System.out.println("Данные успешно записаны в файл '" + fileName + "'");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    /**
     * сортирует данные фио дата рождения тел.номер пол
     * @param personData данные чела
     * @return отсортированый массив
     */
    private Object[] sortPersonData(Object[] personData) {
        Object[] tmp = new Object[6];
        for (int i = 0, s = 0; i < personData.length; i++) {
            if (personData[i].getClass().equals(String.class))
                tmp[s++] = personData[i];
            if (personData[i].getClass().equals(Long.class))
                tmp[4] = personData[i];
            if (personData[i].getClass().equals(LocalDate.class))
                tmp[3] = personData[i];
            if (personData[i].getClass().equals(Character.class))
                tmp[5] = personData[i];
        }
        return tmp;
    }

    /**
     * метод парсит данные чела в нужные классы
     * @param splitPersonData данные чела в строках
     * @return данные чела в нужных классах
     */
    private Object[] parsePersonData(String[] splitPersonData) {
        Object[] res = new Object[6];
        int countStr = 0;
        int countLocalDate = 0;
        int countChar = 0;
        int countphoneNumber = 0;

        for (int i = 0, j = 0; i < splitPersonData.length; i++) {
            try {
                if (isString((String) splitPersonData[i]) == -1) {
                    res[j++] = splitPersonData[i];
                    countStr++;
                }
                if (isString((String) splitPersonData[i]) == 1) {
                    res[j++] = splitPersonData[i].charAt(0);
                    countChar++;
                }
                if (isNumeric((String) splitPersonData[i])) {
                    res[j++] = Long.parseLong(splitPersonData[i]);
                    countphoneNumber++;
                }
                if (isDate((String) splitPersonData[i])) {
                    try {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate localDate = LocalDate.parse(splitPersonData[i], dtf);
                        res[j++] = localDate;
                        countLocalDate++;
                    } catch (DateTimeParseException e) {
                        System.out.println(e.getMessage());
                    }
                }
                // System.out.println(res[i].getClass());
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        // проверка на соответствие даных
        if (countChar != 1 || countLocalDate != 1 || countStr != 3 || countphoneNumber != 1) {
            System.out.println("Вы ввели не правильные данные! Перезапишите данные!");
            splitPersonData[0] = getPersonData("Введите фамилию!");
            splitPersonData[1] = getPersonData("Введите имя!");
            splitPersonData[2] = getPersonData("Введите отчество!");
            splitPersonData[3] = getPersonData("Введите дату рождения!");
            splitPersonData[4] = getPersonData("Введите номер телефона!");
            splitPersonData[5] = getPersonData("Введите пол!");
            parsePersonData(splitPersonData);
        }
        return res;
    }

    /**
     * проверка на соответсвие дате
     * @param dateStr проверка строки 
     * @return true - если строка подходит под парсинг в дату
     */
    private boolean isDate(String dateStr) {
        int countDot = 0;
        for (int i = 0; i < dateStr.length(); i++) {
            if (dateStr.charAt(i) == '.') {
                countDot++;
            }
        }
        return countDot == 2 ? true : false;
    }

    /**
     * проверка на строку и чар елемент
     * @param fio проверка строки
     * @return 1 - если char елем. 0 - если в строке есть числа, -1 - если это строка без цифр
     */
    private int isString(String fio) {
        String tmp;
        if (fio.length() == 1 && !isNumeric(fio))
            return 1;

        for (int i = 0; i < fio.length(); i++) {
            tmp = "" + fio.charAt(i);
            if (isNumeric(tmp)) {
                return 0;
            }
        }
        return -1;
    }

    /**
     * проверка на число
     * @param str строка для проверки
     * @return true если  число
     */
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * разбивает строку данных в массив строк
     * @param personData строка данных чела
     * @return массив данных чела
     */
    private String[] splitPersonData(String personData) {
        String[] res = null;
        boolean flag = true;
        while (flag) {
            res = personData.split(" ");
            try {
                if (res.length != 6) {
                    String message = res.length < 6 ? "Вы ввели меньше данных чем требуется! "
                            : "Вы ввели больше данных чем требуется! ";
                    throw new CheckVolumePersonData(message);
                } else
                    flag = false;
            } catch (CheckVolumePersonData e) {
                personData = getPersonData(e.getMessage() + "\nПерезапишите данные!");
            }
        }
        return res;
    }

    private Scanner scanner = new Scanner(System.in);
    private String getPersonData(String message) {
        System.out.println(message);
        String res = scanner.nextLine();
        return res;
    }
}