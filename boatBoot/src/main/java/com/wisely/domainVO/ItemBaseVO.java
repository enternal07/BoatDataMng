package com.wisely.domainVO;

import java.io.Serializable;

public class ItemBaseVO  implements Serializable{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		//反射系数
		private Float refect;
		
		//透射系数
		private Float transmission;
		
		//吸声系数
		private Float bondacust;
		
		//频率						
		private Integer rate;

		public Float getRefect() {
			return refect;
		}

		public void setRefect(Float refect) {
			this.refect = refect;
		}

		public Float getTransmission() {
			return transmission;
		}

		public void setTransmission(Float transmission) {
			this.transmission = transmission;
		}

		public Float getBondacust() {
			return bondacust;
		}

		public void setBondacust(Float bondacust) {
			this.bondacust = bondacust;
		}

		public Integer getRate() {
			return rate;
		}

		public void setRate(Integer rate) {
			this.rate = rate;
		} 
	
}
