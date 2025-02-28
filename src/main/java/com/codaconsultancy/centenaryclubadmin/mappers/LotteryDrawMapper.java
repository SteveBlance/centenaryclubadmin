package com.codaconsultancy.centenaryclubadmin.mappers;

import com.codaconsultancy.centenaryclubadmin.domain.LotteryDraw;
import com.codaconsultancy.centenaryclubadmin.view.LotteryDrawViewBean;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LotteryDrawMapper {
    LotteryDrawMapper INSTANCE = Mappers.getMapper( LotteryDrawMapper.class );

    LotteryDrawViewBean entityToViewBean(LotteryDraw lotteryDraw);
    LotteryDraw viewBeanToEntity(LotteryDrawViewBean lotteryDrawViewBean);
}
