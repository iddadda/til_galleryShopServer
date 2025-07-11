package kr.co.wikibook.gallery.order.model;


import lombok.Builder;
import lombok.Getter;

@Getter
//@Setter
@Builder
public class OrderDetailGetReq {
    private int orderId;
    private int memberId;
}
