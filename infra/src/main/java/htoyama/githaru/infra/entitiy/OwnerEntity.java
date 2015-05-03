package htoyama.githaru.infra.entitiy;

import com.google.gson.annotations.SerializedName;


/**
 *  Represents a Owner who manage a Github Contents in the infra layer.
 */
public class OwnerEntity {

    @SerializedName("login")
    public String name;

    public int id;

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        String n = "\n";

        b.append(n + "------ OwnerEntity Detail -----" + n);
        b.append("name = " + name + n);
        b.append("id = "+ id + n);
        b.append("-------------------------------------");

        return b.toString();
    }
}
