package kgu.softwareEG.personalMovie.global.auth;

import kgu.softwareEG.personalMovie.domain.user.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Data
public class OAuthPrincipalDetails implements OAuth2User {

    private User user;
    private Map<String, Object> attributes;

    public OAuthPrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    public String getSocialId() {
        return user.getSocialId();
    }

    @Override
    public String getName() {
        return null;
    }
}