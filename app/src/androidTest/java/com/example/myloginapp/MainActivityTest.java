package com.example.myloginapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    final static String correctUsername = "admin@fireflyon.com";
    final static String correctPassword = "admin";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void successLogin() throws InterruptedException {
        prepareLogin(correctUsername, correctPassword);
        onView(withId(R.id.logout)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        Thread.sleep(2000);
    }

    @Test
    public void successLogout() throws InterruptedException {
        prepareLogin(correctUsername, correctPassword);
        onView(withId(R.id.logout)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.logout)).perform(click());
        onView(withId(R.id.loginbtn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        Thread.sleep(2000);
    }

    @Test
    public void failedLoginWithWrongPassword() throws InterruptedException {
        prepareLogin(correctUsername, "admin1234");
        onView(withId(R.id.warningMessage)).check(matches(withText("LOGIN FAILED!")));
        Thread.sleep(2000);
    }

    @Test
    public void failedLoginWithEmptyPassword() throws InterruptedException {
        prepareLogin(correctUsername, "");
        onView(withId(R.id.warningMessage)).check(matches(withText("Email and password should not be empty")));
        Thread.sleep(2000);
    }

    @Test
    public void failedLoginWithMissingCharacterPassword() throws InterruptedException {
        prepareLogin(correctUsername, "123");
        onView(withId(R.id.warningMessage)).check(matches(withText("Password should be minimum 5 characters")));
        Thread.sleep(2000);
    }

    @Test
    public void failedLoginWithEmptyEmail() throws InterruptedException {
        prepareLogin("", "12312344");
        onView(withId(R.id.warningMessage)).check(matches(withText("Email and password should not be empty")));
        Thread.sleep(2000);
    }

    @Test
    public void failedLoginWithWrongFormatEmail() throws InterruptedException {
        prepareLogin("admin.fireflyon.com", "12312344");
        onView(withId(R.id.warningMessage)).check(matches(withText("Email format not correct")));
        Thread.sleep(2000);
    }

    private void prepareLogin(String userName, String password) {
        onView(withId(R.id.username)).perform(typeText(userName));
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.loginbtn)).perform(click());
    }
}
