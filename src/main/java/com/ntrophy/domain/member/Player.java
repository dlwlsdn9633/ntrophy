package com.ntrophy.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private int id;
    private String name;
    private PlayerRankSquad playerRankSquad;
    private PlayerSquad playerSquad;
    private PlayerDuo playerDuo;
    private PlayerSolo playerSolo;

}
