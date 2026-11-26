package com.ipartek.formacion.bibliotecas.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapeador<T> {
	T mapear(ResultSet rs) throws SQLException;
}
