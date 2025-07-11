package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.cart.CartMapper;
import kr.co.wikibook.gallery.item.ItemMapper;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import kr.co.wikibook.gallery.order.model.OrderGetRes;
import kr.co.wikibook.gallery.order.model.OrderItemPostDto;
import kr.co.wikibook.gallery.order.model.OrderPostDto;
import kr.co.wikibook.gallery.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service  // 빈등록
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final OrderItemMapper orderItemMapper;
    private final CartMapper cartMapper; // 주문완료되면 장바구니 목록 삭제를 위함임

//    주문정보 저장
    public int saveOrder(OrderPostReq req, int logginedMemberId) {
        // 상품 정보 DB로 부터 가져온다
        List<ItemGetRes> itemList = itemMapper.findAllByIdIn(req.getItemIds());
        log.info("itemList={}", itemList);
//        총 구매액 콘솔에 출력
        int amount = 0;
        for (ItemGetRes item : itemList) {
            amount += item.getPrice() - ((item.getPrice() * item.getDiscountPer()) / 100);
        }
        log.info("amount={}", amount);

//        OrderPostDto 객체화 하고 데이터 넣기
//        1. Setter로 넣기
//        OrderPostDto orderPostDto = new OrderPostDto();
//        orderPostDto.setMemberId(logginedMemberId);
//        orderPostDto.setName(req.getName());
//        orderPostDto.setAddress(req.getAddress());
//        orderPostDto.setPayment(req.getPayment());
//        orderPostDto.setCardNumber(req.getCardNumber());
//        orderPostDto.setAmount(amount);

//        2. Builder 사용하기
        OrderPostDto orderPostDto = OrderPostDto.builder()
                .memberId(logginedMemberId)
                .name(req.getName())
                .address(req.getAddress())
                .payment(req.getPayment())
                .cardNumber(req.getCardNumber())
                .amount(amount)
                .build();
//        log.info("before-orderPostDto={}", orderPostDto);
        int result = orderMapper.save(orderPostDto);
//        log.info("after-orderPostDto={}", orderPostDto);

//        OrderItemPostDto 객체 생성 (order_items 에 데이터 받기를 위함)
//        orderId 는 order 가 insert 될때 발생한다. auto_increment 된 값을 받아오는 방법
        OrderItemPostDto orderItemPostDto = OrderItemPostDto.builder()
                .orderId(orderPostDto.getOrderId())
                .itemIds(req.getItemIds())
                .build();
        int resultDetail = orderItemMapper.save(orderItemPostDto);

//        장바구니 목록 삭제
        cartMapper.deleteByMemberId(logginedMemberId);

        return 1;
    }

    public List<OrderGetRes> findAllByMemberIdOrderByIdDesc(int memberId) {
        return  orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
    }
}
