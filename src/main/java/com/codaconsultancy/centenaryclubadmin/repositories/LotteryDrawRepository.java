package com.codaconsultancy.centenaryclubadmin.repositories;

import com.codaconsultancy.centenaryclubadmin.domain.LotteryDraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryDrawRepository extends JpaRepository<LotteryDraw, Long> {

    @Query(value = "select * from lottery_draws where id = (select max(id) from lottery_draws where lottery_date < now())", nativeQuery = true)
    LotteryDraw findLastDraw();

    @Query(value = "select * from lottery_draws where id = (select max(id) from lottery_draws where id < (select max(id) from lottery_draws where lottery_date < now()))", nativeQuery = true)
    LotteryDraw findSecondLastDraw();
}
