package tsi.lpv.filmoteca.mensagens;

/**
 * A Interface <code>MensagensAjuda</code> define as constantes com as mensagens que serão usadas nas telas de ajuda 
 * da aplicação
 * 
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 * */ 
public interface MensagensAjuda {
	public String CADASTRAR_POSTER = "Caso queira cadastrar um pôster, "
			+ "clique no botão INSERIR ou utilize as teclas de atalho ALT + I, "
			+ "selecione a imagem e clique em ABRIR. O caminho da imagem será preenchido"
			+ " na caixa de texto acima do botão INSERIR e a mesma aparecerá na janela ao lado. Caso"
			+ " queira escolher outra imagem, basta clicar no botão LIMPAR e realizar novamente a escolha "
			+ "da imagem."
			+ "\n\nO cadastro do Pôster não é obrigatório.";
	
	public String CADASTRAR_ATOR = "Caso queira cadastrar um ator,"
			+ " digite o nome do mesmo na caixa de texto e clique no botão INSERIR ou utilize as teclas de atalho ALT + I."
			+ " Podem ser cadastrados vários atores, um de cada vez. Caso queira remover o último ator cadastrado, clique"
			+ " no botão LIMPAR ou utilize a tecla de atalho ALT + L.";
	
	public String CADASTRAR_AUTOR = "Caso queira cadastrar um autor,"
			+ " digite o nome do mesmo na caixa de texto e clique no botão INSERIR ou utilize as teclas de atalho ALT + I."
			+ " Podem ser cadastrados vários autores, um de cada vez. Caso queira remover o último autor cadastrado, clique"
			+ " no botão LIMPAR ou utilize a tecla de atalho ALT + L.";
	
	public String CADASTRAR_DIRETOR = "Caso queira cadastrar um diretor,"
			+ " digite o nome do mesmo na caixa de texto e clique no botão INSERIR ou utilize as teclas de atalho ALT + I."
			+ " Podem ser cadastrados vários diretores, um de cada vez. Caso queira remover o último diretor cadastrado, clique"
			+ " no botão LIMPAR ou utilize a tecla de atalho ALT + L.";
}//interface MensagensAjuda
