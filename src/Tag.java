import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Reference Object for Value
 */
public class Tag
{
    private static Dictionary _instances = new Hashtable();

    /**
     * "Creates" tag by referencing existing tag of tagName.
     * @param tagName of tag to reference.
     * @return reference to existing tag inside _instances.
     */
    public static Tag createTag(String tagName)
    {
        return (Tag) _instances.get(tagName);
    }

    private final String _tagName;

    /**
     * Constructs tag of specified tag name.
     * @param tagName of the tag object.
     */
    private Tag(String tagName)
    {
        _tagName = tagName;
    }

    public String getTagName()
    {
        return _tagName;
    }

    /**
     * Loads default spreadsheet tags.
     */
    public static void loadTags()
    {
        new Tag(Value.STRING_TAG).store();
        new Tag(Value.DOUBLE_TAG).store();
        new Tag(Value.INVALID_TAG).store();
    }

    /**
     * Creates and stores new tag.
     * @param tagName of new tag.
     */
    public static void createNewTag(String tagName)
    {
        new Tag(tagName).store();
    }

    /**
     * Stores tag in _instances reference.
     */
    private void store()
    {
        _instances.put(this.getTagName(), this);
    }
}
