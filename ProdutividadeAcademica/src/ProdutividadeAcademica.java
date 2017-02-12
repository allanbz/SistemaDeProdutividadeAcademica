import java.util.ArrayList;
import java.util.Scanner;

class Colaborador {
	
	private String nome;
	private String email;
	private ArrayList <Projeto> projetos = new ArrayList <Projeto> ();	//projetos dos quais o colaborador participa
	private ArrayList <Publicacao> publicacoes = new ArrayList <Publicacao> ();	//lista de publicacoes realizadas pelo colaborador
	
	protected void printDados() {
		System.out.printf("\nNome: %s\nEmail: %s\n", this.nome, this.email);
		
		if(projetos.size() != 0) {
			System.out.println("\nParticipou dos Projetos:\n");
			
			//copia objetos para um novo array, que sera ordenado
			Projeto[] projetos = new Projeto[this.projetos.size()];
			
			for(int i = 0; i < this.projetos.size(); i++) {
				projetos[i] = this.projetos.get(i);
			}
			
			Projeto temp = new Projeto();
			
			for(int i = 0; i < this.projetos.size()-1; i++) {
				if(projetos[i].getAnoDeTermino() < projetos[i+1].getAnoDeTermino()) {
					temp = projetos[i];
					projetos[i] = projetos[i+1];
					projetos[i+1] = temp;
				}
			}
			
			for(int i = 0; i < this.projetos.size(); i++) {
				System.out.printf("Título: %s, Ano de término: %d\n", projetos[i].getTitulo(), projetos[i].getAnoDeTermino());
			}
		}

		if(publicacoes.size() != 0) {
			System.out.println("\nRealizou as Publicações:\n");
			
			Producao[] publicacoes = new Publicacao[this.publicacoes.size()];
			
			for(int i = 0; i < this.publicacoes.size(); i++) {
				publicacoes[i] = this.publicacoes.get(i);
			}
			
			Producao temp = new Producao();
			
			for(int i = 0; i < this.publicacoes.size()-1; i++) {
				if(publicacoes[i].getAno() < publicacoes[i+1].getAno()) {
					temp = publicacoes[i];
					publicacoes[i] = publicacoes[i+1];
					publicacoes[i+1] = temp;
				}
			}
			
			for(int i = 0; i < this.publicacoes.size(); i++) {
				System.out.printf("Título: %s, Ano: %d\n", publicacoes[i].getTitulo(), publicacoes[i].getAno());
			}
		}
		
		System.out.println("");
	}
	
	protected String getNome() {
		return this.nome;
	}
	
	protected void setAtributos(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	protected void setProjeto(Projeto projeto) {
		projetos.add(projeto);	//atribui objeto de projeto ao colaborador
	}
	
	protected void setPublicacao(Publicacao publicacao) {
		publicacoes.add(publicacao);	//atribui objeto de publicacao ao colaborador
	}
}

class Graduando extends Colaborador {
	
	private int qtdProjetos;	//um graduando so pode participar de 2 projetos
	
	protected void decProjeto() {
		this.qtdProjetos--;
	}
	
	protected void incProjeto() {
		this.qtdProjetos++;
	}
	
	protected int getQtd() {
		return this.qtdProjetos;
	}	
}

class Mestrando extends Colaborador {
}

class Doutorando extends Colaborador {
}

class Professor extends Colaborador {
	
	private ArrayList <Orientacao> orientador = new ArrayList <Orientacao> ();	//lista de orientacoes feitas pelo professor
	
	protected void printOrientacao() {
		Producao[] orientacoes = new Orientacao[this.orientador.size()];
		
		for(int i = 0; i < this.orientador.size(); i++) {
			orientacoes[i] = this.orientador.get(i);
		}
		
		Producao temp = new Producao();
		
		for(int i = 0; i < this.orientador.size()-1; i++) {
			if(orientacoes[i].getAno() < orientacoes[i+1].getAno()) {
				temp = orientacoes[i];
				orientacoes[i] = orientacoes[i+1];
				orientacoes[i+1] = temp;
			}
		}
		
		if(this.orientador.size() != 0) {
			System.out.println("Foi um Orientador em:\n");
			
			for(int i = 0; i < this.orientador.size(); i++) {
				System.out.printf("''%s'', no ano de %d\n", orientacoes[i].getTitulo(), orientacoes[i].getAno());
			}
		}	
	}
	
	protected void setOrientacao(Orientacao orientacao) {
		orientador.add(orientacao);	//atribui orientacao ao colaborador
	}
}

class Pesquisador extends Colaborador {
	
	private ArrayList <Orientacao> orientado = new ArrayList <Orientacao> ();	//lista de orientacoes recebidas pelo pesquisador
	
	protected void printOrientacao() {
		Producao[] orientado = new Orientacao[this.orientado.size()];
		
		for(int i = 0; i < this.orientado.size(); i++) {
			orientado[i] = this.orientado.get(i);
		}
		
		Producao temp = new Producao();
		
		for(int i = 0; i < this.orientado.size()-1; i++) {
			if(orientado[i].getAno() < orientado[i+1].getAno()) {
				temp = orientado[i];
				orientado[i] = orientado[i+1];
				orientado[i+1] = temp;
			}
		}
		
		System.out.println("Foi orientado em:\n");
		
		for(int i = 0; i < this.orientado.size(); i++) {
			System.out.printf("''%s'', no ano de %d\n", orientado[i].getTitulo(), orientado[i].getAno());
		}
	}
	
	protected void setOrientacao(Orientacao orientacao) {
		orientado.add(orientacao);	//atribui orientacao ao colaborador
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
	private ArrayList <String> participantes = new ArrayList <String> ();	//lista de participantes do projeto
	private ArrayList <Publicacao> producaoAcademica = new ArrayList <Publicacao> ();	//lista de publicacoes associadas ao projeto
	
	private int status;	//1-em elaboracao /2-em andamento /3-concluido
	private int temProfessor;	//0-nao /1-sim
	
	protected void printProjeto() {
		System.out.printf("\nTítulo: %s\nAno de início: %d\nAno de término: %d", this.titulo, this.anoInicio, this.anoTermino);
		System.out.printf("\nAgência Financiadora: %s\nValor financiado %.2f\nDescrição: %s", this.agenciaFinanciadora, this.valorFinanciado, this.descricao);
		System.out.printf("\nObjetivo: %s", this.objetivo);
		
		switch(this.status) {
			case 1:
				System.out.println("\nStatus: EM ELABORAÇÃO");
				break;
			case 2:
				System.out.println("\nStatus: EM ANDAMENTO");
				break;
			case 3:
				System.out.println("\nStatus: CONCLUÍDO");
				break;
		}
		
		if(participantes.size() != 0) {
			System.out.println("\nLista de participantes do Projeto:\n");
			
			for(String s : participantes) {
				System.out.printf("%s\n", s);
			}
		}		
		
		if(producaoAcademica.size() != 0) {
			System.out.println("\nLista de publicações associadas ao Projeto:\n");
			
			Producao[] producaoAcad = new Publicacao[this.producaoAcademica.size()];
			
			for(int i = 0; i < this.producaoAcademica.size(); i++) {
				producaoAcad[i] = this.producaoAcademica.get(i);
			}
			
			Producao temp = new Producao();
			
			for(int i = 0; i < this.producaoAcademica.size()-1; i++) {
				if(producaoAcad[i].getAno() < producaoAcad[i+1].getAno()) {
					temp = producaoAcad[i];
					producaoAcad[i] = producaoAcad[i+1];
					producaoAcad[i+1] = temp;
				}
			}

			for(int i = 0; i < this.producaoAcademica.size(); i++) {
				System.out.printf("Título: %s, ano: %d\n", producaoAcad[i].getTitulo(), producaoAcad[i].getAno());
			}
		}	
	}
	
	protected void addParticipante(String colaborador) {
		participantes.add(colaborador);
		System.out.println("\n>> Colaborador associado com sucesso! <<");
	}
	
	protected int verificarParticipacao(String colaborador) {
		if(participantes.contains(colaborador)) {
			System.out.println("\n>> O colaborador escolhido já está associado ao projeto! <<");
			return 0;
		} 
		
		return 1;
	}
	
	protected void addPublicacao(Publicacao publicacao) {
		producaoAcademica.add(publicacao);	//atribui producao academica ao projeto
	}
	
	protected void setAtributos(String titulo, int anoInicio, int anoTermino, String agencia, double valor, String descricao, String objetivo) {
		this.titulo = titulo;
		this.anoInicio = anoInicio;
		this.anoTermino = anoTermino;
		this.agenciaFinanciadora = agencia;
		this.valorFinanciado = valor;
		this.descricao = descricao;
		this.objetivo = objetivo;
		setStatus(1);	//muda status para 'em elaboracao'
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
	
	protected int getStatus() {
		return this.status;
	}
	
	protected void setStatus(int status) {
		this.status = status;
	}
	
	protected int getAnoDeTermino() {
		return this.anoTermino;
	}
	
	protected String getTitulo() {
		return this.titulo;
	}
}

class Producao {
	
	private String titulo;
	private int anoDePublicacao;
	
	protected int getAno() {
		return this.anoDePublicacao;
	}
	
	protected String getTitulo() {
		return this.titulo;
	}
	
	protected void setAtributos(String titulo, int ano) {
		this.titulo = titulo;
		this.anoDePublicacao = ano;
	}
}

class Publicacao extends Producao {
	
	private ArrayList <String> autores = new ArrayList <String> ();	//lista dos autores da publicacao
	private String nomeDaConferencia;
	private String projetoAssociado;
	
	protected void addAutor(String autor) {
		autores.add(autor);
	}
	
	protected void setConferencia(String nomeConf) {
		this.nomeDaConferencia = nomeConf;
	}
	
	protected void setProjeto(String projetoAss) {
		this.projetoAssociado = projetoAss;
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
		Boolean verificador = false;
		
		while(!verificador) {
			try {
				System.out.println("\nQue tipo de colaborador gostaria de adicionar?\n");
				System.out.println("1-Aluno\n2-Professor\n3-Pesquisador\n");
				System.out.print("Opção desejada: ");
				int opcao = scanner.nextInt();
				scanner.nextLine(); //char de escape
				
				if(opcao != 1 && opcao !=2 && opcao != 3) {
					System.out.println("\n>> Número inválido! Operação cancelada! <<");
					return;
				}
				
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
							default:
								System.out.println("\n>> Número inválido! Operação cancelada! <<");
								return;
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
				verificador = true;
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("\n>> Entrada inválida! Tente novamente. <<");
				scanner.nextLine();	//char de escape
			}
		}
	}
		
	public static void criarProjeto() {
		Scanner scanner = new Scanner(System.in);
		Boolean verificador = false;
		
		while(!verificador) {
			try {
				Projeto projeto = new Projeto();
				
				System.out.println("\nPreencha os dados do Projeto:\n");
				System.out.print("Título: ");
				String titulo = scanner.nextLine();
				System.out.print("Ano de Início: ");
				int anoInicio = scanner.nextInt();
				System.out.print("Ano de término: ");
				int anoTermino = scanner.nextInt();
				System.out.print("Agência financiadora: ");
				scanner.nextLine();	//char de escape
				String agencia = scanner.nextLine();
				System.out.print("Valor financiado: ");
				double valor = scanner.nextDouble();
				System.out.print("Descrição: ");
				scanner.nextLine();	//char de escape
				String descricao = scanner.nextLine();
				System.out.print("Objetivo: ");
				String objetivo = scanner.nextLine();
				
				projeto.setAtributos(titulo, anoInicio, anoTermino, agencia, valor, descricao, objetivo);
				
				projeto = incluirParticipante(projeto);
				listaProjetos.add(projeto);
				System.out.println("\n>> Projeto criado com sucesso! <<");
				verificador = true;
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("\n>> Entrada inválida! Tente novamente. <<");
				scanner.nextLine();	//char de escape
			}
		}		
	}
	
	public static Projeto incluirParticipante(Projeto projeto) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("\nQuantos participantes deseja associar ao projeto? ");
		int qtd = scanner.nextInt();
		scanner.nextLine();	//char de escape
		
		for(int i = 0; i < qtd; i++) {	
			System.out.println("\nLista dos colaboradores cadastrados em nosso Sistema:");
			
			for(Colaborador c : listaColaboradores) {
				System.out.printf("\n%s", c.getNome());
			}
			
			System.out.print("\n\nNome de quem deseja adicionar: ");	
			String colaborador = scanner.nextLine();
			int flag = 0;
			
			for(Colaborador c : listaColaboradores) {
				String participante = c.getNome();
				
				if(participante.equals(colaborador)) {
					flag = 1;	//sinaliza que colaborador foi encontrado
					
					if(projeto.verificarParticipacao(colaborador) == 1) {	
						if(c instanceof Graduando) {
							int qtdProjetos = ((Graduando) c).getQtd();
							
							if(qtdProjetos < 2) {
								((Graduando) c).incProjeto();
							} else {
								System.out.println("\n>> O colaborador escolhido é graduando, e já está participando de 2 projetos! <<");
								break;
							}
						} else if (c instanceof Professor) {	
							projeto.setProfessor();
						}
						
						projeto.addParticipante(colaborador);
						c.setProjeto(projeto);
						break;
					}
				}
			}
			
			if(flag == 0) {
				System.out.println("\n>> Colaborador não encontrado! <<");
			}
		}
		
		return projeto;
	}
	
	public static void editarProjeto() {
		Scanner scanner = new Scanner(System.in);
		Boolean verificador = false;
		
		while(!verificador) {
			try {
				System.out.println("\nLista de Projetos em nosso Sistema:");
				
				for(Projeto p : listaProjetos) {
					System.out.printf("\n%s", p.getTitulo());
				}
				
				System.out.print("\n\nNome do Projeto que deseja editar: ");	
				String titulo = scanner.nextLine();
				
				int flag = 0;
				
				for(Projeto p : listaProjetos) {
					String nomeDoProjeto = p.getTitulo();
					
					if(nomeDoProjeto.equals(titulo)) {
						flag = 1;	//sinaliza que projeto foi encontrado
						
						System.out.println("\nO que deseja fazer?\n\n1-Alocar novo participante\n2-Alterar status do projeto\n");
						System.out.print("Opção desejada: ");
						int opcao = scanner.nextInt();
						
						switch(opcao) {
							case 1:
								if(p.getStatus() == 1)	{
									p = incluirParticipante(p);
								} else {
									System.out.println("\n>> Só é possível adicionar colaboradores em Projetos 'EM ELABORAÇÃO'! <<");
								}
								break;
							case 2:
								if(p.getStatus() == 1) {
									if(p.getProfessor() == 1) {	
										p.setStatus(2);
										numProjetosEmElaboracao--;
										numProjetosEmAndamento++;
										System.out.println("\n>> O status do Projeto foi mudado de 'EM ELABORAÇÃO' para 'EM ANDAMENTO'. <<");
									} else {
										System.out.println("\n>> O Projeto precisa de um Professor antes de entrar 'EM ANDAMENTO'! <<");
									}
								} else if(p.getStatus() == 2) {
									if(p.getPublicacoes() != 0) {
										p.setStatus(3);
										numProjetosEmAndamento--;
										numProjetosConcluidos++;
										System.out.println("\n>> O status do Projeto foi mudado de 'EM ANDAMENTO' para 'CONCLUÍDO'. <<");
									} else {
										System.out.println("\n>> O Projeto precisa de Publicações antes de ser 'CONCLUÍDO'! <<");
									}
								} else if(p.getStatus() == 3) {
									System.out.println("\n>> Esse Projeto já foi 'CONCLUÍDO'! <<\n");
								}
								break;
							default:
								System.out.println("\n>> Número inválido! Operação cancelada. <<");
								return;
						}
						break;
					}
				}
				
				if(flag == 0) {
					System.out.println("\n>> Projeto não encontrado! <<");
				}
				verificador = true;
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("\n>> Entrada inválida! Tente novamente. <<");
				scanner.nextLine();	//char de escape
			}
		}
	}
	
	public static void criarProducao() {
		Scanner scanner = new Scanner(System.in);
		Boolean verificador = false;
		
		while(!verificador) {
			try {
				System.out.println("\nQuem tipo de Produção deseja registrar?\n\n1-Publicação\n2-Orientação\n");
				System.out.print("Opção desejada: ");
				int opcao = scanner.nextInt();
				scanner.nextLine();	//char de escape
				
				switch(opcao) {
					case 1:
						Publicacao publicacao = new Publicacao();
						
						System.out.println("\nPreencha os dados da Publicação:\n");
						System.out.print("Título: ");
						String titulo = scanner.nextLine();
						System.out.print("Ano de publicação: ");
						int anoPublicacao = scanner.nextInt();
						scanner.nextLine();	//char de escape
						System.out.print("Nome da Conferência: ");
						String nomeConferencia = scanner.nextLine();
						
						publicacao.setAtributos(titulo, anoPublicacao);
						publicacao.setConferencia(nomeConferencia);
						publicacao = incluirAutor(publicacao);
						
						System.out.print("\nEssa Publicação está associada a algum Projeto (1-Sim/2-Não)? ");
						int resposta = scanner.nextInt();
						
						if(resposta == 1) {
							publicacao = associarProjeto(publicacao);
						} else if(resposta != 2) {
							System.out.println("\n>> Número inválido! Resposta padrão: NÃO. <<");
						}
						
						System.out.println("\n>> Publicação registrada com sucesso! <<");
						numPublicacoes++;
						listaProducoes.add(publicacao);
						break;
					case 2:
						Orientacao orientacao = new Orientacao();
						
						System.out.println("\nPreencha os dados da Orientação:\n");
						System.out.print("Título: ");
						String titulo2 = scanner.nextLine();
						System.out.print("Ano de orientação: ");
						int anoOrientacao = scanner.nextInt();
						scanner.nextLine();	//char de escape
						System.out.print("Nome do orientador: ");
						String nomeOrientador = scanner.nextLine();
						
						orientacao.setAtributos(titulo2, anoOrientacao);
						
						int flag = 0;
						
						for(Colaborador c : listaColaboradores) {
							String colaborador = c.getNome();
							
							if(colaborador.equals(nomeOrientador)) {
								flag = 1;	//sinaliza que colaborador foi encontrado
								
								if(c instanceof Professor) {
									((Professor) c).setOrientacao(orientacao);
									break;
								} else {
									System.out.println("\n>> O colaborador precisa ser um Professor para ser orientador! <<");
									return;
								}
							}
						}
						
						if(flag == 0) {
							System.out.println("\n>> Professor não encontrado! <<");
							return;
						}
						
						System.out.print("Nome do pesquisador: ");
						String nomePesquisador = scanner.nextLine();
						
						int flag2 = 0;
						
						for(Colaborador c : listaColaboradores) {
							String colaborador = c.getNome();
							
							if(colaborador.equals(nomePesquisador)) {
								flag2 = 1;	//sinaliza que colaborador foi encontrado
								
								if(c instanceof Pesquisador) {
									((Pesquisador) c).setOrientacao(orientacao);
									break;
								} else {
									System.out.println("\n>> O colaborador precisa ser um Pesquisador para ser orientado! <<");
									return;
								}
							}
						}
						
						if(flag2 == 0) {
							System.out.println("\n>> Pesquisador não encontrado! <<");
							return;
						}
						
						System.out.println("\n>> Orientação registrada com sucesso! <<");
						orientacao.setAtributos(nomeOrientador, nomePesquisador);
						numOrientacoes++;
						listaProducoes.add(orientacao);
						break;
					default:
						System.out.println("\n>> Número inválido! Operação cancelada! <<");
						return;
				}
				verificador = true;
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("\n>> Entrada inválida! Tente novamente. <<");
				scanner.nextLine();	//char de escape
			}
		}
	}
	
	public static Publicacao incluirAutor(Publicacao publicacao) {
		Scanner scanner = new Scanner(System.in);
		Boolean verificador = false;
		
		while(!verificador) {
			try {
				System.out.print("\nQuantos autores possui a Publicação? ");
				int qtd = scanner.nextInt();
				scanner.nextLine();	//char de escape
				
				for(int i = 0; i < qtd; i++) {	
					System.out.println("\nLista dos colaboradores cadastrados em nosso Sistema:");
					
					for(Colaborador c : listaColaboradores) {
						System.out.printf("\n%s", c.getNome());
					}
					
					System.out.print("\n\nQual o nome do autor? ");	
					String autor = scanner.nextLine();
					int flag = 0;
					
					for(Colaborador c : listaColaboradores) {
						String colaborador = c.getNome();
						
						if(colaborador.equals(autor)) {
							flag = 1;	//sinaliza que colaborador foi encontrado
							c.setPublicacao(publicacao);
							publicacao.addAutor(colaborador);
							System.out.println("\n>> Autor registrado com sucesso! <<");
							break;
						}
					}
					
					if(flag == 0) {
						System.out.println("\n>> Autor não encontrado! <<");
					}
				}
				verificador = true;
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("\n>> Entrada inválida! Tente novamente. <<");
				scanner.nextLine();	//char de escape
			}
		}
		
		return publicacao;
	}
	
	public static Publicacao associarProjeto(Publicacao publicacao) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nLista dos Projetos cadastrados em nosso Sistema:");
		
		for(Projeto p : listaProjetos) {
			System.out.printf("\n%s", p.getTitulo());
		}
		
		System.out.print("\n\nQual o título do Projeto? ");	
		String tituloDeEntrada = scanner.nextLine();
		int flag = 0;
		
		for(Projeto p : listaProjetos) {
			String titulo = p.getTitulo();
			
			if(titulo.equals(tituloDeEntrada)) {
				flag = 1;	//sinaliza que projeto foi encontrado
				if(p.getStatus() == 2) {
					p.addPublicacao(publicacao);
					publicacao.setProjeto(titulo);
					System.out.println("\n>> Projeto associado com sucesso! <<");
					break;
				} else {
					System.out.println("\n>> O Projeto precisa estar 'EM ANDAMENTO' para ser associado a uma Publicação! <<");
				}
			}
		}
		
		if(flag == 0) {
			System.out.println("\n>> Projeto não encontrado! <<");
		}
		
		return publicacao;
	}
	
	public static void consultarDados() {
		Scanner scanner = new Scanner(System.in);
		Boolean verificador = false;
		
		while(!verificador) {
			try {
				System.out.println("\nQual a modalidade de consulta que deseja realizar?\n\n1-Por colaborador\n2-Por projeto\n");
				System.out.print("Opção desejada: ");
				int opcao = scanner.nextInt();
				scanner.nextLine();	//char de escape
				
				switch(opcao) {
					case 1:
						System.out.println("\nLista dos colaboradores cadastrados em nosso Sistema:");
						
						for(Colaborador c : listaColaboradores) {
							System.out.printf("\n%s", c.getNome());
						}
						
						System.out.print("\n\nNome de quem deseja consultar: ");	
						String colaborador = scanner.nextLine();
						int flag = 0;
						
						for(Colaborador c : listaColaboradores) {
							String participante = c.getNome();
							
							if(participante.equals(colaborador)) {
								flag = 1;	//sinaliza que colaborador foi encontrado
								
								c.printDados();
								
								if(c instanceof Professor) {
									((Professor) c).printOrientacao();
								} else if (c instanceof Pesquisador) {	
									((Pesquisador) c).printOrientacao();
								}
								break;
							}
						}
						
						if(flag == 0) {
							System.out.println("\n>> Colaborador não encontrado! <<");
						}
						break;
					case 2:
						System.out.println("\nLista de Projetos em nosso Sistema:");
						
						for(Projeto p : listaProjetos) {
							System.out.printf("\n%s", p.getTitulo());
						}
						
						System.out.print("\n\nNome do Projeto que deseja consultar: ");	
						String titulo = scanner.nextLine();
						
						int flag2 = 0;
						
						for(Projeto p : listaProjetos) {
							String nomeDoProjeto = p.getTitulo();
							
							if(nomeDoProjeto.equals(titulo)) {
								flag2 = 1;	//sinaliza que titulo foi encontrado
								p.printProjeto();
								break;
							}
						}
						
						if(flag2 == 0) {
							System.out.println("\n>> Projeto não encontrado! <<");
						}
						break;
					default:
						System.out.println("\n>> Número inválido! Operação cancelada. <<");
						return;
				}
				verificador = true;
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("\n>> Entrada inválida! Tente novamente. <<");
				scanner.nextLine();	//char de escape
			}
		}
	}
	
	public static void imprimirRelatorio() {
		System.out.printf("\nRELATÓRIO DE PRODUÇÃO\n\nNúmero de colaboradores cadastrados: %d\n", listaColaboradores.size());
		System.out.printf("Número de projetos em elaboração: %d\nNúmero de projetos em andamento: %d\n", numProjetosEmElaboracao, numProjetosEmAndamento);
		System.out.printf("Número de projetos concluídos: %d\n", numProjetosConcluidos);
		System.out.printf("Número total de projetos: %d\n",(numProjetosEmElaboracao + numProjetosEmAndamento + numProjetosConcluidos));	
		System.out.printf("Número de publicações: %d\nNúmero de orientações: %d\n", numPublicacoes, numOrientacoes);
	}
	
	static ArrayList <Colaborador> listaColaboradores = new ArrayList <Colaborador> ();	//lista de todos os colaboradores adicionados
	static ArrayList <Projeto> listaProjetos = new ArrayList <Projeto> ();	//lista de todos os projetos criados
	static ArrayList <Producao> listaProducoes = new ArrayList <Producao> ();	//lista de todas as producoes registradas
	
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
			Boolean verificador = false;
			
			while(!verificador) {
				try {
					System.out.println("\nSelecione a opção desejada:\n");
					System.out.println("1-Cadastrar colaborador\n2-Criar projeto\n3-Editar projeto\n4-Criar produção");
					System.out.println("5-Consulta de dados\n6-Relatório de Produção Acadêmica\n\n0-Encerrar\n");
					System.out.print("Opção desejada: ");
					
					comando = scanner.nextInt();
					verificador = true;
				}
				catch(java.util.InputMismatchException e) {
					System.out.println("\n>> Entrada inválida! Digite um número inteiro! <<");
					scanner.nextLine();	//char de escape
				}
			}
			
			switch(comando) {
				case 0:
					System.out.println("\n>> Até logo! <<");
					break;
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
					criarProducao();
					break;
				case 5:
					consultarDados();
					break;
				case 6:
					imprimirRelatorio();
					break;
				default:
					System.out.println("\n>> Digite um número válido! <<");
					break;
			}
		}
	}
}