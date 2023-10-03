package med.voll.api.domain.consulta.validaciones;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;

@Component
public class HorariodeAnticipacion implements ValidadordeConsultas{

	public void validar(DatosAgendarConsulta datos) {
		var ahora = LocalDateTime.now();
		var horaDeConsulta = datos.fecha();
		
		var diferenciaDe30Minutos = java.time.Duration.between(ahora, horaDeConsulta).toMinutes()< 30;
		
		if(diferenciaDe30Minutos) {
			throw new ValidationException("Las consultas deben propagarse con 30 minutos de anticipacion");
		}
		
	}	
}
