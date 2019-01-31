package com.tim11.demo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class Metadata {
	private DataSource dataSource;
	private Connection connection;
	JSONArray tables = new JSONArray();
    JSONArray procedures = new JSONArray();
    JSONArray triggers = new JSONArray();
	public Metadata(DataSource ds) {
		try {
			dataSource = ds;
			connection = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Ispis svih baza
	public void printSeme(DatabaseMetaData md) {
		ResultSet rsSema = null;
		try {
		rsSema = md.getCatalogs();
		System.out.println("Spisak kataloga (Baza)");
		while (rsSema.next()) {
		System.out.println(" " + rsSema.getString(1));
		}
		} catch (SQLException e) {
		System.out.println("Greska print - " + e.getMessage());
		}
	}
	
	public void printKolone(DatabaseMetaData md, String imeTabele) {
		try {
		ResultSet kolone = md.getColumns("ETFLAB", "%", imeTabele, "%");
		while (kolone.next()) {
		String columnName = kolone.getString("COLUMN_NAME");
		String datatype = kolone.getString("TYPE_NAME");
		int datasize = kolone.getInt("COLUMN_SIZE");
		int digits = kolone.getInt("DECIMAL_DIGITS");
		int nullable = kolone.getInt("NULLABLE");
		boolean isNull = (nullable == 1);
		String nul;
		if (isNull) nul = "NULL";
		else nul = "NOT NULL";
		System.out.println(" " + columnName + " "+ datatype
		+ "(" + datasize + "," + digits + ") "
		+ nul);
		}
		kolone.close();
		} catch (SQLException e) {
		System.out.println("Greska print - "+e.getMessage());
		}
	}
	
	public void printPrimaryKey(DatabaseMetaData md, String imeTabele) {
		try {
		ResultSet primarniKljucevi = md.getPrimaryKeys("Test2", "%",
		imeTabele);
		System.out.println(" Tabela - primarni ključ: " + imeTabele);
		while (primarniKljucevi.next()) {
		System.out.println(" "
		+ primarniKljucevi.getInt("KEY_SEQ") + " "
		+ primarniKljucevi.getString("COLUMN_NAME"));
		}
		primarniKljucevi.close();
		} catch (SQLException e) {
		System.out.println("Greska print - "+e.getMessage());
		}
	}
	
	public void printIndex(DatabaseMetaData md, String imeTabele) {
		try {
		ResultSet indeksi = md.getIndexInfo(null, "BP11", imeTabele,
		false, true);
		System.out.println(" Tabela - ostali ključevi: " + imeTabele);
		while (indeksi != null && indeksi.next()) {
		String unique = indeksi.getString("NON_UNIQUE");
		String name = indeksi.getString("INDEX_NAME");
		String type = indeksi.getString("TYPE");
		int position = indeksi.getInt("ORDINAL_POSITION");
		String column = indeksi.getString("COLUMN_NAME");
		String asc = indeksi.getString("ASC_OR_DESC");
		System.out.println(" " + unique + " " + name + " " + type + " "
		+ position + " " + column + " " + asc);
		}
		indeksi.close();
		} catch (SQLException e) {
			System.out.println("Ovdje je greska");
		System.out.println("Greska print - "+e.getMessage());
		}
	}
	
	public void printResultSetProperty(ResultSet rs) {
		try {
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		System.out.println("Broj kolona u RS: "+colCount);
		for (int i=1; i<= colCount; i++) {
		System.out.println(i + ". " + rsmd.getColumnClassName(i)
		+ " " + rsmd.getColumnName(i) + " "
		+ rsmd.getColumnType(i) + " "
		+ rsmd.getColumnTypeName(i) + " "
		+ rsmd.getPrecision(i));
		}
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
	}
	
	public void printTabele(DatabaseMetaData md, String imeTabele) {
		printKolone(md, imeTabele);
		printPrimaryKey(md, imeTabele);
		printIndex(md, imeTabele);
	}
	
	public void printView(DatabaseMetaData md, String imeTabele) {
		printKolone(md, imeTabele);
	}
	
	public void printKoloneProcedura(DatabaseMetaData md, String baza,
			String procedura) {
			try {
			ResultSet rsKolone = md.getProcedureColumns("%", null, procedura,
			"%");
			printResultSetProperty(rsKolone);
			while (rsKolone.next()) {
			String ime = rsKolone.getString("PROCEDURE_NAME");
			String remarks = rsKolone.getString("REMARKS");
			System.out.print(" "+ime + " " + remarks);
			printKoloneProcedura(md, baza, ime);
			}
			} catch (SQLException e) {
			System.out.println("Greska print - "+e.getMessage());
			}
		}
	
	public void printProcedure(DatabaseMetaData md, String baza) {
		try {
		ResultSet rsProcedure = md.getProcedures(baza, null, "%");
		while (rsProcedure.next()) {
		String ime = rsProcedure.getString("PROCEDURE_NAME");
		String remarks = rsProcedure.getString("REMARKS");
		System.out.print(" "+ime + " " + remarks);
		printKoloneProcedura(md, baza, ime);
		}
		rsProcedure.close();
		} catch (SQLException e) {
		System.out.println("Greska print - "+e.getMessage());
		}
	}
	
	
	//Ispis podataka jedne baze
	public void printSema(DatabaseMetaData md, String baza) {
		ResultSet rsSema = null;
		String[] tipoviObjekata = {"TABLE", "VIEW"};
		try {
		rsSema = md.getTables(baza, "BP11", "%", tipoviObjekata);
		System.out.println("Spisak kataloga (Baza)");
		while (rsSema.next()) {
		String imeBaze = rsSema.getString(1);
		String imeTabela = rsSema.getString(3);
		String tipTabele = rsSema.getString(4);
		System.out.println("***************");
		System.out.println("Tabela ("+tipTabele+ "): " + imeBaze
		+ '.' + imeTabela);
		System.out.println("****************");
		if (tipTabele.equalsIgnoreCase("TABLE"))
		printTabele(md, imeTabela);
		else if (tipTabele.equalsIgnoreCase("VIEW"))
		printView(md, imeTabela) ;
		else System.out.println("Ostali tipovi objekata");
		}
		rsSema.close();
		printProcedure(md, baza);
		} catch (SQLException e) {
		System.out.println("Greska print - "+e.getMessage());
		}
	}
	
	//Ispis podataka naše baze
	public void IspisiMetapodatke(DataSource dataSource) {
		try {
			//ovdje ispisuje da je null datasource
			System.out.println(dataSource);
			Connection c = dataSource.getConnection();
			DatabaseMetaData md = null;
			md = c.getMetaData();
			System.out.println("getDatabaseProductName: "
					+ md.getDatabaseProductName());
			System.out.println();
			printSeme(md);
			printSema(md, "ETFLAB");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet GetTableData(String nazivTabele) {
		try {
    		connection = dataSource.getConnection();
    		Statement statement = connection.createStatement();
    		StringBuilder sb = new StringBuilder();
    		
    		sb.append("SELECT * FROM ")
    			.append(nazivTabele);
    	
    		return statement.executeQuery(sb.toString());
    		
    	} catch (SQLException e) {
            e.printStackTrace();
    	}
	 return null;
	}
	
	
	public JSONArray getTriggers() throws JSONException {
		 try {
			 connection = dataSource.getConnection();
	    		Statement statement = connection.createStatement();
	    		StringBuilder sb = new StringBuilder();
	    		
	    		sb.append("SELECT DISTINCT name  FROM user_source WHERE TYPE = 'TRIGGER' and name = 'ATRIG'");
	    	
	    		ResultSet rs =  statement.executeQuery(sb.toString());
	    		//ovdje treba while za sve triggere
	    		if(rs.next()) {
	    			Statement stmt = connection.createStatement();
	    			StringBuilder sb2 = new StringBuilder();
		    		
		    		sb2.append("SELECT text FROM user_source WHERE TYPE = 'TRIGGER' and name ='").append(rs.getString("NAME")).append("' ORDER BY line asc");
		    		ResultSet rs2 =  stmt.executeQuery(sb2.toString());
		    		StringBuilder trigger = new StringBuilder();
		    		JSONObject obj = new JSONObject();
	                //obj.put("PROCEDURE_NAME".toLowerCase(), data.getString("PROCEDURE_NAME"));
	                //obj.put("REMARKS".toLowerCase(), data.getString("REMARKS"));
	                procedures.put(obj);
		    		while (rs2.next())  {
		    			trigger.append(rs2.getString("TEXT"));
		    		}
		    		obj.put("TRIGGER_NAME", rs.getString("NAME"));
		    		obj.put("TEXT",trigger.toString());
		    		triggers.put(obj);
		    			
	    		}
			 
		 } catch (SQLException e) {
        e.printStackTrace();
	}
		 return triggers;
	}
	
	public JSONArray getProcedures() throws JSONException {
        try {

            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet data = metadata.getProcedures("BP11", "BP11", "%");
            
            while(data.next()) {
            	JSONObject obj = new JSONObject();
                obj.put("PROCEDURE_NAME".toLowerCase(), data.getString("PROCEDURE_NAME"));
                obj.put("REMARKS".toLowerCase(), data.getString("REMARKS"));
                procedures.put(obj);
            }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return procedures;
    }
	
	public JSONArray getTables() throws JSONException {
        try {

        	tables = new JSONArray();
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet data = metadata.getTables("BP11", "BP11", "%", new String[]{"TABLE", "VIEW"});
          
            while(data.next()) {
            	JSONObject obj = new JSONObject();
                obj.put("table_name", data.getString("TABLE_NAME"));
                obj.put("columns", getColumnsForTable( data.getString("TABLE_NAME")));
                obj.put("foreign_keys", getForeignKeysForTable( data.getString("TABLE_NAME")));
                obj.put("primary_key", getPrimaryKeyForTable( data.getString("TABLE_NAME")));
                obj.put("indexes", getIndexesForTable( data.getString("TABLE_NAME")));
                tables.put(obj);
            }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return tables;
    }
	
	public JSONArray getColumnsForTable(String tableName) throws JSONException {
    	try {

            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet data = metadata.getColumns("BP11", "BP11", tableName, "%");
            JSONArray columns = new JSONArray();
            
            while(data.next()) {
            	JSONObject obj = new JSONObject();
                obj.put("COLUMN_NAME".toLowerCase(), data.getString("COLUMN_NAME"));
                obj.put("TYPE_NAME".toLowerCase(), data.getString("TYPE_NAME"));
                obj.put("COLUMN_SIZE".toLowerCase(), data.getString("COLUMN_SIZE"));
                obj.put("NULLABLE".toLowerCase(), data.getString("NULLABLE"));
                columns.put(obj);
            }
            
            return columns;
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return new JSONArray();
    }
    
    public JSONArray getIndexesForTable(String tableName) throws JSONException {
    	try {

            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet data = metadata.getIndexInfo("BP11", "BP11", tableName, false, true);
            JSONArray columns = new JSONArray();
            
            while(data.next()) {
            	JSONObject obj = new JSONObject();
                obj.put("NON_UNIQUE".toLowerCase(), data.getString("NON_UNIQUE"));
                obj.put("INDEX_NAME".toLowerCase(), data.getString("INDEX_NAME"));
                obj.put("TYPE".toLowerCase(), data.getString("TYPE"));
                obj.put("COLUMN_NAME".toLowerCase(), data.getString("COLUMN_NAME"));
                columns.put(obj);
            }
          
            return columns;
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return new JSONArray();
    }
    
    public JSONArray getForeignKeysForTable(String tableName) throws JSONException {
    	try {

            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet data = metadata.getImportedKeys("BP11", "BP11", tableName);
            JSONArray columns = new JSONArray();
            
            while(data.next()) {
            	JSONObject obj = new JSONObject();
                obj.put("PKTABLE_NAME".toLowerCase(), data.getString("PKTABLE_NAME"));
                obj.put("FKTABLE_NAME".toLowerCase(), data.getString("FKTABLE_NAME"));
                obj.put("PKCOLUMN_NAME".toLowerCase(), data.getString("PKCOLUMN_NAME"));
                obj.put("FKCOLUMN_NAME".toLowerCase(), data.getString("FKCOLUMN_NAME"));
                obj.put("KEY_SEQ".toLowerCase(), data.getString("KEY_SEQ"));
                obj.put("FK_NAME".toLowerCase(), data.getString("FK_NAME"));
                obj.put("PK_NAME".toLowerCase(), data.getString("PK_NAME"));
                columns.put(obj);
            }
            return columns;
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return new JSONArray();
    }
    
    public JSONObject getPrimaryKeyForTable(String tableName) throws JSONException {
    	try {
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet data = metadata.getPrimaryKeys("BP11", "BP11", tableName);
            
            JSONObject obj = new JSONObject();
            
            while(data.next()) {
                obj.put("COLUMN_NAME".toLowerCase(), data.getString("COLUMN_NAME"));
                obj.put("KEY_SEQ".toLowerCase(), data.getString("KEY_SEQ"));
                obj.put("PK_NAME".toLowerCase(), data.getString("PK_NAME"));
            
           }
          
            return obj;
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return new JSONObject();
    }
	
}
