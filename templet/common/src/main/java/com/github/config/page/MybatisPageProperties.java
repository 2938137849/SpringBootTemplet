package com.github.config.page;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Spring Data Web.
 *
 * @author bin
 * @since 2022/11/29
 */
@ConfigurationProperties("spring.mybatis.web")
public class MybatisPageProperties {

    /**
     * Page index parameter name.
     */
    private String pageParameter = "page";

    /**
     * Page size parameter name.
     */
    private String sizeParameter = "size";

    /**
     * Default page size.
     */
    private long defaultPageSize = 20;

    /**
     * Maximum page size to be accepted.
     */
    private long maxPageSize = 2000;

    public String getPageParameter() {
        return pageParameter;
    }

    public void setPageParameter(String pageParameter) {
        this.pageParameter = pageParameter;
    }

    public String getSizeParameter() {
        return sizeParameter;
    }

    public void setSizeParameter(String sizeParameter) {
        this.sizeParameter = sizeParameter;
    }

    public long getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setDefaultPageSize(long defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public long getMaxPageSize() {
        return maxPageSize;
    }

    public void setMaxPageSize(long maxPageSize) {
        this.maxPageSize = maxPageSize;
    }
}
