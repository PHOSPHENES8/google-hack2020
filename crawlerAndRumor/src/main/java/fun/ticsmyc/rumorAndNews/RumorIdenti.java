package fun.ticsmyc.rumorAndNews;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.qianxinyao.analysis.jieba.keyword.Keyword;
import com.qianxinyao.analysis.jieba.keyword.TFIDFAnalyzer;
import fun.ticsmyc.dao.InformationDao;
import fun.ticsmyc.pojo.Rumor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RumorIdenti {
    private List<Rumor> acknowledge;
    private List<List<String>> describeBrief = new ArrayList<List<String>>();
    public RumorIdenti() {
        //get rumor list
        InformationDao dao = new InformationDao();
        acknowledge = dao.getAllRumor();
        for (Rumor rumori : acknowledge) {
            String title = rumori.getTitle();
            String body = rumori.getBody();
            String mainSummary = rumori.getMainSummary();
            JiebaSegmenter segmenter = new JiebaSegmenter();
            List<SegToken> tokens = segmenter.process(title+body, JiebaSegmenter.SegMode.SEARCH);
            List<String> wordL = new ArrayList<String>();
            for (SegToken token : tokens) {
                //System.out.println(token.toString());
                if (token.word != null)
                    wordL.add(token.word);
            }
            if (wordL != null)
                describeBrief.add(wordL);
        }
        System.out.println("init rumor identifer done!");
    }

    private List<Keyword> removeDuplicate(List<Keyword> ori) {
        List<Keyword> dst = new ArrayList<>();
        for (Keyword keyword : ori) {
            boolean has = false;
            for (Keyword exist : dst) {
                if (exist.getName() == keyword.getName()) {
                    has = true;
                }
            }

            if (!has) {
                dst.add(keyword);
                //System.out.println("add "+keyword.getName());
            }
        }
        return dst;
    }

    private float Similraty(List<Keyword> list, List<String > acknowledge) {
        Set<String> ackSet = new HashSet<>(acknowledge);
        //debug

        float matchN = 0;
        float maxN = 0;
        for (Keyword tfIdfWord : list) {
            maxN += tfIdfWord.getTfidfvalue();
            if (ackSet.contains(tfIdfWord.getName())) {
                // System.out.println(tfIdfWord.getName() + " "+tfIdfWord.getTfidfvalue());
                matchN += tfIdfWord.getTfidfvalue();
            }
        }
        float ratio = matchN / maxN;
        return ratio;
    }


    public String RumorIdentify(String describe) {
        //1：获取关键词
        //2: 关键词模糊匹配

        String res = "";

        //将问话切割为单词

        TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
        List<Keyword> list=tfidfAnalyzer.analyze(describe,5);
        List<Keyword> dstList = removeDuplicate(list);

        //default res
        float bestRatio = (float) 0.51;
        res = "我们还不确定该信息是否是谣言";

        int cnt=0;
        for (List<String> rumor: describeBrief) {

            float ratio = Similraty(dstList, rumor);
            if (ratio > bestRatio) {
                bestRatio = ratio;
                res = "如果您想知道的是："+acknowledge.get(cnt).getTitle() +
                    "\n" + acknowledge.get(cnt).getMainSummary() + "\n" +
                        acknowledge.get(cnt).getBody();
            }
            cnt+=1;
        }

        return res;
    }
}
