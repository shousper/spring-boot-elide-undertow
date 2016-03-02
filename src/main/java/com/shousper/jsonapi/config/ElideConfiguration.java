package com.shousper.jsonapi.config;

import com.yahoo.elide.Elide;
import com.yahoo.elide.audit.AuditLogger;
import com.yahoo.elide.audit.LogMessage;
import com.yahoo.elide.core.DataStore;
import com.yahoo.elide.datastores.hibernate5.HibernateStore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;

@Configuration
public class ElideConfiguration {
    private static final Log LOG = LogFactory.getLog(ElideConfiguration.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    @Named("elide")
    public Elide configureElide() {
        final SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        final DataStore dataStore = new HibernateStore(sessionFactory);

        return new Elide(new AuditLogger() {
            @Override
            public void commit() throws IOException {
                try {
                    for (final LogMessage message : messages.get()) {
                        LOG.info(message.getOperationCode() + " " + message.getMessage());
                    }
                } finally {
                    messages.get().clear();
                }
            }
        }, dataStore);
    }

}
