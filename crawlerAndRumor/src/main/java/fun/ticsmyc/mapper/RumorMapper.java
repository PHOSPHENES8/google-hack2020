package fun.ticsmyc.mapper;

import fun.ticsmyc.pojo.Rumor;


import java.util.List;


public interface RumorMapper {
    List<Rumor> selAll();
    Rumor selId(int id);
    Rumor selTitle(String title);
    List<Rumor> selRumorType();
    int addRumor(Rumor rumor);
}
