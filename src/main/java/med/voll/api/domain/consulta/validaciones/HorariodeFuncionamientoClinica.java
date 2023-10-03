package med.voll.api.domain.consulta.validaciones;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;

@Component
public class HorariodeFuncionamientoClinica implements ValidadordeConsultas {
	
	public void validar(DatosAgendarConsulta datos) {
		var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
		
		var antesDeApertura = datos.fecha().getHour()<7 ;
		var despuesdeCierre = datos.fecha().getHour()>19 ;
		
		if(domingo || antesDeApertura || despuesdeCierre) {
			throw new ValidationException("El horario de atencion de la clinica es de lunes a sabado, horario de 7:00 a 19:00 horas");
		}
		
	}
}
