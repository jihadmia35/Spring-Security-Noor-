package bd.edu.seu.ss;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
   public final static HashMap<String, String> userMap = new HashMap<>();

    public final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        userMap.put("017", passwordEncoder.encode("123"));
    }

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String mobile = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();
        IO.println(mobile + " " + rawPassword);

        if (userMap.get(mobile) == null) {
            throw new BadCredentialsException("Invalid mobile or password");
        }

        if (!passwordEncoder.matches(rawPassword, userMap.get(mobile))) {
            throw new BadCredentialsException("Invalid mobile or password");
        }

        return new UsernamePasswordAuthenticationToken(mobile, null, List.of(
                new SimpleGrantedAuthority("READ_INVOICE"),
                new SimpleGrantedAuthority("READ_REPORT")
        ));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
