package kr.co.wikibook.gallery.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrderItemPostDto {
    private int orderId;
    private List<Integer> itemIds;
}

