package com.ntrophy.converter;

import com.ntrophy.domain.match.Match;
import com.ntrophy.domain.match.MatchAttribute;

import com.ntrophy.domain.match.MatchParticipant;
import com.ntrophy.dto.pubg.match.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MatchResponseDtoToMatch implements Converter<MatchResponseDto, Match> {
    @Override
    public Match convert(MatchResponseDto source) {
        try {
            return Match.builder()
                    .id(source.getData().getId())
                    .type(source.getData().getType())
                    .matchAttribute(getMatchAttribute(source.getData().getAttributes()))
                    .rosters(getMatchRosterList(source))
                    .participantsMap(getParticipantsMap(source))
                    .build();
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return Match.builder().build();
        }
    }

    private List<com.ntrophy.domain.match.MatchRoster> getMatchRosterList(MatchResponseDto source) {
        Map<String, MatchIncluded> rosterMap = getRosterMap(source);
        Map<String, MatchIncluded> participantsMap = getParticipantsMap(source);
        List<com.ntrophy.domain.match.MatchRoster> matchRosterList = new ArrayList<>();

        for (Map.Entry<String, MatchIncluded> rosterObj : rosterMap.entrySet()) {
            String rosterId = rosterObj.getKey();
            MatchIncluded roster = rosterMap.get(rosterId);

            com.ntrophy.domain.match.MatchRoster matchRoster = com.ntrophy.domain.match.MatchRoster.builder()
                    .id(rosterId)
                    .rank(roster.getAttributes().getStats().getRank())
                    .teamId(roster.getAttributes().getStats().getTeamId())
                    .won(roster.getAttributes().isWon())
                    .shardId(roster.getAttributes().getShardId())
                    .participants(getMatchParticipantList(participantsMap, roster))
                    .build();
            matchRosterList.add(matchRoster);
        }

        return matchRosterList;
    }

    private List<MatchParticipant> getMatchParticipantList(Map<String, MatchIncluded> participantsMap, MatchIncluded roster) {
        List<MatchParticipantsData> participantsDataList = roster.getRelationships().getParticipants().getData();
        List<MatchParticipant> participantList = new ArrayList<>();

        for (MatchParticipantsData data : participantsDataList) {
            MatchIncluded participant = participantsMap.get(data.getId());
            MatchStats stats = participant.getAttributes().getStats();
            MatchParticipant matchParticipant = MatchParticipant.builder()
                    .DBNOs(stats.getDBNOs())
                    .assists(stats.getAssists())
                    .boosts(stats.getBoosts())
                    .damageDealt(stats.getDamageDealt())
                    .deathType(stats.getDeathType())
                    .headshotKills(stats.getHeadshotKills())
                    .heals(stats.getHeals())
                    .killPlace(stats.getKillPlace())
                    .killStreaks(stats.getKillStreaks())
                    .kills(stats.getKills())
                    .longestKill(stats.getLongestKill())
                    .name(stats.getName())
                    .playerId(stats.getPlayerId())
                    .revives(stats.getRevives())
                    .rideDistance(stats.getRideDistance())
                    .roadKills(stats.getRoadKills())
                    .swimDistance(stats.getSwimDistance())
                    .teamKills(stats.getTeamKills())
                    .timeSurvived(stats.getTimeSurvived())
                    .vehicleDestroys(stats.getVehicleDestroys())
                    .walkDistance(stats.getWalkDistance())
                    .weaponsAcquired(stats.getWeaponsAcquired())
                    .winPlace(stats.getWinPlace())
                    .build();
            participantList.add(matchParticipant);
        }


        return participantList;
    }

    private MatchAttribute getMatchAttribute(MatchAttributes matchAttributes) {
        return MatchAttribute.builder()
                .matchType(matchAttributes.getMatchType())
                .gameMode(matchAttributes.getGameMode())
                .shardId(matchAttributes.getShardId())
                .createdAt(matchAttributes.getCreatedAt())
                .duration(matchAttributes.getDuration())
                .seasonState(matchAttributes.getSeasonState())
                .titleId(matchAttributes.getTitleId())
                .mapName(matchAttributes.getMapName())
                .build();
    }

    private Map<String, MatchIncluded> getRosterMap(MatchResponseDto source) {
        Map<String, MatchIncluded> matchMap = new HashMap<>();
        for (MatchRoster rawRoster : source.getData().getRelationships().getRosters().getData()) {
            MatchIncluded matchIncluded = source.getIncluded().stream()
                    .filter(i -> i.getId().equals(rawRoster.getId()))
                    .findFirst()
                    .orElse(null);
            if (matchIncluded != null) {
                matchMap.put(rawRoster.getId(), matchIncluded);
            } else {
                log.warn("No Match Included Found For roster ID: {}", rawRoster.getId());
            }
        }

        return matchMap;
    }
    private Map<String, MatchIncluded> getParticipantsMap(MatchResponseDto source) {
        Map<String, MatchIncluded> matchParticipantsMap = new HashMap<>();
        for (MatchIncluded matchIncluded : source.getIncluded()) {
            if (matchIncluded.getType().equals("participant")) {
                matchParticipantsMap.put(matchIncluded.getId(), matchIncluded);
            }
        }
        return matchParticipantsMap;
    }
}
