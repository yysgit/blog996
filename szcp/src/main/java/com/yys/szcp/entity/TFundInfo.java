package com.yys.szcp.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yys
 * @since 2021-05-15
 */
public class TFundInfo {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 基金公司
     */
	private Integer fundTypeCompanyId;
    /**
     * 基金版块
     */
	private Integer fundTypeSectionId;
    /**
     * 基金类型
     */
	private Integer fundTypeTypeId;
    /**
     * 购买类型
     */
	private Integer fundTypePurchaseId;
    /**
     * 基金名称
     */
	private String fundName;
    /**
     * 基金代码
     */
	private String fundCode;
    /**
     * 基金规模(亿)
     */
	private BigDecimal fundScale;
    /**
     * 持有收益率
     */
	private BigDecimal holdingYield;
    /**
     * 本金
     */
	private BigDecimal principal;
    /**
     * 盈亏
     */
	private BigDecimal profitLoss;
    /**
     * 购买等级几颗星
     */
	private Integer recommendationLevel;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建人
     */
	private Integer createUserId;
    /**
     * 更新人
     */
	private Integer updateUserId;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新时间
     */
	private Date updateTime;
    /**
     * 删除:0 未删除 1已删除
     */
	private Integer deleteStatus;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFundTypeCompanyId() {
		return fundTypeCompanyId;
	}

	public void setFundTypeCompanyId(Integer fundTypeCompanyId) {
		this.fundTypeCompanyId = fundTypeCompanyId;
	}

	public Integer getFundTypeSectionId() {
		return fundTypeSectionId;
	}

	public void setFundTypeSectionId(Integer fundTypeSectionId) {
		this.fundTypeSectionId = fundTypeSectionId;
	}

	public Integer getFundTypeTypeId() {
		return fundTypeTypeId;
	}

	public void setFundTypeTypeId(Integer fundTypeTypeId) {
		this.fundTypeTypeId = fundTypeTypeId;
	}

	public Integer getFundTypePurchaseId() {
		return fundTypePurchaseId;
	}

	public void setFundTypePurchaseId(Integer fundTypePurchaseId) {
		this.fundTypePurchaseId = fundTypePurchaseId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public BigDecimal getFundScale() {
		return fundScale;
	}

	public void setFundScale(BigDecimal fundScale) {
		this.fundScale = fundScale;
	}

	public BigDecimal getHoldingYield() {
		return holdingYield;
	}

	public void setHoldingYield(BigDecimal holdingYield) {
		this.holdingYield = holdingYield;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getProfitLoss() {
		return profitLoss;
	}

	public void setProfitLoss(BigDecimal profitLoss) {
		this.profitLoss = profitLoss;
	}

	public Integer getRecommendationLevel() {
		return recommendationLevel;
	}

	public void setRecommendationLevel(Integer recommendationLevel) {
		this.recommendationLevel = recommendationLevel;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}


}
