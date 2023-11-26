package world.ntdi.storewhore.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import world.ntdi.storewhore.entity.User;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class DataLite {
    private final JavaPlugin m_javaPlugin;
    private final HikariConfig m_config;
    private final HikariDataSource m_dataSource;
    @Getter
    private final ConnectionSource m_connectionSource;

    public DataLite(final JavaPlugin p_javaPlugin, final String p_fileName) throws SQLException {
        m_config = new HikariConfig();
        m_javaPlugin = p_javaPlugin;

        final File dataFolder = p_javaPlugin.getDataFolder();

        loadFiles(dataFolder);

        m_config.setJdbcUrl("jdbc:sqlite:" + dataFolder + File.separator + p_fileName);
        m_dataSource = new HikariDataSource(m_config);
        m_connectionSource = new DataSourceConnectionSource(m_dataSource, m_config.getJdbcUrl());
    }

    private void loadFiles(final File p_dataFolder) {
        if (!p_dataFolder.exists()) {
            p_dataFolder.mkdirs();
        }
    }

    public void close() {
        try {
            m_dataSource.close();
            m_connectionSource.close();
        } catch (Exception p_e) {
            throw new RuntimeException(p_e);
        }
    }
}
