package com.forezp.dao;

import java.util.List;

import com.forezp.entity.ConfigInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigInfoRepository extends MongoRepository<ConfigInfo, String> {



     ConfigInfo findByEnName(String enName);
}