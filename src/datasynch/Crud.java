/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasynch;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class Crud {
    protected String dbpath;
    protected String tableName;
    protected String tablePk;
    protected String tablePkNumber;
    protected String txtPath;
    protected String[] tableColumns;
    protected String[] tableColumnsType;
    protected int[] selectedColumns;
    protected Connection connection;
    protected Statement statement;
    protected ResultSet myresultSet;
    protected DatabaseMetaData md;
    protected PreparedStatement ps;
    //protected ArrayList<String> tableTypes = new ArrayList<String>();
    
    public void setTxtPath(String dsnPath){
        txtPath = dsnPath;
    }
    
    public String getTxtPath(){
        return txtPath;
    }
    
    public void setPath(String dsnPath){
        dbpath = dsnPath;
    }
    
    public String getTableName(){
        return tableName;
    }
    
    public void setTableName(String tableN){
        tableName = tableN;
    }
    
    public void setTablePk(String tblpk){
        tablePk = tblpk;
    }
    
    public void setTableColumns(String[] columns){
        tableColumns = columns;
    }
    
    public void setSelectedColumns(int[] columns){
        selectedColumns = columns;
    }
    
    public boolean getConncetion(){
        boolean yesItis = false;
        if(null != connection) {
          yesItis = true;    
        }
        return yesItis;
    }
    
    public void openConection(){
        try {
            String dbURL ="jdbc:ucanaccess://" + dbpath + ";memory=true;immediatelyReleaseResources=true;";
            connection = DriverManager.getConnection(dbURL,"","");
            
            md = connection.getMetaData();
            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        } 
    }
    
    public void closeConnection(){
         try {
            if(null != statement) {
                statement.close();
                statement = null;
                //System.out.println("Close Statements");
            }            
            if(null != myresultSet) {
                myresultSet.close();
                myresultSet = null;
                //System.out.println("Close result");
            }            
            if(null != connection) {
                //System.out.println("Close connection");
                connection.close();
                connection = null;
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
    
    public String[] getTablenames(){
        ArrayList<String> tableNames = new ArrayList<>();
        String[] TABLE_TYPES = {"TABLE"};
        String[] nameArr = null;
        try {
            ResultSet rs = md.getTables(null, null, "%", TABLE_TYPES);
            while (rs.next()) {
               tableNames.add(rs.getString(3));
            }
            Collections.sort(tableNames);
            tableNames.add(0,"---");
            nameArr = new String[tableNames.size()];
            nameArr = tableNames.toArray(nameArr);
        }
        catch(SQLException ex) {
            //ex.printStackTrace();
        }
        return nameArr;
    }
    
    
    public String[] getColumnsNames(String tableNameStr){
        ArrayList<String> tableNames = new ArrayList<>();
        ArrayList<String> tableColumnstypes = new ArrayList<>();
        String[] nameArr = null;
        String[] nameArrtype;
        try {
            ResultSet resultSet = this.md.getColumns(null, null, tableNameStr, null);
            while (resultSet.next()) {
               tableNames.add(resultSet.getString("COLUMN_NAME"));
               tableColumnstypes.add(resultSet.getString("TYPE_NAME"));
               //System.out.println(resultSet.getString("TYPE_NAME"));
            }
            nameArr = new String[tableNames.size()];
            tableColumns = new String[tableNames.size()];
            tableColumnsType = new String[tableColumnstypes.size()];
            
            nameArr = tableNames.toArray(nameArr);
            tableColumns = nameArr;
            tableColumnsType = tableColumnstypes.toArray(tableColumnsType);

        }
        catch(SQLException ex) {
            //ex.printStackTrace();
        }
        
        return nameArr;
    }
    
    public ArrayList selection(String idTable){
        String sql;
        String colType;
        String tempColumns;
        int i;
        ArrayList<String[]> listOfLists = new ArrayList<>();

        String[] allresults;
        
        try {
            tempColumns= "";
            
            for (i=0;i<selectedColumns.length;i++){
                colType = tableColumns[selectedColumns[i]];
                tempColumns = tempColumns + tableColumns[selectedColumns[i]];
                if (i != (selectedColumns.length-1)){
                    tempColumns = tempColumns + ",";
                }     
            }
            
            sql = "SELECT " +tempColumns+ " FROM " +tableName;
            
            if (idTable!= null){
                sql = sql + " where " + tablePk + " = " + idTable;
            }
            
            statement = connection.createStatement();
            myresultSet = statement.executeQuery(sql);
            statement.close();
            allresults = new String[selectedColumns.length];
            
            while (myresultSet.next()){
                
                for (i=0;i<selectedColumns.length;i++){
                    if (myresultSet.getObject(i+1) !=  null){
                        allresults[i] = myresultSet.getObject(i+1).toString().trim();
                    }else{
                        allresults[i] = "";
                    }
                }
                
                listOfLists.add(allresults); 
            }
             
        } catch (SQLException ex) {
            listOfLists.clear();
        }
        
        return listOfLists;
    }

    public String insertion(String[] values){
        String sqlStatemens;
        String tempColumns="";
        String tempValues="";
        String insertValues="";
        String colType;
        int i;
        String strResult = null;

            for (i=0;i<selectedColumns.length;i++){
                tempValues = (String) values[selectedColumns[i]];
                tempValues = tempValues.trim();
                colType = tableColumnsType[selectedColumns[i]];
                
                switch (colType) {
                    case "VARCHAR":
                    case "CHAR":
                        tempValues = tempValues.replace("'", "");
                        tempValues = "'"+tempValues+"'";
                        break;
                    case "TIMESTAMP":
                        tempValues = tempValues.replace("'", "");
                        tempValues = "#"+tempValues+"#";
                        break;
                    default:
                        if ("".equals(tempValues.trim()) || tempValues == null){
                            tempValues = "0";
                        }
                        break;
                }
                
                tempColumns = tempColumns + tableColumns[selectedColumns[i]];
                insertValues = insertValues + tempValues.trim();
                
                if (i != (selectedColumns.length-1)){
                    tempColumns = tempColumns + ",";
                    insertValues = insertValues + ",";
                }
            }
            sqlStatemens = "insert into "+tableName+" ( "+tempColumns+") values ("+insertValues+");";
            if (tableName!=null && values != null){
                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(sqlStatemens);
                    strResult = "insert exceuted: " + sqlStatemens;
                    statement.close();
                } catch (SQLException ex) {
                    strResult = "insert not exceuted" + sqlStatemens + " "+ ex.getMessage();
                }
            }   
        return strResult;
    }
    
    public String deletion(String whereCondition){
        
        String sqlStatemens;
        String strResult;
        sqlStatemens = "Delete From "+tableName;
        
        if ( !whereCondition.trim().isEmpty() && whereCondition!=null){
            sqlStatemens = sqlStatemens + " where "+whereCondition;
        }
        
        try {
            ps = connection.prepareStatement(sqlStatemens); 
            ps.execute();
            strResult = "Deleted: " + sqlStatemens;
        } catch (SQLException ex) {
            strResult = "Not Deleted: " + sqlStatemens + " "+ ex.getMessage();
        }
        
        return strResult;
    }
    
    public String update(String[] values, String valueId){
        String sqlStatemens;
        String tempColumns="";
        String tempValues="";
        String updateValues="";
        String colType;
        int i=0;
        String strResult = null;

        sqlStatemens = "update "+tableName+" set ";
            for (i=0;i<selectedColumns.length;i++){
                
                tempValues = (String) values[selectedColumns[i]]; // values[selectedColumns[i]];
                tempValues = tempValues.trim();
                colType = tableColumnsType[selectedColumns[i]];
                
                             
                switch (colType) {
                    case "VARCHAR":
                    case "CHAR":
                        tempValues = tempValues.replace("'", "");
                        tempValues = "'"+tempValues+"'";
                        break;
                    case "TIMESTAMP":
                        tempValues = tempValues.replace("'", "");
                        tempValues = "#"+tempValues+"#";
                        break;
                    default:
                        if ("".equals(tempValues.trim()) || tempValues == null){
                            tempValues = "0";
                        }
                        break;
                }
                
                tempColumns = tableColumns[selectedColumns[i]];
                
                updateValues = updateValues + tempColumns +"="+ tempValues;
                
                if (i != (selectedColumns.length-1)){
                    updateValues = updateValues + ",";
                }
            }
            
            sqlStatemens = sqlStatemens + "" + updateValues + " where " + tablePk + "="+valueId;
                        
            if (tableName!=null && values != null){
                try {         
                    statement = connection.createStatement();
                    statement.executeUpdate(sqlStatemens);
                    statement.close();
                    strResult = "Update exceuted: " + sqlStatemens;
                } catch (SQLException ex) {
                    strResult = "Update not exceuted " + sqlStatemens + " "+ ex.toString();
                }
            }   
            
        return strResult;
    }
    
    protected String sqlCommand(String sql){
        String strResult;
        try {         
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            strResult = "SQL exceuted: " + sql;
        } catch (SQLException ex) {
            strResult = "SQL not exceuted " + sql + " "+ ex.toString();
        }
        return strResult;    
    }

    public void loadXml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
