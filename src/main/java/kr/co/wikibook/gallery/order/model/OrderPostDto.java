package kr.co.wikibook.gallery.order.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
//@Setter
@Builder
@ToString
public class OrderPostDto { // orders 테이블에 데이터를 넣어주는 용도
    private int orderId;
    private int memberId;
    private String name;
    private String address;
    private String payment;
    private String cardNumber;
    private long amount;
}
