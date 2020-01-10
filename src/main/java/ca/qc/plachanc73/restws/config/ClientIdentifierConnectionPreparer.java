package ca.qc.plachanc73.restws.config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.aspectj.lang.annotation.AfterReturning;

// TODO Activate this annotation to get an Oracle Database connection to set identifier
// @Component
// @Aspect
public class ClientIdentifierConnectionPreparer {

	private static boolean updateClientIdentifierActive = true;

	private static final String SQL_SET_IDENTIFIER = "{ call DBMS_SESSION.SET_IDENTIFIER(?) }";

	@AfterReturning(pointcut = "execution(java.sql.Connection javax.sql.DataSource.getConnection(..))", returning = "connection")
	public Connection interceptGetConnection(Connection connection) throws SQLException {
		if (isUpdateClientIdentifierActive()) {
			setClientIdentifier(connection);
		}
		return connection;
	}

	public Connection setClientIdentifier(Connection connection) throws SQLException {
		CallableStatement callableStatement = connection.prepareCall(SQL_SET_IDENTIFIER);
		callableStatement.setString(1, ThreadUserManager.getClientId());
		callableStatement.execute();
		callableStatement.close();
		return connection;
	}

	public static boolean isUpdateClientIdentifierActive() {
		return updateClientIdentifierActive;
	}

	public static void setUpdateClientIdentifierActive(boolean updateClientIdentifierActive) {
		ClientIdentifierConnectionPreparer.updateClientIdentifierActive = updateClientIdentifierActive;
	}
}