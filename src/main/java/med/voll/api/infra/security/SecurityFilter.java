package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuariosRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	
	@Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        var outheader = request.getHeader("Authorization");
         if(outheader != null) {
        	  var token = outheader.replace("Bearer ", "");
        	  
              var subject = tokenService.getSubject(token);
              if(subject != null) {
            	  //toen valido
            	var usuario = usuariosRepository.findByLogin(subject);  
            	var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //forzamos el inicio de sesion
            	SecurityContextHolder.getContext().setAuthentication(autentication);
              }
            	  
        }
         filterChain.doFilter(request, response);

   }
}
