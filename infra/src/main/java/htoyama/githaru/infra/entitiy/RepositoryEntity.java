package htoyama.githaru.infra.entitiy;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class RepositoryEntity {

    public int id;

    public String name;

    @SerializedName("full_name")
    public String fullName;

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        String n = "\n";

        b.append(n + "------ Repository Entity Detail -----" + n);
        b.append("id = "+ id + n);
        b.append("name = " + name + n);
        b.append("fullName = " + fullName + n);
        b.append("-------------------------------------");

        return b.toString();
    }
}
