package tsi.lpv.samuelwagner.tipo;

import java.io.File;
import java.util.Calendar;

/**
 * A classe <code>Filme</code> possui os atributos necessário para armazenar as informações básicas dos
 * filmes cadastrados no aplicativo Filmoteca.
 * 
 * @author Samuel
 * @author Wagner
 */
public class Filme {
	private int codigo,
				duracao,
				ano,
				classificacaoIMDB,
				classificacaoPessoal;
	private String titulo,
				   sinopse,
				   classificacaoEtaria,
				   midia;
	private Calendar dataLancamento;
	private File poster;
	
	/**
	 * Construtor default da classe Filme.
	 */
	public Filme() {}
	
	/**
	 * Obtém um <code>int</code> contendo o código do filme.
	 * @return codigo<code>int</code>
	 */
	public int getCodigo() {
		return codigo;
	}//getCodigo()

	/**
	 * Atribui o código do filme.
	 * @param codigo <code>int</code> com o código do filme.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}//setCodigo()

	/**
	 * Obtém um <code>int</code> contento a duração do filme em minutos.
	 * @return duracao <code>int</code>
	 */
	public int getDuracao() {
		return duracao;
	}//getDuracao()
	
	/**
	 * Atribui a duração do filme.
	 * @param duracao <code>int</code> com a duração do filme em minutos.
	 */
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}//setCodigo()
	
	/**
	 * Obtém um <code>int</code> com o ano do filme.
	 * @return <code>int</code>
	 */
	public int getAno() {
		return ano;
	}//getAno()

	/**
	 * Atribui o ano do filme.
	 * @param ano <code>int</code> com o ano do filme.
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}//setAno()

	/**
	 * Obtém a classificação do filme segundo o IMDB.
	 * @return <code>int</code>
	 */
	public int getClassificacaoIMDB() {
		return classificacaoIMDB;
	}//getClassificacao()
	
	/**
	 * Atribui a classificação <code>int</code> segundo o IMDB.
	 * @param classificacaoIMDB
	 */
	public void setClassificacaoIMDB(int classificacaoIMDB) {
		this.classificacaoIMDB = classificacaoIMDB;
	}//setClassificacaoIMDB()
	
	/**
	 * Obtém a classificação pessoal do filme.
	 * @return <code>int</code>.
	 */
	public int getClassificacaoPessoal() {
		return classificacaoPessoal;
	}//getClassificacaoPessoal()
	
	/**
	 * Atribui a classificacao pessoal ao filme.
	 * @param classificacaoPessoal <code>int</code> com a classificacao pessoal.
	 */
	public void setClassificacaoPessoal(int classificacaoPessoal) {
		this.classificacaoPessoal = classificacaoPessoal;
	}//setClassificacaoPessoal()
	
	/**
	 * Obtém o título do filme.
	 * @return <code>String</code>.
	 */
	public String getTitulo() {
		return titulo;
	}//getTitulo()

	/**
	 * Atribui o título do filme.
	 * @param titulo <code>String</code> com o título do filme.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}//setTitulo()
	
	/**
	 * Obtém a sinopse do filme.
	 * @return <code>String</code>.
	 */
	public String getSinopse() {
		return sinopse;
	}//getSinopse()
	
	/**
	 * Atribui a sinopse do filme.
	 * @param sinopse <code>String</code> com a sinopse do filme.
	 */
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}//setSinopse()
	
	/**
	 * Obtém a classificação etária do filme.
	 * @return <code>String</code>
	 */
	public String getClassificacaoEtaria() {
		return classificacaoEtaria;
	}//getClassificacaoEtaria()
	
	/**
	 * Atribui a classificação etária do filme.
	 * @param classificacaoEtaria <code>String</code>.
	 */
	public void setClassificacaoEtaria(String classificacaoEtaria) {
		this.classificacaoEtaria = classificacaoEtaria;
	}//setClassificacaoEtaria()
	
	/**
	 * Obtém a mídia utilizada para armazenar o filme.
	 * @return <code>String</code>.
	 */
	public String getMidia() {
		return midia;
	}//getMidia()
	
	/**
	 * Atribui a mídia utilizada para armazenar o filme.
	 * @param midia <code>String</code> com a tipo de mídia utilizada para armazenar o filme.
	 */
	public void setMidia(String midia) {
		this.midia = midia;
	}//setMidia()
	
	/**
	 * Obtém a data de lançamento do filme.
	 * @return
	 */
	public Calendar getDataLancamento() {
		return dataLancamento;
	}//getDataLancamento()
	
	/**
	 * Atribui a data de lançamento do filme.
	 * @param dataLancamento <code>Calendar</code> com a data de lançamento do filme.
	 */
	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}//setDataLancamento()
	
	/**
	 * Obtém a imagem usada como pôster do filme.
	 * @return <code>File</code>
	 */
	public File getPoster() {
		return poster;
	}//getPoster()
	
	/**
	 * Atribui a imagem a ser utilizada como pôster do filme.
	 * @param poster
	 */
	public void setPoster(File poster) {
		this.poster = poster;
	}//setPoster()
}//class Filme
