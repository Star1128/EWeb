package com.ethan.eweb.service;

import com.ethan.eweb.data.DataHolder;
import com.ethan.eweb.pojo.ListItem;
import com.ethan.eweb.prop.BaseProp;
import com.ethan.eweb.worker.IdWorker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Ethan 2023/1/7
 */
@Service
public class DataService {
    private final Random random = new Random();
    private final IdWorker idWorker = new IdWorker();

    public List<ListItem> generateJson(boolean isCh) {
        List<ListItem> listItems = new ArrayList<>();

        // 切换数据源
        List<String> origin;
        if (isCh) {
            origin = DataHolder.titles_ch;
        } else {
            origin = DataHolder.titles_en;
        }

        for (String s : origin) {
            ListItem textItem = new ListItem(s, idWorker.nextId() + "", "/static/imgs/" + random.nextInt(BaseProp.COUNT_IMGS) + ".png", random.nextInt(BaseProp.MAX_LIST_ITEMS_VIEW), random.nextInt(BaseProp.MAX_LIST_ITEMS_COMMENT));
            listItems.add(textItem);
        }

        return listItems;
    }
}
