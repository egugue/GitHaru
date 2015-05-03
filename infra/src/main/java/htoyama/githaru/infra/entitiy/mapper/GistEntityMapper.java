package htoyama.githaru.infra.entitiy.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.infra.entitiy.GistEntity;


/**
 * The Dxo to map {@link GistEntity} to {@link Gist}.
 */
@Singleton
public class GistEntityMapper {
    private final OwnerEntityMapper mOwnerEntityMapper;
    private final FileEntityMapper mFileEntityMapper;

    @Inject
    public GistEntityMapper(OwnerEntityMapper ownerEntityMapper,
                            FileEntityMapper fileEntityMapper) {
        mOwnerEntityMapper = ownerEntityMapper;
        mFileEntityMapper = fileEntityMapper;
    }

    /**
     * Map {@link GistEntity} to {@link Gist}.
     */
    public Gist map(GistEntity e) {
        Gist gist = new Gist(e.id);

        gist.owner = mOwnerEntityMapper.map(e.owner);
        gist.description = e.description;
        gist.isPublic = e.isPublic;
        gist.fileList = mFileEntityMapper.map(e.files);

        return gist;
    }

    /**
     * Map a List of {@link GistEntity} to a List of {@link Gist}.
     */
    public List<Gist> map(List<GistEntity> eList) {
        List<Gist> list = new ArrayList<>();
        Gist gist;

        for (GistEntity e : eList) {
            gist = map(e);
            list.add(gist);
        }

        return list;
    }

}
