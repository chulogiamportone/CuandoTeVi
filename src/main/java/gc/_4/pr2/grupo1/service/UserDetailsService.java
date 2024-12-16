package gc._4.pr2.grupo1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import gc._4.pr2.grupo1.entity.Admin;
import gc._4.pr2.grupo1.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	 
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        Admin a = userRepository.findByUser(user).orElse(null);
        if (a == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new User(a.getUser(), a.getContraseña(), getAuthorities(a.getRole()));
    }
    private List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
    
}
