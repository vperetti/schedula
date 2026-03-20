package br.com.perettis.schedula;


/**
 *
 * @author Vinicius Peretti
 */
public class SchedulaConfig {

    private Integer id;
    private String databaseConection;
    private String databaseDialect;
    private String databaseDriver;
    private String databaseUsername;
    private String databasePassword;

    public String getDatabaseConection() {
        return databaseConection;
    }

    public void setDatabaseConection(String databaseConection) {
        this.databaseConection = databaseConection;
    }

    public String getDatabaseDialect() {
        return databaseDialect;
    }

    public void setDatabaseDialect(String databaseDialect) {
        this.databaseDialect = databaseDialect;
    }

    public String getDatabaseDriver() {
        return databaseDriver;
    }

    public void setDatabaseDriver(String databaseDriver) {
        this.databaseDriver = databaseDriver;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public

    Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
}
