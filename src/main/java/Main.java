import java.io.IOException;
import java.util.Arrays;

/**
 * Created by MS7 on 03.04.2015.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(args[0]);

        JsonNode node = new JsonToTreeParser(Arrays.asList(args)).getTree();

        System.out.println(node);
    }
}
