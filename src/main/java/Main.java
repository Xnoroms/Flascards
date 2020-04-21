import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("test");
        Scanner scanner = new Scanner(System.in);
        Map<String, String> cards = new LinkedHashMap<>();
        Map<String, String> reversedCards = new LinkedHashMap<>();

        //MENU

        boolean exit = true;
        while (exit) {
            System.out.println("Input the action (add, remove, import, export, ask, exit): ");


            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {

                case "add":
                    System.out.printf("The card: ");
                    String tempKey = scanner.nextLine();
                    String key = "";
                    if (cards.containsKey(tempKey)) {
                        System.out.printf("The card \"%s\" already exists. \n \n", tempKey);
                        break;
                    } else {
                        key = tempKey;
                    }

                    System.out.printf("The definition of the card: ");
                    String tempValue = scanner.nextLine();
                    String value = "";
                    if (cards.containsValue(tempValue)) {

                        System.out.printf("The definition \"%s\" already exists. \n \n", tempValue);
                        break;
                    } else {
                        value = tempValue;
                    }
                    System.out.printf("\n The pair (\"%s\":\"%s\") has been added. \n \n", key, value);
                    cards.put(key, value);
                    reversedCards.put(value,key);
                    break;
                case "remove":
                    System.out.println("The card: ");
                    key = scanner.nextLine();
                    if (cards.containsKey(key)){
                    cards.remove(key);
                    while (reversedCards.values().remove(key));

                        System.out.println("The card has been removed.\n \n");
                        break;
                    }
                    else {
                        System.out.printf("Can't remove \"%s\": there is no such card.\n \n", key);
                        break;
                    }
                case "ask":
                    System.out.println("How many times to ask?");

                    int numToAsk = Integer.parseInt(scanner.nextLine());

                    List<String> tempValues = new ArrayList<>(cards.values());
                    List<String> tempKeys = new ArrayList<>(cards.keySet());

                    Random random = new Random(1);
                    System.out.println(cards.size());

                    for (int i = 0; i < numToAsk; i++) {
                        Integer  draw = random.nextInt(cards.size());
                        String userDefinition;
                        String correctDefinition = tempValues.get(draw);
                        System.out.printf("Print the definition of \"%s\":\n", tempKeys.get(draw));
                        userDefinition = scanner.nextLine();
                        if (userDefinition.equals(correctDefinition)) {
                            System.out.println("Correct answer");
                        } else if (tempValues.contains(userDefinition)) {
                            System.out.printf("Wrong answer. The correct one is \"%s\", you've just written the definition of \"%s\". \n", correctDefinition, reversedCards.get(userDefinition));
                        } else {
                            System.out.printf("Wrong answer. The correct one is \"%s\"\n", correctDefinition);

                        }
                    }
                    break;


                case "exit":
                    System.out.println("Bye bye!");
                    exit = false;

            }
        }
    }
}

        //type the definition and key to cards

       /* System.out.println("Input the number of cards: ");
        int firstNum = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < firstNum; i++) {
            System.out.printf("The card #%d: \n", i + 1);
            String tempKey = scanner.nextLine();
            String key = "";
            if (cards.containsValue(tempKey)) {
                do {
                    System.out.printf("The card \"%s\" already exists. Try again: \n", tempKey);
                    tempKey = scanner.nextLine();
                }
                while (cards.containsValue(tempKey));
                key = tempKey;
            } else {
                key = tempKey;
            }
            System.out.printf("The definition of the card #%d: \n", i + 1);
            String tempValue = scanner.nextLine();
            String value = "";
            if (cards.containsKey(tempValue)) {
                do {
                    System.out.printf("\"The definition \"%s\" already exists. Try again: \n", tempValue);
                    tempValue = scanner.nextLine();
                } while (cards.containsKey(tempValue));
                value = tempValue;
            } else {
                value = tempValue;
            }

            cards.put(value, key);
        }

        //questions and answers with correct suggestions

        List<String> tempKeys = new ArrayList<>(cards.values());
        List<String> tempValues = new ArrayList<>(cards.keySet());

        for (int i = 0; i < firstNum; i++) {
            String userDefinition;
            String correctDefinition = tempValues.get(i);
            System.out.printf("Print the definition of \"%s\":\n", tempKeys.get(i));
            userDefinition = scanner.nextLine();
            if (userDefinition.equals(correctDefinition)) {
                System.out.println("Correct answer");
            } else if (tempValues.contains(userDefinition)) {
                System.out.printf("Wrong answer. The correct one is \"%s\", you've just written the definition of \"%s\". \n", correctDefinition, output.get(userDefinition));

            } else {
                System.out.printf("Wrong answer. The correct one is \"%s\"\n", correctDefinition);
            }
        }
    }
}
*/