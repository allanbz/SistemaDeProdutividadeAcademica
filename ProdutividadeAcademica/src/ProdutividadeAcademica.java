import java.util.ArrayList;
import java.util.Scanner;

class Colaborador {
	
	private String nome;
	private String email;
	private ArrayList <Projeto> projetos = new ArrayList <Projeto> ();	//lista de projetos que o colaborador participa
	private ArrayList <Publicacao> publicacoes = new ArrayList <Publicacao> ();	//lista de publicacoes realizadas pelo colaborador
	
	protected void setAtributos(String nome, String email) {
		
		this.nome = nome;
		this.email = email;
	}
	
	protected String getNome() {
		return this.nome;
	}
}

class Graduando extends Colaborador {
	
	private int qtdProjetos;	//so pode participar de 2
}

class Mestrando extends Colaborador {

}

class Doutorando extends Colaborador {
	
}

class Professor extends Colaborador {
	
	private ArrayList <Orientacao> orientador = new ArrayList <Orientacao> ();	//lista de orientacoes feitas pelo professor
}

class Pesquisador extends Colaborador {
	
	private ArrayList <Orientacao> orientado = new ArrayList <Orientacao> ();	//lista de orientacoes recebidas pelo pesquisador
}

class Projeto {
	
	private String titulo;
	private int anoDeInicio;
	private int anoDeTermino;
	private String agenciaFinanciadora;
	private int valorFinanciado;
	private String objetivo;
	private String descricao;
	private ArrayList <String> participantes = new ArrayList <String> ();	//lista dos participantes do projeto
	private ArrayList <Publicacao> producaoAcademica = new ArrayList <Publicacao> ();	//lista de publicacoes associadas ao projeto
	private int status;	//1-em elaboracao /2-em andamento /3-concluido
}

class Producao {
	
	private String titulo;
	private int anoDePublicacao;
}

class Publicacao extends Producao {
	
	private ArrayList <String> autores = new ArrayList <String> ();	//lista dos autores da publicacao
	private String nomeDaConferencia;
	private String projetoAssociado;
}

class Orientacao extends Producao {
	
	private String professorOrientador;
	private String pesquisador;
}

public class ProdutividadeAcademica {
	
	static ArrayList <Colaborador> listaColaboradores = new ArrayList <Colaborador> ();
	static ArrayList <Projeto> listaProjetos = new ArrayList <Projeto> ();
	static ArrayList <Producao> listaProducoes = new ArrayList <Producao> ();
	
	static int numColaboradores = 0;
	static int numProjetosEmElaboracao = 0;
	static int numProjetosEmAndamento = 0;
	static int numProjetosConcluidos = 0;
	static int numPublicacoes = 0;
	static int numOrientacoes = 0;
	
	public static void main(String[] args) {
		
		System.out.print("************************************************\n");
		System.out.println("Bem-vindo ao Sistema de Produtividade Acadêmica!");
		System.out.print("************************************************\n");
		
		Scanner scanner = new Scanner(System.in);
		int comando = 1;
		
		while(comando != 0) {
		
			System.out.println("\nSelecione a opção desejada:\n");
			System.out.println("1-Cadastrar colaborador\n2-Criar projeto\n3-Editar projeto\n4-Criar produção");
			System.out.println("5-Consulta de dados\n6-Relatório de Produção Acadêmica\n\n0-Encerrar\n");
			System.out.print("Opção desejada: ");
			
			comando = scanner.nextInt();
			
			switch(comando) {
				case 1:
					System.out.println("\nQue tipo de colaborador gostaria de adicionar?\n");
					System.out.println("1-Aluno\n2-Professor\n3-Pesquisador\n");
					System.out.print("Opção desejada: ");
					int opcao = scanner.nextInt();
					scanner.nextLine(); //char de escape
					
					System.out.print("\nNome: ");
					String nome = scanner.nextLine();
					System.out.print("Email: ");
					String email = scanner.nextLine();
					
					switch(opcao) {
						case 1:
							System.out.println("\nO aluno pertence à qual modalidade de ensino?\n");
							System.out.println("1-Graduação\n2-Mestrado\n3-Doutorado\n");
							System.out.print("Opção desejada: ");
							int aluno = scanner.nextInt();		
							
							switch(aluno) {
								case 1:
									Graduando graduando = new Graduando();
									graduando.setAtributos(nome, email);
									listaColaboradores.add(graduando);
									break;
								case 2:
									Mestrando mestrando = new Mestrando();
									mestrando.setAtributos(nome, email);
									listaColaboradores.add(mestrando);
									break;
								case 3:
									Doutorando doutorando = new Doutorando();
									doutorando.setAtributos(nome, email);
									listaColaboradores.add(doutorando);
									break;
							}
							
							break;
						case 2:
							Professor professor = new Professor();
							professor.setAtributos(nome, email);
							listaColaboradores.add(professor);
							break;
						case 3:
							Pesquisador pesquisador = new Pesquisador();
							pesquisador.setAtributos(nome, email);
							listaColaboradores.add(pesquisador);
							break;
					}
	
					System.out.println("\nColaborador cadastrado com sucesso!");
					numColaboradores++;
					break;
				
				case 2:
					//proximo comando
					break;
			}
		}		
	}
}
