package wxdgaming.spring.gamebase.background.module.server;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxdgaming.spring.boot.core.io.Objects;
import wxdgaming.spring.gamebase.background.entity.bean.Account;
import wxdgaming.spring.gamebase.background.entity.bean.ServerInfo;
import wxdgaming.spring.gamebase.background.entity.store.ServerInfoRepository;

import java.util.*;
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

    @PostConstruct
    public void initialize() {
    }

    public Stream<ServerInfo> streamAll(int gameId) {
        return serverInfoRepository.findAllByGameId(gameId).stream();
    }

    public Stream<String> streamPlatforms(Account loginAccount, int gameId) {
        if (!loginAccount.isRoot() && !loginAccount.getGames().contains(gameId)) {
            return null;
        }

        Stream<ServerInfo> stringStream = streamAll(gameId);
        if (!loginAccount.isRoot()) {
            stringStream = stringStream.filter(gameInfo -> loginAccount.getPlatforms().contains(gameInfo.getPlatform()));
        }
        return stringStream.map(ServerInfo::getPlatform);
    }

    public Optional<Collection<String>> listPlatforms(Account account, int gameId) {
        return Optional.ofNullable(streamPlatforms(account, gameId)).map(Stream::toList);
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
        return serverInfoRepository.findAllByGP(gameId, platform).stream();
    }

    public Collection<ServerInfo> list(int gameId, String platform) {
        return streamAll(gameId, platform).sorted(Comparator.comparingInt(ServerInfo::getSid)).toList();
    }

    public ServerInfo get(int gameId, String platform, int sid) {
        return serverInfoRepository.findOneGPS(gameId, platform, sid).orElse(null);
    }

}
