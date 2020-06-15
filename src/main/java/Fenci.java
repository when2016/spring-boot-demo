

import com.google.gson.Gson;
import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Fenci {

    public static Object[] start(String text) {
        try {
            List<String> list = new ArrayList<String>();
            Analyzer analyzer = new PaodingAnalyzer();
            TokenStream tokenizer = analyzer.tokenStream("text", new StringReader(text));
            tokenizer.reset();
            CharTermAttribute offAtt = (CharTermAttribute) tokenizer.addAttribute(CharTermAttribute.class);
            while (tokenizer.incrementToken()) {
                list.add(offAtt.toString());
            }
            tokenizer.close();
            return list.toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[]{text};
    }

    public static void main(String args[]) {
        Object arr[] = start("这些沙糖桔怎么回事？");
        System.out.println(new Gson().toJson(arr));
    }

}
