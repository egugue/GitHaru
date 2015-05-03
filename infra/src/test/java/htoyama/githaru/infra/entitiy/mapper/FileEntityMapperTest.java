package htoyama.githaru.infra.entitiy.mapper;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import htoyama.githaru.domain.entity.File;
import htoyama.githaru.infra.entitiy.FileEntity;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class FileEntityMapperTest {
    private static final String FAKE_NAME = "fake_name";
    private static final String FAKE_CONTENT = "fake_content";
    private static final String FAKE_LANGUAGE = "fake_language";
    private static final String FAKE_RAW_URL = "fake_raw_url";
    private static final String FAKE_TYPE = "fake_type";
    private static final int FAKE_SIZE = 1000;
    private static final boolean FAKE_TRUNCATED = true;

    private FileEntityMapper sut;

    @Before
    public void setUp() {
        sut = new FileEntityMapper();
    }

    @Test
    public void map_FileEntity() {
        File actual = sut.map(FAKE_NAME, createFakeFileEntity());

        assertThat(actual.name,      is(FAKE_NAME));
        assertThat(actual.content,   is(FAKE_CONTENT));
        assertThat(actual.language,  is(FAKE_LANGUAGE));
        assertThat(actual.rawUrl,    is(FAKE_RAW_URL));
        assertThat(actual.type,      is(FAKE_TYPE));
        assertThat(actual.size,      is(FAKE_SIZE));
        assertThat(actual.truncated, is(FAKE_TRUNCATED));
    }

    @Test
    public void map_ListOfFileEntity() {
        FileEntity entity1 = mock(FileEntity.class);
        FileEntity entity2 = mock(FileEntity.class);
        Map<String, FileEntity> fileList = new HashMap<>();
        fileList.put("entity1", entity1);
        fileList.put("entity2", entity2);

        List<File> actual = sut.map(fileList);

        assertThat(actual.size(), is(2));
        assertThat(actual.get(0), is(instanceOf(File.class)));
        assertThat(actual.get(1), is(instanceOf(File.class)));
    }

    private FileEntity createFakeFileEntity() {
        FileEntity entity = new FileEntity();
        entity.content = FAKE_CONTENT;
        entity.language = FAKE_LANGUAGE;
        entity.rawUrl = FAKE_RAW_URL;
        entity.type = FAKE_TYPE;
        entity.size = FAKE_SIZE;
        entity.truncated = FAKE_TRUNCATED;

        return entity;
    }

}