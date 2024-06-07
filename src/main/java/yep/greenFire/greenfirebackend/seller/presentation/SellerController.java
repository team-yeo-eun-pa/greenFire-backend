package yep.greenFire.greenfirebackend.seller.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.seller.service.SellerService;

@Slf4j
@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;


}