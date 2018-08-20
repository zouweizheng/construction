package com.company.project.foundation.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "con_order_del")
public class ConOrderDel {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 基建单号
     */
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_type")
    private String orderType;

    /**
     * 基建单状态
     */
    @Column(name = "order_status")
    private String orderStatus;

    /**
     * 创建人id
     */
    @Column(name = "create_person_id")
    private String createPersonId;

    /**
     * 创建人姓名
     */
    @Column(name = "create_person_name")
    private String createPersonName;

    /**
     * 打印次数
     */
    @Column(name = "print_num")
    private Integer printNum;

    @Column(name = "bind_operation")
    private Integer bindOperation;

    /**
     * 所属人id
     */
    @Column(name = "belong_person_id")
    private String belongPersonId;

    /**
     * 所属人姓名
     */
    @Column(name = "belong_person_name")
    private String belongPersonName;

    /**
     * 所属项目id
     */
    @Column(name = "project_id")
    private String projectId;

    /**
     * 所属项目名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 总价格
     */
    private Double money;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 所属车队id
     */
    @Column(name = "motorcade_id")
    private String motorcadeId;

    /**
     * 所属车队名称
     */
    @Column(name = "motorcade_name")
    private String motorcadeName;

    /**
     * 费用类型
     */
    @Column(name = "fee_type")
    private String feeType;

    /**
     * 费用小类
     */
    @Column(name = "fee_second_type")
    private String feeSecondType;

    /**
     * 机械类型
     */
    @Column(name = "machine_type")
    private String machineType;

    /**
     * 施工类型
     */
    @Column(name = "work_type")
    private String workType;

    /**
     * 供应类型
     */
    @Column(name = "provision_type")
    private String provisionType;

    /**
     * 目的项目
     */
    @Column(name = "destination_id")
    private String destinationId;

    /**
     * 目的项目名称
     */
    @Column(name = "destination_name")
    private String destinationName;

    /**
     * 数量
     */
    private Double num;

    /**
     * 单价
     */
    @Column(name = "unit_price")
    private Double unitPrice;

    /**
     * 车头图片
     */
    @Column(name = "first_pic")
    private String firstPic;

    /**
     * 车尾图片
     */
    @Column(name = "secondly_pic")
    private String secondlyPic;

    /**
     * 备用图片
     */
    @Column(name = "thirdly_pic")
    private String thirdlyPic;

    /**
     * 当前流程id
     */
    @Column(name = "process_instance_id")
    private String processInstanceId;

    /**
     * 车牌号码
     */
    @Column(name = "plate_number")
    private String plateNumber;

    /**
     * 单位
     */
    @Column(name = "unit_word")
    private String unitWord;

    /**
     * 是否绑定
     */
    @Column(name = "is_bind")
    private String isBind;

    /**
     * 台班开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 台班结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取基建单号
     *
     * @return order_id - 基建单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置基建单号
     *
     * @param orderId 基建单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return order_name
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * @param orderName
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * @return order_type
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取基建单状态
     *
     * @return order_status - 基建单状态
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置基建单状态
     *
     * @param orderStatus 基建单状态
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取创建人id
     *
     * @return create_person_id - 创建人id
     */
    public String getCreatePersonId() {
        return createPersonId;
    }

    /**
     * 设置创建人id
     *
     * @param createPersonId 创建人id
     */
    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    /**
     * 获取创建人姓名
     *
     * @return create_person_name - 创建人姓名
     */
    public String getCreatePersonName() {
        return createPersonName;
    }

    /**
     * 设置创建人姓名
     *
     * @param createPersonName 创建人姓名
     */
    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    /**
     * 获取打印次数
     *
     * @return print_num - 打印次数
     */
    public Integer getPrintNum() {
        return printNum;
    }

    /**
     * 设置打印次数
     *
     * @param printNum 打印次数
     */
    public void setPrintNum(Integer printNum) {
        this.printNum = printNum;
    }

    /**
     * @return bind_operation
     */
    public Integer getBindOperation() {
        return bindOperation;
    }

    /**
     * @param bindOperation
     */
    public void setBindOperation(Integer bindOperation) {
        this.bindOperation = bindOperation;
    }

    /**
     * 获取所属人id
     *
     * @return belong_person_id - 所属人id
     */
    public String getBelongPersonId() {
        return belongPersonId;
    }

    /**
     * 设置所属人id
     *
     * @param belongPersonId 所属人id
     */
    public void setBelongPersonId(String belongPersonId) {
        this.belongPersonId = belongPersonId;
    }

    /**
     * 获取所属人姓名
     *
     * @return belong_person_name - 所属人姓名
     */
    public String getBelongPersonName() {
        return belongPersonName;
    }

    /**
     * 设置所属人姓名
     *
     * @param belongPersonName 所属人姓名
     */
    public void setBelongPersonName(String belongPersonName) {
        this.belongPersonName = belongPersonName;
    }

    /**
     * 获取所属项目id
     *
     * @return project_id - 所属项目id
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * 设置所属项目id
     *
     * @param projectId 所属项目id
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取所属项目名称
     *
     * @return project_name - 所属项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置所属项目名称
     *
     * @param projectName 所属项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取总价格
     *
     * @return money - 总价格
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置总价格
     *
     * @param money 总价格
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取所属车队id
     *
     * @return motorcade_id - 所属车队id
     */
    public String getMotorcadeId() {
        return motorcadeId;
    }

    /**
     * 设置所属车队id
     *
     * @param motorcadeId 所属车队id
     */
    public void setMotorcadeId(String motorcadeId) {
        this.motorcadeId = motorcadeId;
    }

    /**
     * 获取所属车队名称
     *
     * @return motorcade_name - 所属车队名称
     */
    public String getMotorcadeName() {
        return motorcadeName;
    }

    /**
     * 设置所属车队名称
     *
     * @param motorcadeName 所属车队名称
     */
    public void setMotorcadeName(String motorcadeName) {
        this.motorcadeName = motorcadeName;
    }

    /**
     * 获取费用类型
     *
     * @return fee_type - 费用类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置费用类型
     *
     * @param feeType 费用类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * 获取费用小类
     *
     * @return fee_second_type - 费用小类
     */
    public String getFeeSecondType() {
        return feeSecondType;
    }

    /**
     * 设置费用小类
     *
     * @param feeSecondType 费用小类
     */
    public void setFeeSecondType(String feeSecondType) {
        this.feeSecondType = feeSecondType;
    }

    /**
     * 获取机械类型
     *
     * @return machine_type - 机械类型
     */
    public String getMachineType() {
        return machineType;
    }

    /**
     * 设置机械类型
     *
     * @param machineType 机械类型
     */
    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    /**
     * 获取施工类型
     *
     * @return work_type - 施工类型
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * 设置施工类型
     *
     * @param workType 施工类型
     */
    public void setWorkType(String workType) {
        this.workType = workType;
    }

    /**
     * 获取供应类型
     *
     * @return provision_type - 供应类型
     */
    public String getProvisionType() {
        return provisionType;
    }

    /**
     * 设置供应类型
     *
     * @param provisionType 供应类型
     */
    public void setProvisionType(String provisionType) {
        this.provisionType = provisionType;
    }

    /**
     * 获取目的项目
     *
     * @return destination_id - 目的项目
     */
    public String getDestinationId() {
        return destinationId;
    }

    /**
     * 设置目的项目
     *
     * @param destinationId 目的项目
     */
    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    /**
     * 获取目的项目名称
     *
     * @return destination_name - 目的项目名称
     */
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * 设置目的项目名称
     *
     * @param destinationName 目的项目名称
     */
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Double getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Double num) {
        this.num = num;
    }

    /**
     * 获取单价
     *
     * @return unit_price - 单价
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置单价
     *
     * @param unitPrice 单价
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取车头图片
     *
     * @return first_pic - 车头图片
     */
    public String getFirstPic() {
        return firstPic;
    }

    /**
     * 设置车头图片
     *
     * @param firstPic 车头图片
     */
    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    /**
     * 获取车尾图片
     *
     * @return secondly_pic - 车尾图片
     */
    public String getSecondlyPic() {
        return secondlyPic;
    }

    /**
     * 设置车尾图片
     *
     * @param secondlyPic 车尾图片
     */
    public void setSecondlyPic(String secondlyPic) {
        this.secondlyPic = secondlyPic;
    }

    /**
     * 获取备用图片
     *
     * @return thirdly_pic - 备用图片
     */
    public String getThirdlyPic() {
        return thirdlyPic;
    }

    /**
     * 设置备用图片
     *
     * @param thirdlyPic 备用图片
     */
    public void setThirdlyPic(String thirdlyPic) {
        this.thirdlyPic = thirdlyPic;
    }

    /**
     * 获取当前流程id
     *
     * @return process_instance_id - 当前流程id
     */
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    /**
     * 设置当前流程id
     *
     * @param processInstanceId 当前流程id
     */
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    /**
     * 获取车牌号码
     *
     * @return plate_number - 车牌号码
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * 设置车牌号码
     *
     * @param plateNumber 车牌号码
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * 获取单位
     *
     * @return unit_word - 单位
     */
    public String getUnitWord() {
        return unitWord;
    }

    /**
     * 设置单位
     *
     * @param unitWord 单位
     */
    public void setUnitWord(String unitWord) {
        this.unitWord = unitWord;
    }

    /**
     * 获取是否绑定
     *
     * @return is_bind - 是否绑定
     */
    public String getIsBind() {
        return isBind;
    }

    /**
     * 设置是否绑定
     *
     * @param isBind 是否绑定
     */
    public void setIsBind(String isBind) {
        this.isBind = isBind;
    }

    /**
     * 获取台班开始时间
     *
     * @return start_time - 台班开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置台班开始时间
     *
     * @param startTime 台班开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取台班结束时间
     *
     * @return end_time - 台班结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置台班结束时间
     *
     * @param endTime 台班结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}