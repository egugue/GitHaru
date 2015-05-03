package htoyama.githaru.infra.net;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit.RetrofitError;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ApiErrorHandlerTest {
    private ApiErrorHandler sut;

    @Mock
    private RetrofitError mRetrofitError;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = new ApiErrorHandler();
    }

    @Test
    public void handleError_returnRetrofitError_whenResponseIsNull() {
        when(mRetrofitError.getResponse()).thenReturn(null);

        Throwable throwable = sut.handleError(mRetrofitError);

        assertTrue(throwable instanceof RetrofitError);
        assertThat((RetrofitError) throwable, is(mRetrofitError));
    }

}