package kr.co.wikibook.gallery.order.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class OrderPostReq {
    private String name;
    private String address;
    private String payment;
    private String cardNumber; //    카드 결제가 아니라면 빈 문자열이 들어옴
    private List<Integer> itemIds;
}
