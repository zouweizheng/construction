package com.company.project.foundation.core;


import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected mapper<T> Mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T model) {
        Mapper.insertSelective(model);
    }

    public void save(List<T> models) {
        Mapper.insertList(models);
    }

    public void deleteById(Integer id) {
        Mapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(String ids) {
        Mapper.deleteByIds(ids);
    }

    public void update(T model) {
        Mapper.updateByPrimaryKeySelective(model);
    }

    public T findById(Integer id) {
        return Mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return Mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<T> findByIds(String ids) {
        return Mapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition) {
        return Mapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return Mapper.selectAll();
    }


}
