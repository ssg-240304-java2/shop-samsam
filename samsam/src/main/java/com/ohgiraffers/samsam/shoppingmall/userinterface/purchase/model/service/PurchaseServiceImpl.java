package com.ohgiraffers.samsam.shoppingmall.userinterface.purchase.model.service;

import com.ohgiraffers.samsam.shoppingmall.userinterface.purchase.model.dao.PurchaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseMapper purchaseMapper;

    public PurchaseServiceImpl(PurchaseMapper purchaseMapper) {
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    @Transactional
    public void processPurchase(int customerSeq, int productSeq, int quantity) {
        try {
            // 상품 가격 조회
            int pricePerUnit = purchaseMapper.findProductPriceById(productSeq);

            // 결제 금액 계산
            int totalAmount = quantity * pricePerUnit;

            // 주문 상태
            String orderStatus = "결제완료";

            // 새로운 구매 등록
            purchaseMapper.insertPurchase(customerSeq, productSeq, totalAmount, orderStatus, quantity);

            log.info("구매가 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            log.error("구매 중 오류가 발생했습니다.", e);
            throw new RuntimeException("구매 중 오류가 발생했습니다.");
        }
    }
}
