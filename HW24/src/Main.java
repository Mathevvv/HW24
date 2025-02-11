import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static Map<String, String> phoneNumbers = new TreeMap<>();
    private final static String NAME_REGEX = "[a-zA-Zа-яА-ЯёЁ]+";

    public static void main(String[] args) {

        while (true) {
            System.out.println("Введите номер, имя или команду LIST, EXIT");
            String input = new Scanner(System.in).nextLine();

            if (input.equals("LIST")) {
                print();
            } else if (input.equals("EXIT")) {
                return;
            } else if (input.matches(NAME_REGEX)) {
                nameRecording(input);
            } else if (input.replaceAll("\\D", "").length() == 11) {
                numberRecording(input);
            } else System.out.println("Ваш ввод некорректен");
        }
    }

    private static void numberRecording(String input) {
        if (!phoneNumbers.containsValue(input)) {
        System.out.println("Запишите имя абонента для номера " + input);
        String sinput = new Scanner(System.in).nextLine();
        if (sinput.matches(NAME_REGEX)) {
            if (!phoneNumbers.containsKey(sinput)) {
                phoneNumbers.put(sinput, input);
                System.out.println("Абонент " + sinput + " успешно добавлен с номером " + input);
            }
            else System.out.println("Этот абонент уже занесен с другим номером");
        }
        else System.out.println("Имя внесено некорректно");
    }
    else {
        System.out.println("Номер уже есть у другого абонента");
    }
    }

    private static void nameRecording(String input) {
        if (!phoneNumbers.containsKey(input)){
            System.out.println("Запишите номер абонента с именем " + input );
            String sinput = new Scanner(System.in).nextLine();
            if (sinput.replaceAll("\\D", "").length() == 11){
                sinput = sinput.replaceAll("\\D", "");
                if (!phoneNumbers.containsValue(sinput)) {
                    phoneNumbers.put(input, sinput);
                    System.out.println("Абонент " + input + " успешно добавлен с номером " + sinput);
                }
                else System.out.println("Этот номер уже есть у другого абонента");
            }
            else System.out.println("Номер внесен некорректно");
        }
        else System.out.println("Абонент " + input + " уже есть в списке");
    }


    public static void print(){
        for (Map.Entry<String, String> entry : phoneNumbers.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}