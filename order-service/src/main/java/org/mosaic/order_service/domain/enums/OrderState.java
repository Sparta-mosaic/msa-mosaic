package org.mosaic.order_service.domain.enums;


public enum OrderState {
	PENDING("주문대기"),
	CREATED("주문생성"),
	SHIPPED("배송중"),
	DELIVERED("배송완료"),
	CONFIRMED("주문확정"),
	CANCELLED("주문취소");

	final String description;

	OrderState(String description) {
		this.description = description;
	}
}