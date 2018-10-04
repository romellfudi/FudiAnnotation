package com.example.romelldominguez.dsbwidget;

import android.app.Activity;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.romellfudi.fudi_annotation.sample.MainMenuActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {
    @Rule
    public ActivityTestRule<MainMenuActivity> mActivityRule = new ActivityTestRule<>(MainMenuActivity.class);

    @Test
    public void BuildWithAnnotation() throws Exception{
        InstrumentationRegistry.getInstrumentation().runOnMainSync(
                new Runnable() {
                    @Override
                    public void run() {
                        final Activity act = mActivityRule.getActivity();
                    }
                }
        );
        SystemClock.sleep(5000);
    }
}