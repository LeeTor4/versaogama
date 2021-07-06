package versaogama;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import versaogama.conexao.Pool;
import versaogama.dao.empresadao.EmpresaDAO;
import versaogama.dao.enderecodao.EnderecoDAO;
import versaogama.dao.estabelecimentodao.equipecf.EquipamentoECFDao;
import versaogama.dao.estabelecimentodao.estabelecimento.EstabelecimentoDao;
import versaogama.model.system.empresa.Empresa;
import versaogama.model.system.equipecf.EquipamentoECF;
import versaogama.model.system.estabelecimento.Endereco;
import versaogama.model.system.estabelecimento.Estabelecimento;

public class MainCrudBenelux {

	private EmpresaDAO empDao;
	private EstabelecimentoDao estDao;
	private EnderecoDAO endDao;
	private EquipamentoECFDao daoECF;
	
	public MainCrudBenelux() {
		Pool pool = new Pool();
		empDao = new EmpresaDAO(pool);
		estDao = new EstabelecimentoDao(pool);
		endDao = new EnderecoDAO(pool);
		daoECF = new EquipamentoECFDao(pool);
	}
	

	public static void main(String[] args) throws SQLException {
		
		Empresa emp = new Empresa();
		emp.setRazaoSocial("Benelux Distribuidora de Alimentos LTDA");
		emp.setNmFantasia("Benelux");
		emp.setCnpjBase("24653373");
		
		new MainCrudBenelux().empDao.salvar(emp);
			
		Endereco end1 = new Endereco();
		end1.setNmLogradouro("Rua Idelfonso Albano");

		
		new MainCrudBenelux().endDao.salvar(end1);	

		Endereco id_end1 = new MainCrudBenelux().endDao.getEndereco(7);
		
		
		Long id = (long) new MainCrudBenelux().empDao.getEmpresas().size();
		System.out.println("Id_Emp " + id);
		Estabelecimento matriz = 
		 new Estabelecimento(id,id_end1.getId(),"24653373000120","Benelux Distribuidora de Alimentos LTDA","Matriz",end1,emp);

		
		emp.adicionaEstab(matriz);		
		
		for(Estabelecimento est :  emp.getEstabs()){
			new MainCrudBenelux().estDao.salvar(est);
	    }
		
		
		
		
		
		
//		Empresa empresa = new MainCrudBenelux().empDao.getEmpresa(1);
//		
//		Endereco end6 = new Endereco();		
//		end6.setNmLogradouro("Rua Rui Barbosa");
//		new MainCrudBenelux().endDao.salvar(end6);
//		Endereco id_end6 = new MainCrudBenelux().endDao.getEndereco(6);
//		
//		Estabelecimento loja6 = 
//				 new Estabelecimento(empresa.getId(),id_end6.getId(),"05329222000680","Sellene Comercio e Representações Ltda","Megafarma",end6,empresa);
//		
//		empresa.adicionaEstab(loja6);
//		for(Estabelecimento est :  empresa.getEstabs()){
//			new MainCrudBenelux().estDao.salvar(est);
//	    }

		
//		new MainCrud().empDao.excluir(1);	
//		for(int i=1;i<10;i++){
//		     new MainCrud().endDao.excluir(i);
//	    }
		
//		for(Estabelecimento est:  emp.getEstabs()){
//			
//			System.out.println(emp.getId() + "|"+ est.getIdPai() + "|"+ emp.getCnpjBase()+"|"+est.getCnpj() + "|" + est.getEnd().getId()+"|"+est.getEnd().getNmLogradouro());
//		}
		
//		for(int i=1;i<10;i++){
//			new MainCrud().endDao.excluir(i);
//		}
		//new MainCrud().empDao.excluir(1);
		
		//new MainCrud().dao.cadastrar(emp);
		//new MainCrud().dao.atualizar(emp);
		
		
//		System.out.println(new MainCrud().endDao.pegarValorAutoIncremento());
//		
//		int n = Integer.valueOf(new MainCrud().endDao.pegarValorAutoIncremento().intValue()); // tamanho do vetor
//		int v[] = new int[n]; // declaração e alocação de espaço para o vetor "v"
//		int i; // índice ou posição
//
//		// processando os "n" elementos do vetor "v"
//		for (i=1; i<n; i++) {
//		    v[i] = i; // na i-ésima posição do vetor "v" armazena o valor da variável "i"
//		    System.out.println(v[i]);
//		}
		
		
		
		//new MainCrud().empDao.excluir(1);	
		
		
//		Empresa empresa = new MainCrud().dao.getEmpresa(1);
//		System.out.println(empresa.getRazaoSocial());
		
		//new MainCrud().dao.getEmpresas().forEach((t) -> System.out.println(t.getNmFantasia()));
		
	}
	public EmpresaDAO getEmpDao() {
		return empDao;
	}

	
}
