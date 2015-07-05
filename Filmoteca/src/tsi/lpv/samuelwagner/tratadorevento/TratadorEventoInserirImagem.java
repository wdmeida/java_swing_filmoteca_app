package tsi.lpv.samuelwagner.tratadorevento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import tsi.lpv.samuelwagner.gui.IgCadastrarFilme;

public class TratadorEventoInserirImagem implements ActionListener {
	private IgCadastrarFilme igCadastraFilme;
	
	public TratadorEventoInserirImagem(IgCadastrarFilme igCadastraFilme) {
		this.igCadastraFilme = igCadastraFilme;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(igCadastraFilme.getInserirFotoButton() == event.getSource())
			obtemCaminhoFoto();
		else
			if(igCadastraFilme.getLimparFotoButton() == event.getSource()){
				igCadastraFilme.getPosterLabel().removeAll();
				igCadastraFilme.getFotoField().setText("");
				igCadastraFilme.getPosterLabel().revalidate();
				igCadastraFilme.getPosterLabel().repaint();
			}
				
	}
	
	private void obtemCaminhoFoto(){
		JFileChooser dialogoAbrir = new JFileChooser();
		  
		  /*
		   * Define as extensões que serão usadas pelo JFileChooser para filtrar os tipos de arquivos 
		   * que serão exibidos na janela.
		   */
		  FileNameExtensionFilter extensaoFoto = new FileNameExtensionFilter("Arquivo (*.png)", "png","jpg");
		  dialogoAbrir.setFileFilter(extensaoFoto);
		  
		  // Indica que o usuário poderá selecionar apenas nomes de arquivos. 
		  dialogoAbrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		  // Define o título do diálogo. 
		  dialogoAbrir.setDialogTitle("Obter Pôster");
		
		  // Exibe o diálogo.
		  int opcao = dialogoAbrir.showOpenDialog(igCadastraFilme);
		
		  // Verifica se o usuário cancelou a operação (clicou no botão Cancelar).
		  if (opcao == JFileChooser.CANCEL_OPTION)
			  return;
		  else
			  // Obtém o nome do arquivo selecionado pelo usuário no diálogo.
			  if (opcao == JFileChooser.APPROVE_OPTION){ 
				 String caminhoImagem = dialogoAbrir.getSelectedFile().getPath();
				 ImageIcon icon = new ImageIcon(caminhoImagem);
				 igCadastraFilme.getPosterLabel().setIcon(icon);
				 igCadastraFilme.getFotoField().setText(caminhoImagem);
			  }else 
				  return;
		} // dialogoAbrirArquivo()
}
