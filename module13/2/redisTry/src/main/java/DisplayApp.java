import org.redisson.Redisson;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class DisplayApp {
    private static final Duration SHOW_TIME = Duration.ofSeconds(1);
    private static RedisWorker rw;

    public static void main(String[] args) throws InterruptedException {
        rw = new RedisWorker();
        rw.init();

        while (true) {
            if (rw.getSizeQue() > 0) {
                Integer user = rw.showAndRotateUser();
                String log = String.format("- На главной странице показываем пользователя: %d", user);
                out.println(log);
                TimeUnit.SECONDS.sleep(SHOW_TIME.toMillis());
            }
        }
    }

    private static class RedisWorker {
        private final static String KEY = "ONLINE_USERS";
        private RedissonClient redisson;
        private RDeque<Integer> onlineUsers;

        private void init() {
            Config config = new Config();
            config.useSingleServer().setAddress("redis://127.0.0.1:6379");
            try {
                redisson = Redisson.create(config);
            } catch (RedisConnectionException Exc) {
                out.println("Не удалось подключиться к Redis");
                out.println(Exc.getMessage());
            }
            onlineUsers = redisson.getDeque(KEY);
        }

        private Integer showAndRotateUser() {
            Integer user = onlineUsers.poll();
            onlineUsers.add(user);
            return user;
        }

        private int getSizeQue() {
            return onlineUsers.size();
        }
    }
}