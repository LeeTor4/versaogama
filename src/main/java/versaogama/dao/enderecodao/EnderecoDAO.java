package versaogama.dao.enderecodao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.estabelecimento.Endereco;


public class EnderecoDAO implements EnderecoDaoInterface{

	private final Pool pool;
	public EnderecoDAO(Pool pool) {
		this.pool = pool;
	}
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_endereco WHERE id = ?";
		Connection con = pool.getConnection();
        try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		pool.liberarConnection(con);
	}

	@Override
	public void cadastrar(Endereco end) throws SQLException {
		String sql = "INSERT INTO tb_endereco (nmlogradouro,numlogradouro,compl,bairro,cep,coduf,uf,codmun,municipio,id) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtEnd(end, stmt);
			stmt.execute();
		}
	
		pool.liberarConnection(con);
	}

	private void stmtEnd(Endereco end, PreparedStatement stmt) throws SQLException {
		
		stmt.setString(1, end.getNmLogradouro());
		stmt.setString(2, end.getNumLogradouro());
		stmt.setString(3, end.getCompl());
		stmt.setString(4, end.getBairro());
		stmt.setString(5, end.getCep());
        
		stmt.setLong(6, (end.getCodUf()==null ? 0 : end.getCodUf()));
		
        stmt.setString(7, end.getUf());
		stmt.setLong(8, (end.getCodMun()==null ?0 : end.getCodMun()));
		stmt.setString(9, end.getNmMun());
		if(end.getId()!= null && end.getId() > 0) {
			stmt.setLong(10, end.getId());
		}else {
			stmt.setLong(10, 0);
		}
	}
	@Override
	public void salvar(Endereco end) throws SQLException {
		if(end.getId() != null && end.getId() > 0) {
			atualizar(end);
		}else {
			cadastrar(end);
		}
		
	}

	@Override
	public void atualizar(Endereco end) throws SQLException {
	    String sql = "UPDATE tb_endereco SET nmlogradouro=?,numlogradouro=?,compl=?,bairro=?,cep=?,coduf=?,uf=?,codmun=?,municipio=?"
	    		+" WHERE id=?";
	    Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtEnd(end, stmt);
			stmt.execute();
		}
		pool.liberarConnection(con);
	}

	@Override
	public Endereco getEndereco(Integer codigo) throws SQLException {
		Endereco end = new Endereco(Long.valueOf(codigo));
		String sql= "SELECT * FROM tb_endereco WHERE id = ?";		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){				
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){					
				while(rs.next()) {					
					end = rsEnd(rs);					
				}			
			}			
		}		
		pool.liberarConnection(con);
		return end;
	}

	@Override
	public List<Endereco> getEnderecos() throws SQLException {
		List<Endereco> retorno = new ArrayList<>();
		String sql= "SELECT * FROM tb_endereco";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){		
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){
				while(rs.next()) {					
					Endereco end = rsEnd(rs);
					retorno.add(end);
				}
			}
		}		
		pool.liberarConnection(con);
		return retorno;
	}
	
	
	private Endereco rsEnd(ResultSet rs) throws SQLException {
		Endereco end = new Endereco(Long.valueOf(rs.getLong("id")));
		end.setId(rs.getLong("id"));
		end.setNmLogradouro(rs.getString("nmLogradouro"));
		end.setNumLogradouro(rs.getString("numlogradouro"));
		end.setCompl(rs.getString("compl"));
		end.setBairro(rs.getString("bairro"));
		end.setCep(rs.getString("cep"));
		end.setCodUf(rs.getLong("coduf"));
		end.setUf(rs.getString("uf"));
		end.setCodMun(rs.getLong("codmun"));
		end.setNmMun(rs.getString("municipio"));
		return end;
	}
	
	public Long pegarValorAutoIncremento(String nomeBanco) throws SQLException {
		Long id = null;
		String sql = 
				"SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'tb_endereco' AND table_schema = " + "'"+nomeBanco+"'";
	
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){		
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){
				while(rs.next()) {			
					id = rs.getLong("AUTO_INCREMENT");
				}
				
			}
		}
	
	     return id;
	}
}
