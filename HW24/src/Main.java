import java.util.*;

public class Main {
    public static Map<String, Set<String>> phoneNumbers = new TreeMap<>();
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
                addByName(input);
            } else if (input.replaceAll("\\D", "").length() == 11) {
                addByNum(input);
            } else System.out.println("Ваш ввод некорректен");
        }
    }



    private static void addByName(String input) {
        if (phoneNumbers.containsKey(input)) System.out.println("Абонент с именем " + input + " уже есть в списке");
        System.out.println("Введите номер абонента " + input + ":");
        String sinput = new Scanner(System.in).nextLine();
        sinput = sinput.replaceAll("\\D+", "");
        if (sinput.length()!=11) {
            System.out.println("Это не номер!");
            return;
        }

        for(Map.Entry<String, Set<String>> contact : phoneNumbers.entrySet()){
            if (contact.getValue().contains(sinput)){
                System.out.println("Номер " +  sinput + " уже есть у другого абонента");
                return;
            }
        }
        addToBook(input, sinput);
    }

    private static void addByNum(String input) {

        input = input.replaceAll("\\D+", "");
        for(Map.Entry<String, Set<String>> contact : phoneNumbers.entrySet()){
            if (contact.getValue().contains(input)){
                System.out.println("Номер " +  input + " уже есть у другого абонента");
                return;
            }
        }
        System.out.println("Введите имя абонента для номера " + input + ":");
        String sinput = new Scanner(System.in).nextLine();
        if (!sinput.matches(NAME_REGEX)) {
            System.out.println("Это не имя!");
            return;
        }
        addToBook(sinput, input);

    }



    public static void print(){
        for (Map.Entry<String, Set<String>> entry : phoneNumbers.entrySet()) {
            System.out.println("Абонент: " + entry.getKey() + " : " );
            for (String num: entry.getValue()) {
                System.out.println("\t" + num);
            }
        }
    }

    public static void addToBook(String input, String sinput) {
        if (phoneNumbers.containsKey(input)) {
            phoneNumbers.get(input).add(sinput);
            System.out.println("Абоненту " + input + " добавлен номер " + sinput);
        }
        else {
            Set<String> numbers = new TreeSet<>();
            numbers.add(sinput);
            phoneNumbers.put(input, numbers);
            System.out.println("Абонент " + input + " с номером " + sinput + " успешно записан в список");
        }
    }

}