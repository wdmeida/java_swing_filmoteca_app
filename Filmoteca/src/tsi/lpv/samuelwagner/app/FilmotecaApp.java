package tsi.lpv.samuelwagner.app;

import java.io.File;
import java.sql.Connection;
import java.util.Calendar;

import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;
import tsi.lpv.samuelwagner.gui.IgFilmoteca;
import tsi.lpv.samuelwagner.persistencia.ConnectionFactory;
import tsi.lpv.samuelwagner.persistencia.FilmeDAO;
import tsi.lpv.samuelwagner.tipo.Filme;

public class FilmotecaApp {

	public static void main(String[] args) {
		new IgFilmoteca();
	}//testa

}
