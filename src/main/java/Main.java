import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Map<String, String> cards = new LinkedHashMap<>();
        Map<String, String> reversedCards = new LinkedHashMap<>();

        //MENU

        boolean exit = true;
        while (exit) {
            System.out.printf("\nInput the action (add, remove, import, export, ask, exit): \n");

            List<String> tempValues = new ArrayList<>(cards.values());
            List<String> tempKeys = new ArrayList<>(cards.keySet());

            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {

                case "add":
                    System.out.printf("The card: ");
                    String tempKey = scanner.nextLine();
                    String key = "";
                    if (cards.containsKey(tempKey)) {
                        System.out.printf("The card \"%s\" already exists.\n", tempKey);
                        break;
                    } else {
                        key = tempKey;
                    }

                    System.out.printf("The definition of the card: ");
                    String tempValue = scanner.nextLine();
                    String value = "";
                    if (cards.containsValue(tempValue)) {

                        System.out.printf("The definition \"%s\" already exists.\n", tempValue);
                        break;
                    } else {
                        value = tempValue;
                    }
                    System.out.printf("The pair (\"%s\":\"%s\") has been added.\n", key, value);
                    cards.put(key, value);
                    reversedCards.put(value,key);
                    break;
                case "remove":
                    System.out.println("The card: ");
                    key = scanner.nextLine();
                    if (cards.containsKey(key)){
                    cards.remove(key);
                    while (reversedCards.values().remove(key));

                        System.out.println("The card has been removed.\n");
                        break;
                    }
                    else {
                        System.out.printf("Can't remove \"%s\": there is no such card.\n", key);
                        break;
                    }
                case "export":

                    // print to file all map elements in separate lines

                    System.out.println("File name:");
                    String fileNameToExport =scanner.nextLine();
                    File fileExport = new File("./src/main/java/"+fileNameToExport);
                   try (PrintWriter printWriter = new PrintWriter(fileExport)) {
                       for (int i=0; i< cards.size();i++){
                           printWriter.println(tempKeys.get(i));
                           printWriter.println(tempValues.get(i));
                       }

                   } catch (IOException e) {
                       System.out.printf("An exception occurs %s\", e.getMessage()");
                   }
                    System.out.printf("%s cards have been saved. \n", cards.size());

                    break;

                case "import":
                    String importKey;
                    String importValue;
                    System.out.println("File name:");
                    String fileNameToImport ="./src/main/java/"+ scanner.nextLine();
                    File fileImport = new File(fileNameToImport);
                    int j = 0;                    try (Scanner fileScanner = new Scanner(fileImport)) {
                        while (fileScanner.hasNext()) {
                            importKey = fileScanner.nextLine();
                            importValue = fileScanner.nextLine();
                            cards.put(importKey,importValue);
                            reversedCards.put(importValue,importKey);
                            j++;
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("No file found: " + fileNameToImport);
                    }

                    System.out.printf("%s cards have been loaded. \n", j);

                    break;
                case "ask":
                    System.out.println("How many times to ask?");

                    int numToAsk = Integer.parseInt(scanner.nextLine());

                    Random random = new Random(1);

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
