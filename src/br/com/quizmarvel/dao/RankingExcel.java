package br.com.quizmarvel.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.quizmarvel.utl.QuizMarvelUtil;

public class RankingExcel {

	private FileInputStream fileInputStream;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cellrow;
	private HSSFCell cell0row;

	// inicio - metodo para exibir o ranking top 10
	public String[][] exibirRanking() throws FileNotFoundException, IOException {

		fileInputStream = new FileInputStream(new File(QuizMarvelUtil.JOGADOR_ARQUIVO));
		workbook = new HSSFWorkbook(fileInputStream);
		sheet = workbook.getSheetAt(0);

		int contCelNaoNula = 1;

		try {
			do {
				row = sheet.getRow(contCelNaoNula);
				cell0row = row.getCell(0);
				contCelNaoNula++;
			} while (!cell0row.getStringCellValue().equals(null));

		} catch (NullPointerException e) {
			// System.out.println("o maior é: " + maior);
		}

		String[][] matrizTemp = new String[contCelNaoNula][4];

		for (int i = 1; i < contCelNaoNula; i++) {
			row = sheet.getRow(i);
			for (int j = 0; j < 4; j++) {
				cellrow = row.getCell(j);
				if ((j == 2) || (j == 3)) {
					matrizTemp[i][j] = String.valueOf(cellrow.getNumericCellValue());
				} else {
					matrizTemp[i][j] = cellrow.getStringCellValue();
				}
			}
		}

		// inicio - ordenação por seleção direta
		int k, l, menor;
		String[] aux;
		for (k = 1; k < matrizTemp.length - 1; k++) {
			menor = k;
			for (l = k + 1; l < matrizTemp.length; l++) {

				String pontuacaoString1 = matrizTemp[l][2];
				double pont1 = Double.parseDouble(pontuacaoString1);

				String pontuacaoString2 = matrizTemp[menor][2];
				double pont2 = Double.parseDouble(pontuacaoString2);

				if (pont1 < pont2) {
					menor = l;
				}
			}
			aux = matrizTemp[k];
			matrizTemp[k] = matrizTemp[menor];
			matrizTemp[menor] = aux;
		}
		// fim - ordenação por seleção direta

		String[][] matrizTempOrd = new String[contCelNaoNula][4];

		// inicio - inverte a ordenação para ir do maior para menor
		for (int i = contCelNaoNula - 1; i > 0; i--) {
			for (int j = 0; j < 4; j++) {
				matrizTempOrd[contCelNaoNula - i][j] = matrizTemp[i][j];
			}
		}
		// fim - inverte a ordenação para ir do maior para menor

		return matrizTempOrd;
	}
	// fim - metodo para exibir o ranking top 10

}
