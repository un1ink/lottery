package com.un1ink.domain.support.ids.policy;

import com.un1ink.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Random;

/**
 * @description: 短码生成策略
 * @author：un1ink
 * @date: 2023/3/27
 */

@Component
public class ShortCode implements IIdGenerator {


    @Override
    public long nextId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        StringBuilder idStr = new StringBuilder();
        idStr.append(year - 2020);
        idStr.append(hour);
        idStr.append(String.format("%02d",week));
        idStr.append(day);
        idStr.append(String.format("%03d", new Random().nextInt(1000)));

        return Long.parseLong(idStr.toString());

    }
}
