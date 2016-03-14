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
		
		String text = "2012年奥迪Q5好看的宝马x1奔驰";	
		
		int[] startOff = new int[] {5, 12, 16};
		int[] endOff = new int[] {9, 16, 18};
		
		String[] output = new String[] {"奥迪q5", "宝马x1", "奔驰"};
		
		assertAnalyzesTo(analyzer, text, output, startOff, endOff);

		String[] types = new String[] {"alias", "alias", "brandname"};
		TokenStream tokenStream = analyzer.tokenStream("", text);
		assertTokenStreamContents(tokenStream, output, types);
	}

}
