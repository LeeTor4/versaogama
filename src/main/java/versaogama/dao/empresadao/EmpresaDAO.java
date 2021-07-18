package versaogama.dao.empresadao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.empresa.Empresa;
import versaogama.util.UtilsEConverters;

public class EmpresaDAO implements EmpresaDaoInterface{
	
	private final Pool pool;

	public EmpresaDAO(Pool pool) {
		this.pool = pool;
	}
	
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_empresa WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Empresa excluída  com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void cadastrar(Empresa empresa) throws SQLException {
		String sql = "INSERT INTO tb_empresa(razaosocial,nomefantasia,cnpjbase,cpf,dt_ini_ativ,id)"
				+ "VALUES(?,?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){				
			smtEmpresa(empresa, stmt);
			stmt.execute();
		}
		System.out.println("Empresa " + empresa.getRazaoSocial() + ", cadastrada com sucesso!!");
		pool.liberarConnection(con);
	}

	private void smtEmpresa(Empresa empresa, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, empresa.getRazaoSocial());
		stmt.setString(2, empresa.getNmFantasia());
		stmt.setString(3, empresa.getCnpjBase());
		stmt.setString(4, empresa.getCpf());
		stmt.setDate(5, UtilsEConverters.getLocalDateParaDateSQL(empresa.getDtIniAtiv()));
		if(empresa.getId() != null) {
			stmt.setLong(6, empresa.getId());
		}else {
			stmt.setLong(6, 0);
		}
	}


	@Override
	public void salvar(Empresa empresa) throws SQLException {
		if(empresa.getId() !=null && empresa.getId() > 0) {
			atualizar(empresa);
		}else {
			cadastrar(empresa);
		}	
	}

	@Override
	public void atualizar(Empresa empresa) throws SQLException {
		String sql = "UPDATE tb_empresa set razaosocial=?,nomefantasia=?,cnpjbase=?,cpf=?,dt_ini_ativ=? WHERE id=?";
			
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			smtEmpresa(empresa, stmt);
			stmt.execute();
		}
		
		System.out.println("Empresa " + empresa.getRazaoSocial() + ", alterada com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public Empresa getEmpresa(Integer codigo) throws SQLException {
		Empresa emp = new Empresa();
		String sql = "SELECT * FROM tb_empresa WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){				
				while(rs.next()) {
					emp = rsEmpresa(rs);
				}				
			}			
		}	
		pool.liberarConnection(con);
		return emp;
	}
	
	public Empresa getEmpresa(String cnpjbase) throws SQLException {
		Empresa emp = new Empresa();
		String sql = "SELECT * FROM tb_empresa WHERE cnpjbase = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setString(1, cnpjbase);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){				
				while(rs.next()) {
					emp = rsEmpresa(rs);
				}				
			}			
		}	
		pool.liberarConnection(con);
		return emp;
	}

	@Override
	public List<Empresa> getEmpresas() throws SQLException {
		List<Empresa> retorno = new ArrayList<>();
		String sql = "SELECT * FROM tb_empresa";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){
				
				while(rs.next()) {
					Empresa emp = rsEmpresa(rs);
					retorno.add(emp);
				}
			}
			pool.liberarConnection(con);
		}
		
		
		return retorno;
	}

	private Empresa rsEmpresa(ResultSet rs) throws SQLException {
		Empresa emp = new Empresa(rs.getLong("id"));
		//emp.setId(rs.getLong("id"));
		emp.setRazaoSocial(rs.getString("razaosocial"));
		emp.setNmFantasia(rs.getString("nomefantasia"));
		emp.setCnpjBase(rs.getString("cnpjbase"));
		emp.setCpf(rs.getString("cpf"));
		emp.setDtIniAtiv(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_ini_ativ")));
		return emp;
	}

	public Empresa getUltimaEmpresa() throws SQLException {
		Empresa emp = new Empresa();
		String sql = "SELECT * FROM tb_empresa WHERE id = (SELECT MAX(id) FROM tb_empresa)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){				
				while(rs.next()) {
					emp = rsEmpresa(rs);
				}				
			}			
		}	
		pool.liberarConnection(con);
		return emp;
	}
	
}
