package us.xingkong.user.biz.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 15:09
 */
@Setter
public class UrlBuilder {

    private final Map<String, Object> params = Maps.newLinkedHashMapWithExpectedSize(8);
    private String baseUrl;

    private UrlBuilder() {
    }

    public static UrlBuilder fromBaseUrl(String baseUrl) {
        UrlBuilder builder = new UrlBuilder();
        builder.setBaseUrl(baseUrl);
        return builder;
    }

    public UrlBuilder buildWithParam(String key, Object value) {
        if (Strings.isBlank(key)) {
            return this;
        }
        String valueAsString = (value != null ? value.toString() : "");
        this.params.put(key, valueAsString);
        return this;
    }

    public String build() {
        if (params != null && params.size() > 0) {
            String paramSting = Joiner.on("&").withKeyValueSeparator("=")
                    .join(params);
            return baseUrl + paramSting;
        }
        return this.baseUrl;
    }

}
