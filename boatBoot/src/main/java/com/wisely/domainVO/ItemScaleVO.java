package com.wisely.domainVO;

import java.io.Serializable;

import javax.persistence.Column;

public class ItemScaleVO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    
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


}
