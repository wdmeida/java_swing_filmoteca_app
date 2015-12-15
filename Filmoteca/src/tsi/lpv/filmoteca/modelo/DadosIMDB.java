package tsi.lpv.filmoteca.modelo;
/**
 * A classe <code>DadosIMDB</code> é responsável por encapsular os atributos que serão utilizados
 * para a busca dos dados através da api do IMDB. 
 * 
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class DadosIMDB {
	/*
	 * Atributos que armazenaram as informações do filme obtidas através da api. Os atributos estão definidos usando letras
	 * maíusculas, o que foge da convenção de nomeação adotada pelos desenvolvedores Java, que manda que atributos sejam nomeados
	 * começando com letras minúsculas. Os atributos estão nomeados da forma erronea, pois a utilização do api GSON necessita que 
	 * todos os atributos sejam nomeados de forma identica a chave de acesso do Json recebido, e este é retornado com a primeira letra
	 * escrita em maiúscula pela api do imdb.
	 */
	
	private String Title,
				   Year,
				   Rated,
				   Released,
				   Runtime,
				   Renre,
				   Director,
				   Writer,
				   Actors,
				   Plot,
				   Language,
				   Country,
				   Awards,
				   Poster,
				   Metascore,
				   ImdbRating,
				   ImdbVotes,
				   ImdbID,
				   Type;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getRated() {
		return Rated;
	}

	public void setRated(String rated) {
		Rated = rated;
	}

	public String getReleased() {
		return Released;
	}

	public void setReleased(String released) {
		Released = released;
	}

	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String runtime) {
		Runtime = runtime;
	}

	public String getRenre() {
		return Renre;
	}

	public void setRenre(String renre) {
		Renre = renre;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public String getWriter() {
		return Writer;
	}

	public void setWriter(String writer) {
		Writer = writer;
	}

	public String getActors() {
		return Actors;
	}

	public void setActors(String actors) {
		Actors = actors;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		Plot = plot;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getAwards() {
		return Awards;
	}

	public void setAwards(String awards) {
		Awards = awards;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String poster) {
		Poster = poster;
	}

	public String getMetascore() {
		return Metascore;
	}

	public void setMetascore(String metascore) {
		Metascore = metascore;
	}

	public String getImdbRating() {
		return ImdbRating;
	}

	public void setImdbRating(String imdbRating) {
		ImdbRating = imdbRating;
	}

	public String getImdbVotes() {
		return ImdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		ImdbVotes = imdbVotes;
	}

	public String getImdbID() {
		return ImdbID;
	}

	public void setImdbID(String imdbID) {
		ImdbID = imdbID;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	
}//class DadosIMDB
