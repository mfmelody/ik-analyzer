package org.wltea.analyzer.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Test;

public class IKAnalyzerTest extends BaseTokenStreamTestCase {

	private final Analyzer analyzer = new IKAnalyzer(true);

	@Test
	public void testAnalyzer() throws IOException {
		
		String text = "2012年奥迪Q5奔驰GLES好看的宝马";	
		
		int[] startOff = new int[] {5, 7, 9, 11, 18};
		int[] endOff = new int[] {7, 9, 11, 14, 20};
		
		String[] output = new String[] {"奥迪", "q5", "奔驰", "gle", "宝马"};
		
		assertAnalyzesTo(analyzer, text, output, startOff, endOff);

		String[] types = new String[] {"brandname", "chexi", "brandname", "chexi", "brandname"};
		TokenStream tokenStream = analyzer.tokenStream("", text);
		assertTokenStreamContents(tokenStream, output, types);
	}

}
