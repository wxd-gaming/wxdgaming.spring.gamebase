package wxdgaming.spring.gamebase.background.module.server;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.collection.concurrent.ConcurrentTable;
import wxdgaming.spring.boot.core.io.Objects;
import wxdgaming.spring.gamebase.background.entity.bean.ServerInfo;
import wxdgaming.spring.gamebase.background.entity.store.ServerInfoRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 区服信息服务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-14 13:55
 **/
@Getter
@Service()
public class ServerInfoService {

    @Autowired private ServerInfoRepository serverInfoRepository;
    private final ConcurrentTable<Integer, String, Map<Integer, ServerInfo>> serverInfoTable = new ConcurrentTable<>();

    @PostConstruct
    public void initialize() {
        List<ServerInfo> all = serverInfoRepository.findAll();
        all.forEach(serverInfo -> push(serverInfo));
    }

    public void push(ServerInfo serverInfo) {
        serverInfoTable.computeIfAbsent(serverInfo.getGameId(), serverInfo.getPlatform(), l -> new ConcurrentSkipListMap<>())
                .put(serverInfo.getSid(), serverInfo);
    }

    public Stream<String> streamPlatforms(int gameId) {
        Map<String, Map<Integer, ServerInfo>> stringMapMap = serverInfoTable.get(gameId);
        if (stringMapMap == null)
            return Stream.of();
        return stringMapMap.keySet().stream();
    }

    public Collection<String> listPlatforms(int gameId) {
        return streamPlatforms(gameId).toList();
    }

    public Stream<ServerInfo> streamAll(int gameId) {
        Map<String, Map<Integer, ServerInfo>> stringMapMap = serverInfoTable.get(gameId);
        if (stringMapMap == null)
            return Stream.of();
        return stringMapMap.values().stream().flatMap(map -> map.values().stream());
    }

    public Collection<ServerInfo> list(int gameId) {
        return streamAll(gameId)
                .sorted((o1, o2) -> {
                    if (Objects.equals(o1.getPlatform(), o2.getPlatform())) {
                        return o1.getPlatform().compareTo(o2.getPlatform());
                    }
                    return Integer.compare(o1.getSid(), o2.getSid());
                })
                .toList();
    }

    public Map<String, List<ServerInfo>> groupedByPlatform(int gameId) {
        return streamAll(gameId)
                .sorted((o1, o2) -> {
                    if (Objects.equals(o1.getPlatform(), o2.getPlatform())) {
                        return o1.getPlatform().compareTo(o2.getPlatform());
                    }
                    return Integer.compare(o1.getSid(), o2.getSid());
                })
                .collect(Collectors.groupingBy(ServerInfo::getPlatform));
    }

    public Stream<ServerInfo> streamAll(int gameId, String platform) {
        Map<String, Map<Integer, ServerInfo>> stringMapMap = serverInfoTable.get(gameId);
        if (stringMapMap == null)
            return Stream.of();
        Map<Integer, ServerInfo> integerServerInfoMap = stringMapMap.get(platform);
        if (integerServerInfoMap == null)
            return Stream.of();
        return integerServerInfoMap.values().stream();
    }

    public Collection<ServerInfo> list(int gameId, String platform) {
        return streamAll(gameId, platform).sorted(Comparator.comparingInt(ServerInfo::getSid)).toList();
    }

    public ServerInfo get(int gameId, String platform, int sid) {
        return serverInfoTable
                .opt(gameId, platform)
                .map(v -> v.get(sid))
                .orElse(null);
    }

}
