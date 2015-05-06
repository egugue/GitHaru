package htoyama.githaru.infra.entitiy;


import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Represent a Gist in the infra layer.
 */
public class GistEntity {
    public String id;

    @SerializedName("description")
    public String title;

    public boolean isPublic;
    public OwnerEntity owner;
    public Map<String, FileEntity> files;

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        String n = "\n";

        b.append(n + "------ GistEntity Detail -----" + n);
        b.append("id = " + id + n);
        b.append("title = " + title + n);
        b.append("isPublic = " + isPublic + n);
        b.append("owner = " + owner.toString() + n);
        b.append("-------------------------------------");

        return b.toString();
    }
}
