package com.un1ink.infrastructure.repository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.un1ink.common.constants.ActivityState;
import com.un1ink.domain.activity.model.vo.ActivityVO;
import com.un1ink.domain.activity.model.vo.AwardVO;
import com.un1ink.domain.activity.model.vo.StrategyDetailVO;
import com.un1ink.domain.activity.model.vo.StrategyVO;
import com.un1ink.domain.activity.repository.IActivityRepository;
import com.un1ink.domain.award.repository.IAwardRepository;
import com.un1ink.infrastructure.dao.*;
import com.un1ink.infrastructure.po.Activity;
import com.un1ink.infrastructure.po.Award;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：un1ink
 * @date: 2023/3/26
 */
@Component
public class AwardRepository implements IAwardRepository {


}
