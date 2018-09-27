package com.wisely.domainVO;

import java.io.Serializable;

public class ItemBaseVO  implements Serializable{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private String pk;
		
		private String metaPK;
		//反射系数
		private Float refect;
		
		//透射系数
		private Float transmission;
		
		//吸声系数
		private Float bondacust;
		
		//频率						
		private Integer rate;
		
		//private List<Integer> rates;
		
		//private Map<Integer,Float> refects;
		
		//private Map<Integer,Float> transmissions;
		
		//private Map<Integer,Float> bondacusts;

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

		public String getPk() {
			return pk;
		}

		public void setPk(String pk) {
			this.pk = pk;
		}

		public String getMetaPK() {
			return metaPK;
		}

		public void setMetaPK(String metaPK) {
			this.metaPK = metaPK;
		}

//		public List<Integer> getRates() {
//			return rates;
//		}

//		public void setRates(List<Integer> rates) {
//			this.rates = rates;
//		}
//
//		public Map<Integer, Float> getRefects() {
//			return refects;
//		}
//
//		public void setRefects(Map<Integer, Float> refects) {
//			this.refects = (Map<Integer, Float>) refects;
//		}

//		public Map<Integer, Float> getTransmissions() {
//			return transmissions;
//		}
//
//		public void setTransmissions(Map<Integer, Float> transmissions) {
//			this.transmissions = transmissions;
//		}
//
//		public Map<Integer, Float> getBondacusts() {
//			return bondacusts;
//		}

//		public void setBondacusts(Map<Integer, Float> bondacusts) {
//			this.bondacusts = bondacusts;
//		} 
//	
}
