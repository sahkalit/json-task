
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by MS7 on 03.04.2015.
 */
public class JsonToTreeParser {

    private List<String> skipIds;

    /**
     * @param skipIds ids которые будут пропущены
     */
    public JsonToTreeParser(List<String> skipIds) {
        this.skipIds = skipIds;
    }

    /**
     * @return JsonNode корневой элемент дерева
     * @throws IOException
     */
    public JsonNode getTree() throws IOException {
        JsonFactory f = new JsonFactory();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.json").getFile());
        JsonParser jp = f.createJsonParser(file);

        jp.nextToken(); //Инициализация парсера
        JsonNode rootNode = parseNode(jp);
        jp.close();
        return rootNode;
    }

    /**
     * @param jp дексприптор парсера
     * @return JsonNode
     * @throws IOException
     */
    private JsonNode parseNode(JsonParser jp) throws IOException {
        JsonNode currNode = new JsonNode();

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            jp.nextToken();

            switch (fieldName) {
                case ("id"):
                    currNode.setId(jp.getText());

                    break;
                case ("type"): {
                    currNode.setType(jp.getText());

                    break;
                }
                case ("title"): {
                    currNode.setTitle(jp.getText());

                    break;
                }
                case ("children"): {
                    if (skipIds.contains(currNode.getId())) {
                        while (jp.nextToken() != JsonToken.END_ARRAY) {/** NOPE */}
                    } else {
                        currNode.setChildren(parseChildren(jp));
                    }

                    break;
                }
                default: {
                    throw new IllegalStateException("Unrecognized field '" + fieldName + "'!");
                }
            }
        }

        return currNode;
    }

    private List<JsonNode> parseChildren(JsonParser jp) throws IOException {
        List<JsonNode> nodes = new ArrayList<>();

        while (jp.nextToken() != JsonToken.END_ARRAY) {
            nodes.add(parseNode(jp));
        }

        return nodes;
    }
}