package br.com.quizmarvel.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.com.quizmarvel.dao.RankingExcel;
import br.com.quizmarvel.utl.QuizMarvelUtil;

public class TelaRanking extends JFrame {

	private static final long serialVersionUID = -5151366217075164797L;
	private JPanel contentPane;
	private JTable table;

	public TelaRanking() throws FileNotFoundException, IOException {
		setTitle("Ranking Top 10 - QuizMarvel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 500);
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

		// traz o rank
		RankingExcel rankingExcel = new RankingExcel();
		String[][] rankTmp;
		rankTmp = rankingExcel.exibirRanking();
		String[][] ranking = new String[10][4];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				try {
					ranking[i][j] = rankTmp[i + 1][j];
				} catch (Exception e) {
					ranking[i][j] = null;
				}
			}

		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 762, 331);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(new Color (0,0,0),2));
		table.getTableHeader().setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{ranking[0][0], ranking[0][1], ranking[0][2], ranking[0][3]},
				{ranking[1][0], ranking[1][1], ranking[1][2], ranking[1][3]},
				{ranking[2][0], ranking[2][1], ranking[2][2], ranking[2][3]},
				{ranking[3][0], ranking[3][1], ranking[3][2], ranking[3][3]},
				{ranking[4][0], ranking[4][1], ranking[4][2], ranking[4][3]},
				{ranking[5][0], ranking[5][1], ranking[5][2], ranking[5][3]},
				{ranking[6][0], ranking[6][1], ranking[6][2], ranking[6][3]},
				{ranking[7][0], ranking[7][1], ranking[7][2], ranking[7][3]},
				{ranking[8][0], ranking[8][1], ranking[8][2], ranking[8][3]},
				{ranking[9][0], ranking[9][1], ranking[9][2], ranking[9][3]},
			},
			new String[] {
				"Nome", "Categoria", "Maior Pontua\u00E7\u00E3o", "\u00DAltima Pontua\u00E7\u00E3o"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblRanking = new JLabel("Ranking Top 10");
		lblRanking.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblRanking.setBounds(313, 11, 152, 32);
		contentPane.add(lblRanking);

	

		JButton btnVoltar = new JButton("Voltar ao Inicio");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicio telaInicio = null;
				try {
					telaInicio = new TelaInicio();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				telaInicio.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnVoltar.setBounds(294, 407, 188, 31);
		contentPane.add(btnVoltar);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(QuizMarvelUtil.IMAGEM_RANKING));
		lblBackground.setBounds(0, 0, 800, 500);
		contentPane.add(lblBackground);
		
	}
}
