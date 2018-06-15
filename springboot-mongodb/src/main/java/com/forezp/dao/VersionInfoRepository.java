package com.forezp.dao;

import com.forezp.entity.ConfigInfo;
import com.forezp.entity.VersionInfo;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VersionInfoRepository extends MongoRepository<VersionInfo, String> {



     VersionInfo findByVersion(String version);
     VersionInfo findByVersionOrderByCreateTimeDesc(String version);
     List<VersionInfo> findByPojoInfoOrderByCreateTimeDesc(String info);



}