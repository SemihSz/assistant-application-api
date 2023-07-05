package com.sentimentanalyzer.core;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by Semih, 6.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Component
public class Pipeline {


    private static Properties properties;

    private static String propertiesName = "tokenize, ssplit, pos, lemma, ner";

    private static StanfordCoreNLP stanfordCoreNLP;

    private Pipeline() {

    }

    static {
        properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,sentiment,depparse,coref,kbp,quote");
    }

    @Bean(name = "stanfordCoreNLP")
    public static StanfordCoreNLP getInstance() {

        if (stanfordCoreNLP == null) {
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }

        return stanfordCoreNLP;
    }
}
