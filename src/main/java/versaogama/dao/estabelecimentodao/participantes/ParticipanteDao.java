package versaogama.dao.estabelecimentodao.participantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.estabelecimento.Participante;

public class ParticipanteDao implements ParticipanteDaoInterface{

	private Pool pool;
	
	public ParticipanteDao(Pool pool) {
		this.pool = pool;
	}
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_participantes WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Participante excluído  com sucesso!!");
		pool.liberarConnection(con);
		
		
	}

	@Override
	public void salvar(Participante part) throws SQLException {
		if(part.getId() != null && part.getId() > 0) {
			atualizar(part);
		}else {
			cadastrar(part);
		}
		
	}

	@Override
	public void cadastrar(Participante part) throws SQLException {
		String sql = "INSERT INTO tb_participantes (id_pai_emp,"
				+ "id_pai_est,"
				+ "cod_part,"
				+ "nome,"
				+ "cod_pais,"
				+ "cnpjcpf,"
				+ "insc_estadual,"
				+ "cod_municipio,"
				+ "insc_municipal,"
				+ "logradouro,"
				+ "numero,"
				+ "compl,"
				+ "bairro,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtParticipante(part, stmt);
			stmt.execute();
			
		}
		System.out.println( " Participante " + part.getNome() + " cadastrado  com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void atualizar(Participante part) throws SQLException {
		String sql = "UPDATE tb_participantes SET id_pai_emp=?,id_pai_est=?,cod_part=?,nome=?,cod_pais=?,cnpjcpf=?,insc_estadual=?,cod_municipio=?,insc_municipal=?,logradouro=?,numero=?,compl=?,bairro=? WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
		
			stmtParticipante(part, stmt);
			stmt.execute();
		}
		System.out.println("Participante " + part.getNome() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public Participante getParticipante(Integer codigo) throws SQLException {
		Participante part = new Participante();
		String sql = "SELECT * FROM tb_participantes WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {		
					
                    part = rsParticipante(rs);
				}
				
			}
			
		}

		return part;
	}

	@Override
	public List<Participante> getParticipantes() throws SQLException {
		List<Participante> retorno = new ArrayList<Participante>();
		String sql = "SELECT * FROM tb_participantes";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {		
					Participante part = rsParticipante(rs);
                    retorno.add(part);
				}	
			}
		}
		pool.liberarConnection(con);
		return retorno;
	}

	private void stmtParticipante(Participante part, PreparedStatement stmt) throws SQLException {
		stmt.setLong(1, part.getIdPaiEmp());
		stmt.setLong(2, part.getIdpaiEst());
		stmt.setString(3, part.getCodPart());
		stmt.setString(4, part.getNome());
		stmt.setString(5, part.getCodPais());
		stmt.setString(6, part.getCnpjCpf());
		stmt.setString(7, part.getInscEstadual());
		stmt.setString(8, part.getCodMunicipio());
		stmt.setString(9,part.getInscMunicipal());
		stmt.setString(10, part.getEndereco().getNmLogradouro());
		stmt.setString(11, part.getEndereco().getNumLogradouro());
		stmt.setString(12, part.getEndereco().getCompl());
		stmt.setString(13, part.getEndereco().getBairro());
		if(part.getId() != null && part.getId() > 0) {
			stmt.setLong(14, part.getId());
		}else {
			stmt.setLong(14, 0);
		}
	}
	
	private Participante rsParticipante(ResultSet rs) throws SQLException {
		Participante part = new Participante();
		
		part.setId(rs.getLong("id"));
		part.setIdPaiEmp(rs.getLong("id_pai_emp"));
		part.setIdpaiEst(rs.getLong("id_pai_est"));
		part.setCodPart(rs.getString("cod_part"));
		part.setNome(rs.getString("nome"));
		part.setCodPais(rs.getString("cod_pais"));
		part.setCnpjCpf(rs.getString("cnpjcpf"));
		part.setInscEstadual(rs.getString("insc_estadual"));
		part.setCodMunicipio(rs.getString("cod_municipio"));
		part.setInscMunicipal(rs.getString("insc_municipal"));
		part.getEndereco().setNmLogradouro(rs.getString("logradouro"));
		part.getEndereco().setNumLogradouro(rs.getString("numero"));
		part.getEndereco().setCompl(rs.getString("compl"));
		part.getEndereco().setBairro(rs.getString("bairro"));
		return part;
	}
	
	public Long getIncPart() {
		Long idRetorno = null;
		Pool pool = new Pool();
		String sql = "SELECT AUTO_INCREMENT AS ID FROM information_schema.tables WHERE table_name = 'tb_participantes' AND table_schema = 'versaogamadb' ;";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){					
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {
					
					idRetorno =  rs.getLong("ID");
					
				}				
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return idRetorno;
	}
}
