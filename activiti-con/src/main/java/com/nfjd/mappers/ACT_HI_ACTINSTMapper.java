package com.nfjd.mappers;

import com.nfjd.domain.ACT_HI_ACTINST;
import com.nfjd.domain.ACT_HI_ACTINSTExample;
import java.util.List;

public interface ACT_HI_ACTINSTMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    int countByExample(ACT_HI_ACTINSTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    int insert(ACT_HI_ACTINST record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    int insertSelective(ACT_HI_ACTINST record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    List<ACT_HI_ACTINST> selectByExample(ACT_HI_ACTINSTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    ACT_HI_ACTINST selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    int updateByPrimaryKeySelective(ACT_HI_ACTINST record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACT_HI_ACTINST
     *
     * @mbggenerated Wed Aug 09 14:28:51 CST 2017
     */
    int updateByPrimaryKey(ACT_HI_ACTINST record);
}