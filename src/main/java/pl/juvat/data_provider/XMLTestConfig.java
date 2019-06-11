package pl.juvat.data_provider;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import pl.juvat.data_provider.model.Parameter;
import pl.juvat.data_provider.model.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author lominskk on 2019-06-11
 */
public final class XMLTestConfig {

    private final String title;
    private TreeMap<String, String> params = new TreeMap<>();

    XMLTestConfig(final Test test, final String testTitle) {
        title = testTitle;
        if (test.getParameters() != null) {
            params = getParams(test.getParameters());
        }
    }

    public String get(final String paramName) {
        return params.get(paramName);
    }

    public List<String> getList(final String paramPrefix) {
        return params.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(paramPrefix))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public String getTitle() {
        return Strings.nullToEmpty(title);
    }

    private TreeMap<String, String> getParams(final List<Parameter> parameters) {
        return parameters.stream().collect(Collectors.toMap(
                Parameter::getName,
                Parameter::getValue,
                (oldValue, newValue) -> oldValue,
                TreeMap::new));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("params", params)
                .toString();
    }

}
