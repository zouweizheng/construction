package com.company.project.construction.pojo;

import com.company.project.core.pojo.OrderConfigInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by ZouWeizheng on 2017-11-20.
 */
@ConfigurationProperties( prefix = "construction")
@Component
@PropertySource("classpath:application.properties")
@Service("ConstructionConfigInfo")
public class ConstructionConfigInfo extends OrderConfigInfo{

    private String historyProjectGroup;
    private String currentProjectGroup;
    private String analysisGroup;

    public String getHistoryProjectGroup() {
        return historyProjectGroup;
    }

    public void setHistoryProjectGroup(String historyProjectGroup) {
        this.historyProjectGroup = historyProjectGroup;
    }

    public String getCurrentProjectGroup() {
        return currentProjectGroup;
    }

    public void setCurrentProjectGroup(String currentProjectGroup) {
        this.currentProjectGroup = currentProjectGroup;
    }

    public String getAnalysisGroup() {
        return analysisGroup;
    }

    public void setAnalysisGroup(String analysisGroup) {
        this.analysisGroup = analysisGroup;
    }
}
