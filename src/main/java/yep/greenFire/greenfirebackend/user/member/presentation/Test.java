package yep.greenFire.greenfirebackend.user.member.presentation;


import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.user.member.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class Test {

    private final MemberService memberService;

    @GetMapping("/member/mypage/CsCenter")
    public String getCsList (
            Model model,
            @RequestParam(defaultValue="1") int page,
            @RequestParam(required = false) String searchCondition,
            @RequestParam(required = false) String searchValue
    ) {


        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> boardListAndPaging = memberService.selectCsList(searchMap, page);
    }
}
