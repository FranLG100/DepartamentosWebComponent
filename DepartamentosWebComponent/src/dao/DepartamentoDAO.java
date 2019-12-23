package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

import modelo.Conexion;
import modelo.Departamento;

 
/*
 * @autor: Elivar Largo
 * @web: www.ecodeup.com
 */
 
public class DepartamentoDAO {
	private Conexion con;
	private Connection connection;
 
	public DepartamentoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
 
	// insertar artículo
	/*public boolean insertar(Departamento departamento) throws SQLException {
		String sql = "INSERT INTO articulos (id, codigo, nombre, descripcion, existencia, precio) VALUES (?, ?, ?,?,?,?)";
		System.out.println(departamento.getDescripcion());
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, null);
		statement.setString(2, departamento.getCodigo());
		statement.setString(3, departamento.getNombre());
		statement.setString(4, departamento.getDescripcion());
		statement.setDouble(5, departamento.getExistencia());
		statement.setDouble(6, departamento.getPrecio());
 
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}*/
 
	// listar todos los productos
	public List<Departamento> listarDepartamentos() throws SQLException {
 
		List<Departamento> listaDptos = new ArrayList<Departamento>();
		String sql = "SELECT * FROM departamentos";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt(1);
			String nombre = resulSet.getString(2);
			String localidad = resulSet.getString(3);
			Departamento dpto = new Departamento(id, nombre, localidad);
			listaDptos.add(dpto);
		}
		con.desconectar();
		return listaDptos;
	}
 
	// obtener por id
	/*public Articulo obtenerPorId(int id) throws SQLException {
		Articulo articulo = null;
 
		String sql = "SELECT * FROM articulos WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			articulo = new Articulo(res.getInt("id"), res.getString("codigo"), res.getString("nombre"),
					res.getString("descripcion"), res.getDouble("existencia"), res.getDouble("precio"));
		}
		res.close();
		con.desconectar();
 
		return articulo;
	}
 
	// actualizar
	public boolean actualizar(Articulo articulo) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE articulos SET codigo=?,nombre=?,descripcion=?,existencia=?, precio=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, articulo.getCodigo());
		statement.setString(2, articulo.getNombre());
		statement.setString(3, articulo.getDescripcion());
		statement.setDouble(4, articulo.getExistencia());
		System.out.println(articulo.getPrecio());
		statement.setDouble(5, articulo.getPrecio());
		statement.setInt(6, articulo.getId());
 
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Articulo articulo) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM articulos WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, articulo.getId());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
	*/
}
