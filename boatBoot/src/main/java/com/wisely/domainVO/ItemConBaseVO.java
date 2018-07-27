package com.wisely.domainVO;

import java.io.Serializable;

public class ItemConBaseVO implements Serializable{
	

	    /**
	 * 
	 */
	private static final long serialVersionUID = 4530435277025484748L;
	//目标强度
	   private Float target;  
	    //辐射声功率/dB
		private int radiation;
		
		//辐射声功率插入损失
		private Float radiationlose;
		//频率						
		private Integer rate;

		public int getRadiation() {
			return radiation;
		}

		public void setRadiation(int radiation) {
			this.radiation = radiation;
		}

		public Float getRadiationlose() {
			return radiationlose;
		}

		public void setRadiationlose(Float radiationlose) {
			this.radiationlose = radiationlose;
		}

		public Float getTarget() {
			return target;
		}

		public void setTarget(Float target) {
			this.target = target;
		}

		public Integer getRate() {
			return rate;
		}

		public void setRate(Integer rate) {
			this.rate = rate;
		}

}
