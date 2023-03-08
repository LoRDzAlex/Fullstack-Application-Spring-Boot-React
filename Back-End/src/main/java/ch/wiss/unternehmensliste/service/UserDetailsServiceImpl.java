package ch.wiss.unternehmensliste.service;

import ch.wiss.unternehmensliste.model.Role;
import ch.wiss.unternehmensliste.model.User;
import ch.wiss.unternehmensliste.model.UserDetailsImpl;
import ch.wiss.unternehmensliste.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found with username: " +username));
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role r: user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        }
        return UserDetailsImpl.build(user);
    }
}
