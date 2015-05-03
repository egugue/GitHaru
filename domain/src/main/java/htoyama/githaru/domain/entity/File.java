package htoyama.githaru.domain.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Represents File contained by the Gist in the Domain layer.
 */
//TODO: reconsider if the class must be in Value Object.
public class File {
    public String name;
    public int size;
    public String rawUrl;
    public String type;
    public boolean truncated;
    public String language;
    public String content;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
        /*
        StringBuilder b = new StringBuilder();
        String n = "\n";

        b.append(n + "------ File Detail -----" + n);
        b.append("name = " + name + n);
        b.append("size = " + size + n);
        b.append("rawUrl = " + rawUrl + n);
        b.append("type = " + type + n);
        b.append("truncated = " + truncated + n);
        b.append("language = " + language + n);
        b.append("-------------------------------------");

        return b.toString();
        */
    }
}
