package kr.co.wikibook.gallery.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartDeleteReq {
    private int memberId;
    private int itemId;

}
