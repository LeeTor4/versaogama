package versaogama.dao.enderecodao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.estabelecimento.Municipio;


public class MunicipioDao implements MunicipioDaoInterface{

	private final Pool pool;
	
	public MunicipioDao(Pool pool) {
		this.pool = pool;
	}
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_municipio WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
			
		}
		pool.liberarConnection(con);
	}

	@Override
	public void cadastrar(Municipio mun) throws SQLException {
		String sql = "INSERT INTO tb_municipio(ufid,nome,id) VALUE (?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmtMunicipio(mun, stmt);
			stmt.execute();			
		}
		pool.liberarConnection(con);
	}

	private void stmtMunicipio(Municipio mun, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, mun.getNome());
		stmt.setLong(2,mun.getUfId());
		if(mun.getId() != null && mun.getId() > 0){
			stmt.setLong(3, mun.getId());
		}else {
			stmt.setLong(3, 0);
		}
	}
	@Override
	public void salvar(Municipio mun) throws SQLException {
		if(mun.getId() != null && mun.getId()> 0) {
			atualizar(mun);
		}else {
			cadastrar(mun);
		}
	}

	@Override
	public void atualizar(Municipio mun) throws SQLException {
		String sql = "UPDATE tb_municipio SET ufid=?,nome=? WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmtMunicipio(mun, stmt);
			stmt.execute();					
		}
		pool.liberarConnection(con);
	}

	@Override
	public Municipio getMunicipio(Integer codigo) throws SQLException {
		Municipio mun = new Municipio();
		String sql = "SELECT * FROM tb_municipio WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){						
				while(rs.next()) {								
					mun = rsMun(rs);
				}
			}
		}
		
		return mun;
	}

	@Override
	public List<Municipio> getMunicipios() throws SQLException {
		List<Municipio> retorno = new ArrayList<>();
		String sql = "SELECT * FROM tb_municipio";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {	
					Municipio mun = new Municipio();					
					mun = rsMun(rs);
					retorno.add(mun);
				}
			}
			
		}
		pool.liberarConnection(con);		
		return retorno;
	}
	private Municipio rsMun(ResultSet rs) throws SQLException {
		Municipio mun = new Municipio();
		mun.setId(rs.getLong("id"));
		mun.setNome(rs.getString("nome"));
		mun.setUfId(rs.getLong("ufid"));
		
		return mun;
	}

}
