package htoyama.githaru.infra.entitiy;


import java.util.Map;

/**
 * Represent a Gist in the infra layer.
 */
public class GistEntity {
    public String id;
    public String description;
    public boolean isPublic;
    public OwnerEntity owner;
    public Map<String, FileEntity> files;

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        String n = "\n";

        b.append(n + "------ GistEntity Detail -----" + n);
        b.append("id = " + id + n);
        b.append("description = " + description + n);
        b.append("isPublic = " + isPublic + n);
        b.append("owner = " + owner.toString() + n);
        b.append("-------------------------------------");

        return b.toString();
    }
}
