package com.sentimentanalyzer;

import com.sentimentanalyzer.core.Pipeline;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

/**
 * Created by Semih, 6.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
public class test {

    public static void main(String[] args) {

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getInstance();
        CoreDocument coreDocument = new CoreDocument("I really don't like this place.");
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();
        final List<CoreSentence> coreSentences = coreDocument.sentences();

        for (CoreSentence sentence: coreSentences) {
            final String sentiment = sentence.sentiment();
            System.out.println(sentiment);
        }

    }
}
