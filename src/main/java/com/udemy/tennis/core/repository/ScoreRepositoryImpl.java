package com.udemy.tennis.core.repository;


import com.udemy.tennis.core.DataSourceProvider;
import com.udemy.tennis.core.HibernateUtil;
import com.udemy.tennis.core.entity.Score;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreRepositoryImpl {

 public void create(Score score) {
     Connection connection = null;
     try {
         DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

        connection=dataSource.getConnection();
        PreparedStatement requeteCreate = connection.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        requeteCreate.setLong(1,score.getMatch().getId());
        requeteCreate.setByte(2,score.getSet1());
        requeteCreate.setByte(3,score.getSet2());
        requeteCreate.setByte(4,score.getSet3());
        if(score.getSet4()==null){
            requeteCreate.setNull(5,Types.TINYINT);
        }else{
        requeteCreate.setByte(5,score.getSet4());}
        if(score.getSet5()==null) {
            requeteCreate.setNull(6, Types.TINYINT);
        }else{
        requeteCreate.setByte(6,score.getSet5());}

        requeteCreate.executeUpdate();
        ResultSet retourGenerateKeys=requeteCreate.getGeneratedKeys();
        if(retourGenerateKeys.next()){
           score.setId(retourGenerateKeys.getLong(1));
        }
         System.out.println("score crée");
     } catch (SQLException e) {
         e.printStackTrace();
     } finally {

         try {
             if (connection != null) {
                 connection.close();
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
        }
    }
    public Score getByID(Long id) {
     Session session=HibernateUtil.getSessionFactory().getCurrentSession();
      Score score = session.get(Score.class, id);
        System.out.println("score affiché");
        return score;
    }
}
