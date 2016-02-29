package com.shousper.jsonapi.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This configuration class mostly serves to please Heroku's MySQL service providers.
 * <p/>
 * You need to copy the configuration URL from the provider environment variable to DATABASE_URL.
 * For development this environment variable can serve from where you like. For example:
 *
 * <pre>{@code
 *  DATABASE_URL=mysql://username:password@hostname/databaseName
 * }</pre>
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    public DataSource dataSource() throws URISyntaxException {
        final URI dbUri = new URI(System.getenv("DATABASE_URL"));
        final String[] userInfo = dbUri.getUserInfo().split(":");

        final String username = userInfo[0];
        final String password = userInfo[1];
        final String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath() + "?" + dbUri.getQuery();

        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .build();
    }

}
