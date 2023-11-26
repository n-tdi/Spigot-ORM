package world.ntdi.storewhore;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.java.JavaPlugin;
import world.ntdi.storewhore.database.DataLite;
import world.ntdi.storewhore.entity.User;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

public final class StoreWhore extends JavaPlugin {
    private DataLite m_dataLite;

    @Override
    public void onEnable() {
        try {
            m_dataLite = new DataLite(this, "users.db");
            TableUtils.createTableIfNotExists(m_dataLite.getConnectionSource(), User.class);

            Dao<User, UUID> userDao = DaoManager.createDao(m_dataLite.getConnectionSource(), User.class);

            final UUID uuid = UUID.fromString("521843cf-718e-45a4-b9cc-994f85564499");

            userDao.createIfNotExists(new User(uuid, 15));

            getLogger().info(userDao.queryForId(uuid).getAge() + "");
        } catch (SQLException p_e) {
            throw new RuntimeException(p_e);
        }
    }

    @Override
    public void onDisable() {
        m_dataLite.close();
    }
}
