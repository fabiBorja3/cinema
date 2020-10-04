package co.edu.uniajc.cinema.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.ParseException;

public class Util {

	public Date obtieneFecha() {
		java.util.Date d = new java.util.Date();
		java.sql.Date date2 = new java.sql.Date(d.getTime());
		return date2;
	}

	/**
	 * Permite convertir un String en fecha (Date).
	 *
	 * @param fecha Cadena de fecha dd/MM/yyyy
	 * @return Objeto Date
	 */
	public Date ParseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaDate = null;
		try {
			java.util.Date date = formato.parse(fecha);
			fechaDate = new Date(date.getTime());
		} catch (ParseException ex) {
			System.out.println(ex);
		}
		return fechaDate;
	}

	public boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	
	public int obtenerPeriodo() {
		int periodo = 0;
		try {
			Calendar fecha = Calendar.getInstance();
			int mes = fecha.get(Calendar.MONTH) + 1;
			if (mes >= 1 && mes <= 6) {
				periodo = 	1;
			} else {
				periodo = 	2;
			}
			return periodo;
		} catch (Exception exp) {
			exp.getMessage();
		}
		return periodo;
	}
	
	public boolean validaCorreo(String email) {
		boolean resultado = false;
		try {
			Pattern pattern = Pattern
					.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
							+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher mather = pattern.matcher(email);
			if (mather.find() == true) {
				resultado = true;
			} else {
				resultado = false;
			}
			return resultado;
		} catch(Exception exp) {
			exp.getMessage();
		}
		return resultado;
	}
}
