package com.study.shop.sales;

import com.study.shop.member.CustomUser;
import com.study.shop.member.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;

    @PostMapping("/order")
    String postOrder(
            @RequestParam String title,
            @RequestParam Integer price,
            @RequestParam Integer count,
            Authentication auth
    ) {
        Sales sales = new Sales();
        sales.setCount(count);
        sales.setPrice(price);
        sales.setItemName(title);
        CustomUser user = (CustomUser) auth.getPrincipal();
        var member = new Member();
        member.setId(user.id);
        sales.setMember(member);
        salesRepository.save(sales);

        return "redirect:/list";
    }

    @GetMapping("/orders")
    String getOrderAll(Model model, Authentication auth) {
        List<SalesDto> sales = salesRepository.customFindAll().stream()
                .map(s -> new SalesDto(
                        s.getItemName(),
                        s.getPrice(),
                        s.getMember().getUsername()
                ))
                .toList();
        model.addAttribute("sales", sales);
        return "orders";
    }

}

@Getter
class SalesDto {
    private final String itemName;
    private final Integer price;
    private final String username;

    public  SalesDto(String itemName, Integer price, String username) {
        this.itemName = itemName;
        this.price = price;
        this.username = username;
    }
}