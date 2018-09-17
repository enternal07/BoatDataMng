package com.wisely.domain.scale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BaseModel;


/**
 * 缩放模型Item
 */
@Entity
@Inheritance
@Table(name="item_scale")
public class ItemScalePO  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //2
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	private  int rate;
	
	//光壳声目标强度
	@Column(name = "light_shell_ts")
	private float lightShellTS;
	
	//光壳辐射声功率
	@Column(name = "light_shell_sp")
	private float lightShellSP;
	
	//敷瓦声目标强度
	@Column(name = "laying_shell_ts")
	private float layingShellTS;
	
	//敷瓦辐射声功率
	@Column(name = "laying_shell_sp")
	private float layingShellSP;
	
	//声目标强度降低量
	@Column(name = "reduction_ts")
	private float reductionTS;
	
	//辐射声功率插入损失
	@Column(name = "reduction_sp")
	private float reductionSP ;
	
	@ManyToOne
	@JoinColumn(name="scalemata_pk")
	private ScaleMataPO scaleMataPO;

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public float getLightShellTS() {
		return lightShellTS;
	}

	public void setLightShellTS(float lightShellTS) {
		this.lightShellTS = lightShellTS;
	}

	public float getLightShellSP() {
		return lightShellSP;
	}

	public void setLightShellSP(float lightShellSP) {
		this.lightShellSP = lightShellSP;
	}

	public float getLayingShellTS() {
		return layingShellTS;
	}

	public void setLayingShellTS(float layingShellTS) {
		this.layingShellTS = layingShellTS;
	}

	public float getLayingShellSP() {
		return layingShellSP;
	}

	public void setLayingShellSP(float layingShellSP) {
		this.layingShellSP = layingShellSP;
	}

	public float getReductionTS() {
		return reductionTS;
	}

	public void setReductionTS(float reductionTS) {
		this.reductionTS = reductionTS;
	}

	public float getReductionSP() {
		return reductionSP;
	}

	public void setReductionSP(float reductionSP) {
		this.reductionSP = reductionSP;
	}

	public ScaleMataPO getScaleMataPO() {
		return scaleMataPO;
	}

	public void setScaleMataPO(ScaleMataPO scaleMataPO) {
		this.scaleMataPO = scaleMataPO;
	}
	
	
}
