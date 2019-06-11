package pl.juvat.listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * @author lominskk on 2019-06-11
 */
@Slf4j
public final class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(final ITestResult tr) {
        log.info("---- TEST FAILED ----");
    }

    @Override
    public void onTestSuccess(final ITestResult tr) {
        log.info("---- TEST PASSED -----");
    }
}
