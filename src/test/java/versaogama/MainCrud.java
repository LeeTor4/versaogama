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
		
		Endereco end5 = new Endereco();
		               end5.setNmLogradouro("Rua Joao Carvalho");
		
		new MainCrud().endDao.salvar(end1);
		new MainCrud().endDao.salvar(end2);
		new MainCrud().endDao.salvar(end3);
		new MainCrud().endDao.salvar(end4);
		new MainCrud().endDao.salvar(end5);
	

		Endereco id_end1 = new MainCrud().endDao.getEndereco(1);
		Endereco id_end2 = new MainCrud().endDao.getEndereco(2);
		Endereco id_end3 = new MainCrud().endDao.getEndereco(3);
		Endereco id_end4 = new MainCrud().endDao.getEndereco(4);
		Endereco id_end5 = new MainCrud().endDao.getEndereco(5);

		
		
		
		Long id = (long) new MainCrud().empDao.getEmpresas().size();
		System.out.println("Id_Emp " + id);
		Estabelecimento matriz = 
		 new Estabelecimento(id,id_end1.getId(),"05329222000176","Sellene Comercio e Representações Ltda","Matriz",end1,emp);

		Estabelecimento mega =
		 new Estabelecimento(id,id_end2.getId(),"05329222000419","Sellene Comercio e Representações Ltda","Megadiet",end2,emp);


		Estabelecimento sao = 
		 new Estabelecimento(id,id_end3.getId(),"05329222000755","Sellene Comercio e Representações Ltda","Sao Mateus",end3,emp);
		
		Estabelecimento harm = 
				 new Estabelecimento(id,id_end4.getId(),"05329222000842","Sellene Comercio e Representações Ltda","Harmony",end4,emp);

		Estabelecimento loja3 = 
				 new Estabelecimento(id,id_end5.getId(),"05329222000338","Sellene Comercio e Representações Ltda","Delivery",end5,emp);
		
		emp.adicionaEstab(matriz);		
		emp.adicionaEstab(mega);
		emp.adicionaEstab(sao);
		emp.adicionaEstab(harm);
		emp.adicionaEstab(loja3);
		for(Estabelecimento est :  emp.getEstabs()){
			new MainCrud().estDao.salvar(est);
	    }
		
		
		List<EquipamentoECF> ecfsMegadiet = new ArrayList<EquipamentoECF>();
		EquipamentoECF ecf1 = new EquipamentoECF();
		ecf1.setId_pai_emp(1L);
		ecf1.setId_pai_est(2L);
		ecf1.setCodModDocFiscal("2D");
		ecf1.setModeloEquip("ECF"); 
		ecf1.setNumSerieFabECF("BE090910100010021930");
		ecf1.setNumECF("3");
		ecfsMegadiet.add(ecf1);
		EquipamentoECF ecf2 = new EquipamentoECF();
		ecf2.setId_pai_emp(1L);
		ecf2.setId_pai_est(2L);
		ecf2.setCodModDocFiscal("2D");
		ecf2.setModeloEquip("ECF"); 
		ecf2.setNumSerieFabECF("BE091210100011203011");
		ecf2.setNumECF("4");
		ecfsMegadiet.add(ecf2);
		EquipamentoECF ecf3 = new EquipamentoECF();
		ecf3.setId_pai_emp(1L);
		ecf3.setId_pai_est(2L);
		ecf3.setCodModDocFiscal("2D");
		ecf3.setModeloEquip("ECF"); 
		ecf3.setNumSerieFabECF("BE091410100011250892");
		ecf3.setNumECF("6");
		ecfsMegadiet.add(ecf3);
		
		
		List<EquipamentoECF> ecfsLoja03 = new ArrayList<EquipamentoECF>();
		EquipamentoECF ecf1_loja03 = new EquipamentoECF();
		ecf1_loja03.setId_pai_emp(1L);
		ecf1_loja03.setId_pai_est(5L);
		ecf1_loja03.setCodModDocFiscal("2D");
		ecf1_loja03.setModeloEquip("ECF"); 
		ecf1_loja03.setNumSerieFabECF("1234");
		ecf1_loja03.setNumECF("1");
		ecfsLoja03.add(ecf1_loja03);
		
		EquipamentoECF ecf2_loja03 = new EquipamentoECF();
		ecf2_loja03.setId_pai_emp(1L);
		ecf2_loja03.setId_pai_est(5L);
		ecf2_loja03.setCodModDocFiscal("2D");
		ecf2_loja03.setModeloEquip("ECF"); 
		ecf2_loja03.setNumSerieFabECF("4321");
		ecf2_loja03.setNumECF("2");
		ecfsLoja03.add(ecf2_loja03);
		
		
		EquipamentoECF ecf3_loja03 = new EquipamentoECF();
		ecf3_loja03.setId_pai_emp(1L);
		ecf3_loja03.setId_pai_est(5L);
		ecf3_loja03.setCodModDocFiscal("2D");
		ecf3_loja03.setModeloEquip("ECF"); 
		ecf3_loja03.setNumSerieFabECF("BE0107SC56000035178");
		ecf3_loja03.setNumECF("3");
		ecfsLoja03.add(ecf3_loja03);
		
		EquipamentoECF ecf4_loja03 = new EquipamentoECF();
		ecf4_loja03.setId_pai_emp(1L);
		ecf4_loja03.setId_pai_est(5L);
		ecf4_loja03.setCodModDocFiscal("2D");
		ecf4_loja03.setModeloEquip("ECF"); 
		ecf4_loja03.setNumSerieFabECF("BE0107SC56000035180");
		ecf4_loja03.setNumECF("4");
		ecfsLoja03.add(ecf4_loja03);
		
		EquipamentoECF ecf5_loja03 = new EquipamentoECF();
		ecf5_loja03.setId_pai_emp(1L);
		ecf5_loja03.setId_pai_est(5L);
		ecf5_loja03.setCodModDocFiscal("2D");
		ecf5_loja03.setModeloEquip("ECF"); 
		ecf5_loja03.setNumSerieFabECF("BE051175610000105333");
		ecf5_loja03.setNumECF("5");
		ecfsLoja03.add(ecf5_loja03);
		
		EquipamentoECF ecf6_loja03 = new EquipamentoECF();
		ecf6_loja03.setId_pai_emp(1L);
		ecf6_loja03.setId_pai_est(5L);
		ecf6_loja03.setCodModDocFiscal("2D");
		ecf6_loja03.setModeloEquip("ECF"); 
		ecf6_loja03.setNumSerieFabECF("BE051175610000105336");
		ecf6_loja03.setNumECF("6");
		ecfsLoja03.add(ecf6_loja03);
		
		
		for(int i = 0; i < ecfsMegadiet.size();i++) {
			if(!new MainCrud().daoECF.getEquipamentosFiscais().contains(ecfsMegadiet.get(i))){
				new MainCrud().daoECF.cadastrar(ecfsMegadiet.get(i));
			}
		}
		
		for(int i = 0; i < ecfsLoja03.size();i++) {
			if(!new MainCrud().daoECF.getEquipamentosFiscais().contains(ecfsLoja03.get(i))){
				new MainCrud().daoECF.cadastrar(ecfsLoja03.get(i));
			}
		}
		
		EquipamentoECF ecfHarmony = new EquipamentoECF();
		ecfHarmony.setId_pai_emp(1L);
		ecfHarmony.setId_pai_est(4L);
		ecfHarmony.setCodModDocFiscal("2D");
		ecfHarmony.setModeloEquip("ECF"); 
		ecfHarmony.setNumSerieFabECF("BE091410100011241014");
		ecfHarmony.setNumECF("1");
		new MainCrud().daoECF.cadastrar(ecfHarmony);
		
		
		Empresa empresa = new MainCrud().empDao.getEmpresa(1);
		
		Endereco end6 = new Endereco();		
		end6.setNmLogradouro("Rua Rui Barbosa");
		new MainCrud().endDao.salvar(end6);
		Endereco id_end6 = new MainCrud().endDao.getEndereco(6);
		
		Estabelecimento loja6 = 
				 new Estabelecimento(empresa.getId(),id_end6.getId(),"05329222000680","Sellene Comercio e Representações Ltda","Megafarma",end6,empresa);
		
		empresa.adicionaEstab(loja6);
		for(Estabelecimento est :  empresa.getEstabs()){
			new MainCrud().estDao.salvar(est);
	    }
		
		EquipamentoECF ecf01_Megafarma = new EquipamentoECF();
		ecf01_Megafarma.setId_pai_emp(1L);
		ecf01_Megafarma.setId_pai_est(6L);
		ecf01_Megafarma.setCodModDocFiscal("2D");
		ecf01_Megafarma.setModeloEquip("ECF"); 
		ecf01_Megafarma.setNumSerieFabECF("BE091010100011201429");
		ecf01_Megafarma.setNumECF("1");
		new MainCrud().daoECF.cadastrar(ecf01_Megafarma);
		
		EquipamentoECF ecf02_Megafarma = new EquipamentoECF();
		ecf02_Megafarma.setId_pai_emp(1L);
		ecf02_Megafarma.setId_pai_est(6L);
		ecf02_Megafarma.setCodModDocFiscal("2D");
		ecf02_Megafarma.setModeloEquip("ECF"); 
		ecf02_Megafarma.setNumSerieFabECF("DR0814BR000000407938");
		ecf02_Megafarma.setNumECF("2");
		new MainCrud().daoECF.cadastrar(ecf02_Megafarma);
		
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
