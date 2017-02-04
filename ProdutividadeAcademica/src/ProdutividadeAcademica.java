import java.util.ArrayList;
import java.util.Scanner;

class Colaborador {
	
	private String nome;
	private String email;
	private ArrayList <Projeto> projetos = new ArrayList <Projeto> ();	//lista de projetos que o colaborador participa
	private ArrayList <Publicacao> publicacoes = new ArrayList <Publicacao> ();	//lista de publicacoes realizadas pelo colaborador
	
	protected String getNome() {
		
		return this.nome;
	}
	
	protected void setAtributos(String nome, String email) {
		
		this.nome = nome;
		this.email = email;
	}
	
	protected void setProjeto(Projeto projeto) {
		
		projetos.add(projeto);
	}
	
	protected void setPublicacao(Publicacao publicacao) {
		
		publicacoes.add(publicacao);
	}
}

class Graduando extends Colaborador {
	
	private int qtdProjetos;	//so pode participar de 2
	
	protected int getQtd() {
		
		return this.qtdProjetos;
	}
	
	protected void incProjeto() {
		
		this.qtdProjetos++;
	}
	
	protected void decProjeto() {
		
		this.qtdProjetos--;
	}
}

class Mestrando extends Colaborador {

}

class Doutorando extends Colaborador {
	
}

class Professor extends Colaborador {
	
	private ArrayList <Orientacao> orientador = new ArrayList <Orientacao> ();	//lista de orientacoes feitas pelo professor
	
	protected void setOrientacao(Orientacao orientacao) {
		
		orientador.add(orientacao);
	}
}

class Pesquisador extends Colaborador {
	
	private ArrayList <Orientacao> orientado = new ArrayList <Orientacao> ();	//lista de orientacoes recebidas pelo pesquisador
	
	protected void setOrientacao(Orientacao orientacao) {
		
		orientado.add(orientacao);
	}
}

class Projeto {
	
	private String titulo;
	private int anoInicio;
	private int anoTermino;
	private String agenciaFinanciadora;
	private double valorFinanciado;
	private String descricao;
	private String objetivo;
	private ArrayList <String> participantes = new ArrayList <String> ();	//lista dos participantes do projeto
	private ArrayList <Publicacao> producaoAcademica = new ArrayList <Publicacao> ();	//lista de publicacoes associadas ao projeto
	
	private int status;	//1-em elaboracao /2-em andamento /3-concluido
	private int temProfessor;	//0-nao /1-sim
	
	protected String getTitulo() {
		
		return this.titulo;
	}
	
	protected int getStatus() {
		
		return this.status;
	}
	
	protected void setStatus(int status) {
		
		this.status = status;
	}
	
	protected int getProfessor() {
		
		return this.temProfessor;
	}
	
	protected void setProfessor() {
		
		this.temProfessor = 1;
	}
	
	protected int getPublicacoes() {
		
		return this.producaoAcademica.size();
	}
	
	protected void setAtributos(String titulo, int anoInicio, int anoTermino, String agencia, double valor, String descricao, String objetivo) {
		
		this.titulo = titulo;
		this.anoInicio = anoInicio;
		this.anoTermino = anoTermino;
		this.agenciaFinanciadora = agencia;
		this.valorFinanciado = valor;
		this.descricao = descricao;
		this.objetivo = objetivo;
		setStatus(1);
	}
	
	protected void addParticipante(String colaborador) {
		
		if(participantes.contains(colaborador)) {
			System.out.println("\n>> O colaborador escolhido já está associado ao projeto! <<");
		} 
		
		else {
			participantes.add(colaborador);
			System.out.println("\n>> Colaborador associado com sucesso! <<\n");
		}
	}	
	
	protected void addPublicacao(Publicacao publicacao) {
		
		producaoAcademica.add(publicacao);
	}
}

class Producao {
	
	private String titulo;
	private int anoDePublicacao;
	
	protected void setAtributos(String titulo, int ano) {
		
		this.titulo = titulo;
		this.anoDePublicacao = ano;
	}
}

class Publicacao extends Producao {
	
	private ArrayList <String> autores = new ArrayList <String> ();	//lista dos autores da publicacao
	private String nomeDaConferencia;
	private String projetoAssociado;
	
	protected void setAtributos(String nomeConf, String projetoAss) {
		
		this.nomeDaConferencia = nomeConf;
		this.projetoAssociado = projetoAss;
	}
	
	protected void addAutor(String autor) {
		
		autores.add(autor);
	}
}

class Orientacao extends Producao {
	
	private String professorOrientador;
	private String pesquisador;
	
	protected void setAtributos(String orientador, String pesquisador) {
		
		this.professorOrientador = orientador;
		this.pesquisador = pesquisador;
	}
}

public class ProdutividadeAcademica {
	
	public static void addColaborador() {
		
		Scanner scanner = new Scanner(System.in);
		
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

		System.out.println("\n>> Colaborador cadastrado com sucesso! <<");
	}
	
	public static Projeto incluirParticipante(Projeto projeto) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("\nQuantos participantes deseja associar ao projeto? ");
		int qtd = scanner.nextInt();
		scanner.nextLine();	//escape
		
		for(int i = 0; i < qtd; i++) {	
			System.out.println("\nLista dos colaboradores cadastrados em nosso Sistema:");
			
			for(Colaborador c : listaColaboradores) {
				System.out.printf("\n%s", c.getNome());
			}
			
			System.out.print("\n\nNome de quem deseja adicionar: ");	
			String colaborador = scanner.nextLine();
			int flag = 0;
			
			for(Colaborador c : listaColaboradores) {	//procura o nome do colaborador na lista
				String participante = c.getNome();
				
				if(participante.equals(colaborador)) {	//compara os nomes pra pegar objeto correspondente
					
					flag = 1;	//sinaliza que nome foi encontrado
					if(c instanceof Graduando) {	//se for graduando, verifica a quantidade de projetos em que ja esta
						
						int qtdProjetos = ((Graduando) c).getQtd();
						
						if(qtdProjetos < 2) {
							((Graduando) c).incProjeto();	//incrementa quantidade
						}
						
						else {
							System.out.println(">> O colaborador escolhido é graduando, e já está participando de 2 projetos! <<");
							break;
						}
					}
					
					else if (c instanceof Professor) {	
						projeto.setProfessor();	//seta flag no projeto
					}
					
					c.setProjeto(projeto);	//associa projeto ao colaborador
					projeto.addParticipante(colaborador);	//adiciona o nome do colaborador na lista de participantes
					break;
				}
			}
			
			if(flag == 0) {
				System.out.println("\n>> Colaborador não encontrado! <<");
			}
		}
		
		return projeto;
	}
	
	public static void criarProjeto() {
		
		Scanner scanner = new Scanner(System.in);
		
		Projeto projeto = new Projeto();
		
		System.out.println("\nPreencha os dados do Projeto:\n");
		System.out.print("Título: ");
		String titulo = scanner.nextLine();
		System.out.print("Ano de Início: ");
		int anoInicio = scanner.nextInt();
		System.out.print("Ano de término: ");
		int anoTermino = scanner.nextInt();
		System.out.print("Agência financiadora: ");
		scanner.nextLine();	//escape
		String agencia = scanner.nextLine();
		System.out.print("Valor financiado: ");
		double valor = scanner.nextDouble();
		System.out.print("Descrição: ");
		scanner.nextLine();	//escape
		String descricao = scanner.nextLine();
		System.out.print("Objetivo: ");
		String objetivo = scanner.nextLine();
		
		projeto.setAtributos(titulo, anoInicio, anoTermino, agencia, valor, descricao, objetivo);
		
		projeto = incluirParticipante(projeto);
		listaProjetos.add(projeto);
		System.out.println(">> Projeto criado com sucesso! <<");
	}
	
	public static void editarProjeto() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nLista de Projetos em nosso Sistema:");
		
		for(Projeto p : listaProjetos) {
			System.out.printf("\n%s", p.getTitulo());
		}
		
		System.out.print("\n\nNome do Projeto que deseja editar: ");	
		String titulo = scanner.nextLine();
		
		int flag = 0;
		
		for(Projeto p : listaProjetos) {	//procura o nome do projeto na lista
			String nomeDoProjeto = p.getTitulo();
			
			if(nomeDoProjeto.equals(titulo)) {	//compara os titulos pra pegar objeto correspondente
				
				flag = 1;	//sinaliza que titulo foi encontrado
				
				System.out.println("\nO que deseja fazer?\n\n1-Alocar novo participante\n2-Alterar status do projeto\n");
				System.out.print("Opção desejada: ");
				int opcao = scanner.nextInt();
				
				if(opcao == 1) {
					if(p.getStatus() == 1)	{
						p = incluirParticipante(p);
					}
					
					else {
						System.out.println("\n>> Só é possível adicionar colaboradores em Projetos 'EM ELABORAÇÃO'! <<");
					}
				}
				
				else {
					if(p.getStatus() == 1) {
						if(p.getProfessor() == 1) {	
							p.setStatus(2);
							numProjetosEmElaboracao--;
							numProjetosEmAndamento++;
							System.out.println("\n>> O status do Projeto foi mudado de 'EM ELABORAÇÃO' para 'EM ANDAMENTO'. <<");
						}
						
						else {
							System.out.println("\n>> O Projeto precisa de um Professor antes de entrar 'EM ANDAMENTO'! <<");
						}
					}
					
					else if(p.getStatus() == 2) {
						if(p.getPublicacoes() != 0) {
							p.setStatus(3);
							numProjetosEmAndamento--;
							numProjetosConcluidos++;
							System.out.println("\n>> O status do Projeto foi mudado de 'EM ANDAMENTO' para 'CONCLUÍDO'. <<");
						}
						
						else {
							System.out.println("\n>> O Projeto precisa de Publicações antes de ser 'CONCLUÍDO'! <<");
						}
					}
					
					else if(p.getStatus() == 3) {
						System.out.println("\n>> Esse Projeto já foi 'CONCLUÍDO'! <<\n");
					}
				}

				break;
			}
		}
		
		if(flag == 0) {
			System.out.println("\n>> Projeto não encontrado! <<");
		}
	}
	
	static ArrayList <Colaborador> listaColaboradores = new ArrayList <Colaborador> ();
	static ArrayList <Projeto> listaProjetos = new ArrayList <Projeto> ();
	static ArrayList <Producao> listaProducoes = new ArrayList <Producao> ();
	
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
					addColaborador();
					break;
				case 2:
					criarProjeto();
					numProjetosEmElaboracao++;
					break;
				case 3:
					editarProjeto();
					break;
				case 4:
					//metodo de criacao de producao
					break;
				case 5:
					//metodo para consulta de dados
					break;
				case 6:
					//metodo para imprimir relatorio de producao
					break;
			}
		}
	}
}
