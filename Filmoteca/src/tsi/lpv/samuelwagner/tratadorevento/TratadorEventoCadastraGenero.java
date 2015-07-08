package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import tsi.lpv.samuelwagner.funcaoauxiliar.FuncaoAuxiliar;
import tsi.lpv.samuelwagner.gui.IgCadastraGenero;
import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;
import tsi.lpv.samuelwagner.persistencia.GeneroDAO;
import tsi.lpv.samuelwagner.tipo.Genero;

/**Classe Responsavel por tratar do Eventos da Classe <code>TratadorEventoCadastraGenero</code>.
 * @author Samuel
 * @author Wagner
 */
public class TratadorEventoCadastraGenero implements ActionListener{
	private IgCadastrarFilme igCadastrarFilme;
	private IgCadastraGenero igCadastraGenero;
	
	/**Construtor Sobregarregado.
	 * @param igCadastrarFilme <code>IgCadastraFilme</code>.
	 * @param igCadastraGenero <code>IgCadastraGenero</code>.
	 */
	public TratadorEventoCadastraGenero(IgCadastrarFilme igCadastrarFilme,IgCadastraGenero igCadastraGenero) {
		this.igCadastrarFilme = igCadastrarFilme;
		this.igCadastraGenero = igCadastraGenero;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == igCadastraGenero.getAdicionarButton() && event.getSource() == igCadastraGenero.getAreaDescricao())
			cadastraGenero();
		else
			igCadastraGenero.dispose();
	}
	
	private void cadastraGenero(){
		if(!igCadastraGenero.getAreaDescricao().getText().equals("")){
			if(GeneroDAO.pesquisaGenero(igCadastraGenero.getAreaDescricao().getText()) == null){
				GeneroDAO.cadastrarGenero(new Genero(0, igCadastraGenero.getAreaDescricao().getText()));
				FuncaoAuxiliar.exibirMensagem(igCadastraGenero, "Genero Cadastrado.", "Cadastra Genero.");
				igCadastrarFilme.getGeneroComboBox().setModel(new DefaultComboBoxModel<String>(GeneroDAO.obtemNomesGenero()));
				igCadastraGenero.dispose();
				return;
			}
			FuncaoAuxiliar.exibirMensagemErro(igCadastraGenero, "Genero já Cadastrado.", "Cadastra Genero.");
			return;
		}
		FuncaoAuxiliar.exibirMensagemErro(igCadastraGenero, "Campo Descrição Não Pode ser Vazio.", "Cadastra Genero.");
	}
}
