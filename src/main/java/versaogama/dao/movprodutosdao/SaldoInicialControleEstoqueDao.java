package versaogama.dao.movprodutosdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.movprodutos.ModeloSaldoInicialControleEstoque;

public class SaldoInicialControleEstoqueDao {

	 private final Pool pool;
	 
	 public SaldoInicialControleEstoqueDao(Pool pool) {
		 this.pool = pool;
	}
	 
	 
   public void importarSaldoInicialEstoque(ModeloSaldoInicialControleEstoque obj) throws SQLException {
	   
	   String sql = "INSERT INTO tb_saldo_inicial_estoque_mensal (cnpj,ano,cod_item,cod_ant_item,descricao,saldo) VALUES (?,?,?,?,?,?)";
	   
	   Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.setString(1, obj.getCnpj());
			stmt.setString(2, obj.getAno());
			stmt.setString(3, obj.getCodItem());
			stmt.setString(4, obj.getCodAntItem());
			stmt.setString(5, obj.getDescricao());
			stmt.setDouble(6, obj.getQtdeInicial());
			
			stmt.execute();
			
		}
		
		System.out.println("Saldo inicial do prod " + obj.getCodItem() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
   }
   
   public ModeloSaldoInicialControleEstoque getSaldoInicial(String codItem, String codAntItem, String cnpj, String ano) throws SQLException{
	   ModeloSaldoInicialControleEstoque obj = null;
	   String sql = "SELECT * FROM versaogamadb.tb_saldo_inicial_estoque_mensal where cod_item in (?,?) and cnpj = ? and ano = ?;";
	   Connection con = pool.getConnection();
	   try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
		   stmt.setString(1, codItem);
			stmt.setString(2, codAntItem);
			stmt.setString(3, cnpj);
			stmt.setString(4, ano);
			stmt.execute();
			ResultSet rs = stmt.executeQuery();
		 	while(rs.next()) {    		 	
		 	    obj = new ModeloSaldoInicialControleEstoque();
		 		
		 		obj.setId(rs.getLong("id"));
		 		obj.setCnpj(rs.getString("cnpj"));
		 		obj.setAno(rs.getString("ano"));
		 		obj.setCodItem(rs.getString("cod_item"));
		 		obj.setCodAntItem(rs.getString("cod_ant_item"));
		 		obj.setDescricao(rs.getString("descricao"));
		 		obj.setQtdeInicial(rs.getDouble("saldo"));
		 		
		 	}
       }
		   
		pool.liberarConnection(con);
	   
	    return obj;
   }
   	 
}
