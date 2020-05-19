import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Game {

        public Scanner scanner = new Scanner(System.in);
        public Map<String, String> cards = new LinkedHashMap<>();
        public Map<String, String> reversedCards = new LinkedHashMap<>();
        public String key = "";
        public String value = "";

        Menu menu = new Menu();

    public void play(){

        boolean exit = true;
        while (exit) {
           // System.out.printf("\nInput the action (add, remove, import, export, ask, exit): \n");
           String choice = menu.menu(scanner);

            switch (choice.toLowerCase()) {

                case "add":

                    add();
                    break;

                case "remove":

                    remove();
                    break;

                case "export":

                    export();
                    break;

                case "import":

                    importFromFile();
                    break;

                case "ask":
                    ask();
                    break;


                case "exit":

                    System.out.println("Bye bye!");
                    exit = false;

            }
         }
        }
    public void add(){

        String tempValue = null;
        String tempKey;

        Contain contain = new Contain();

        System.out.printf("The card: ");

        tempKey = scanner.nextLine();


            contain.containChecker(tempKey, cards);

        if (contain.getKeyContain()) {

            System.out.printf("The definition of the card: ");
            tempValue = scanner.nextLine();
            contain.containChecker(tempValue, reversedCards);
        }
            if (contain.getKeyContain()){

            System.out.printf("The pair (\"%s\":\"%s\") has been added.\n", tempKey, tempValue);

            cards.put(tempKey, tempValue);
            reversedCards.put(tempValue, tempKey);
        }
        System.out.println(cards);
    }
    public void remove(){
      System.out.println("The card: ");
              key = scanner.nextLine();
              if (cards.containsKey(key)){
              cards.remove(key);
              while (reversedCards.values().remove(key));

              System.out.println("The card has been removed.\n");
                return;
              }
              else {
                  System.out.printf("Can't remove \"%s\": there is no such card.\n", key);
      }
    }
    public void export(){

        // print to file all map elements in separate lines
        List<String> tempValues = new ArrayList<>(cards.values());
        List<String> tempKeys = new ArrayList<>(cards.keySet());
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


    }

    public void importFromFile(){

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

        }

        public void ask(){

            List<String> tempValues = new ArrayList<>(cards.values());
            List<String> tempKeys = new ArrayList<>(cards.keySet());
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
        }
}
