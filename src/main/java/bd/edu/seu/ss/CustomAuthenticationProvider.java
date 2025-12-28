package bd.edu.seu.ss;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String mobile = authentication.getName();
        String password = authentication.getCredentials().toString();
        IO.println(mobile + " " + password);

        if (!mobile.equals(password)) {
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
