package pl.juvat.data_provider;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import pl.juvat.data_provider.model.TestData;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * @author lominskk on 2019-06-11
 */
@Slf4j
public final class XMLTestDataProvider {

    @DataProvider(name = "XmlDataProvider", parallel = true)
    public static Iterator<Object[]> createData(final Method caller) {
        final var testData = getTestDataFromCaller(caller);

        return testData.getTests().stream()
                .filter(test -> test.getName() != null && test.getName().equals(caller.getName()))
                .map(test -> new Object[]{new XMLTestConfig(test, testData.getTitle())})
                .collect(Collectors.toList())
                .iterator();
    }

    private static TestData getTestDataFromCaller(final Method caller) {
        final var xmlMapper = new XmlMapper();
        try {
            @Cleanup final var inputStream = caller.getDeclaringClass().getClassLoader()
                    .getResourceAsStream(getTestNameFromCaller(caller));
            return xmlMapper.readValue(inputStream, TestData.class);
        } catch (final IOException e) {
            log.error("Exception thrown while trying to deserialize XML file", e);
            return null;
        }
    }

    private static String getTestNameFromCaller(final Method caller) {
        return caller.getDeclaringClass().getName().replaceAll("\\.", "/") + ".xml";
    }
}
