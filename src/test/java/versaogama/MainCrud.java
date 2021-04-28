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

public class MainCrud {

	private EmpresaDAO empDao;
	private EstabelecimentoDao estDao;
	private EnderecoDAO endDao;
	private EquipamentoECFDao daoECF;
	
	public MainCrud() {
		Pool pool = new Pool();
		empDao = new EmpresaDAO(pool);
		estDao = new EstabelecimentoDao(pool);
		endDao = new EnderecoDAO(pool);
		daoECF = new EquipamentoECFDao(pool);
	}
	

	public static void main(String[] args) throws SQLException {
		
		Empresa emp = new Empresa();
		emp.setRazaoSocial("Sellene Comercio e Representações Ltda");
		emp.setNmFantasia("Sellene Matriz");
		emp.setCnpjBase("05329222");
		
		new MainCrud().empDao.salvar(emp);
			
		Endereco end1 = new Endereco();
		end1.setNmLogradouro("Joao Carvalho");
		
		Endereco end2 = new Endereco();		
		end2.setNmLogradouro("Rua Rui Barbosa");
		
		Endereco end3 = new Endereco();
		end3.setNmLogradouro("Av. Santos Dumont");
		
		Endereco end4 = new Endereco();
		end4.setNmLogradouro("Av. Santos Dumont");
		
		new MainCrud().endDao.salvar(end1);
		new MainCrud().endDao.salvar(end2);
		new MainCrud().endDao.salvar(end3);
		new MainCrud().endDao.salvar(end4);
	

		Endereco id_end1 = new MainCrud().endDao.getEndereco(1);
		Endereco id_end2 = new MainCrud().endDao.getEndereco(2);
		Endereco id_end3 = new MainCrud().endDao.getEndereco(3);
		Endereco id_end4 = new MainCrud().endDao.getEndereco(4);

		
		
		
		Long id = (long) new MainCrud().empDao.getEmpresas().size();
		System.out.println("Id_Emp " + id);
		Estabelecimento matriz = 
		 new Estabelecimento(id,id_end1.getId(),"05329222000176","Sellene Comercio e Representações Ltda","Matriz",end1,emp);

		Estabelecimento mega =
		 new Estabelecimento(id,id_end2.getId(),"05329222000419","Sellene Comercio e Representações Ltda","Megadiet",end2,emp);


		Estabelecimento sao = 
		 new Estabelecimento(id,id_end3.getId(),"05329222000755","Sellene Comercio e Representações Ltda","Sao Mateus",end3,emp);
		
		Estabelecimento harm = 
				 new Estabelecimento(id,id_end4.getId(),"05329222000886","Sellene Comercio e Representações Ltda","Harmony",end4,emp);

		
		emp.adicionaEstab(matriz);		
		emp.adicionaEstab(mega);
		emp.adicionaEstab(sao);
		emp.adicionaEstab(harm);
		for(Estabelecimento est :  emp.getEstabs()){
			new MainCrud().estDao.salvar(est);
	    }
		
		
		List<EquipamentoECF> ecfs = new ArrayList<EquipamentoECF>();
		EquipamentoECF ecf1 = new EquipamentoECF();
		ecf1.setId_pai_emp(1L);
		ecf1.setId_pai_est(1L);
		ecf1.setCodModDocFiscal("2D");
		ecf1.setModeloEquip("ECF"); 
		ecf1.setNumSerieFabECF("BE090910100010021930");
		ecf1.setNumECF("3");
		ecfs.add(ecf1);
		EquipamentoECF ecf2 = new EquipamentoECF();
		ecf2.setId_pai_emp(1L);
		ecf2.setId_pai_est(1L);
		ecf2.setCodModDocFiscal("2D");
		ecf2.setModeloEquip("ECF"); 
		ecf2.setNumSerieFabECF("BE091210100011203011");
		ecf2.setNumECF("4");
		ecfs.add(ecf2);
		EquipamentoECF ecf3 = new EquipamentoECF();
		ecf3.setId_pai_emp(1L);
		ecf3.setId_pai_est(1L);
		ecf3.setCodModDocFiscal("2D");
		ecf3.setModeloEquip("ECF"); 
		ecf3.setNumSerieFabECF("BE091410100011250892");
		ecf3.setNumECF("6");
		ecfs.add(ecf3);
		for(int i = 0; i < ecfs.size();i++) {
			if(!new MainCrud().daoECF.getEquipamentosFiscais().contains(ecfs.get(i))){
				new MainCrud().daoECF.cadastrar(ecfs.get(i));
			}
		}
		
		
		
		
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
