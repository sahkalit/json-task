import java.util.List;

/**
 * Created by MS7 on 03.04.2015.
 */
public class JsonNode {

    private String id;
    private String type;
    private String title;
    private List<JsonNode> children;


    public void setType(String type) {
        this.type = type;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChildren(List<JsonNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "JsonNode{" +
            "id='" + id + '\'' +
            ", children=" + children +
            ", title='" + title + '\'' +
            '}';
    }
}