package com.sentimentanalyzer.service;

import com.assistant.commonapi.task.SimpleTask;
import com.sentimentanalyzer.request_response.SentimentAnalyzerRequest;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.Tree;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Semih, 6.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SentimentAnalyzerService implements SimpleTask<SentimentAnalyzerRequest, String> {


    private final StanfordCoreNLP stanfordCoreNLP;

    @Override
    public String apply(SentimentAnalyzerRequest sentimentAnalyzerRequest) {
        CoreDocument coreDocument = new CoreDocument(sentimentAnalyzerRequest.getInputText());
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();

        // list yarıya bölüp buna uygun nlp operasyonları yapılır
        final List<CoreSentence> coreSentences = coreDocument.sentences();

        // Cümle içerisinde bulunan relation gösteriyor.
        List<RelationTriple> relations =
                coreDocument.sentences().get(0).relations();
        System.out.println("Example: relation");
        System.out.println(relations.get(0));
        System.out.println();

        for (CoreSentence sentence: coreSentences) {
            final String sentiment = sentence.sentiment();
            log.info(sentence.dependencyParse().toString());
            log.info(sentiment + " = " + sentence);
            Tree constituencyParse = sentence.constituencyParse();
            System.out.println("Example: constituency parse");
            System.out.println(constituencyParse);
            System.out.println();

            SemanticGraph dependencyParse = sentence.dependencyParse();
            System.out.println("Example: dependency parse");
            System.out.println(dependencyParse);
            System.out.println();

        }

        return null;
    }



}
