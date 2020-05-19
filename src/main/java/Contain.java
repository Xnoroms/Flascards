import java.util.Map;

public class Contain extends Game {

    Boolean isKeyContain = true;

    public Boolean getKeyContain() {
        return isKeyContain;
    }

    public void setKeyContain(Boolean keyContain) {
        isKeyContain = keyContain;
    }

    void containChecker(String tempValue, Map<String, String> cards) {
        if (cards.containsKey(tempValue)) {
            System.out.printf("The card with \"%s\" already exists.\n", tempValue);
            isKeyContain = false;
        }

    }
}
