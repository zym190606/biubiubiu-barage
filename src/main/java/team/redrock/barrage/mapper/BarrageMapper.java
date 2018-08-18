package team.redrock.barrage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import team.redrock.barrage.been.BarrageMessage;

@Mapper
@Component
public interface BarrageMapper {
    @Insert("INSERT INTO barrage(openid,text,color,timestamp) VALUE(#{openid},#{text},#{color},#{timestamp})")
    void insert(@Param("text") String text, @Param("color") String color, @Param("openid") String openid,@Param("timestamp") String timestamp);
}
