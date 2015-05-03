package htoyama.githaru.infra.entitiy;

import com.google.gson.annotations.SerializedName;


/**
 * Represents File contained by the Gist in the infra layer.
 */
//TODO: reconsider if the class must be in Value Object.
public class FileEntity {
    public int size;

    @SerializedName("raw_url")
    public String rawUrl;

    public String type;
    public boolean truncated;
    public String language;

    public String content;
}
