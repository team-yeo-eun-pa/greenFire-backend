package yep.greenFire.greenfirebackend.user.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.user.member.dto.response.ProfileResponse;
import yep.greenFire.greenfirebackend.user.member.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    //마이페이지 접근
    @GetMapping("/{memberId}")
    @PreAuthorize("#memberId == authentication.principal.username")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String memberId) {
        ProfileResponse profileResponse = memberService.getProfile(memberId);

        return ResponseEntity.ok(profileResponse);
    }

}
