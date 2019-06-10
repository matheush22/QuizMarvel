package br.com.quizmarvel.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.quizmarvel.bns.Jogador;
import br.com.quizmarvel.utl.QuizMarvelUtil;

public class JogadorExcel {

	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cellrow;
	private HSSFCell cell0;
	private HSSFCell cell1;
	private HSSFCell cell2;
	private HSSFCell cell3;

	// metodo para criar novos jogadores
	public void criarJogador(Jogador jogador) throws FileNotFoundException, IOException {

		fileInputStream = new FileInputStream(new File(QuizMarvelUtil.JOGADOR_ARQUIVO));
		workbook = new HSSFWorkbook(fileInputStream);
		sheet = workbook.getSheetAt(0);

		int i = 1;

		try {
			do {
				row = sheet.getRow(i);
				cellrow = row.getCell(0);
				i++;
			} while (!cellrow.getStringCellValue().equals(null));
		} catch (NullPointerException e) {
			row = sheet.createRow(i);

			cell0 = row.createCell(0);
			cell1 = row.createCell(1);
			cell2 = row.createCell(2);
			cell3 = row.createCell(3);

			cell0.setCellValue(jogador.getNome());
			cell1.setCellValue(jogador.getCategoria());
			cell2.setCellValue(jogador.getMaiorPontuacao());
			cell3.setCellValue(jogador.getUltimaPontuacao());

			fileInputStream.close();

			fileOutputStream = new FileOutputStream(new File(QuizMarvelUtil.JOGADOR_ARQUIVO));
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		}

	}

	// metodo para verificar se o jogador já foi cadastrado
	public boolean verificaJogadorExistente(Jogador jogador) throws FileNotFoundException, IOException {

		fileInputStream = new FileInputStream(new File(QuizMarvelUtil.JOGADOR_ARQUIVO));
		workbook = new HSSFWorkbook(fileInputStream);
		sheet = workbook.getSheetAt(0);

		int i = 1;

		try {
			do {
				row = sheet.getRow(i);
				cell0 = row.getCell(0);
				cell1 = row.getCell(1);
				i++;
			} while ((!cell0.getStringCellValue().equals(jogador.getNome()))
					|| (!cell1.getStringCellValue().equals(jogador.getCategoria())));
			return true;
		} catch (NullPointerException e) {
			return false;
		}

	}
	// metodo para verificar se o jogador já foi cadastrado

	// metodo para exibir os jogadores e seus dados
	public String[] exibirJogador() throws FileNotFoundException, IOException {

		fileInputStream = new FileInputStream(new File(QuizMarvelUtil.JOGADOR_ARQUIVO));
		workbook = new HSSFWorkbook(fileInputStream);
		sheet = workbook.getSheetAt(0);

		int contCelNaoNula = 1;

		try {
			do {
				row = sheet.getRow(contCelNaoNula);
				cellrow = row.getCell(0);
				contCelNaoNula++;
			} while (!cellrow.getStringCellValue().equals(null));
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		// remover 1 das células não nulas pois nâo pode haver
		String[] nomeJogador = new String[contCelNaoNula - 1];

		// começando o for de 1 remover 1 das células não nulas
		for (int i = 0; i < contCelNaoNula - 1; i++) {
			row = sheet.getRow(i + 1);
			cell0 = row.getCell(0);
			cell1 = row.getCell(1);
			nomeJogador[i] = cell0.getStringCellValue() + " " + "(" + cell1.getStringCellValue() + ")";
		}

		// colocando os nomes em ordem alfabética
		Arrays.sort(nomeJogador);

		return nomeJogador;
	}

	// metodo para atualizar a pontuação dos jogadores
	public void atualizarJogadorPontuacao(String nomeJogador, double pontuacao) throws FileNotFoundException, IOException {

		fileInputStream = new FileInputStream(new File(QuizMarvelUtil.JOGADOR_ARQUIVO));
		workbook = new HSSFWorkbook(fileInputStream);
		sheet = workbook.getSheetAt(0);

		double maiorPontuacao;
		double ultimaPontuacao;
		int contPosicaoJogador = 0;

		try {
			do {
				contPosicaoJogador++;
				row = sheet.getRow(contPosicaoJogador);
				cellrow = row.getCell(0);
			} while (!cellrow.getStringCellValue().equals(nomeJogador));
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		// row = sheet.getRow(contPosicaoJogador);
		cell2 = row.getCell(2);
		cell3 = row.getCell(3);

		maiorPontuacao = cell2.getNumericCellValue();
		if (pontuacao > maiorPontuacao) {
			cell2.setCellValue(pontuacao);
		}
		ultimaPontuacao = pontuacao;
		cell3.setCellValue(ultimaPontuacao);

		fileInputStream.close();

		fileOutputStream = new FileOutputStream(new File(QuizMarvelUtil.JOGADOR_ARQUIVO));
		workbook.write(fileOutputStream);
		fileOutputStream.close();

	}

}
