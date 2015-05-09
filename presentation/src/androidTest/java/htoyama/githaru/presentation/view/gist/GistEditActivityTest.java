package htoyama.githaru.presentation.view.gist;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.squareup.spoon.Spoon;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.presentation.AppComponent;
import htoyama.githaru.presentation.GitharuApp;
import htoyama.githaru.presentation.R;
import htoyama.githaru.testutil.rule.MockInfraModule;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.not;
import static htoyama.githaru.testutil.TestUtil.getApplication;

@RunWith(AndroidJUnit4.class)
public class GistEditActivityTest {

    @Inject
    GistRepository mGistRepository;

    @Rule
    public ActivityTestRule<GistEditActivity> mActivityTestRule = new ActivityTestRule<>(
            GistEditActivity.class,
            true,
            false
    );

    @Before
    public void setUp() {
        TestComponent component = DaggerGistEditActivityTest_TestComponent
                .builder()
                .build();
        component.inject(this);

        GitharuApp app = getApplication();
        app.setAppComponent(component);
    }

    @Test
    public void saveButton_disable_whenJustStartingInStateOfCreateGist() {
        Intent intent = GistEditActivity.createIntent(
                getApplication());

        Activity activity = mActivityTestRule.launchActivity(intent);
        Spoon.screenshot(activity, "start");

        onView(withId(R.id.edit_save)).check(matches(not(isEnabled())));
    }

    @Test
    public void scenario() {
        Intent intent = GistEditActivity.createIntent(
                getApplication());

        Activity activity = mActivityTestRule.launchActivity(intent);
        Spoon.screenshot(activity, "start__check_if_button_is_disable");

        onView(withId(R.id.edit_gist_title)).perform(typeText("Gist Title"), closeSoftKeyboard());
        checkSaveButton(not(isEnabled()));
        Spoon.screenshot(activity, "type_gist_title");

        onView(withId(R.id.edit_file_name)).perform(typeText("File Title"), closeSoftKeyboard());
        checkSaveButton(not(isEnabled()));
        Spoon.screenshot(activity, "type_file_title");

        onView(withId(R.id.edit_file_content)).perform(
                typeText("file content hgoehg\noheogheohgoehgoehgoehgoehogehgoe\nhoge"),
                closeSoftKeyboard());
        Spoon.screenshot(activity, "typed_file_content");

        checkSaveButton(isEnabled());
        Spoon.screenshot(activity, "finish__check_if_button_change_enable");
    }

    private void checkSaveButton(Matcher<? super View> matcher) {
        onView(withId(R.id.edit_save)).check(matches(matcher));
    }

    @Singleton
    @Component(modules = {
            MockInfraModule.class
    })
    public interface TestComponent extends AppComponent {
        void inject(GistEditActivityTest activity);
    }
}