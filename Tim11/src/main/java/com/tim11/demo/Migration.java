package com.tim11.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class Migration {
	private static final String DATABASE = "migration";
	private static final String URL = "jdbc:mysql://localhost:3306/migration?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    JSONArray tables = new JSONArray();
    JSONArray triggers = new JSONArray();
    private Metadata metadata;
    Connection connection;
    public Migration(DataSource dataSource) {
    	metadata = new Metadata(dataSource);
    }
    
    public void CreateDatabase() throws JSONException {
    	try {
    		System.out.println("here");
    		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    		System.out.println("here");
    		connection.setAutoCommit(false);
    		tables = metadata.getTables();
    		triggers = metadata.getTriggers();
    		System.out.println("here2");
    		if(!tablesExist(connection)) {
    			
    			CreateTables(connection);
    			System.out.println("here3");
        		CreatePrimaryKeys(connection);
        		System.out.println("here4");
        		FillData(connection);
        		System.out.println("here5");
        		CreateForeignKeys(connection);
        		System.out.println("here6");
        		CreateIndexes(connection);
        		System.out.println("here7");
        		CreateTriggers(connection);
        		//System.out.println(metadata.getTriggers());
        		connection.commit();
    		}
    	
    	} catch (SQLException e) {
    		try {
    			connection.rollback();
    			System.out.println("Rollback...");
    			System.out.println(e.getMessage());
    		} catch(SQLException n) {
    			n.printStackTrace();
    		}
        }
    }
    
    public boolean tablesExist(Connection connection) throws SQLException {
        
		DatabaseMetaData metadata = connection.getMetaData();
        ResultSet data = metadata.getTables(DATABASE, DATABASE, "%", new String[]{"TABLE"});
       
       	return data.next();
}

public void FillData(Connection connection) throws JSONException, SQLException {
	
	for(int i = 0; i < tables.length(); i++) {
			
			JSONObject table = tables.getJSONObject(i);
    		
			StringBuilder sb = new StringBuilder("INSERT INTO ");
    		sb.append(table.getString("table_name"))
    			.append(" VALUES ( ");
    		
    		for(int j = 0; j < table.getJSONArray("columns").length(); j++) {
    			if(j > 0) sb.append(", ");
    			sb.append("?");
    		}
    		
    		sb.append(" )");
    		
    		PreparedStatement statement = connection.prepareStatement(sb.toString());
    		
    		ResultSet data = metadata.GetTableData(table.getString("table_name"));
    		
    		ResultSetMetaData rset = data.getMetaData();
    		
    		while(data.next()) {
    			
    			for(int k = 0; k < rset.getColumnCount(); k++) {
    				
    				Object o = data.getObject(k + 1);
    				
    				if ( o instanceof oracle.sql.TIMESTAMP ) {
    					
    					   oracle.sql.TIMESTAMP tstamp = (oracle.sql.TIMESTAMP)o;
    					   o = tstamp.timestampValue();
    				}
    				statement.setObject(k + 1, o);
    			}
    			
    			statement.executeUpdate();
    		}
		}
}

public void CreateTables(Connection connection) throws JSONException, SQLException {
	
		Statement statement = connection.createStatement();
		
		
    	for(int i = 0; i < tables.length(); i++) {
    	
    		JSONObject table = tables.getJSONObject(i);
    		StringBuilder sb = new StringBuilder();
    		
    		sb.append("CREATE TABLE IF NOT EXISTS ")
    			.append(table.getString("table_name"))
    			.append(" ( ");
    		
    		for(int j = 0; j < table.getJSONArray("columns").length(); j++) {
    			
    			if(j > 0) sb.append(", ");
    			
    			JSONObject column = table.getJSONArray("columns").getJSONObject(j);
    			
    			sb.append(column.getString("column_name"))
    			.append(" ");
    			
    			if(!column.getString("type_name").equals("TIMESTAMP(6)"))
    					sb.append(column.getString("type_name"));
    			else sb.append("TIMESTAMP");
    			
    			if(column.getInt("column_size") != 0) {
    			
    				if(!column.getString("type_name").equals("TIMESTAMP(6)")
    						&& !column.getString("type_name").equals("BLOB")
    						&& !column.getString("type_name").equals("DATE"))
        				sb.append("(")
    					.append(column.getInt("column_size"))
        				.append(")");
    			}
    			
    			sb.append(" ");
    			
    			if(column.getInt("nullable") == 0) {
    				sb.append("NOT NULL");
    			}
    		}
    		
    		sb.append(" )");
    		String edited = sb.toString().replaceAll("VARCHAR2", "VARCHAR")
			        					.replaceAll("NUMBER", "NUMERIC")
			        					.replaceAll("BLOB", "LONGBLOB")
			        					.replaceAll("DATE(7)", "DATE");
    		sb = new StringBuilder(edited);
    		statement.addBatch(sb.toString());
    		
    		System.out.println(sb);
    	}
    	
    	statement.executeBatch();
}

public void CreatePrimaryKeys(Connection connection) throws JSONException, SQLException {
	
		Statement statement = connection.createStatement();
		
		/*ALTER TABLE (TABLE_NAME)
		 * ADD PRIMARY KEY (COLUMN_NAME)
		 */
    	
    	for(int i = 0; i < tables.length(); i++) {
    	
    		JSONObject table = tables.getJSONObject(i);
    		StringBuilder sb = new StringBuilder();
    		
    		sb.append("ALTER TABLE ")
    			.append(table.getString("table_name"))
    			.append(" ADD PRIMARY KEY (")
    			.append(table.getJSONObject("primary_key").getString("column_name"))
    			.append(")");
    	
    		statement.addBatch(sb.toString());
    		System.out.println(sb);
    	}
    	
    	statement.executeBatch();
    
}

public void CreateForeignKeys(Connection connection) throws JSONException, SQLException {
	
		Statement statement = connection.createStatement();
		
		/*ALTER TABLE (TABLE_NAME)
		 * ADD FOREIGN KEY (FKCOLUMN_NAME)
		 * REFERENCES PKTABLE_NAME (PKCOLUMN_NAME)
		 */
               
    	for(int i = 0; i < tables.length(); i++) {
    	
    		JSONObject table = tables.getJSONObject(i);
    	
    		for(int j = 0; j < table.getJSONArray("foreign_keys").length(); j++) {
    			
    			StringBuilder sb = new StringBuilder();
    			
    			JSONObject fk = table.getJSONArray("foreign_keys").getJSONObject(j);
    			
    			sb.append("ALTER TABLE ")
    			.append(table.getString("table_name"))
    			.append(" ADD FOREIGN KEY (")
    			.append(fk.getString("fkcolumn_name"))
    			.append(") REFERENCES ")
    			.append(fk.getString("pktable_name"))
    			.append(" (")
    			.append(fk.getString("pkcolumn_name"))
    			.append(")");
    			
    			statement.addBatch(sb.toString());
        		System.out.println(sb);
    		}
    	}
    	
    	statement.executeBatch();
    
}
public void CreateTriggers(Connection connection) throws JSONException, SQLException {
	Statement statement = connection.createStatement();
	for(int i = 0; i < triggers.length(); i++) {
    	
		JSONObject trigger = triggers.getJSONObject(i);
		StringBuilder tijeloT = new StringBuilder();
		tijeloT.append("CREATE ");
		tijeloT.append(trigger.getString("TEXT"));
		String edited = tijeloT.toString().replace("Raise_Application_Error(-", "SIGNAL SQLSTATE '").replace(", 'Not insered');", "' set message_text = 'Not inserted';").replace(":new","new");
				//replaceAll("Raise_Application_Error(-", "SIGNAL SQLSTATE '").replaceAll(", 'Not insered');", "' set message_text = 'Not inserted';");  
		System.out.println(edited);
		statement.addBatch(edited);
	}
	statement.executeBatch();
}

public void CreateIndexes(Connection connection) throws JSONException, SQLException {

		Statement statement = connection.createStatement();
		
		/*CREATE non_unique ? null: UNIQUE INDEX index_name
		* ON table_name (column_name)
		*/
               
    	for(int i = 0; i < tables.length(); i++) {
    	
    		JSONObject table = tables.getJSONObject(i);
    	
    		for(int j = 0; j < table.getJSONArray("indexes").length(); j++) {
    			
    			StringBuilder sb = new StringBuilder();
    			
    			JSONObject index = table.getJSONArray("indexes").getJSONObject(j);
    			
    			if(!index.isNull("index_name")) {
    				
    				sb.append("CREATE ");
        			
        			if(index.getInt("non_unique") == 0) {
        				sb.append(" UNIQUE ");
        			}
        			
        			sb.append(" INDEX ")
        			.append(index.getString("index_name"))
        			.append(" ON ")
        			.append(table.getString("table_name"))
        			.append(" (")
        			.append(index.getString("column_name"))
        			.append(")");
        			
        			statement.addBatch(sb.toString());
            		System.out.println(sb);
    			}
    		}
    	}
    	
    	statement.executeBatch();
   
}
    
}
