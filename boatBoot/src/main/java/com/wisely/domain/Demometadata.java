package com.wisely.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
/**
 * 记录smallDemo的原数据信息,
 * 小样数据的元数据信息。
 * @author dingqi
 *
 */
@Entity 
@Inheritance
@Table(name="smallmetadata")
public class Demometadata  extends BaseMeta{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //2
	//@GeneratedValue //3
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	private Float temparture;
	
	private Integer press;
	
	//区分大小样元数据
	private boolean small=false;
	
	@Column(name="sample_pk")
    private String samplepk;
	
    @Column(name="backing_pk")
	private String bakingpk;
	/**
	 * 样品信息
	 */
	@Transient
	BaseMetaSample  sample;
	/**
	 * 背衬信息
	 */
	@Transient
	BaseMetaBacking  backing;
	
	
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}



	public float getTemparture() {
		return temparture;
	}

	public void setTemparture(float temparture) {
		this.temparture = temparture;
	}

	public int getPress() {
		return press;
	}

	public void setPress(int press) {
		this.press = press;
	}


	public boolean isSmall() {
		return small;
	}

	public void setSmall(boolean small) {
		this.small = small;
	}




	public void setTemparture(Float temparture) {
		this.temparture = temparture;
	}

	public void setPress(Integer press) {
		this.press = press;
	}

	public String toString() {
		return "样品名称："+this.getSamplename()+",背衬："+this.getBackgroundtype()+",温度："+this.getTemparture()+",压力："+this.getPress();
		
	}

	public BaseMetaSample getSample() {
		return sample;
	}

	public void setSample(BaseMetaSample sample) {
		this.sample = sample;
	}

	public BaseMetaBacking getBacking() {
		return backing;
	}

	public void setBacking(BaseMetaBacking backing) {
		this.backing = backing;
	}

	public String getSamplepk() {
		return samplepk;
	}

	public void setSamplepk(String samplepk) {
		this.samplepk = samplepk;
	}

	public String getBakingpk() {
		return bakingpk;
	}

	public void setBakingpk(String bakingpk) {
		this.bakingpk = bakingpk;
	}

}
