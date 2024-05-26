package yep.greenFire.greenfirebackend.auth.type;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class CustomUser extends User {
    private Integer memberCode;
    public CustomUser(Integer memberCode, UserDetails userDetails) {
        super(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        this.memberCode = memberCode;
    }
}
