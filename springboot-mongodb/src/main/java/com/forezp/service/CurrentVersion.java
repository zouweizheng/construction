package com.forezp.service;

import com.forezp.entity.VersionInfo;
import org.springframework.stereotype.Service;

/**
 * Created by zou on 2018/2/6.
 */
@Service
public class CurrentVersion {


    public VersionInfo setVersion(String version , String url , String describe){

        VersionInfo versionInfo = new VersionInfo();
        versionInfo.setVersion(version);
        versionInfo.setUrl(url);
        versionInfo.setDescribe(describe);
        return versionInfo;

    }
}
