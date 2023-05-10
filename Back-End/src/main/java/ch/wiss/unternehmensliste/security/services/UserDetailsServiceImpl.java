package ch.wiss.unternehmensliste.security.services;

import ch.wiss.unternehmensliste.model.Role;
import ch.wiss.unternehmensliste.model.User;
import ch.wiss.unternehmensliste.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Das UserDetailsServiceImpl ist eine Implementierung des UserDetailsService Interfaces.
 * Es wird verwendet, um die Datenbankabfragen für die User-Objekte zu definieren.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // loadUserByUsername() wird verwendet, um den User anhand des Usernamens zu finden.
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username" + username));
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role r : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(r.getName().toString()));
        }
        return UserDetailsImpl.build(user);
    }
}
