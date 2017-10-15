import java.util.Dictionary;
import java.util.Hashtable;

public class Tag
{
    private static Dictionary _instances = new Hashtable();
    public static Tag createTag(String tagName)
    {
        return (Tag) _instances.get(tagName);
    }

    private final String _tagName;
    private Tag(String tagName)
    {
        _tagName = tagName;
    }

    public String getTagName()
    {
        return _tagName;
    }

    public static void loadTags()
    {
        new Tag(Value.STRING_TAG).store();
        new Tag(Value.DOUBLE_TAG).store();
        new Tag(Value.INVALID_TAG).store();
    }

    public static void createNewTag(String tagName)
    {
        new Tag(tagName).store();
    }

    private void store()
    {
        _instances.put(this.getTagName(), this);
    }
}
