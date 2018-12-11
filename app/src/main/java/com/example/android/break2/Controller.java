package com.example.android.break2;
import android.app.DownloadManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    private DBManager dbMan; // A Reference of type DBManager

    public Controller() {
        dbMan = new DBManager(); // Create the DBManager Object
    }
    public void TerminateConnection() {
       dbMan.closeConnection();
    }

    public int InsertNewFollower(String name , int age , String email , String password , String username) throws SQLException {
        // query for checking this username is used before or not
        String username_queryCheck = "Select * from follower,admins where follower.username ='" + username + "' or admins.username ='" + username + "' ;";
        ResultSet username_RS = (ResultSet) dbMan.ExecuteReader(username_queryCheck);
        if(username_RS.next() )
        {
            return -1 ; // if it is used the function returns -1 for username used error
        }

        // query for checking this email is used before or not

        String email_queryCheck = "Select * from follower,admins where follower.email ='" + email + "' or admins.email ='" + email + "';";
        ResultSet emailcheck_RS = (ResultSet) dbMan.ExecuteReader(email_queryCheck);
        if(emailcheck_RS.next() )
        {
            return 404 ; // if it is used the function returns 404 for email used error
        }
        // otherwise the username and the email are valid so we can enter the query now
        String query = "Insert into follower (name,age,Email,fpassword,username) values( "+
                "'" +   name + "',"+
                        age+ ","+
                "'" +   email + "',"+
                "'" +   password + "',"+
                "'" + username + "' ) ; " ;

        return  dbMan.ExecuteNonQuery(query);
    }
    public int InsertNewAdmin(String username , String email , String password) throws SQLException {
        // query for checking this username is used before or not
        String username_queryCheck = "Select * from follower,admins where follower.username ='" + username + "' or admins.username ='" + username + "' ;";
        ResultSet username_RS = (ResultSet) dbMan.ExecuteReader(username_queryCheck);
        if(username_RS.next() )
        {
            return -1 ; // if it is used the function returns -1 for username used error
        }
        // otherwise the username is valid so we can enter the query now
        String query = "Insert into admins (username,email,a_password) values( "+
                "'" +   username + "',"+
                "'" +   email + "',"+
                "'" +   password + "' ) ; " ;
        return  dbMan.ExecuteNonQuery(query);
    }


    public int [] LoginCheck(String username , String password ) throws SQLException {
        String query = "Select * from follower "+"" +
                     "where "+
                " ( follower.username= '"+username+"') and follower.fpassword = '"+password+"';";
        ResultSet isUser=dbMan.ExecuteReader(query);
        int ResultArr[] = new int[2];
        if(isUser.next())
        {
            ResultArr[0]=2;  // the first element is for the privileges  2 means it is follower
            ResultArr[1] =  isUser.getInt("follower_id");  // the second element is for the user ID
            return ResultArr ;
        }
        query = "Select * from admins "+"" +
                "where "+
                " ( admins.username= '"+username+"' ) and a_password = '"+password+"';";
        ResultSet isAdmin=dbMan.ExecuteReader(query);
        if(isAdmin.next())
        {
            ResultArr[0]=1;  // the first element is for the privileges 1 means it is an admin
            ResultArr[1] =  isAdmin.getInt("admin_id"); // the second element is for the user ID
            return ResultArr ;
        }
        else
            return null;
    }
    public int InsertAdv(String title,String cmpName,String stDate,String endDate,String content , String price , String url,int adminID) {
        // here we need to pickup a query that contains the current logged admin id
        String query = "Insert into advertisement (company_name,ad_title,ad_content,ad_start_date,ad_end_date,price,url,admin_id) values( "+
                "'" +   title + "',"+
                "'"+   cmpName+ "',"+
                "'" + content + "',"+
                "'" +   stDate + "',"+
                "'" +   endDate + "',"+
                +Integer.parseInt(price)+","+
                "'"+url+"',"+adminID+");" ;
        return  dbMan.ExecuteNonQuery(query);
    }

    public int InsertNewCinema(String cname , String clocation)
    {
        String query = "Insert into cinema (cinema_name,cinema_location) values( "+
                "'" +   cname + "',"+
                "'"+   clocation+ "');";
        return  dbMan.ExecuteNonQuery(query);
    }


    public int InsertNewMovie(String name, String language,
                              String rdate,String description,
                              int age_rest,String url,
                              Float reviewers,Float users,
                              int directorID,int authorID,
                              String duration,String genre)
    {

        return 1;
    }

    public int InsertActor(String ActorName, String ActorBirthDay, String brief) throws SQLException {
    String checkquery ="select * from crew_members where member_name = '"+ActorName+"' and birth_date="+"'"+ ActorBirthDay+"' ;";
    ResultSet actor_exists = dbMan.ExecuteReader(checkquery);
    if(actor_exists.next())
        return 404; // returns 404 if the actor is already exist with same name and birthday

    String query = "Insert into crew_members(member_name,birth_date,brief,member_type)"+
            " values('"+ActorName+"',"+
                      "'"+ActorBirthDay+"',"+
                      "'"+brief+"',0);" ; //Zero for actor tpyes
    int res =  dbMan.ExecuteNonQuery(query);
    if (res==1)
    {
        query = "select * from crew_members where member_name = '"+ActorName+"' and birth_date="+"'"+ ActorBirthDay+"' ;";
        ResultSet actor = dbMan.ExecuteReader(query);
        int actorID;
        if(actor.next())
        { actorID=actor.getInt("member_id"); }
        else return -1;

        query="Insert into actors values ("+actorID+");";
        return dbMan.ExecuteNonQuery(query);
    }
    else return -1;
    }


    public int InsertDirector(String Name, String BirthDay, String Brief , String Style) throws SQLException {
        String checkquery ="select * from crew_members where member_name = '"+Name+"' and birth_date="+"'"+ BirthDay+"' ;";
        ResultSet actor_exists = dbMan.ExecuteReader(checkquery);
        if(actor_exists.next())
            return 404; // returns 404 if this crew_member is already exist with same name and birthday

        String query = "Insert into crew_members(member_name,birth_date,brief,member_type)"+
                " values('"+Name+"',"+
                "'"+BirthDay+"',"+
                "'"+Brief+"',1);" ; //Zero for Director tpyes
        int res =  dbMan.ExecuteNonQuery(query);
        if (res==1)
        {
            query = "select * from crew_members where member_name = '"+Name+"' and birth_date="+"'"+ BirthDay+"' ;";
            ResultSet crew_memb = dbMan.ExecuteReader(query);
            int memberID;
            if(crew_memb.next())
            { memberID=crew_memb.getInt("member_id"); }
            else return -1;

            query="Insert into director values ("+memberID+",'"+Style+"');";
            return dbMan.ExecuteNonQuery(query);
        }
        else return -1;
    }

    public int InsertAuthor(String Name, String BirthDay, String Brief , String Style) throws SQLException {
        String checkquery ="select * from crew_members where member_name = '"+Name+"' and birth_date="+"'"+ BirthDay+"' ;";
        ResultSet actor_exists = dbMan.ExecuteReader(checkquery);
        if(actor_exists.next())
            return 404; // returns 404 if this crew_member is already exist with same name and birthday

        String query = "Insert into crew_members(member_name,birth_date,brief,member_type)"+
                " values('"+Name+"',"+
                "'"+BirthDay+"',"+
                "'"+Brief+"',1);" ; //Zero for Director tpyes
        int res =  dbMan.ExecuteNonQuery(query);
        if (res==1)
        {
            query = "select * from crew_members where member_name = '"+Name+"' and birth_date="+"'"+ BirthDay+"' ;";
            ResultSet crew_memb = dbMan.ExecuteReader(query);
            int memberID;
            if(crew_memb.next())
            { memberID=crew_memb.getInt("member_id"); }
            else return -1;

            query="Insert into authors values ("+memberID+",'"+Style+"');";
            return dbMan.ExecuteNonQuery(query);
        }
        else return -1;
    }




}

