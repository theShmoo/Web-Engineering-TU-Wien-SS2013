/*
 * Copyright 2013 david.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * AbstractTestInitializer
 *
 * @author David
 * @author Fabian
 * @author Matthias
 */
abstract public class AbstractTestInitializer {

    @BeforeClass
    public static void setUpClass() throws NamingException {
        
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
        System.setProperty(Context.URL_PKG_PREFIXES,
                "org.apache.naming");
        InitialContext ic = new InitialContext();

        ic.createSubcontext("java:");
        ic.createSubcontext("java:comp");
        ic.createSubcontext("java:comp/env");
        ic.createSubcontext("java:comp/env/jdbc");

        EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName("lab4");
        // tell Derby to create the database if it does not already exist
        ds.setCreateDatabase("create");

        ic.bind("java:comp/env/jdbc/lab4", ds);
        
    }

    @AfterClass
    public static void tearDownClass() throws NamingException {
        
        InitialContext ic = new InitialContext();
        ic.unbind("java:comp/env/jdbc/lab4");
        
    }
}
