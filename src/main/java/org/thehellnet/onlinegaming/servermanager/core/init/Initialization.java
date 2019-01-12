package org.thehellnet.onlinegaming.servermanager.core.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.AppUser;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.Game;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.GameGametype;
import org.thehellnet.onlinegaming.servermanager.core.model.persistence.Gametype;
import org.thehellnet.onlinegaming.servermanager.core.repository.AppUserRepository;
import org.thehellnet.onlinegaming.servermanager.core.repository.GameGametypeRepository;
import org.thehellnet.onlinegaming.servermanager.core.repository.GameRepository;
import org.thehellnet.onlinegaming.servermanager.core.repository.GametypeRepository;
import org.thehellnet.utility.PasswordUtility;

import java.util.HashMap;
import java.util.Map;

@Component
@Transactional
public class Initialization {

    private static final String GAMETYPE_DEATHMATCH = "Deathmatch";
    private static final String GAMETYPE_TEAM_DEATHMATCH = "Team Deathmatch";
    private static final String GAMETYPE_HEADQUARTERS = "Headquarters";
    private static final String GAMETYPE_SEARCH_AND_DESTROY = "Search and Destroy";
    private static final String GAMETYPE_BEHIND_ENEMY_LINES = "Behind enemy lines";
    private static final String GAMETYPE_RETRIEVAL = "Retrieval";
    private static final String GAMETYPE_CAPTURE_THE_FLAG = "Capture the flag";
    private static final String GAMETYPE_DOMINATION = "Domination";
    private static final String GAMETYPE_SABOTAGE = "Sabotage";
    private static final String GAMETYPE_BOMB_MODE = "Bomb mode";
    private static final String GAMETYPE_TEAM_SURVIVOR = "Team survivor";
    private static final String GAMETYPE_FOLLOW_THE_LEADER = "Follow the leader";
    private static final String GAMETYPE_CAPTURE_AND_HOLD = "Capture and hold";
    private static final String GAMETYPE_JUMP_TRAINING = "Jump training";
    private static final String GAMETYPE_FREEZE_TAG = "Freeze tag";
    private static final String GAMETYPE_LAST_MAN_STANDING = "Last man standing";
    private static final String GAMETYPE_TOTAL_WAR = "Total war";
    private static final String GAMETYPE_TOURNAMENT = "Tournament";
    private static final String GAMETYPE_SINGLE_PLAYER = "Single player";
    private static final String GAMETYPE_ONE_FLAG_CTF = "One flag CTF";
    private static final String GAMETYPE_OVERLOAD = "Overload";
    private static final String GAMETYPE_HARVESTER = "Harvester";

    private static final Logger logger = LoggerFactory.getLogger(Initialization.class);

    private boolean alreadyRun = false;

    private final GameRepository gameRepository;
    private final GametypeRepository gametypeRepository;
    private final GameGametypeRepository gameGametypeRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public Initialization(GameRepository gameRepository, GametypeRepository gametypeRepository, GameGametypeRepository gameGametypeRepository, AppUserRepository appUserRepository) {
        this.gameRepository = gameRepository;
        this.gametypeRepository = gametypeRepository;
        this.gameGametypeRepository = gameGametypeRepository;
        this.appUserRepository = appUserRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshed() {
        if (alreadyRun) {
            return;
        }

        alreadyRun = true;

        logger.info("Initializing database data");

        checkGames();
        checkGametypes();
        checkGameGametypes();

        checkUsers();

        logger.info("Database data initialization complete");
    }

    private void checkGames() {
        logger.debug("Checking games");

        Map<String, String> gameMap = new HashMap<>();
        gameMap.put("q3a", "Quake III Arena");
        gameMap.put("q3ut4", "Urban Terror");
        gameMap.put("cod", "Call of Duty");
        gameMap.put("cod2", "Call of Duty 2");
        gameMap.put("cod4", "Call of Duty 4: Modern Warfare");
        gameMap.put("codwaw", "Call of Duty: World at War");

        for (String tag : gameMap.keySet()) {
            Game game = gameRepository.findByTag(tag);
            if (game == null) {
                game = new Game(tag, gameMap.get(tag));
                gameRepository.save(game);
            }
        }
    }

    private void checkGametypes() {
        logger.debug("Checking gametypes");

        String[] gametypeNames = {
                GAMETYPE_DEATHMATCH,
                GAMETYPE_TEAM_DEATHMATCH,
                GAMETYPE_HEADQUARTERS,
                GAMETYPE_SEARCH_AND_DESTROY,
                GAMETYPE_BEHIND_ENEMY_LINES,
                GAMETYPE_RETRIEVAL,
                GAMETYPE_CAPTURE_THE_FLAG,
                GAMETYPE_DOMINATION,
                GAMETYPE_SABOTAGE,
                GAMETYPE_BOMB_MODE,
                GAMETYPE_TEAM_SURVIVOR,
                GAMETYPE_FOLLOW_THE_LEADER,
                GAMETYPE_CAPTURE_AND_HOLD,
                GAMETYPE_JUMP_TRAINING,
                GAMETYPE_FREEZE_TAG,
                GAMETYPE_LAST_MAN_STANDING,
                GAMETYPE_TOTAL_WAR,
                GAMETYPE_TOURNAMENT,
                GAMETYPE_SINGLE_PLAYER,
                GAMETYPE_ONE_FLAG_CTF,
                GAMETYPE_OVERLOAD,
                GAMETYPE_HARVESTER
        };

        for (String gametypeName : gametypeNames) {
            Gametype gametype = gametypeRepository.findByName(gametypeName);
            if (gametype == null) {
                gametype = new Gametype(gametypeName);
                gametypeRepository.save(gametype);
            }
        }
    }

    private void checkGameGametypes() {
        logger.debug("Checking game gametypes");

        Map<String, String> gametypeTags = new HashMap<>();

        gametypeTags.clear();
        gametypeTags.put(GAMETYPE_DEATHMATCH, "0");
        gametypeTags.put(GAMETYPE_TOURNAMENT, "1");
        gametypeTags.put(GAMETYPE_SINGLE_PLAYER, "2");
        gametypeTags.put(GAMETYPE_TEAM_DEATHMATCH, "3");
        gametypeTags.put(GAMETYPE_CAPTURE_THE_FLAG, "4");
        gametypeTags.put(GAMETYPE_ONE_FLAG_CTF, "5");
        gametypeTags.put(GAMETYPE_OVERLOAD, "6");
        gametypeTags.put(GAMETYPE_HARVESTER, "7");
        persistGameGametypes(gametypeTags, "q3a");

        gametypeTags.clear();
        gametypeTags.put(GAMETYPE_DEATHMATCH, "0");
        gametypeTags.put(GAMETYPE_TEAM_DEATHMATCH, "3");
        gametypeTags.put(GAMETYPE_TEAM_SURVIVOR, "4");
        gametypeTags.put(GAMETYPE_FOLLOW_THE_LEADER, "5");
        gametypeTags.put(GAMETYPE_CAPTURE_AND_HOLD, "6");
        gametypeTags.put(GAMETYPE_CAPTURE_THE_FLAG, "7");
        gametypeTags.put(GAMETYPE_BOMB_MODE, "8");
        gametypeTags.put(GAMETYPE_JUMP_TRAINING, "9");
        gametypeTags.put(GAMETYPE_FREEZE_TAG, "10");
        persistGameGametypes(gametypeTags, "q3ut4");

        gametypeTags.clear();
        gametypeTags.put(GAMETYPE_DEATHMATCH, "dm");
        gametypeTags.put(GAMETYPE_TEAM_DEATHMATCH, "tdm");
        gametypeTags.put(GAMETYPE_HEADQUARTERS, "hq");
        gametypeTags.put(GAMETYPE_SEARCH_AND_DESTROY, "sd");
        gametypeTags.put(GAMETYPE_BEHIND_ENEMY_LINES, "bel");
        gametypeTags.put(GAMETYPE_RETRIEVAL, "ret");
        persistGameGametypes(gametypeTags, "cod");

        gametypeTags.clear();
        gametypeTags.put(GAMETYPE_DEATHMATCH, "dm");
        gametypeTags.put(GAMETYPE_TEAM_DEATHMATCH, "tdm");
        gametypeTags.put(GAMETYPE_HEADQUARTERS, "hq");
        gametypeTags.put(GAMETYPE_SEARCH_AND_DESTROY, "sd");
        gametypeTags.put(GAMETYPE_CAPTURE_THE_FLAG, "ctf");
        persistGameGametypes(gametypeTags, "cod2");

        gametypeTags.clear();
        gametypeTags.put(GAMETYPE_DEATHMATCH, "dm");
        gametypeTags.put(GAMETYPE_TEAM_DEATHMATCH, "war");
        gametypeTags.put(GAMETYPE_HEADQUARTERS, "koth");
        gametypeTags.put(GAMETYPE_SEARCH_AND_DESTROY, "sd");
        gametypeTags.put(GAMETYPE_DOMINATION, "dom");
        gametypeTags.put(GAMETYPE_SABOTAGE, "sab");
        persistGameGametypes(gametypeTags, "cod4");

        gametypeTags.clear();
        gametypeTags.put(GAMETYPE_DEATHMATCH, "dm");
        gametypeTags.put(GAMETYPE_TEAM_DEATHMATCH, "tdm");
        gametypeTags.put(GAMETYPE_HEADQUARTERS, "koth");
        gametypeTags.put(GAMETYPE_SEARCH_AND_DESTROY, "sd");
        gametypeTags.put(GAMETYPE_DOMINATION, "dom");
        gametypeTags.put(GAMETYPE_SABOTAGE, "sab");
        gametypeTags.put(GAMETYPE_CAPTURE_THE_FLAG, "ctf");
        gametypeTags.put(GAMETYPE_TOTAL_WAR, "twar");
        persistGameGametypes(gametypeTags, "codwaw");
    }

    private void checkUsers() {
        logger.debug("Checking users");

        Map<String, String> userMap = new HashMap<>();
        userMap.put("admin", "admin");

        for (String username : userMap.keySet()) {
            AppUser appUser = appUserRepository.findByEmail(username);
            if (appUser == null) {
                String password = userMap.get(username);
                String hashedPassword = PasswordUtility.hash(password);
                appUser = new AppUser(username, hashedPassword);
                appUserRepository.save(appUser);
            }
        }
    }

    private void persistGameGametypes(Map<String, String> gametypeTags, String gameTag) {
        Game game = gameRepository.findByTag(gameTag);

        for (String gametypeTag : gametypeTags.keySet()) {
            Gametype gametype = gametypeRepository.findByName(gametypeTag);
            if (gameGametypeRepository.findByGameAndGametype(game, gametype) == null) {
                gameGametypeRepository.save(new GameGametype(game, gametype, gametypeTags.get(gametypeTag)));
            }
        }
    }
}
