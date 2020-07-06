package fun.ticsmyc.rumorAndNews;

public class RumorIdentiTest {
    public static void main(String[] args) {
        RumorIdenti rumorIdenti = new RumorIdenti();
        String[] sentences =
                new String[] {
                        "服用消毒液可以治疗新冠肺炎",
                        "猫猫可以传播病毒",
                        "有人在天灾预测里预测过新冠疫情，是真的吗",
                        "改用吸痰机后，武汉死亡人数减半",
                        ""
                };
        for (String sentence : sentences) {
            System.out.println("ack: "+sentence);
            System.out.println(rumorIdenti.RumorIdentify(sentence) + "\n");
        }
    }
}
