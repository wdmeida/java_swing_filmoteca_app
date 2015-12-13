package tsi.lpv.filmoteca.trataeventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;

import tsi.lpv.filmoteca.gui.IgCadastraAtorAutorDiretor;
import tsi.lpv.filmoteca.gui.IgCadastrarFilme;
import tsi.lpv.filmoteca.gui.IgMensagem;
import tsi.lpv.filmoteca.modelo.Filme;
import tsi.lpv.filmoteca.persistencia.FilmeDAO;
import tsi.lpv.filmoteca.persistencia.GeneroDAO;
import tsi.lpv.filmoteca.persistencia.GeneroFilmeDAO;
import tsi.lpv.filmoteca.persistencia.PaisDAO;
import tsi.lpv.filmoteca.persistencia.PaisFilmeDAO;
/**
 * A classe <code>TratadorEventoCadastraAAD</code> é responsável por tratar os eventos dos cadastro de dados do 
 * aplicativo filmoteca.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class TratadorEventoCadastraAAD implements ActionListener{
	private IgCadastrarFilme igCadastrarFilme;
	private IgCadastraAtorAutorDiretor igCadastraAtorAutorDiretor;
	
	/**
	 * Construtor defaul da classe <code>TratadorEventoCadastraAAD</code>.
	 * @param igCadastrarFilme <code>IgCadastrarFilme</code>
	 * @param igCadastraAtorAutorDiretor <code>IgCadastraAtorAutorDiretor</code>
	 */
	public TratadorEventoCadastraAAD(IgCadastrarFilme igCadastrarFilme,IgCadastraAtorAutorDiretor igCadastraAtorAutorDiretor) {
		this.igCadastrarFilme = igCadastrarFilme;
		this.igCadastraAtorAutorDiretor = igCadastraAtorAutorDiretor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!igCadastraAtorAutorDiretor.getAreaDescricao().getText().equals("")){
			Filme filme = FilmeDAO.pesquisarFilme(igCadastraAtorAutorDiretor.getAreaDescricao().getText());
			if(filme != null){
				carregaOsDadosFilme(filme);
			}else
				new IgMensagem(igCadastraAtorAutorDiretor, "Filme: " + igCadastraAtorAutorDiretor.getAreaDescricao().getText()
						+ ", forneceido não costa no seu acervo de filmes.");
		}else
			new IgMensagem(igCadastraAtorAutorDiretor, "Area do Texto deve ser Preenchida");
	};
	
	private void carregaOsDadosFilme(Filme filme){
		igCadastrarFilme.getTituloField().setText(filme.getTitulo());
		igCadastrarFilme.getSinopseEditorPane().setText(filme.getSinopse());
		igCadastrarFilme.getDuracaoField().setText(String.valueOf(filme.getDuracao()));
		igCadastrarFilme.getChooser().setCalendar(filme.getDataLancamento());
		igCadastrarFilme.getjYearChooser().setValue(filme.getAno());
		igCadastrarFilme.getClassificacaoIMDBspinner().getModel().setValue(filme.getClassificacaoIMDB());
		igCadastrarFilme.getCllassificacaoSpinner().getModel().setValue(filme.getClassificacaoPessoal());
		igCadastrarFilme.getFaixaEtariaComboBox().setSelectedIndex(comboBoxSelecionadoEtaria(filme.getClassificacaoEtaria()));
		tipoMidia(filme);
		if(filme.getPoster() != null)
			igCadastrarFilme.getPosterLabel().setIcon(new ImageIcon(filme.getPoster()));
		obtemPais(filme);
		obtemGenero(filme);
		igCadastrarFilme.getGeneroComboBox().setSelectedIndex(obtemGenero(filme));
		desabilitaEdicao();
		TratadorEventoCadastraFilme.setFilme(filme);
		igCadastrarFilme.setVisible(true);
	}

	private int comboBoxSelecionadoEtaria(String classificacaoEtaria) {
		for(int indice = 0; indice < igCadastrarFilme.getFaixaEtariaComboBox().getModel().getSize();indice++){
			if(igCadastrarFilme.getFaixaEtariaComboBox().getModel().getElementAt(indice).equalsIgnoreCase(classificacaoEtaria))
				return indice;
		}
		return -1;
	}
	
	private int obtemGenero(Filme filme) {
		List<Integer> codigoGenero = GeneroFilmeDAO.pesquisaGeneroFilme(filme.getCodigo());
		String nomeGenero = GeneroDAO.pesquisaGenero(codigoGenero.get(0)).getNome();
		for(int indice = 0; indice < igCadastrarFilme.getGeneroComboBox().getModel().getSize();indice++){
			if(igCadastrarFilme.getGeneroComboBox().getModel().getElementAt(indice).equalsIgnoreCase(nomeGenero))
				return indice;
		}
		return -1;
	}
	
	//Retorna o tipo de Midia selecionado Pelo Usuario.
		private void tipoMidia(Filme filme){
			if("DVD".equalsIgnoreCase(filme.getMidia()))
				igCadastrarFilme.getDvdRadio().setSelected(true);
			else
				if("Blu-Ray".equalsIgnoreCase(filme.getMidia()))
					igCadastrarFilme.getBluRayRadio().setSelected(true);
				else
					if("Arquivo Digital".equalsIgnoreCase(filme.getMidia()))
						igCadastrarFilme.getArquivoDigitalRadio().setSelected(true);
		}
	private void desabilitaEdicao(){
		igCadastrarFilme.getTituloField().setEnabled(false);
		igCadastrarFilme.getSinopseEditorPane().setEnabled(false);
		igCadastrarFilme.getDuracaoField().setEnabled(false);
		igCadastrarFilme.getChooser().setEnabled(false);
		igCadastrarFilme.getjYearChooser().setEnabled(false);
		igCadastrarFilme.getClassificacaoIMDBspinner().setEnabled(false);
		igCadastrarFilme.getCllassificacaoSpinner().setEnabled(false);
		igCadastrarFilme.getFaixaEtariaComboBox().setEnabled(false);
		igCadastrarFilme.getGeneroComboBox().setEnabled(false);
		igCadastrarFilme.getInserirFotoButton().setEnabled(false);
		igCadastrarFilme.getPaisField().setEnabled(false);
		igCadastrarFilme.getDvdRadio().setEnabled(false);
		igCadastrarFilme.getArquivoDigitalRadio().setEnabled(false);
		igCadastrarFilme.getBluRayRadio().setEnabled(false);
	}
	
	private void obtemPais(Filme filme){
		int codigoPais = PaisFilmeDAO.obterPaisFilme(filme.getCodigo());
		igCadastrarFilme.getPaisField().setText(PaisDAO.pesquisaPais(codigoPais).getNome());
	}
}

