package htoyama.githaru.infra.entitiy.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import htoyama.githaru.domain.entity.File;
import htoyama.githaru.infra.entitiy.FileEntity;

/**
 * The Dxo to map {@link htoyama.githaru.infra.entitiy.FileEntity} to {@link File}.
 */
@Singleton
public class FileEntityMapper {
    private static final String TAG = FileEntityMapper.class.getSimpleName();

    @Inject
    public FileEntityMapper() {
    }

    /**
     * Map {@link htoyama.githaru.infra.entitiy.FileEntity} to {@link File}
     *
     * @param fileName The file name in a Gist.
     * @param e The FileEntity to be map
     */
    public File map(String fileName, FileEntity e) {
        File file = new File();
        file.name = fileName;
        file.language = e.language;
        file.rawUrl = e.rawUrl;
        file.size = e.size;
        file.truncated = e.truncated;
        file.type = e.type;
        file.content = e.content;

        return file;
    }

    /**
     * Map a map of {@link FileEntity} to a list of {@link File}
     */
    public List<File> map(Map<String, FileEntity> map) {
        List<File> list = new ArrayList<>();
        File file;

        for (Map.Entry<String, FileEntity> entry : map.entrySet()){
            file = map(entry.getKey(), entry.getValue());
            list.add(file);
        }

        return list;
    }

}
