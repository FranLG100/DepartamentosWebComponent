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

 
public class DepartamentoDAO {
	private Conexion con;
	private Connection connection;
 
	public DepartamentoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
 
	// insertar artículo
	public boolean insertar(Departamento departamento) throws SQLException {
		String sql = "INSERT INTO departamentos (dnombre, loc) VALUES (?,?)";
		//System.out.println(departamento.getDescripcion());
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		//statement.setString(1, null);
		statement.setString(1, departamento.getDnombre());
		statement.setString(2, departamento.getLoc());

 
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}
 
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
	public Departamento obtenerPorId(int id) throws SQLException {
		Departamento dpto = null;
		System.out.println("ID DE DEPARTAMENTO: "+id);
		String sql = "SELECT * FROM departamentos WHERE dept_no= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			dpto = new Departamento(res.getInt(1), res.getString(2), res.getString(3));
			
		}
		res.close();
		con.desconectar();
 
		return dpto;
	}
 
	// actualizar
	public boolean actualizar(Departamento dpto) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE departamentos SET dnombre=?,loc=? WHERE dept_no=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, dpto.getDnombre());
		statement.setString(2, dpto.getLoc());
		statement.setInt(3, dpto.getDeptno());
		System.out.println("Actualizado");
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Departamento dpto) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM departamentos WHERE dept_no=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, dpto.getDeptno());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		System.out.println("Departamento eliminado");
		return rowEliminar;
	}
	
}
