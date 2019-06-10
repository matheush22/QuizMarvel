package br.com.quizmarvel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.quizmarvel.dao.JogadorExcel;
import br.com.quizmarvel.dao.PerguntaExcel;
import br.com.quizmarvel.utl.QuizMarvelUtil;

public class TelaInicio extends JFrame {

	private static final long serialVersionUID = 4798874077549891300L;
	private JPanel contentPane;

	public TelaInicio() throws FileNotFoundException, IOException {
		setTitle("QuizMarvel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}

			private void formWindowClosing(java.awt.event.WindowEvent evt) {
				if (JOptionPane.showConfirmDialog(null, "Deseja sair?", "Logoff Quiz Marvel",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(TelaInicio.DISPOSE_ON_CLOSE);
				} else {
					setDefaultCloseOperation(TelaInicio.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		JLabel lblSaudacao = new JLabel("Bem-vindo Jogador");
		lblSaudacao.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblSaudacao.setBounds(138, 24, 201, 40);
		contentPane.add(lblSaudacao);

		JLabel lblSelecionarUsuario = new JLabel("Usu\u00E1rio:");
		lblSelecionarUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblSelecionarUsuario.setBounds(59, 104, 70, 22);
		contentPane.add(lblSelecionarUsuario);

		JogadorExcel jogadorExcel = new JogadorExcel();
		String[] jogadoresTmp = jogadorExcel.exibirJogador();
		Vector comboBoxItens = new Vector();
		for (int i = 0; i < jogadoresTmp.length; i++) {
			comboBoxItens.add(jogadoresTmp[i]);
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItens);
		JComboBox cbUsuario = new JComboBox(model);
		cbUsuario.setBounds(131, 107, 260, 22);
		contentPane.add(cbUsuario);

		JButton btnNewButton = new JButton("Rank");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRanking telaRanking = null;
				try {
				 telaRanking = new TelaRanking();
				} catch (Exception e2) {
				  e2.printStackTrace();
				}
				telaRanking.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewButton.setBounds(20, 215, 97, 31);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroJogador telaCadastroJogador = new TelaCadastroJogador();
				telaCadastroJogador.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewButton_1.setBounds(303, 215, 120, 31);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Jogar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 PerguntaExcel perguntaExcel = new PerguntaExcel();
				 String[][] pergNv1 = null;
				 try {
					pergNv1 = perguntaExcel.capturarPerguntas(0);
					
				} catch (Exception e2) {
					e2.printStackTrace();
					
				}
				String[][] pergNv1Sorteadas = perguntaExcel.sortearPerguntas(pergNv1);
				String[] respostaCorretaNv1 = new String[5];
				for (int j = 0; j < pergNv1Sorteadas.length; j++) {
					respostaCorretaNv1[j] = pergNv1Sorteadas[j][2];
				}
				String[][] pergRespNv1Sorteadas = perguntaExcel.sortearPerguntasRespostas(pergNv1Sorteadas);
				
				//remove categoria do jogador e deixa so o nome
				String[] jogadores = new String[jogadoresTmp.length];
				int indInicio = 0;
				int indFim = 0;
				String nomeJogador = "";
				
				for (int i = 0; i < jogadores.length; i++) {
					if((cbUsuario.getSelectedItem().toString()).equals(jogadoresTmp[i])) {
						indFim = jogadoresTmp[i].indexOf(" ");
						jogadores[i] = jogadoresTmp[i].substring(indInicio, indFim);
						nomeJogador = jogadores[i];
					}
				}
				
				TelaPerguntaNv1 telaPerguntaNv1 = new TelaPerguntaNv1(pergRespNv1Sorteadas,respostaCorretaNv1,nomeJogador,"Nível 1");
				telaPerguntaNv1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnNewButton_2.setBounds(166, 215, 97, 31);
		contentPane.add(btnNewButton_2);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(QuizMarvelUtil.IMAGEM_INICIO));
		lblBackground.setBounds(0, 0, 450, 300);
		contentPane.add(lblBackground);
		
	}
	
}
