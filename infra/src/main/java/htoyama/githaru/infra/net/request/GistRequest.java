package htoyama.githaru.infra.net.request;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import htoyama.githaru.domain.entity.File;
import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.infra.entitiy.FileEntity;

/**
 * Request to represent sending request body related to {@link Gist}
 */
public class GistRequest {

    public String description;

    @SerializedName("public")
    public boolean isPublic;

    public Map<String, FileEntity> files;

    /**
     * Get {@link GistRequest} instanse.
     */
    public static GistRequest with(Gist gist) {
        GistRequest request = new GistRequest();
        request.description = gist.description;
        request.isPublic = gist.isPublic;

        Map<String, FileEntity> map = new HashMap<>();
        for (File file : gist.fileList) {
            FileEntity entity = new FileEntity();
            entity.content = file.content;

            map.put(file.name, entity);
            request.files = map;
        }

        return request;
    }

}
