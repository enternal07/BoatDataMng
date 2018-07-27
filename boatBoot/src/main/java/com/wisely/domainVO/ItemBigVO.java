package com.wisely.domainVO;

import java.io.Serializable;

public class ItemBigVO extends ItemBaseVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 //辐射声功率/dB
	private int radiation;
	
	//辐射声功率插入损失
	private Float radiationlose;
	
	//回声降低
		private Float echoes;

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

		public Float getEchoes() {
			return echoes;
		}

		public void setEchoes(Float echoes) {
			this.echoes = echoes;
		}


}
