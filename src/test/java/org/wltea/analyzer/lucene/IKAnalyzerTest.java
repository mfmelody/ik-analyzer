package org.wltea.analyzer.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Test;

public class IKAnalyzerTest extends BaseTokenStreamTestCase {

	private final Analyzer analyzer = new IKAnalyzer(true);

	@Test
	public void tokenizeSuccess() throws IOException {
		String[] output = new String[] { "歌诗图", "雅阁" };
		int[] startOff = new int[] { 0, 3 };
		int[] endOff = new int[] { 3, 5 };
		String text = "歌诗图雅阁";
		assertAnalyzesTo(analyzer, text, output, startOff, endOff);

		String[] types = new String[] { "chexi", "chexi" };
		TokenStream tokenStream = analyzer.tokenStream("", text);
		assertTokenStreamContents(tokenStream, output, types);
	}

}
