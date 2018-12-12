/**create by liuhua at 2018年1月17日 下午2:39:42**/
package com.booting.common;

public class PxConstants {

	//状态 1待支付 2已支付 3已退款
	public enum ApplyStatus{
		dfk(1),
		yzf(2),
		ytk(3);
		
		private Integer status;
		
		private ApplyStatus(Integer status){
			this.status = status;
		}
		public Integer getStatus() {
			return status;
		}
	}
}
